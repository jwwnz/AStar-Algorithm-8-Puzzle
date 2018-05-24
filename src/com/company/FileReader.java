package com.company;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReader {
    public BufferedReader reader;
    public List<Node> nodes;

    public FileReader(String fileLocation) {
        try {
            this.reader = new BufferedReader(new java.io.FileReader(fileLocation));
            String line = "";
            nodes = new ArrayList<>();
            while ((line = reader.readLine()) != null){
                Pattern pattern = Pattern.compile("( \\d+)");
                Matcher matcher = pattern.matcher(line);
                matcher.group().trim();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
