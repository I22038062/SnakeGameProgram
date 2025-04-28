package SnakeGame;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.control.Slider;

public class Settings {
	private class FoodRect extends Pane{
    	private final int block = 2;
    	private int iconID;
    	public void setID(int ID) {
    		iconID=ID;
    	}
    	public int getID() {
    		return iconID;
    	}
    	public void refresh(int choose) {
    		switch(choose) {
    		case 1: iconID=0; break;
    		case 2: iconID=1; break;
    		case 3: iconID=2; break;
    		case 4: iconID=3; break;
    		default: break;
    		}
    		add();
    	}
    	private void add() {
    		double squaresize= Math.min(getHeight(),getWidth())*0.8 / block;
    		double centerX = getWidth() / 2;
            double centerY = getHeight() / 2;
            getChildren().clear();
	        Rectangle Rect = new Rectangle(squaresize,squaresize);
	        Rect.setTranslateX(centerX-squaresize*block/2+squaresize*(iconID/2));
	        Rect.setTranslateY(centerY-squaresize*block/2+squaresize*(iconID%2));
	        Rect.setStroke(Color.RED);
	        Rect.setStrokeWidth(5);
        	Rect.setFill(Color.TRANSPARENT);
	        getChildren().add(Rect);
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
    private class Food1 extends Pane{ // 蘋果
		private final int block=2;
		private int[] position=new int[2];
		
		Food1(){
			position[0]=0;
			position[1]=0;
		}
		
		private void add() {
			double squaresize= Math.min(getHeight(),getWidth())*0.8 / block;
			double centerX = getWidth() / 2;
	        double centerY = getHeight() / 2;
	        getChildren().clear();
	        Circle cir=new Circle();
	        cir.setRadius(squaresize/2*0.6);
	        cir.setCenterX(centerX-squaresize*(block/2-0.5)+squaresize*position[0]);
	        cir.setCenterY(centerY-squaresize*(block/2-0.5)+squaresize*position[1]);
	        cir.setStroke(Color.BLACK);
	        cir.setFill(Color.RED);
	        getChildren().add(cir);
	        //------------------------
	        Ellipse ell=new Ellipse();
	        ell.setFill(Color.LIGHTGREEN);
	        ell.setStroke(Color.BLACK);
	        ell.setCenterX(centerX-squaresize*(block/2-0.5)+squaresize*position[0]-squaresize*0.1);
	        ell.setCenterY(centerY-squaresize*(block/2-0.5)+squaresize*position[1]-squaresize*0.2);
	        ell.setRadiusX(squaresize/3*0.6);
	        ell.setRadiusY(squaresize/3*0.3);
	        Rotate rotate2 = new Rotate();
	        rotate2.setAngle(30);  // 設置旋轉角度
	        rotate2.setPivotX(ell.getCenterX());  // 設置旋轉中心點X
	        rotate2.setPivotY(ell.getCenterY()); // 設置旋轉中心點Y
	        ell.getTransforms().add(rotate2);
	        getChildren().add(ell);
	        //-----------------------
	        Rectangle rect=new Rectangle(squaresize*0.05,squaresize/3);
	        rect.setFill(Color.BLACK);
	        rect.setStroke(Color.BLACK);
	        rect.setTranslateX(centerX-squaresize*(block/2)+squaresize*position[0]+squaresize*0.6);
	        rect.setTranslateY(centerY-squaresize*(block/2)+squaresize*position[1]+squaresize*0.1);
	        Rotate rotate = new Rotate();
	        rotate.setAngle(30);  // 設置旋轉角度
	        rotate.setPivotX(rect.getX() + rect.getWidth() / 2);  // 設置旋轉中心點X
	        rotate.setPivotY(rect.getY() + rect.getHeight() / 2); // 設置旋轉中心點Y
	        rect.getTransforms().add(rotate);
	        getChildren().add(rect);
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
    private class Food2 extends Pane{ // 西瓜
		private final int block=2;
		private int[] position=new int[2];
		
		Food2(){
			position[0]=0;
			position[1]=1;
		}
		
		private void add() {
			
			double squaresize= Math.min(getHeight(),getWidth())*0.8 / block;
			double centerX = getWidth() / 2;
	        double centerY = getHeight() / 2;
	        getChildren().clear();
	        
	        Arc arc2=new Arc(centerX,centerY,squaresize/1.2,squaresize/1.2,250,40);
			arc2.setCenterX(centerX-squaresize*(block/2-0.5)+squaresize*position[0]);
	        arc2.setCenterY(centerY-squaresize*(block/2-0.1)+squaresize*position[1]);
	        arc2.setType(ArcType.ROUND);
	        arc2.setStroke(Color.BLACK);
			arc2.setFill(Color.GREEN);
			getChildren().add(arc2);

	        Arc arc=new Arc(centerX,centerY,squaresize/1.5,squaresize/1.5,250,40);
			arc.setCenterX(centerX-squaresize*(block/2-0.5)+squaresize*position[0]);
	        arc.setCenterY(centerY-squaresize*(block/2-0.1)+squaresize*position[1]);
	        arc.setType(ArcType.ROUND);
	        arc.setStroke(Color.BLACK);
			arc.setFill(Color.RED);
			getChildren().add(arc);
	        
			int[][] rand= {{64,82},{14,59},{47,58},{70,39},{18,19},{36,84},{41,29},{39,96}};
			for(int i=0;i<8;i++) {
				Circle cir=new Circle();
				cir.setRadius(squaresize/50);
				double radi=squaresize/1.6*(20+rand[i][0])/100;
				double theta=(255+30*rand[i][1]/100)*Math.PI/180;
				cir.setCenterX(centerX-squaresize*(block/2-0.5)+squaresize*position[0]+radi*Math.cos(theta));
		        cir.setCenterY(centerY-squaresize*(block/2-0.1)+squaresize*position[1]-radi*Math.sin(theta));
		        getChildren().add(cir);
		    }
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
    private class Food3 extends Pane{ // 葡萄
		private final int block=2;
		private int[] position=new int[2];
		
		Food3(){
			position[0]=1;
			position[1]=0;
		}
		
		private void add() {
			double squaresize= Math.min(getHeight(),getWidth())*0.8 / block;
			double centerX = getWidth() / 2;
	        double centerY = getHeight() / 2;
	        getChildren().clear();
	        
	        Ellipse ell=new Ellipse();
	        ell.setFill(Color.DARKGREEN);
	        ell.setStroke(Color.BLACK);
	        ell.setCenterX(centerX-squaresize*(block/2-0.5)+squaresize*position[0]-squaresize*0.1);
	        ell.setCenterY(centerY-squaresize*(block/2-0.36)+squaresize*position[1]-squaresize*0.2);
	        ell.setRadiusX(squaresize/3*0.6);
	        ell.setRadiusY(squaresize/3*0.3);
	        Rotate rotate = new Rotate();
	        rotate.setAngle(20);  // 設置旋轉角度
	        rotate.setPivotX(ell.getCenterX());  // 設置旋轉中心點X
	        rotate.setPivotY(ell.getCenterY()); // 設置旋轉中心點Y
	        ell.getTransforms().add(rotate);
	        getChildren().add(ell);

	        Ellipse ell2=new Ellipse();
	        ell2.setFill(Color.DARKGREEN);
	        ell2.setStroke(Color.BLACK);
	        ell2.setCenterX(centerX-squaresize*(block/2-0.8)+squaresize*position[0]-squaresize*0.1);
	        ell2.setCenterY(centerY-squaresize*(block/2-0.36)+squaresize*position[1]-squaresize*0.2);
	        ell2.setRadiusX(squaresize/3*0.6);
	        ell2.setRadiusY(squaresize/3*0.3);
	        Rotate rotate2 = new Rotate();
	        rotate2.setAngle(-20);  // 設置旋轉角度
	        rotate2.setPivotX(ell2.getCenterX());  // 設置旋轉中心點X
	        rotate2.setPivotY(ell2.getCenterY()); // 設置旋轉中心點Y
	        ell2.getTransforms().add(rotate2);
	        getChildren().add(ell2);

	        
	        double[][] iconPosition = {
	        		{0.69,0.31},{0.77,0.49},{0.66,0.60},
	        		{0.43,0.27},{0.63,0.24},{0.52,0.77},
	        		{0.32,0.41},{0.52,0.38},{0.72,0.36},
	        		{0.30,0.55},{0.42,0.53},{0.60,0.49},
	        		{0.33,0.69},{0.53,0.64},{0.35,0.85}
	        		};
			for(double[] iconPos:iconPosition) {
		        Circle cir=new Circle();
		        cir.setRadius(squaresize/2*0.22);
		        cir.setCenterX(centerX-squaresize*(block/2)+squaresize*(position[0]+iconPos[0]));
		        cir.setCenterY(centerY-squaresize*(block/2)+squaresize*(position[1]+iconPos[1]));
		        cir.setStroke(Color.BLACK);
		        cir.setFill(Color.PURPLE);
		        getChildren().add(cir);
			}
	        
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
    private class Food4 extends Pane{ // 奇異果
		private final int block=2;
		private int[] position=new int[2];
		
		Food4(){
			position[0]=1;
			position[1]=1;
		}
		
		private void add() {
			double squaresize= Math.min(getHeight(),getWidth())*0.8 / block;
			double centerX = getWidth() / 2;
	        double centerY = getHeight() / 2;
	        getChildren().clear();
	        
	        for(int i=0;i<3;i++) {
	            Circle cir=new Circle();
		        cir.setCenterX(centerX-squaresize*(block/2-0.5)+squaresize*position[0]);
		        cir.setCenterY(centerY-squaresize*(block/2-0.5)+squaresize*position[1]);
		        if(i==0) {
		        	cir.setRadius(squaresize/2*0.7);
			        cir.setStroke(Color.BLACK);
			        cir.setFill(Color.GREEN);
		        }else if(i==1) {
		        	cir.setRadius(squaresize/2*0.65);
			        cir.setStroke(Color.TRANSPARENT);
			        cir.setFill(Color.DARKOLIVEGREEN);
		        }else { // i==2
		        	cir.setRadius(squaresize/2*0.3);
			        cir.setStroke(Color.TRANSPARENT);
			        cir.setFill(Color.WHITE);
			        cir.setOpacity(0.6);
		        }
			    getChildren().add(cir);
	        }
	        for(int i=0;i<8;i++) {
	        	double theta=45*i*Math.PI/180;
	            Circle cir=new Circle();
	        	cir.setCenterX(centerX-squaresize*(block/2-0.5)+squaresize*position[0]+squaresize/2*0.4*Math.cos(theta));
		        cir.setCenterY(centerY-squaresize*(block/2-0.5)+squaresize*position[1]+squaresize/2*0.4*Math.sin(theta));
	        	cir.setRadius(squaresize/2*0.02);
		        cir.setStroke(Color.BLACK);
		        cir.setFill(Color.BLACK);
			    getChildren().add(cir);
	        }
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

	private class GearUI extends Pane{ // 齒輪
		private void add() {
			double radius=Math.min(getHeight(),getWidth()/5);
			double centerX = getWidth() / 2;
	        double centerY = getHeight() / 2;
	        getChildren().clear();
	        
	        for(int i=0;i<8;i++) {
		        Rectangle rect=new Rectangle(radius/2,radius/2);
		        rect.setFill(Color.BLACK);
		        rect.setStroke(Color.BLACK);
		        rect.setTranslateX(centerX - radius/2 / 2);
		        rect.setTranslateY(centerY - radius*2.5 / 2);
		        Rotate rotate = new Rotate();
		        rotate.setAngle(45*i);  // 設置旋轉角度
		        rotate.setPivotX(rect.getX() + radius/2 / 2);  // 設置旋轉中心點X
		        rotate.setPivotY(rect.getY() + radius*2.5 / 2); // 設置旋轉中心點Y
		        rect.getTransforms().add(rotate);
		        getChildren().add(rect);
	        }
	        int div=50;
	        for(int i=0;i<div;i++) {
	        	Circle c1=new Circle();
				c1.setRadius(radius*(0.5+0.5*i/div));
		        c1.setCenterX(centerX);
		        c1.setCenterY(centerY);
		        c1.setFill(Color.TRANSPARENT);
		        c1.setStroke(Color.BLACK);
		        getChildren().add(c1);
	        }
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
	
	private class ColorChoose extends GridPane {
	    private Color selectedColor1=Color.SLATEBLUE;
	    private Color selectedColor2=Color.DARKSLATEBLUE;
	    private ObjectProperty<Color> color1Property = new SimpleObjectProperty<>();	    
        private ObjectProperty<Color> color2Property = new SimpleObjectProperty<>();
        private ArrayList<Color> colorList=new ArrayList<>();
	    public ObjectProperty<Color> getProperty1() {
            return color1Property;
        }

        public ObjectProperty<Color> getProperty2() {
            return color2Property;
        }
	    public Color getColor1() {
	    	return selectedColor1;
	    }
	    public Color getColor2() {
	    	return selectedColor2;
	    }
	    ColorChoose(Color color1,Color color2){
	    	selectedColor1=color1;
	    	selectedColor2=color2;
	    	Field[] fields = Color.class.getDeclaredFields();
	        for (Field field : fields) {
	            if (field.getType() == Color.class) {
	                try {
	                    Color color = (Color) field.get(null);
	                    if(field.getName().equals("TRANSPARENT")) {
	                    	continue;
	                    }
	                    colorList.add(color);
	                } catch (IllegalAccessException event) {
	                    event.printStackTrace();
	                }
	            }
	        }
	    }
	    private void addRectColor() {
	    	getChildren().clear();
	    	int row=0;
	    	int col=0;
	    	setHgap(5);
	        setVgap(5);
	        double squaresize= 50;
	        int NumColumn=(int)(getWidth()/squaresize);
	        getChildren().clear();
	        for (int i = 0; i < colorList.size(); i++) {                
	            Rectangle rectangle = new Rectangle(squaresize*0.8, squaresize*0.8 , colorList.get(i) );
	            rectangle.setStroke(Color.BLACK);
	            Color color=colorList.get(i);
	            HBox hBox = new HBox(10, rectangle);
	            hBox.setOnMouseClicked(event -> handleColorSelection(color));

	            add(hBox, col, row);
	            
	            col++;
	            if (col > NumColumn) {
	                col = 0;
	                row++;
	            }
	        }
	    }
	    @Override
	    public void setHeight(double height) {
	        super.setHeight(height);
	        addRectColor();
	    }

	    @Override
	    public void setWidth(double width) {
	        super.setWidth(width);
	        addRectColor();
	    }
	    private void handleColorSelection(Color color) {
            selectedColor1 = selectedColor2;
            selectedColor2 = color;
            color1Property.set(selectedColor1);
            color2Property.set(selectedColor2);
	    }
	}
	
	
	private static Stage stage=new Stage();
	private static Button[] btn= {
			new Button("Snake Color"),
			new Button("Map Color"),
			new Button("Food Icon"),
			new Button("Volume"),
			new Button("Back to Menu"),
	};
	private BorderPane pane=new BorderPane();
	private BorderPane topPane = new BorderPane();
	private VBox centerBtn=new VBox(10,btn[0],btn[1],btn[2],btn[3],btn[4]);
	private File cssFile = new File("style.css");
	private Scene scene=new Scene(pane,1000,605);
	private FoodRect foodicon=new FoodRect();
	private ColorChoose mapColor;
	private ColorChoose snakeColor;
	private SettingsData settData=new SettingsData();
	
	Settings(SettingsData settDataInput){
		mapColor=new ColorChoose(settDataInput.getmapColor1(),settDataInput.getmapColor2());
		snakeColor=new ColorChoose(settDataInput.getbodyColor(),settDataInput.getheadColor());
		foodicon.setID(settDataInput.getfoodID());
		GearUI gear=new GearUI();
		gear.setPrefWidth(150);
		gear.setPrefHeight(150);
		
		for(Button b:btn) {
			b.getStyleClass().add("btn");
			b.getStylesheets().add(cssFile.toURI().toString());
			b.setPrefWidth(300);
			b.setPrefHeight(75);
			b.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,30));
		}
		GearUI gearRight=new GearUI(); // 為了讓中間的Label能夠置中
		gearRight.setPrefWidth(150);
		gearRight.setPrefHeight(150);
		gearRight.setVisible(false);
		topPane.setRight(gearRight);
		topPane.setLeft(gear);
		
        pane.getStyleClass().add("pane-background");
        pane.setCenter(centerBtn);
        
        scene.getStylesheets().add(cssFile.toURI().toString());
        stage.setTitle("Settings");
		stage.setScene(scene);
		stage.setResizable(false);
	}
	static double lastTimeMusicVolume = 1, lastTimeSoundVolume = 1;
	public void show() {		
		centerBtn.setAlignment(Pos.CENTER);
		GearUI gearBottom=new GearUI(); // 為了讓中間的按鈕能夠置中
        gearBottom.setPrefWidth(150);
        gearBottom.setPrefHeight(150);
        gearBottom.setVisible(false);
        
        pane.setCenter(centerBtn);
        pane.setBottom(gearBottom);
        pane.setTop(topPane);
		
		stage.show();
		
		btn[0].setOnMouseClicked(e->{
			Label label=new Label("Snake Color");
			label.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,50));
			label.setTextFill(Color.DARKRED);
			topPane.setCenter(label);
			
			Label[] circleInfo= {new Label("body"),new Label("head")};
			Text[] circleInfoBorder= {new Text("body"),new Text("head")};
			for(int i=0;i<2;i++) {
				circleInfo[i].setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,20));
				circleInfo[i].setTextFill(Color.BLACK);
				
				circleInfoBorder[i].setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,20));
				circleInfoBorder[i].setStroke(Color.WHITE);
				circleInfoBorder[i].setFill(Color.TRANSPARENT);
				circleInfoBorder[i].setStrokeWidth(2);
			}
			
			Circle c1=new Circle(25,snakeColor.getColor1());
	        Circle c2=new Circle(25,snakeColor.getColor2());
	        StackPane c1text=new StackPane();
	        c1text.getChildren().addAll(c1,circleInfoBorder[0],circleInfo[0]);
	        StackPane c2text=new StackPane();
	        c2text.getChildren().addAll(c2,circleInfoBorder[1],circleInfo[1]);
	        
	        snakeColor.getProperty1().addListener((observable, oldValue, newValue) -> c1.setFill(newValue));
	        snakeColor.getProperty2().addListener((observable, oldValue, newValue) -> c2.setFill(newValue));
	        
	        Button backSettings=new Button("Save and Back to Settings");
			backSettings.getStyleClass().add("btn");
			backSettings.getStylesheets().add(cssFile.toURI().toString());
			backSettings.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,30));
			backSettings.setPrefWidth(560);
			backSettings.setPrefHeight(40);
	        HBox hb=new HBox(10,c1text,c2text);
	        hb.setAlignment(Pos.BOTTOM_CENTER);
	        
	        VBox vb=new VBox(10,hb,backSettings);
	        vb.setAlignment(Pos.BOTTOM_CENTER);
	        
	        pane.setCenter(snakeColor);
	        pane.setBottom(vb);
	        backSettings.setOnMouseClicked(event->{
				label.setVisible(false);
				show();
			});
		});
		
		btn[1].setOnMouseClicked(e->{
			Label label=new Label("Map Color");
			label.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,50));
			label.setTextFill(Color.DARKRED);
			topPane.setCenter(label);
			
	        Rectangle r1=new Rectangle(100,50,mapColor.getColor1());
	        Rectangle r2=new Rectangle(100,50,mapColor.getColor2());
	        
	        mapColor.getProperty1().addListener((observable, oldValue, newValue) -> r1.setFill(newValue));
	        mapColor.getProperty2().addListener((observable, oldValue, newValue) -> r2.setFill(newValue));
	        
	        Button backSettings=new Button("Save and Back to Settings");
			backSettings.getStyleClass().add("btn");
			backSettings.getStylesheets().add(cssFile.toURI().toString());
			backSettings.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,30));
			backSettings.setPrefWidth(560);
			backSettings.setPrefHeight(40);
	        HBox hb=new HBox(10,r1,r2);
	        hb.setAlignment(Pos.BOTTOM_CENTER);
	        
	        VBox vb=new VBox(10,hb,backSettings);
	        vb.setAlignment(Pos.BOTTOM_CENTER);
	        
	        pane.setCenter(mapColor);
	        pane.setBottom(vb);
	        backSettings.setOnMouseClicked(event->{
				label.setVisible(false);
				show();
			});
		});
		
		btn[2].setOnMouseClicked(e->{
			Label label=new Label("Food Icon");
			label.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,50));
			label.setTextFill(Color.DARKRED);
			topPane.setCenter(label);
			StackPane mapPane=new StackPane();
			
			mapPane.getChildren().addAll(new Food1(),new Food2(),new Food3(),new Food4(),foodicon);
			pane.setCenter(mapPane);
			Button[] chooseBtn= {
					new Button("Apple"),
					new Button("Watermelon"),
					new Button("Grape"),
					new Button("Kiwi"),
			};
			for(int i=0;i<4;i++) {
				chooseBtn[i].getStyleClass().add("btn");
				chooseBtn[i].getStylesheets().add(cssFile.toURI().toString());
				chooseBtn[i].setPrefWidth(135);
				chooseBtn[i].setPrefHeight(30);
				chooseBtn[i].setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,20));
			}
			HBox chooseBtnHBox=new HBox(10,chooseBtn[0],chooseBtn[1],chooseBtn[2],chooseBtn[3]);
			chooseBtnHBox.setAlignment(Pos.BOTTOM_CENTER);
			
			Button backSettings=new Button("Save and Back to Settings");
			backSettings.getStyleClass().add("btn");
			backSettings.getStylesheets().add(cssFile.toURI().toString());
			backSettings.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,30));
			backSettings.setPrefWidth(560);
			backSettings.setPrefHeight(40);
			HBox hb=new HBox(10,backSettings);
			hb.setAlignment(Pos.BOTTOM_CENTER);
			
			VBox vb=new VBox(10,chooseBtnHBox,hb);
			pane.setBottom(vb);
			
			backSettings.setOnMouseClicked(event->{
				label.setVisible(false);
				show();
			});
			
			chooseBtn[0].setOnMouseClicked(event->{
				foodicon.refresh(1);
			});
			chooseBtn[1].setOnMouseClicked(event->{
				foodicon.refresh(2);
			});
			chooseBtn[2].setOnMouseClicked(event->{
				foodicon.refresh(3);
			});
			chooseBtn[3].setOnMouseClicked(event->{
				foodicon.refresh(4);
			});
		});
		
		btn[3].setOnMouseClicked(e->{
			Label volumeLabel = new Label("Volume");
            volumeLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
            volumeLabel.setTextFill(Color.DARKRED);
            topPane.setCenter(volumeLabel);
            
            Slider musicSlider = new Slider(0, 1, lastTimeMusicVolume); //最小值為0，最大值為1，初始值為0.5
            musicSlider.setShowTickLabels(false);
            musicSlider.setBlockIncrement(0.1);
            musicSlider.setMaxWidth(300);
            Slider soundEffectSlider = new Slider(0, 1, lastTimeSoundVolume); //最小值為0，最大值為1，初始值為0.5
            soundEffectSlider.setShowTickLabels(false);
            soundEffectSlider.setBlockIncrement(0.1);
            soundEffectSlider.setMaxWidth(300);
            Label soundEffectLabel = new Label("Sound Effect");
            Label musicLabel = new Label("Music");
            musicLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
            soundEffectLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
            Label musicVolumeValue = new Label();
            Label soundEffectVolumeValue = new Label();
            musicVolumeValue.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
            musicVolumeValue.setTextFill(Color.DARKBLUE);
            soundEffectVolumeValue.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
            soundEffectVolumeValue.setTextFill(Color.DARKBLUE);
            musicVolumeValue.textProperty().bind(
                musicSlider.valueProperty().multiply(100).asString("%.0f%%")
            );
            soundEffectVolumeValue.textProperty().bind(
                soundEffectSlider.valueProperty().multiply(100).asString("%.0f%%")
            );
            PlaySound.musicPlayer.volumeProperty().bind(musicSlider.valueProperty());
            PlaySound.menuMusicPlayer.volumeProperty().bind(musicSlider.valueProperty());
            PlaySound.bombPlayer.volumeProperty().bind(soundEffectSlider.valueProperty());
            PlaySound.eatFoodPlayer.volumeProperty().bind(soundEffectSlider.valueProperty());
            PlaySound.hitWallPlayer.volumeProperty().bind(soundEffectSlider.valueProperty());
            PlaySound.hitBodyPlayer.volumeProperty().bind(soundEffectSlider.valueProperty());
            VBox vb = new VBox(10, musicLabel, musicSlider, musicVolumeValue, soundEffectLabel, soundEffectSlider, soundEffectVolumeValue);
            vb.setAlignment(Pos.CENTER);
            pane.setCenter(vb);
            
            Button backSettings=new Button("Save and Back to Settings");
			backSettings.getStyleClass().add("btn");
			backSettings.getStylesheets().add(cssFile.toURI().toString());
			backSettings.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,30));
			backSettings.setPrefWidth(500);
			backSettings.setPrefHeight(40);
			VBox vb_bottom=new VBox(10,backSettings);
			vb_bottom.setAlignment(Pos.CENTER);
			pane.setBottom(vb_bottom);
            backSettings.setOnMouseClicked(event->{
            	volumeLabel.setVisible(false);
            	lastTimeMusicVolume = musicSlider.getValue();
            	lastTimeSoundVolume = soundEffectSlider.getValue();
				show();
			});
		});
		
		btn[4].setOnMouseClicked(e->{
			settData.set(snakeColor.getColor1(),snakeColor.getColor2(),mapColor.getColor1(),mapColor.getColor2(),foodicon.getID());
			stage.close();
			Menu NewMenu=new Menu(settData);
			NewMenu.show();
		});
	}
}
