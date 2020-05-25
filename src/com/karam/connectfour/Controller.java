package com.karam.connectfour;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

	private static final int COLUMNS=7;
	private static final int ROWS=6;
	private static final int CIRCLE_DIAMETER=80;
	private static final String discColor1="#24303E";
	private static final String discColor2="#4CAA88";

	private static String PLAYER_ONE="Player One";
	private static String PLAYER_TWO="Player Two";

	private boolean isPlayerOneTurn=true;


	@FXML
	public GridPane rootGridPane;

	@FXML
	public Pane insertedDiscPane;

	@FXML
	public Label playerNameLabel;

	public void createPlayground(){

		Shape rectangleWithHoles=createGameStructureGrid();
		//now place this rectangle on pane
		rootGridPane.add(rectangleWithHoles,0,1);
		List<Rectangle> rectangleList=createClickableColumns();
		for (Rectangle rectangle:rectangleList){
			rootGridPane.add(rectangle,0,1);
		}

	}

	private Shape createGameStructureGrid(){
		Shape rectangleWithHoles=new Rectangle((COLUMNS+1)*CIRCLE_DIAMETER,(ROWS+1)*CIRCLE_DIAMETER);

		for (int row=0;row<ROWS;row++){
			for (int col=0;col<COLUMNS;col++){
				Circle circle=new Circle();
				circle.setRadius(CIRCLE_DIAMETER/2);
				circle.setCenterX(CIRCLE_DIAMETER/2);
				circle.setCenterY(CIRCLE_DIAMETER/2);
				circle.setTranslateX(col*(CIRCLE_DIAMETER+5)+CIRCLE_DIAMETER/4);
				circle.setTranslateY(row*(CIRCLE_DIAMETER+5)+CIRCLE_DIAMETER/4);
				rectangleWithHoles=Shape.subtract(rectangleWithHoles,circle);
			}
		}

		rectangleWithHoles.setFill(Color.WHITE);
		return rectangleWithHoles;
	}

	private List<Rectangle> createClickableColumns(){

		List<Rectangle> rectangleList=new ArrayList<>();
		for (int col=0;col<COLUMNS;col++){
			Rectangle rectangle=new Rectangle(CIRCLE_DIAMETER,(ROWS+1)*CIRCLE_DIAMETER);
			rectangle.setFill(Color.TRANSPARENT);
			rectangle.setTranslateX(col*(CIRCLE_DIAMETER+5)+CIRCLE_DIAMETER/4);

			rectangle.setOnMouseEntered(event -> rectangle.setFill(Color.valueOf("#eeeeee66")));
			rectangle.setOnMouseExited(event -> rectangle.setFill(Color.TRANSPARENT));

			final int column=col;//because of lambda expression
			rectangle.setOnMouseClicked(event -> {

				insertDisc(new Disc(isPlayerOneTurn),column);
			});
			rectangleList.add(rectangle);
		}

		return rectangleList;
	}
	private static void insertDisc(Disc disc,int column){

	}

	private static class Disc extends Circle{

		private final boolean isPlayerOneMove;
		public Disc(boolean isPlayerOneMove){
			this.isPlayerOneMove=isPlayerOneMove;
			setRadius(CIRCLE_DIAMETER/2);
			setFill(isPlayerOneMove? Color.valueOf(discColor1) : Color.valueOf(discColor2));
			setCenterX(CIRCLE_DIAMETER/2);
			setCenterY(CIRCLE_DIAMETER/2);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
