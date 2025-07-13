package com.csc.parser;

import com.csc.model.Cheese;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CheeseParserTest {

    @Test
    public void testParseValidCSV() throws IOException {
        String csv = "CheeseId,MilkTreatmentTypeEn,Organic,MoisturePercent,MilkTypeEn,FlavourEn\n" +
                     "200,pasteurized,1,42.5,cow,Mild and lactic\n" +
                     "201,raw,0,38.0,goat,Sharp\n";

        File temp = File.createTempFile("cheese", ".csv");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(temp))) {
            bw.write(csv);
        }

        List<Cheese> result = CheeseParser.parse(temp.getAbsolutePath());
        assertEquals(2, result.size());

        Cheese cheese1 = result.get(0);
        assertEquals(200, cheese1.getId());
        assertEquals("pasteurized", cheese1.getMilkTreatment());
        assertTrue(cheese1.isOrganic());
        assertEquals(42.5, cheese1.getMoisturePercent());
        assertEquals("cow", cheese1.getMilkType());
        assertTrue(cheese1.getFlavourEn().contains("lactic"));
    }
}
