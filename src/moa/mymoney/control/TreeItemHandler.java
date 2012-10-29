/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moa.mymoney.control;

import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import moa.mymoney.BankView;
import moa.mymoney.MyMoney;
import moa.mymoney.hmapping.Banque;

    class TreeItemHandler implements EventHandler<MouseEvent> {
        MyMoney myMoney = null;
        public TreeItemHandler(MyMoney argsMoney){
            myMoney=argsMoney;
        }

        @Override
        public void handle(MouseEvent event) {
            TreeCell treeCell = (TreeCell) event.getSource();
            TreeItemMMValue itemValue = (TreeItemMMValue) treeCell.getTreeItem().getValue();
            MMType type = itemValue.type;
            System.out.println("Hello " + itemValue.node + " Type " + itemValue.type.name());
            switch (type) {
                case BANQUE:
                    Banque bk = myMoney.mmHelper.getBanque(itemValue.node);
                    if (bk!=null){
                        BankView bv = new BankView (bk);
                        bv.setVisible(true);
                        myMoney.root.setCenter(bv);
                        myMoney.createActionButton(MMType.BANQUE);
                    }else{
                        LOG.severe("Bank is null for the Node " + treeCell.getText());
                    }
                    
                    break;
                case CATEGORY: {
                    Text ctext = new Text("Category");
                    ScrollPane sp = new ScrollPane();
                    sp.setContent(ctext);
                    myMoney.root.setCenter(sp);
                    myMoney.createActionButton(MMType.BANQUE);
                    break;
                }
                case COMPTE: {
                    Text ctext = new Text("Compte");
                    myMoney.root.setCenter(ctext);
                    break;
                }
                default: {
                    Text ctext = new Text("Welcome to my money");
                    myMoney.root.setCenter(ctext);
                    myMoney.createActionButton(MMType.COMPTE);
                    break;
                }
            }

        }
    private static final Logger LOG = Logger.getLogger(TreeItemHandler.class.getName());
    }
           
