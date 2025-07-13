package com.csc;

import com.csc.model.Cheese;
import com.csc.parser.CheeseParser;
import com.csc.service.*;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String csv = "cheese_data.csv";
        String out = "output.txt";
        String miss = "missing_ids.txt";

        try {
            List<Cheese> cheeses = CheeseParser.parse(csv);

            CsvUtil.removeHeaders(csv, "cheese_without_headers.csv");
            CsvUtil.removeIds(csv, "cheese_without_ids.csv");

            DataAnalyzer analyzer = new DataAnalyzer();
            DataAnalyzer.AnalysisResult res = analyzer.analyze(cheeses);

            FileWriterService fw = new FileWriterService();
            fw.writeOutput(out, res);
            fw.writeMissingIds(miss, res.missingIds);

            System.out.println("Done. See output.txt and missing_ids.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
