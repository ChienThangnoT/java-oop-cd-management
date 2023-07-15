/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Data.CDList;
import TOOL.Validator;

/**
 *
 * @author Chien Thang
 */
public class Managerment {
    public static void main(String[] args) throws Exception {
//        ProductList pList = new ProductList();
        int choice;
        Menu menu = null;
        boolean count = true;
        CDList cdlist = new CDList();
        cdlist.loadFromFile("CD.dat");
        do {
            String[] options = {"Add CD to the catalog", "Search CD by CD title",
                "Display the catalog", "Update CD", "Save to file", "Print all list CD from file","Exit program"};
            menu = new Menu("---------------CD House Managing---------------");
            
            do {
                choice = menu.getUserChoice(options);
                switch (choice) {
                    case 1:
                        cdlist.addCD();
                        break;
                    case 2:
                        cdlist.searchCD();
                        break;
                    case 3:
                        cdlist.displayCatalog();
                        break;
                    case 4:
                        cdlist.updateCD();
                        break;
                    case 5:
                        cdlist.saveAccount();
                        break;
                    case 6:
                        cdlist.printList();
                        break;
                    case 7:
                        System.exit(0);
                    default:
                        boolean res = Validator.readBool("Save to file?");
                        if (res) {
                            cdlist.saveAccount();
                        }
                }
            } while (choice > 0 && choice < 7);
            count = Validator.readBool("Continue program?");
            System.out.println("==========================================");
        } while (count == true);
    }
}
