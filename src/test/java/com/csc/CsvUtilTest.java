package com.csc.service;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class CsvUtilTest {

    @Test
    public void testRemoveHeaders() throws IOException {
        File input = File.createTempFile("cheese_in", ".csv");
        File output = File.createTempFile("cheese_out", ".csv");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(input))) {
            bw.write("CheeseId,MilkTreatmentTypeEn,Organic,MoisturePercent,MilkTypeEn,FlavourEn\n");
            bw.write("200,pasteurized,1,42.5,cow,Lactic\n");
            bw.write("201,raw,0,38.0,goat,Sharp\n");
        }

        CsvUtil.removeHeaders(input.getAbsolutePath(), output.getAbsolutePath());

        List<String> lines = Files.readAllLines(output.toPath());
        assertEquals(2, lines.size());
        assertFalse(lines.get(0).contains("CheeseId"));
    }

    @Test
    public void testRemoveIds() throws IOException {
        File input = File.createTempFile("cheese_id_in", ".csv");
        File output = File.createTempFile("cheese_id_out", ".csv");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(input))) {
            bw.write("CheeseId,MilkTreatmentTypeEn,Organic\n");
            bw.write("200,pasteurized,1\n");
            bw.write("201,raw,0\n");
        }

        CsvUtil.removeIds(input.getAbsolutePath(), output.getAbsolutePath());

        List<String> lines = Files.readAllLines(output.toPath());
        assertEquals("MilkTreatmentTypeEn,Organic", lines.get(0));
        assertEquals("pasteurized,1", lines.get(1));
        assertEquals("raw,0", lines.get(2));
    }
}
