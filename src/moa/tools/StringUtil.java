/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moa.tools;

/**
 *
 * @author IronMan
 */
public class StringUtil {
    /*
     * Transfrom a String in CamelCase
     * TOTO -> Toto, TUtu -> Tutu
     */
    public final static String inCamelCase(String strToChange){
        String strChanged = strToChange.toLowerCase();
        strChanged = strChanged.substring(0,1).toUpperCase()+strChanged.substring(1);
        return strChanged;
    }
    
}
