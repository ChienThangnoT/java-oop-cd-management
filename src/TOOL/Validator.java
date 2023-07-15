/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TOOL;

import Data.CD;
import Data.CDList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Chien Thang
 */
public class Validator extends ArrayList<CD>{

    public static Scanner sc = new Scanner(System.in);

    public static int getAnInteger(String inMsg, int min, int max) {
        int n;
        if (min > max) {
            int t = min;
            min = max;
            max = t;
        }
        do {
            System.out.print(inMsg);
            n = Integer.parseInt(sc.nextLine());
            if (n > max || n < min) {
                System.out.println("Your inputter must from "
                        + min + "-" + max);
            }
        } while (n > max || n < min);
        return n;
    }

    public static double getADouble(String inMsg, double min, double max) {
        double n;
        if (min > max) {
            double t = min;
            min = max;
            max = t;
        }
        do {
            System.out.print(inMsg);
            n = Integer.parseInt(sc.nextLine());
            if (n > max || n < min) {
                System.out.println("Your inputter must from "
                        + min + "-" + max);
            }
        } while (n > max || n < min);
        return n;
    }

    public static void writeFile(String fName, List list) {
        try {
            FileWriter fw = new FileWriter(fName);
            PrintWriter pw = new PrintWriter(fw); // write with printwriter
            for (Object x : list) {
                pw.println(x.toString());
            }
            fw.close();
            pw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String getFormatString(String msg, String format) {
        String input = "";
        boolean valid = true;
        do {
            System.out.print(msg);
            input = sc.nextLine().trim();
            valid = checkRegx(input, format);
            if (!valid) {
                System.out.println("Invalid format");
            }
        } while (!valid);
        return input;
    }

    public static List<String> readFromFile(String fName) {
        File f = new File(fName);
        if (!f.exists()) {
            System.out.println("File " + fName + " doesn't exist");
            return null;
        }
        List<String> listing = new ArrayList<>();
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                listing.add(line);
            }
            fr.close();
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return listing;
    }

    public static void writeDataFile(String fName, List list) {
        try {
            FileWriter fw = new FileWriter(fName);
            PrintWriter pw = new PrintWriter(fw);
            for (Object x : list) {
                pw.println(x);
            }
            fw.close();
            pw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String parseStringByBool(String validT, String validF) {
        boolean b = readBool("True Valid for " + validT.toUpperCase() + "-"
                + validF.toUpperCase() + " else: ");
        if (b) {
            return validT;
        }
        return validF;
    }

    public static int getAnInteger(String inMsg, String errMsg) {
        int n;
        do {
            System.out.print(inMsg);
            n = Integer.parseInt(sc.nextLine());
            if (n < 0) {
                System.out.println(errMsg);
            }
        } while (n < 0);
        return n;
    }

    public static double getADouble(String inMsg, String errMsg) {
        double n;
        do {
            System.out.print(inMsg);
            n = Double.parseDouble(sc.nextLine());
            if (n < 0) {
                System.out.println(errMsg);
            }
        } while (n < 0);
        return n;
    }

    public static String getNonBlankString(String inMsg, String errMsg) {
        String n;
        do {
            System.out.print(inMsg);
            n = sc.nextLine().trim();
            if (n.isEmpty() || n.equals(" ")) {
                System.out.println(errMsg);
            }
        } while (n.isEmpty() || n.equals(" "));
        return n;
    }

    public static boolean readBool(String msg) {
        String res;
        System.out.print(msg + " [1/0 - Y/N - T/F]: ");
        res = sc.nextLine().trim();
        char c = res.toUpperCase().charAt(0);
        return (c == '1' || c == 'T' || c == 'Y');
    }

    public static boolean checkRegx(String str, String rex) {
        return str.matches(rex);
    }

//    public static boolean validPassword(String pwd, int minL) {
//        if (pwd.length() < minL) {
//            System.out.println("Your password must at least " + minL + " characters" );
//            return false; // min length of password
//        }
//        if (pwd.matches(".*[a-zA-Z].*") &&// at least 1 alphabet
//            pwd.matches(".*[\\d+].*") && // at least 1 digit
//            pwd.matches(".*[\\W+].*")) {  // at least 1 character \\W+
//            return true;
//        }
//        return false;
//    }
    public static boolean parseBool(String msg) {
        char c = msg.toUpperCase().charAt(0);
        return (c == '1' || c == 'Y' || c == 'T');
    }
}
