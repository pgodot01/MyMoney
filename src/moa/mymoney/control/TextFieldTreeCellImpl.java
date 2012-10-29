/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moa.mymoney.control;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import moa.mymoney.MyMoney;

    public final class TextFieldTreeCellImpl extends TreeCell<TreeItemMMValue> {

        private TextField textField;
        private ContextMenu addMenu = new ContextMenu();

        public TextFieldTreeCellImpl(MyMoney argsMoney) {
            MenuItem addMenuItem = new MenuItem("Add Compte");
            addMenu.getItems().add(addMenuItem);
            addMenuItem.setOnAction(new EventHandler() {
                public void handle(Event t) {
                    TreeItem newEmployee =
                            new TreeItem<>(new TreeItemMMValue(MMType.BANQUE, "New Compte"));
                    //new TreeItem<TreeItemMMValue>(new TreeItemMMValue(MMType.BANQUE, "New Compte"));
                    getTreeItem().getChildren().add(newEmployee);
                }
            });
            setOnMouseClicked(new TreeItemHandler(argsMoney));
        }

        @Override
        public void startEdit() {
            System.out.println("stratEdit");
            super.startEdit();

            if (textField == null) {
                createTextField();
            }
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText((String) getItem().node);
            setGraphic(getTreeItem().getGraphic());
        }

        @Override
        public void updateItem(TreeItemMMValue item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(item.node);
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(item.node);
                    setGraphic(getTreeItem().getGraphic());
                    if (!getTreeItem().isLeaf() && getTreeItem().getParent() != null) {
                        setContextMenu(addMenu);
                    }
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getText());
            textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {

                        commitEdit(new TreeItemMMValue(MMType.BANQUE, textField.getText()));
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });

        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
