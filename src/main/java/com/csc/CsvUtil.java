package com.csc.service;

import java.io.*;

public class CsvUtil {
    public static void removeHeaders(String in, String out) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(in));
             BufferedWriter bw = new BufferedWriter(new FileWriter(out))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) bw.write(line + "\n");
        }
    }

    public static void removeIds(String in, String out) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(in));
             BufferedWriter bw = new BufferedWriter(new FileWriter(out))) {
            String header = br.readLine();
            if (header != null) bw.write(removeFirst(header) + "\n");

            String line;
            while ((line = br.readLine()) != null)
                bw.write(removeFirst(line) + "\n");
        }
    }

    private static String removeFirst(String line) {
        int idx = line.indexOf(',');
        return idx == -1 ? "" : line.substring(idx + 1);
    }
}
