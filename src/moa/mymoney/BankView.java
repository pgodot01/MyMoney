/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moa.mymoney;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import moa.mymoney.control.BankEventHandler;
import moa.mymoney.hmapping.Banque;
import moa.tools.StringUtil;

/**
 *
 * @author IronMan
 */
public class BankView extends ScrollPane {

    private Banque bank = null;

    public BankView(Banque argsBank) {
        super();
        bank = argsBank;
        Text btext = new Text("Banque: " + bank.getName());
        try {
            Field[] methods = Banque.class.getDeclaredFields();
            GridPane gp = new GridPane();
            int iLine = 0;
            gp.add(btext, 0, ++iLine);
            if (methods.length == 0) {
                btext.setText("Error in reflection");
                super.getChildren().addAll(btext);
            } else {
                for (Field field : methods) {
                    if (field.getType().isAssignableFrom(String.class)) {
                        String name = field.getName();
                        Label label = new Label(field.getName());
                        TextField text;
                        Method m = Banque.class.getDeclaredMethod("get" + StringUtil.inCamelCase(name), null);
                        text = new TextField((String) m.invoke(bank, null));
                        text.setId(StringUtil.inCamelCase(name));
                        text.setUserData(bank);
                        BankEventHandler beh = new BankEventHandler();
                        text.setOnKeyPressed(beh);
                        
                        text.setOnKeyReleased(beh);
                        
                        label.setContentDisplay(ContentDisplay.LEFT);
                        gp.addRow(++iLine, label, text);



                    }
                }
                super.setContent(gp);
            }
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(BankView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BankView.class.getName()).log(Level.SEVERE, null, ex);

        } catch (NoSuchMethodException ex) {
            Logger.getLogger(BankView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(BankView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(BankView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Banque getBank() {
        return bank;
    }
}
