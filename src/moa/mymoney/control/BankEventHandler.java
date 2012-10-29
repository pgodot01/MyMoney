/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moa.mymoney.control;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import moa.mymoney.BankView;
import moa.mymoney.hmapping.Banque;
import moa.tools.StringUtil;

/**
 *
 * @author IronMan
 */
public class BankEventHandler implements EventHandler<KeyEvent> {

    @Override
    public void handle(KeyEvent t) {
        try {

            if (t.getEventType() == KeyEvent.KEY_PRESSED) {
                if (t.getCode() == KeyCode.TAB) {
                    updateBanqueField(t);
                }
            } else if (t.getEventType() == KeyEvent.KEY_RELEASED) {
                if (t.getCode() == KeyCode.ENTER) {
                    updateBanqueField(t);

                } else if (t.getCode() == KeyCode.ESCAPE) {
                    resetBankField(t);
                }
            }
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(BankView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(BankView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BankView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(BankView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateBanqueField(KeyEvent t) throws InvocationTargetException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException {
        TextField tf = (TextField) t.getSource();
        String field = tf.getId();
        Banque b = (Banque) tf.getUserData();
        Method m = Banque.class.getDeclaredMethod("set" + StringUtil.inCamelCase(field), String.class);
        m.invoke(b, tf.getText());
        //Method m = Banque.class.getDeclaredMethod("get" + StringUtil.inCamelCase(name), null);
        //m.invoke(bank, (TextField)t.getSource());
    }

    private void resetBankField(KeyEvent t) throws IllegalArgumentException, NoSuchMethodException, SecurityException, InvocationTargetException, IllegalAccessException {
        // Put the latest value known
        TextField tf = (TextField) t.getSource();
        String field = tf.getId();
        Banque b = (Banque) tf.getUserData();
        Method m = Banque.class.getDeclaredMethod("get" + StringUtil.inCamelCase(field), null);
        tf.setText((String) m.invoke(b, null));
    }
}
