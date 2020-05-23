package com.karam.connectfour;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("game.fxml"));
	    GridPane rootGridPane=loader.load();
	    controller=loader.getController();

	    MenuBar menuBar=createMenu();//here is the menubar
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());//Binding to entire width

	    Pane menuPane= (Pane) rootGridPane.getChildren().get(0);//get the 0th index which is a pane for menue bar
	    menuPane.getChildren().add(menuBar);//Inserting menu bar in menuPane


	    Scene scene=new Scene(rootGridPane);

	    primaryStage.setScene(scene);
	    primaryStage.setTitle("Connect Four");
	    primaryStage.setResizable(false);
	    primaryStage.show();
    }

	private MenuBar createMenu(){
    	//File menu
		Menu fileMenu=new Menu("File");

		MenuItem newGame=new MenuItem("New game");
		MenuItem resetGame=new MenuItem("Reset game");
		SeparatorMenuItem separatorMenuItem=new SeparatorMenuItem();
		MenuItem exitGame=new MenuItem("Exit game");

		fileMenu.getItems().addAll(newGame,resetGame,separatorMenuItem,exitGame);

		//Help Menu
		Menu helpMenu=new Menu("Help");

		MenuItem aboutGame=new MenuItem("About Connect 4");
		SeparatorMenuItem separatorHelpItem=new SeparatorMenuItem();
		MenuItem aboutMe=new MenuItem("About Me");

		helpMenu.getItems().addAll(aboutGame,separatorHelpItem,aboutMe);


		MenuBar menuBar=new MenuBar();
		menuBar.getMenus().addAll(fileMenu,helpMenu);
		return menuBar;

	}

    public static void main(String[] args) {
        launch(args);
    }
}
