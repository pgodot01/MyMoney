/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moa.mymoney.control;

import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;
import moa.mymoney.MyMoney;

/**
 *
 * @author IronMan
 */
public class TreeViewCallBack<p,r> implements Callback<TreeView<TreeItemMMValue>, TreeCell<TreeItemMMValue>> {
    MyMoney myMoney = null; 
    public TreeViewCallBack (MyMoney argsMoney){
        myMoney=argsMoney;
    }
    
     @Override
            public TreeCell<TreeItemMMValue> call(TreeView<TreeItemMMValue> p) {
                return new TextFieldTreeCellImpl(myMoney);
            }
}
