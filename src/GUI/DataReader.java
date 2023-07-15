/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Chien Thang
 */
public class DataReader {
    private static final String configFile = "config.txt";
    private static final String CD_FILE = "cd_file";

    public static Config read() {
        File f = new File(configFile);
        Config config = new Config();
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String lines;
            while ((lines = br.readLine()) != null) {
                String parts[] = lines.split("=");
                if (parts[0].equals(CD_FILE)) {
                    config.cdFile = parts[1];
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return config;
    }

    public static class Config {
        public String cdFile;
    }

    public static void main(String[] args) throws IOException {
        String fName = read().cdFile;
        System.out.println(fName);
    }
}
