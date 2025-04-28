package SnakeGame;

import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Menu {
	
	private class SnakeGIF extends Pane{
		private int pos;
		private int div;
		
		SnakeGIF(){
			pos=0;
			div=180;
		}
		
		private void update() {
			pos++;
			pos%=div;
			add();
		}
		
		private void add() {
			double radius=Math.min(getHeight(),getWidth()/5);
			double centerX = getWidth() / 2;
	        double centerY = getHeight() / 2;
	        getChildren().clear();
	        double rectX=radius/3;
	        double rectY=radius*2.7;
	        Rectangle rect=new Rectangle(rectX,rectY);
	        rect.setTranslateX(centerX - rect.getWidth() / 2);
	        rect.setTranslateY(centerY - rect.getHeight() / 2);
	        for(int i=0;i<div;i++) {
	        	Boolean nobody=false;
	        	for(int j=0;j<div*0.1;j++) {
		        	if( (i-j+div)%div==pos ) {
		        		nobody=true;
		        		break;
		        	}
	        	}
	        	if(nobody) {
	        		continue;
	        	}
		        Circle c=new Circle(radius/8);
		        c.setFill(Color.CORNFLOWERBLUE);
	        	
		        Rotate rotate = new Rotate();
		        rotate.setAngle(360/div*i);
		        rotate.setPivotX(rect.getX() + rect.getWidth() / 2);
		        rotate.setPivotY(rect.getY() + rect.getHeight() / 2);
		        c.setStroke(Color.TRANSPARENT);
		        c.setTranslateX(centerX - rect.getWidth() / 2);
		        c.setTranslateY(centerY - rect.getHeight() / 2);
		        c.getTransforms().add(rotate);
		        getChildren().add(c);
	        }
	        
	        // head最上層最後畫
	        Circle c=new Circle(radius/8*1.2);
	        c.setFill(Color.DARKORANGE);
	        Rotate rotate = new Rotate();
	        rotate.setAngle(360/div*pos);
	        rotate.setPivotX(rect.getX() + rect.getWidth() / 2);
	        rotate.setPivotY(rect.getY() + rect.getHeight() / 2);
	        c.setStroke(Color.TRANSPARENT);
	        c.setTranslateX(centerX - rect.getWidth() / 2);
	        c.setTranslateY(centerY - rect.getHeight() / 2);
	        c.getTransforms().add(rotate);
	        getChildren().add(c);
		}
		@Override
		public void setHeight(double height) {
			super.setHeight(height);
			add();
		}
		@Override
		public void setWidth(double width) {
			super.setWidth(width);
			add();
	 	}
	}
	
	private static Stage stage=new Stage();
	private Label title=new Label("Snake\nGame");
	private static Button[] btn= {
			new Button("Single Player"),
			new Button("Not Single"),
			new Button("Settings"),
			new Button("Exit")
			};
	private SettingsData settData=new SettingsData();
	private BorderPane pane=new BorderPane();
	private VBox vb=new VBox(10,title,btn[0],btn[1],btn[2],btn[3]);
	private File cssFile = new File("style.css");
	private Scene scene=new Scene(pane,1000,605);
	private SnakeGIF snakeGIF=new SnakeGIF();
	
	Menu(){
		settData.set(Color.LIGHTGRAY,Color.LIGHTGREEN, Color.SLATEBLUE, Color.DARKSLATEBLUE, 0);
	}
	Menu(SettingsData settDataInput){
		settData=settDataInput;
	}
	
	public void show() {
		title.setAlignment(Pos.CENTER);
		title.setFont(Font.font("Ravie", FontWeight.BOLD, FontPosture.ITALIC, 50));
		title.setTextFill(Color.DARKRED);
		title.setAlignment(Pos.TOP_CENTER);
		
		pane.getStyleClass().add("pane-background");
		for(Button b:btn) {
			b.getStyleClass().add("btn");
			b.getStylesheets().add(cssFile.toURI().toString());
			b.setPrefWidth(250);
			b.setPrefHeight(20);
			b.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,30));
		}
		
		StackPane paneCenter=new StackPane();
		paneCenter.getChildren().add(snakeGIF);
		paneCenter.getChildren().add(vb);
		
		vb.setAlignment(Pos.CENTER);
		pane.setCenter(paneCenter);
	    scene.getStylesheets().add(cssFile.toURI().toString());
		stage.setTitle("MENU");
		stage.setScene(scene);
		stage.setResizable(false);
		
		Timeline timelineGIF = new Timeline(new KeyFrame(Duration.seconds(0.02), event ->{
        	snakeGIF.update();
        }));
        timelineGIF.setCycleCount(Timeline.INDEFINITE);
        timelineGIF.play();
		
		// show
		stage.show();
		btn[0].setOnMouseClicked(e->{
			ModeSelect modeSelect=new ModeSelect(settData);
			modeSelect.show();
			timelineGIF.stop();
			stage.close();
		});
		btn[1].setOnMouseClicked(e ->{
			Game2P mp = new Game2P(settData);
			mp.show();
			timelineGIF.stop();
			stage.close();
		});
		btn[2].setOnMouseClicked(e->{
			Settings set=new Settings(settData);
			timelineGIF.stop();
			set.show();
			stage.close();
		});
		btn[3].setOnMouseClicked(e->{
			timelineGIF.stop();
			stage.close();
		});
	}
}