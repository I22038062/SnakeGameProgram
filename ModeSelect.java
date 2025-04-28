package SnakeGame;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ModeSelect{
	private static Stage stage=new Stage();
	private static Button[] btn_GameMode= { new Button("Origional"),
											new Button("MultipleBomb"),
											new Button("CumulativeBomb"),
											new Button("FoodwithBorder"),
											new Button("MultipleFood")};
	private static Button[] btn_SnakeMode= {new Button("CrossWall_Off"),
											new Button("CrossWall_On")};
	private static boolean[] GameMode_boolean = {false,false,false,false,false};
	private static boolean[] SnakeMode_boolean = {false,false};
	private static Button[] btn_StartandBack = {new Button("Enter Game"),new Button("Back to Menu")};
	private SettingsData settData=new SettingsData();
	private File cssFile = new File("style.css");
	private File cssFile_select = new File("select.css");
	ModeSelect(SettingsData settDataInput){
		settData = settDataInput;
		BorderPane pane=new BorderPane();
		pane.getStyleClass().add("pane-background");
		HBox hb_GameMode1 = new HBox(10);
		HBox hb_GameMode2 = new HBox(10);
		for (int i = 0; i < 5; i++) {
            final int index = i;
			btn_GameMode[i].setPrefWidth(230);
			btn_GameMode[i].setPrefHeight(30);
			btn_GameMode[i].setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,25));
			btn_GameMode[i].getStyleClass().add("btn");
			btn_GameMode[i].getStylesheets().add(cssFile.toURI().toString());
            btn_GameMode[i].setOnMouseClicked(e -> HandleButtonSelect_GameMode(index));
            if(i<=2) {
            	hb_GameMode1.getChildren().add(btn_GameMode[i]);
            }else {
            	hb_GameMode2.getChildren().add(btn_GameMode[i]);
            }
        }
		hb_GameMode1.setAlignment(Pos.CENTER);
		hb_GameMode2.setAlignment(Pos.CENTER);
		VBox vb_GameMode=new VBox(10,hb_GameMode1,hb_GameMode2);
		HBox hb_SnakeMode=new HBox(10);
		for (int i = 0; i < 2; i++) {
            final int index = i;
			btn_SnakeMode[i].setPrefWidth(300);
			btn_SnakeMode[i].setPrefHeight(30);
			btn_SnakeMode[i].setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,25));
			btn_SnakeMode[i].getStyleClass().add("btn");
			btn_SnakeMode[i].getStylesheets().add(cssFile.toURI().toString());
            btn_SnakeMode[i].setOnMouseClicked(e -> HandleButtonSelect_SnakeMode(index));
            hb_SnakeMode.getChildren().add(btn_SnakeMode[i]);
        }
		HBox hb_Bottom = new HBox(10);
		for(Button bt:btn_StartandBack) {
			bt.getStyleClass().add("btn");
			bt.getStylesheets().add(cssFile.toURI().toString());
			bt.setPrefWidth(400);
			bt.setPrefHeight(50);
			bt.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,30));
			hb_Bottom.getChildren().add(bt);
		}
		
		vb_GameMode.setAlignment(Pos.CENTER);
		hb_SnakeMode.setAlignment(Pos.CENTER);
		Label lab_GameMode=new Label("GameMode");
		lab_GameMode.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,40));
		lab_GameMode.setTextFill(Color.DARKRED);
		Label lab_SnakeMode=new Label("SnakeMode");
		lab_SnakeMode.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,40));
		lab_SnakeMode.setTextFill(Color.DARKRED);
		VBox vb = new VBox(10,lab_GameMode,vb_GameMode,lab_SnakeMode,hb_SnakeMode);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(15);
		hb_Bottom.setAlignment(Pos.CENTER);
		hb_Bottom.setPadding(new Insets(0,0,25,0));
		
		Label title=new Label("Mode Select");
		title.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,50));
		title.setTextFill(Color.DARKRED);
		BorderPane topPane=new BorderPane();
		topPane.setCenter(title);
		topPane.setPrefHeight(150);
		
		pane.setTop(topPane);
		pane.setCenter(vb);
		pane.setBottom(hb_Bottom);
		Scene scene=new Scene(pane,1000,605);
        scene.getStylesheets().add(cssFile.toURI().toString());
        
		stage.setTitle("ModeSelect");
		stage.setScene(scene);
		stage.setResizable(false);
	}
	private void HandleButtonSelect_GameMode(int index) {
		for(int i=0;i<5;i++) {
			btn_GameMode[i].getStylesheets().clear();
			GameMode_boolean[i]=false;
		}
		for (int i = 0; i < 5; i++) {
			if(i==index) {
				continue;
			}
            btn_GameMode[i].getStyleClass().add("btn");
			btn_GameMode[i].getStylesheets().add(cssFile.toURI().toString());
		}
		btn_GameMode[index].getStyleClass().add("btn");
        btn_GameMode[index].getStylesheets().add(cssFile_select.toURI().toString());
        GameMode_boolean[index]=true;
        
	}
	private void HandleButtonSelect_SnakeMode(int index) {
		for(int i=0;i<2;i++) {
			btn_SnakeMode[i].getStylesheets().clear();
			SnakeMode_boolean[i]=false;
		}
		for (int i = 0; i < 2; i++) {
			if(i==index) {
				continue;
			}
            btn_SnakeMode[i].getStyleClass().add("btn");
			btn_SnakeMode[i].getStylesheets().add(cssFile.toURI().toString());
		}
		btn_SnakeMode[index].getStyleClass().add("btn");
        btn_SnakeMode[index].getStylesheets().add(cssFile_select.toURI().toString());
        SnakeMode_boolean[index]=true;
	}
	public void show() {
//		stage.setMaximized(true);
		stage.show();
		
		btn_StartandBack[0].setOnMouseClicked(e -> {
			PlaySound.menuMusicPlayer.stop();
			if(SnakeMode_boolean[0]) {
				if(GameMode_boolean[0]) {
					Game00 gm = new Game00(settData);
					gm.show();
					stage.close();
				}
				else if(GameMode_boolean[1]) {
					Game01 gm = new Game01(settData);
					gm.show();
					stage.close();
				}
				else if(GameMode_boolean[2]) {
					Game02 gm = new Game02(settData);
					gm.show();
					stage.close();
				}
				else if(GameMode_boolean[3]) {
					Game03 gm = new Game03(settData);
					gm.show();
					stage.close();
				}
				else if(GameMode_boolean[4]) {
					Game04 gm = new Game04(settData);
					gm.show();
					stage.close();
				}
			}
			else if(SnakeMode_boolean[1]) {
				if(GameMode_boolean[0]) {
					Game10 gm = new Game10(settData);
					gm.show();
					stage.close();
				}
				else if(GameMode_boolean[1]) {
					Game11 gm = new Game11(settData);
					gm.show();
					stage.close();
				}
				else if(GameMode_boolean[2]) {
					Game12 gm = new Game12(settData);
					gm.show();
					stage.close();
				}
				else if(GameMode_boolean[3]) {
					Game13 gm = new Game13(settData);
					gm.show();
					stage.close();
				}
				else if(GameMode_boolean[4]) {
					Game14 gm = new Game14(settData);
					gm.show();
					stage.close();
				}
			}
		});
		btn_StartandBack[1].setOnMouseClicked(e -> {
			stage.close();
			Menu menu = new Menu(settData);
			menu.show();
		});
	}
}