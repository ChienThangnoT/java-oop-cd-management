/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author Chien Thang
 */
public class CD implements Comparable<CD> {

    private String CD_ID;
    private String name;
    private String type;
    private String title;
    private double price;

    private int yearPublic;

    public CD(String CD_ID, String name, String type, String title, double price, int yearPublic) {
        this.CD_ID = CD_ID;
        this.name = name;
        this.type = type;
        this.title = title;
        this.price = price;
        this.yearPublic = yearPublic;
    }

    public String getCD_ID() {
        return CD_ID;
    }

    public void setCD_ID(String CD_ID) {
        this.CD_ID = CD_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYearPublic() {
        return yearPublic;
    }

    public void setYearPublic(int yearPublic) {
        this.yearPublic = yearPublic;
    }

    // convert line into CD   
    public CD(String line) {
        String parts[] = line.split("\\|");
        CD_ID = parts[0].trim();
        name = parts[1].trim();
        type = parts[2].trim();
        title = parts[3].trim();
        price = Double.parseDouble(parts[4].trim());
        yearPublic = Integer.parseInt(parts[5].trim());
    }

    @Override
    public String toString() {
        return CD_ID + " | " + name + " | " + type + " | " + title + " | "
                + price + " | " + yearPublic;

    }

    public void display() {
        String format = String.format("%-7s|%-7s|%-15s|%-9.2f|%-6s|%-5d",
                CD_ID, name, type, title, price, yearPublic);
        System.out.println(format);
    }

    @Override
    public int compareTo(CD x) {
        return this.getName().compareTo(x.getName());
    }
}
