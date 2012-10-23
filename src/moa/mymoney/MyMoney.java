/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moa.mymoney;

import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import moa.mymoney.hmapping.Banque;
import moa.mymoney.hmapping.Category;
import moa.mymoney.hmapping.MyMoneyHelper;

/**
 * Main class to launch MyMoney
 * @author IronMan
 */
public class MyMoney extends Application {
    // Hibernate helper class
    private MyMoneyHelper mmHelper = new MyMoneyHelper();
    // Main FX object 
    BorderPane root = new BorderPane();
    // Different object category type
    protected enum MMType {

        BANQUE("Banque","Banque"), CATEGORY("Categorie","Category"), COMPTE("Compte","Compte"), OPERATION("Operation","Operation"), TIERS("Tiers","Tiers");
        public final String display;
        public final String tableName;

        MMType(String argsDisplay, String argsTable) {
            display = argsDisplay;
            tableName = argsTable;
        }
    }

    @Override
    public void start(Stage primaryStage) {




        HBox hbox = addHBox();

        root.setTop(hbox);
//border.setLeft(addVBox());
//addStackPane(hbox);         // Add stack to HBox in top region

//border.setCenter(addGridPane());
//border.setRight(addFlowPane());



        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("MyMoney");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(Insets.EMPTY);
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        for (MMType type : MMType.values()) {
            System.out.println(type.display);
            Button btn = new Button();
            btn.setText(type.display);
            btn.setId(type.display);
            btn.setUserData(type);
            btn.setOnAction(new ButtonHandler() {
            });
            hbox.getChildren().add(btn);
        }

        return hbox;
    }

    public VBox addVBox(MMType type) {
        VBox vbox = new VBox();
        switch (type) {
            case BANQUE:
                List<Banque> listBank = mmHelper.getAllName(type.tableName);
                Text title = new Text(type.display);
                title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                vbox.getChildren().add(title);
                for (Banque bank : listBank) {
                    Hyperlink hl = new Hyperlink(bank.getName());
                    vbox.getChildren().add(hl);
                }
                break;
            case CATEGORY:
                List<Category> listCateg = mmHelper.getAllName(type.tableName);
                title = new Text(type.display);
                title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                vbox.getChildren().add(title);
                for (Category categ : listCateg) {
                    Hyperlink hl = new Hyperlink(categ.getName());
                    vbox.getChildren().add(hl);
                }
                break;
                
                
        }
        return vbox;
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    /*
     * Inner class to manage top button
     */
    class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            System.out.println("Hello " + ((Button) event.getSource()).getId());
            MMType type = (MMType) ((Button) event.getSource())
                    .getUserData();
            switch (type) {
                case BANQUE :
                case CATEGORY : {
                    VBox vbbank = addVBox(type);
                    root.setLeft(vbbank);
                    break;
                }
                default: {
                    root.setLeft(null);
                    break;
                }
            }
        }
    }
}
