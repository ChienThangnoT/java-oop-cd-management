/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import TOOL.Validator;

/**
 *
 * @author Chien Thang
 */
public class Menu {
        public String titile = "";

    public Menu(String titile) {
        this.titile = titile;
    }
    
    public int getUserChoice(String[] options) {
        System.out.println(titile);
        int max = options.length;
        for (int i = 0; i < max; i++) {
            System.out.println((i+1) + "-" + options[i]);
        }
        System.out.println("Other for quit!!!");
        String message = "Enter your selections [1..." +  max + "]: ";
        String error = "Your inputter must from " + 1 + "-" + max;
        return Validator.getAnInteger(message, error);
    }
}
