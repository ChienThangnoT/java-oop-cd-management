/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import GUI.DataReader;
import TOOL.Validator;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Chien Thang
 */
public class CDList {

    private List<CD> cdList = new ArrayList<>();
    public final String SEPARATOR = "\\|";
    boolean changed = false;
    private DB data;
    private int count = 0;    //count number CD in archive lists
    private final int maxSize = 700; // initial an array has size 700
    private final Scanner sc = new Scanner(System.in);

    public List<CD> loadFromFile(String fileName) {
        List<CD> listCD = new ArrayList<>();
        try {
            Path pathf = Paths.get(fileName);
            List<String> list = Files.readAllLines(pathf);
            if (list.isEmpty()) {
                return null;
            } else {
                boolean firstLine = true;
                for (String x : list) {
                    if (firstLine) {
                        firstLine = false;
                        continue;
                    }
                    String parts[] = x.split(SEPARATOR);
                    if (parts.length == 6) {
                        String ID = parts[0].trim();
                        
                        String type = parts[1].trim().toUpperCase();
                        String title = parts[2].trim();
                        String name = parts[3].trim().toUpperCase();
                        double price = Double.parseDouble(parts[4].trim());
                        int year = Integer.parseInt(parts[5].trim());
                        listCD.add(new CD(ID, name, type, title, price, year));
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return listCD;
    }

    private boolean checkIsFull() {
        return (cdList.size() == 700);
    }

    private boolean IsEmpty() {
        return cdList.isEmpty();
    }

    private void BREAK_LINE() {
        System.out.println("");
    }
//function 1

    public void addCD() {
        if (checkIsFull()) {
            System.out.println("Your array containing CD are fully! - Unable to add CD!");
        } else {
            String ID = setID();
            String name;
            do {
                name = setName();
                if (name == null) {
                    System.out.println("CD's Name must be in range required!");
                }
            } while (name == null);
            String type;
            do {
                type = setType();
                if (type == null) {
                    System.out.println("CD's Type must be in range required");
                }
            } while (type == null);
            String title;
            do {
                title = setTitle();
                if (title == null) {
                    System.out.println("CD's title cannot be blank");
                }
            } while (title == null);
            double price;
            do {
                price = setPrice();
                if (price == -1) {
                    System.out.println("CD's price must greater than 0");
                }
            } while (price == -1);
            int yearPublish;
            do {
                yearPublish = setYear();
                if (yearPublish == -1) {
                    System.out.println("CD's year publishing must in range");
                }
            } while (yearPublish == -1);
            cdList.add(new CD(ID, name, type, title, price, yearPublish));
            count++; //size increases 1 unit
            System.out.println("New CD has been added sucessfully!");
            BREAK_LINE();
        }
    }

    private String setID() {
        String ID;
        boolean isDuplicated = false;
        do {
            ID = Validator.getFormatString("Enter new CD's ID: ", "^D\\d{3}");
            isDuplicated = searchID(ID);
            if (isDuplicated) {
                System.out.println("New CD has ID :" + ID + " is duplicated!");
            }
        } while (isDuplicated == true);
        return ID.toUpperCase().trim();
    }

    private String setName() {
        System.out.print("Enter new CD's name [Movie, Game, Music]: ");
        String name = sc.nextLine().trim().toUpperCase();
        if (name.isEmpty() || name.equals("") || !name.matches("(MOVIE)|(GAME)|(MUSIC)")) {
            return null;
        }
        return name;
    }

    private String setType() {
        System.out.print("Enter new CD's type [Audio, Video]: ");
        String type = sc.nextLine().trim().toUpperCase();
        if (type.isEmpty() || type.equals("") || !type.matches("(VIDEO)|(AUDIO)")) {
            return null;
        }
        return type;
    }

    private String setTitle() {
        System.out.print("Enter new CD's titile: ");
        String title = sc.nextLine().trim();
        if (title.isEmpty() || title.equals("")) {
            return null;
        }
        return title;
    }

    private double setPrice() {
        double price = Validator.getADouble("Enter new CD's price(0..$$$): ", 0, Double.MAX_VALUE);
        if (price == 0) {
            return -1; //default price -> checking is null value or not
        }
        return price;
    }

    private int setYear() {
        System.out.print("Enter new CD's year publish [1960 - 2023]: ");
        int yearOfPublish = Integer.parseInt(sc.nextLine());
        if (yearOfPublish != 0 && yearOfPublish >= 1960 && yearOfPublish <= 2023) {
            return yearOfPublish;
        }
        return -1; //default value year
    }

    private void print() {
        String format = String.format("%-7s|%-7s|%-15s|%-9s|%-8s|%-5s", "CD ID", "Type", "Title",
                "Price", "Name", "Publish Year");
        System.out.println(format);
    }
//function 2 

    public void searchCD() { //search by CD Title
        if (IsEmpty()) {
            System.out.println("Your archive CD is empty! Search fail!");
        } else {
            String titile = Validator.getNonBlankString("Enter a titile of searched: ",
                    "Titile of searched cannot be blank");
            List<CD> searched = searchListByTitle(titile, cdList);
            if (searched.isEmpty()) {
                System.out.println("Your titile of searched are not found!");
            } else {
                System.out.println("Found CD");
                print();
                for (CD x : searched) {
                    System.out.println(x);
                }
            }
            BREAK_LINE();
        }
    }

// function 3
    public void displayCatalog() {
        if (IsEmpty()) {
            System.out.println("Your archive catalog's CD is empty - "
                    + "Displays on screen fail!");
        } else {
            print();
            int i = 0;
            for (CD x : cdList) {
                System.out.print((++i) + "-");
                x.display();
            }
            System.out.println("There are " + count + " all of Catalog's CD in archive");
            BREAK_LINE();
        }
    }

// function 4
    public void updateCD() {
        if (IsEmpty()) {
            System.out.println("Your list is empty ! Updated fail!");
        } else {
            String choice;
            System.out.println("'U' for update CD");
            System.out.println("'D' for delete CD");
            choice = Validator.parseStringByBool("UPDATE", "DELETE");
            switch (choice) {
                case "UPDATE":
                    update();
                    break;
                case "DELETE":
                    remove();
                    break;
            }
            BREAK_LINE();
        }
    }

//function 4.1
    private boolean update() {
        //set the CD's ID is a key -> not changes
        String ID = Validator.getNonBlankString("Enter an ID to update: ",
                "An ID of updated cannot be blank").toUpperCase();;
        CD searchCD = searchByID(ID);
        if (searchCD == null) {
            System.out.println("CD has an ID: " + ID + " not found!");
            return false;
        } else {
            boolean change;
            String newName = setName();
            change = (newName != null) ? true : false;
            if (change) {
                searchCD.setName(newName.toUpperCase());
            }
            String newType = setType();
            change = (newType != null) ? true : false;
            if (change) {
                searchCD.setType(newType.toUpperCase());
            }
            String newTitle = setTitle();
            change = (newTitle != null) ? true : false;
            if (change) {
                searchCD.setTitle(newTitle);
            }
            double newPrice = setPrice();
            change = (newPrice != -1) ? true : false;
            if (change) {
                searchCD.setPrice(newPrice);
            }
            int year = setYear();
            change = (year != -1) ? true : false;
            if (change) {
                searchCD.setYearPublic(year);
            }
            System.out.println("Updated sucessfully!");
            BREAK_LINE();
            return true;
        }
    }
//function 4.2

    private boolean remove() {
        String ID;
        ID = Validator.getNonBlankString("Enter an ID to remove: ",
                "An ID of removed product cannot be blank");
        CD searchCD = searchByID(ID);
        if (searchCD == null) {
            System.out.println("CD has an ID: " + ID + " not found!");
            return false;
        } else {
            cdList.remove(searchCD);
            System.out.println("Product has an ID: " + ID + " has been removed");
            count--; //decrease 1 unit
            BREAK_LINE();
            return true;
        }

    }

// function 5
    public void saveAccount() {

        String fname = DataReader.read().cdFile;
//            Validator.writeDataFile(fname, cdList);
            try {
                FileWriter fw = new FileWriter(fname);
                PrintWriter pw = new PrintWriter(fw);
                String prerequisite = "ID | TYPE | TITLE | PRICE | CD_ID | YEAR_PUBLISH\n";
                pw.print(prerequisite);
                for (CD x : cdList) {
                    pw.println(x);
                }
                pw.close();
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("Save to file sucessfully");
            BREAK_LINE();
    }
//function 6

 public void printList() {
        if (data.getArrayCD_List() == null) {
            System.out.println("Data in file is empty");
        } else {
            print();
            List<CD> sorting = data.getArrayCD_List();
            Collections.sort(sorting);
            int i = 0;
            for (CD x : sorting) {
                System.out.print((++i) + "-" );
                x.display();
            }
            BREAK_LINE();
        }
    }

private List<CD> searchListByTitle(String title, List<CD> t) {
        List<CD> storageCD = new ArrayList<>();
        for (CD x : t) {
            if (x.getTitle().equalsIgnoreCase(title)) {
                storageCD.add(x);
            }
        }
        return storageCD;
    }

    private int search(String sName) {  //search by name
        sName = sName.toUpperCase();
        for (int i = 0; i < cdList.size(); i++) {
            CD x = cdList.get(i);
            if (x.getName().equals(sName)) {
                return i;
            }
        }
        return -1;
    }

    private CD searchByID(String ID) {
        ID = ID.toUpperCase();
        for (CD x : cdList) {
            if (x.getCD_ID().toUpperCase().equals(ID)) {
                return x;
            }
        }
        return null;
    }

    private boolean searchID(String ID) {
        ID = ID.toUpperCase();
        for (CD x : cdList) {
            if (x.getCD_ID().toUpperCase().equals(ID)) {
                return true;
            }
        }
        return false;
    }

}
