package com.csc.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriterService {
    public void writeOutput(String path, DataAnalyzer.AnalysisResult r) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(path))) {
            w.write("Pasteurized milk cheeses: " + r.pasteurized + "\n");
            w.write("Raw milk cheeses: " + r.raw + "\n");
            w.write("Organic & moisture > 41%: " + r.organicHighMoisture + "\n");
            w.write("Most common milk type: " + r.mostCommonMilk + "\n");
            w.write(String.format("Average moisture: %.2f%%\n", r.averageMoisture));
            w.write("Lactic cheeses count: " + r.lacticCount + "\n");
        }
    }

    public void writeMissingIds(String path, List<Integer> ids) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(path))) {
            for (int i : ids) w.write(i + "\n");
        }
    }
}
