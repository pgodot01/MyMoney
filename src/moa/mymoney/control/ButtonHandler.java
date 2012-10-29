/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moa.mymoney.control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import moa.mymoney.BankView;
import moa.mymoney.MyMoney;
import moa.mymoney.hmapping.Banque;
import moa.mymoney.hmapping.MyMoneyHelper;

/**
 *
 * @author IronMan
 */
public class ButtonHandler implements EventHandler<ActionEvent> {
    private MyMoney myMoney = null;
    public ButtonHandler(MyMoney argsMoney){
        myMoney=argsMoney;
    }
    
    @Override
    public void handle(ActionEvent event) {
        String buttonId = ((Button) event.getSource()).getId();
        MMType type = (MMType) ((Button) event.getSource()).getUserData();
        switch (type) {
            case BANQUE:
                if (buttonId.equals(MMType.BANQUE.display)){
                    TreeView<TreeItemMMValue> bankTree = myMoney.addTreeView(type);
                    myMoney.root.setLeft(bankTree);
                }else if (buttonId.equals("Update")){
                    BankView bkV = (BankView)myMoney.root.getCenter();
                    updateBank (bkV);
                    TreeView<TreeItemMMValue> bankTree = myMoney.addTreeView(type);
                    myMoney.root.setLeft(bankTree);
                }else if (buttonId.equals("Delete")){
                    
                }
                break;
            case CATEGORY:
                {
                    VBox vbbank = myMoney.addVBox(type);
                    myMoney.root.setLeft(vbbank);
                    break;
                }
            
            default:
                {
                    myMoney.root.setLeft(null);
                    break;
                }
        }
    }

    private void updateBank(BankView bkV) {
        Banque bk = bkV.getBank();
        MyMoneyHelper mmh = myMoney.mmHelper;
        mmh.updateBank (bk);
       
       
    }
    
}
