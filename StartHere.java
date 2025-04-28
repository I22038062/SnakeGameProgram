package SnakeGame;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

class SettingsData{
	private Color bodyColor;
	private Color headColor;
	private Color mapColor1;
	private Color mapColor2;
	private int foodID;
	public Color getbodyColor() {
		return bodyColor;
	}
	public Color getheadColor() {
		return headColor;
	}
	public Color getmapColor1() {
		return mapColor1;
	}
	public Color getmapColor2() {
		return mapColor2;
	}
	public int getfoodID() {
		return foodID;
	}
	public void set(Color c1,Color c2,Color c3,Color c4,int ID){
		bodyColor=c1;
		headColor=c2;
		mapColor1=c3;
		mapColor2=c4;
		foodID=ID;
	}
}

public class StartHere extends Application {
	public void start(Stage primaryStage) {
		Menu menu=new Menu();
		menu.show();
		PlaySound.playMenuMusic();
	}
	public static void main(String[] args) {
		launch(args);
	}
}