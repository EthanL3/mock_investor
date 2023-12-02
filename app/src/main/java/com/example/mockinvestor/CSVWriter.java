package com.example.mockinvestor;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    public static void writeStringToCSV(Context context, String data, String filename) throws IOException {
        File csvDirectory = new File(context.getFilesDir(), "CSVFiles");
        if (!csvDirectory.exists()) {
            csvDirectory.mkdirs();
        }
        File csvFile = new File(csvDirectory, filename);
        FileWriter writer = new FileWriter(csvFile);
        writer.append(data);
        writer.flush();
        writer.close();
    }
}
