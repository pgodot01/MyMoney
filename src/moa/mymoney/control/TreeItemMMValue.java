/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moa.mymoney.control;

/**
 *
 * @author IronMan
 */
public class TreeItemMMValue {

    public MMType type = null;
    public String node = "";

    public TreeItemMMValue(MMType argsType, String name) {
        type = argsType;
        node = name;

    }
}