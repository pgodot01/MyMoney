/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moa.mymoney;

import moa.mymoney.control.ButtonHandler;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import moa.mymoney.control.MMType;
import moa.mymoney.control.TreeItemMMValue;
import moa.mymoney.control.TreeViewCallBack;
import moa.mymoney.hmapping.Banque;
import moa.mymoney.hmapping.Category;
import moa.mymoney.hmapping.Compte;
import moa.mymoney.hmapping.MyMoneyHelper;

/**
 * Main class to launch MyMoney
 *
 * @author IronMan
 */
public class MyMoney extends Application {
    // Images ressources

    private final Node rootIcon =
            new ImageView(new Image(getClass().getResourceAsStream("/images/root.png")));
    private final Image depIcon =
            new Image(getClass().getResourceAsStream("/images/department.png"));
    // Hibernate helper class
    public MyMoneyHelper mmHelper = new MyMoneyHelper();
    // Main FX object 
    public static BorderPane root = new BorderPane();
    // Different object category type

    @Override
    public void start(Stage primaryStage) {




        HBox hbox = addHBox();

        root.setTop(hbox);
//border.setLeft(addVBox());
//addStackPane(hbox);         // Add stack to HBox in top region

//border.setCenter(addGridPane());
//border.setRight(addFlowPane());

        Text ctext = new Text("Welcome to my money");
        ctext.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        ScrollPane sp = new ScrollPane();
        sp.setContent(ctext);
        root.setCenter(sp);


        Scene scene = new Scene(root, 500, 250);

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
            btn.setOnAction(new ButtonHandler(this) {
            });
            hbox.getChildren().add(btn);
        }

        return hbox;
    }

    public TreeView<TreeItemMMValue> addTreeView(MMType type) {
        TreeItem<TreeItemMMValue> rootNode = null;
        switch (type) {
            case BANQUE:
                TreeItemMMValue itemValue = new TreeItemMMValue(MMType.BANQUE, "Banque list");
                rootNode =
                        new TreeItem<TreeItemMMValue>(itemValue);
                List<Banque> listBank = mmHelper.getAllName(type.tableName);
                // Set an observable Listener
                ObservableList<Banque> oListBank = FXCollections.observableList(listBank);
                oListBank.addListener(new ListChangeListener() {
                    @Override
                    public void onChanged(ListChangeListener.Change change) {
                        System.out.println("oListBank Detected a change! ");
                    }
                });
                //Add Banques
                for (Banque bank : listBank) {
                    itemValue = new TreeItemMMValue(MMType.BANQUE, bank.getName());
                    TreeItem<TreeItemMMValue> bankLeaf = new TreeItem<TreeItemMMValue>(itemValue);


                    // Add comptes
                    List<Compte> listCompte = mmHelper.getCompteByBank(bank.getId());
                    for (Compte compte : listCompte) {
                        itemValue = new TreeItemMMValue(MMType.COMPTE, compte.getName());
                        TreeItem<TreeItemMMValue> compteLeaf = new TreeItem<TreeItemMMValue>(itemValue);
                        bankLeaf.getChildren().add(compteLeaf);
                    }
                    rootNode.getChildren().add(bankLeaf);
                }


                break;
        }
        TreeView<TreeItemMMValue> treeView = new TreeView<TreeItemMMValue>(rootNode);
        treeView.setShowRoot(true);
        treeView.setEditable(true);
        treeView.setCellFactory(new TreeViewCallBack(this));
        return treeView;
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

    public void createActionButton(MMType type) {
        switch (type) {
            case BANQUE:
            case CATEGORY:
            case COMPTE:
            case OPERATION: {
                Button updateButton = new Button("Update");
                updateButton.setId("Update");
                updateButton.setOnAction(new ButtonHandler(this));
                updateButton.setUserData(MMType.BANQUE);
                Button deleteButton = new Button("Delete");
                deleteButton.setOnAction(new ButtonHandler(this));
                deleteButton.setId("Delete");
                deleteButton.setUserData(MMType.BANQUE);
                HBox hbAction = new HBox();
                hbAction.getChildren().addAll(updateButton, deleteButton);
                hbAction.setAlignment(Pos.CENTER_RIGHT);
                // TODO try to put on left action button
                root.setBottom(hbAction);

                break;
            }
            default: {
                Button exitButton = new Button("Exit");
                HBox hbAction = new HBox();
                hbAction.getChildren().addAll(exitButton);
                root.setBottom(hbAction);
                break;
            }
        }
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
}
