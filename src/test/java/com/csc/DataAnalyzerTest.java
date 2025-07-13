package com.csc.service;

import com.csc.model.Cheese;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataAnalyzerTest {

    @Test
    public void testAnalyzeDataCorrectly() {
        List<Cheese> cheeses = Arrays.asList(
            new Cheese(200, "pasteurized", true, 42.5, "cow", "Mild and lactic"),
            new Cheese(201, "raw", false, 38.0, "goat", "Sharp"),
            new Cheese(202, "pasteurized", true, 43.0, "cow", "Lactic"),
            new Cheese(204, "pasteurized", true, null, "cow", "Nutty"),
            new Cheese(206, "raw", true, 45.0, "buffalo", "Lactic profile")
        );

        DataAnalyzer analyzer = new DataAnalyzer();
        DataAnalyzer.AnalysisResult result = analyzer.analyze(cheeses);

        assertEquals(3, result.pasteurized);
        assertEquals(2, result.raw);
        assertEquals(3, result.organicHighMoisture); // moisture > 41 and organic
        assertEquals("cow", result.mostCommonMilk);
        assertEquals(3, result.lacticCount); // corrected from 4 to 3
        assertEquals(Arrays.asList(203, 205), result.missingIds);
        assertTrue(result.averageMoisture > 0);
    }
}
