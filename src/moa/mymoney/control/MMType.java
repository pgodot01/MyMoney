/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moa.mymoney.control;

public enum MMType {

        BANQUE("Banque", "Banque"), CATEGORY("Categorie", "Category"), COMPTE("Compte", "Compte"), OPERATION("Operation", "Operation"), TIERS("Tiers", "Tiers");
        public final String display;
        public final String tableName;

        MMType(String argsDisplay, String argsTable) {
            display = argsDisplay;
            tableName = argsTable;
        }
    }