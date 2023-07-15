/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.List;

/**
 *
 * @author Chien Thang
 */
public class DB {
      private List<CD> arrayCD_List;

    public DB(List<CD> arrayCD_List) {
        this.arrayCD_List = arrayCD_List;
    }

    public List<CD> getArrayCD_List() {
        return arrayCD_List;
    }
}
