package com.csc.service;

import com.csc.model.Cheese;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataAnalyzer {
    public static class AnalysisResult {
        public final int pasteurized, raw, organicHighMoisture, lacticCount;
        public final String mostCommonMilk;
        public final double averageMoisture;
        public final List<Integer> missingIds;

        public AnalysisResult(int pasteurized, int raw, int organicHighMoisture,
                              String mostCommonMilk, double averageMoisture,
                              List<Integer> missingIds, int lacticCount) {
            this.pasteurized = pasteurized;
            this.raw = raw;
            this.organicHighMoisture = organicHighMoisture;
            this.mostCommonMilk = mostCommonMilk;
            this.averageMoisture = averageMoisture;
            this.missingIds = missingIds;
            this.lacticCount = lacticCount;
        }
    }

    public AnalysisResult analyze(List<Cheese> cheeses) {
        int pasteurized = 0, raw = 0, organicHighMoisture = 0, lacticCount = 0;
        Map<String, Integer> milkCount = new HashMap<>();
        double moistureSum = 0; 
        int moistureCount = 0;
        Set<Integer> ids = new HashSet<>();
        Pattern pat = Pattern.compile("\\blactic\\b", Pattern.CASE_INSENSITIVE);

        for (Cheese c : cheeses) {
            if ("pasteurized".equalsIgnoreCase(c.getMilkTreatment())) pasteurized++;
            else if ("raw".equalsIgnoreCase(c.getMilkTreatment())) raw++;

            if (c.isOrganic() && c.getMoisturePercent() != null && c.getMoisturePercent() > 41)
                organicHighMoisture++;

            moistureSum += Optional.ofNullable(c.getMoisturePercent()).orElse(0.0);
            if (c.getMoisturePercent() != null) moistureCount++;

            milkCount.merge(c.getMilkType(), 1, Integer::sum);
            ids.add(c.getId());

            if (c.getFlavourEn() != null && pat.matcher(c.getFlavourEn()).find())
                lacticCount++;
        }

        String commonMilk = milkCount.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("N/A");

        int min = ids.stream().mapToInt(i -> i).min().orElse(0);
        int max = ids.stream().mapToInt(i -> i).max().orElse(0);
        List<Integer> missing = IntStream.rangeClosed(min, max)
            .filter(i -> !ids.contains(i))
            .boxed()
            .collect(Collectors.toList());

        double avgMoisture = moistureCount > 0 ? moistureSum / moistureCount : 0.0;

        return new AnalysisResult(pasteurized, raw, organicHighMoisture,
                commonMilk, avgMoisture, missing, lacticCount);
    }
}
