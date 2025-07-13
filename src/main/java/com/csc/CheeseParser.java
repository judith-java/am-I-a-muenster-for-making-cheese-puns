package com.csc.parser;

import com.csc.model.Cheese;

import java.io.*;
import java.util.*;

public class CheeseParser {
    public static List<Cheese> parse(String csvPath) throws IOException {
        List<Cheese> cheeses = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
            String headerLine = br.readLine();
            if (headerLine == null) return cheeses;

            String[] headers = headerLine.split(",", -1);
            Map<String, Integer> idx = new HashMap<>();
            for (int i = 0; i < headers.length; i++)
                idx.put(headers[i].trim(), i);

            String line;
            while ((line = br.readLine()) != null) {
                String[] cols = line.split(",", -1);
                try {
                    int id = Integer.parseInt(cols[idx.get("CheeseId")]);
                    String milkTreatment = cols[idx.get("MilkTreatmentTypeEn")];
                    boolean organic = "1".equals(cols[idx.get("Organic")]);
                    String moistRaw = cols[idx.get("MoisturePercent")];
                    Double moisture = moistRaw.isEmpty() ? null : Double.parseDouble(moistRaw);
                    String milkType = cols[idx.get("MilkTypeEn")];
                    String flavour = cols[idx.get("FlavourEn")];

                    cheeses.add(new Cheese(id, milkTreatment, organic, moisture, milkType, flavour));
                } catch (Exception ignored) { }
            }
        }
        return cheeses;
    }
}
