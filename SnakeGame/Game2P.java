package SnakeGame;

import java.io.File;
import java.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game2P {
	class MapUI extends Pane {
		private Color color1=Color.SLATEBLUE;
		private Color color2=Color.DARKSLATEBLUE;
		public void setColor(Color c1,Color c2) {
			color1=c1;
			color2=c2;
		}
		
		private void add() {
			double squaresize= Math.min(getHeight(),getWidth())*0.8 / block;
			double centerX = getWidth() / 2;
	        double centerY = getHeight() / 2;
	        getChildren().clear();
	        Rectangle wallRect = new Rectangle(squaresize*block*1.01,squaresize*block*1.01);
	        wallRect.setTranslateX(centerX-wallRect.getWidth()/2);
	        wallRect.setTranslateY(centerY-wallRect.getHeight()/2);
	        wallRect.setStroke(Color.BLACK);
	        wallRect.setFill(Color.BROWN);
	        getChildren().add(wallRect);
	        int div=100;
	        for(int i=0;i<div;i++) {
	        	Line line1 = new Line();
	        	line1.setStartX(wallRect.getTranslateX()+wallRect.getWidth()*i/div);
	        	line1.setEndX(wallRect.getTranslateX()+wallRect.getWidth()*(div-i)/div);
	        	line1.setStartY(wallRect.getTranslateY());
	        	line1.setEndY(wallRect.getTranslateY()+wallRect.getHeight());
	        	line1.setStrokeWidth(1);
	        	getChildren().add(line1);
	        	
	        	Line line2 = new Line();
	        	line2.setStartX(wallRect.getTranslateX());
	        	line2.setEndX(wallRect.getTranslateX()+wallRect.getWidth());
	        	line2.setStartY(wallRect.getTranslateY()+wallRect.getHeight()*i/div);
	        	line2.setEndY(wallRect.getTranslateY()+wallRect.getHeight()*(div-i)/div);
	        	line2.setStrokeWidth(1);
	        	getChildren().add(line2);
	        }
			for(int i=0;i<block;i++) {
				for(int j=0;j<block;j++) {
			        Rectangle Rect = new Rectangle(squaresize,squaresize);
			        Rect.setTranslateX(centerX-squaresize*block/2+squaresize*i);
			        Rect.setTranslateY(centerY-squaresize*block/2+squaresize*j);
			        Rect.setStroke(Color.BLACK);
			        if((i+j)%2==0) {
			        	Rect.setFill(color1);
			        }else {
			        	Rect.setFill(color2);
			        }
			        getChildren().add(Rect);
				}
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
	
	class Snake extends Pane {
		private Color bodyColor=Color.LIGHTGRAY;
		private Color headColor=Color.LIGHTGREEN;
		public void setColor(Color c1,Color c2) {
			bodyColor=c1;
			headColor=c2;
		}
		private ArrayList<int[]> position=new ArrayList<>();
		
		public ArrayList<int[]> getPosition(){
			return position;
		}
		
		public void addlen() {
			int[] tail= {position.get(0)[0],position.get(0)[1]};
			position.add(0,tail);
		}
		
		public void move(String cmd) {
			int[] head= {position.get(position.size()-1)[0],position.get(position.size()-1)[1]};
			switch (cmd) {
				case "up":
					head[1]--;
					break;
				case "down":
					head[1]++;
					break;
				case "left":
					head[0]--;
					break;
				case "right":
					head[0]++;
					break;
				default:
					break;
			}
			position.add(head);
			position.remove(0); // tail
			add();
		}
		
		private Text text=new Text();
		Snake(String str){
			text= new Text(str);
			if(str.equals("P1")) {
				int[] toadd={0,0};
				position.add(toadd);
				int[] toadd2={1,0};
				position.add(toadd2);
				int[] toadd3={2,0};
				position.add(toadd3);
			}else {
				int[] toadd={block-1,block-1};
				position.add(toadd);
				int[] toadd2={block-2,block-1};
				position.add(toadd2);
				int[] toadd3={block-3,block-1};
				position.add(toadd3);
			}
		}
		private void add() {
			double squaresize= Math.min(getHeight(),getWidth())*0.8 / block;
			double centerX = getWidth() / 2;
	        double centerY = getHeight() / 2;
	        getChildren().clear();
	        for(int i=0;i<position.size()-1;i++) {
	        	int div=20;
	        	for(int j=0;j<div;j++) {
	        		Circle body = new Circle(squaresize*0.6/2);
	        		body.setTranslateX(centerX-squaresize*block/2+squaresize*0.5+squaresize*(position.get(i)[0]*(div-j)+position.get(i+1)[0]*j)/div);
	        		body.setTranslateY(centerY-squaresize*block/2+squaresize*0.5+squaresize*(position.get(i)[1]*(div-j)+position.get(i+1)[1]*j)/div);
	        		body.setStroke(Color.BLACK);
	        		body.setStroke(Color.TRANSPARENT);
	        		body.setFill(bodyColor);
	    	        getChildren().add(body);
	        	}
	        }
			Circle head = new Circle();
		    head.setRadius(squaresize/2*0.8);
	        head.setCenterX(centerX-squaresize*(block/2-0.5)+squaresize*position.get(position.size()-1)[0]);
	        head.setCenterY(centerY-squaresize*(block/2-0.5)+squaresize*position.get(position.size()-1)[1]);
	        head.setStroke(Color.BLACK);
	        head.setFill(headColor);
	        getChildren().add(head);
	        
			text.setFont(Font.font("Times New Roman",FontWeight.BOLD,10));
			text.setFill(Color.BLACK);
			text.setStroke(Color.GRAY);
			text.setStrokeWidth(1);
			text.setTranslateX(centerX-squaresize*(block/2-0.25)+squaresize*position.get(position.size()-1)[0]);
			text.setTranslateY(centerY-squaresize*(block/2-0.65)+squaresize*position.get(position.size()-1)[1]);
	        getChildren().add(text);
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
	
	class Food extends Pane{
		private int[] position=new int[2];
		
		private int foodID=0;
		
		public void setfoodID(int foodID) {
			this.foodID=foodID;
		}
		
		public int[] getPosition(){
			return position;
		}
		
		Food(int x,int y){
			position[0]=x;
			position[1]=y;
		}
		
		public void rotate() {
			Random random = new Random();
			position[0]=random.nextInt(block);
			position[1]=random.nextInt(block);
		}
		
		private void add() {
			double squaresize= Math.min(getHeight(),getWidth())*0.8 / block;
			double centerX = getWidth() / 2;
	        double centerY = getHeight() / 2;
	        getChildren().clear();
	        if(foodID==0) {
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
	        }else if(foodID==1) {
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
	        }else if(foodID==2) {
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
	        }else { // foodID==3
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
	
	class Bomb extends Pane{
		private int[] position=new int[2];
		
		public int[] getPosition(){
			return position;
		}
		
		Bomb(int x,int y){
			position[0]=x;
			position[1]=y;
		}
		
		public void rotate() {
			Random random = new Random();
			position[0]=random.nextInt(block);
			position[1]=random.nextInt(block);
		}
		
		private void add() {
			double squaresize= Math.min(getHeight(),getWidth())*0.8 / block;
			double centerX = getWidth() / 2;
	        double centerY = getHeight() / 2;
	        getChildren().clear();
	
	        Circle c1=new Circle();
	        c1.setRadius(squaresize/2*0.6);
	        c1.setCenterX(centerX-squaresize*(block/2-0.5)+squaresize*position[0]);
	        c1.setCenterY(centerY-squaresize*(block/2-0.5)+squaresize*position[1]);
	        c1.setStroke(Color.BLACK);
	        c1.setFill(Color.BLACK);
	        getChildren().add(c1);
	        Rectangle rect=new Rectangle(squaresize*0.07,squaresize/3);
	        rect.setFill(Color.BLACK);
	        rect.setStroke(Color.BLACK);
	        rect.setTranslateX(centerX-squaresize*(block/2)+squaresize*position[0]+squaresize*0.66);
	        rect.setTranslateY(centerY-squaresize*(block/2)+squaresize*position[1]+squaresize*0.1);
	        Rotate rotate = new Rotate();
	        rotate.setAngle(30);  // 設置旋轉角度
	        rotate.setPivotX(rect.getX());  // 設置旋轉中心點X
	        rotate.setPivotY(rect.getY()); // 設置旋轉中心點Y
	        rect.getTransforms().add(rotate);
	        getChildren().add(rect);
	        Circle c2=new Circle();
	        c2.setRadius(squaresize*0.06);
	        c2.setTranslateX(centerX-squaresize*(block/2)+squaresize*position[0]+squaresize*0.7);
	        c2.setTranslateY(centerY-squaresize*(block/2)+squaresize*position[1]+squaresize*0.1);
	        c2.setFill(Color.RED);
	        c2.setStroke(Color.BLACK);
	        getChildren().add(c2);
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
	
	private boolean IsSnakeTouchFood(Snake snake,Food food) {
		for(int[] body:snake.getPosition()) {
			if(food.getPosition()[0]==body[0] && food.getPosition()[1]==body[1]) {
				PlaySound.playEatFoodSound();
				return true;
			}
		}
		return false;
	}
	private boolean IsFoodTouchFood(Food food1,Food food2) {
		if(food1.getPosition()[0]==food2.getPosition()[0] && food1.getPosition()[1]==food2.getPosition()[1]) {
			return true;
		}
		return false;
	}
	private boolean IsBombTouchBomb(Bomb bomb1,Bomb bomb2) {
		if(bomb1.getPosition()[0]==bomb2.getPosition()[0] && bomb1.getPosition()[1]==bomb2.getPosition()[1]) {
			return true;
		}
		return false;
	}
	private boolean IsSnakeTouchBomb(Snake snake,Bomb bomb) {
		for(int[] body:snake.getPosition()) {
			if(bomb.getPosition()[0]==body[0] && bomb.getPosition()[1]==body[1]) {
				return true;
			}
		}
		return false;
	}
	private boolean IsBombTouchFood(Bomb bomb,Food food) {
		if(food.getPosition()[0]==bomb.getPosition()[0] && food.getPosition()[1]==bomb.getPosition()[1]) {
			return true;
		}
		return false;
	}
	private boolean IsBombTouchSnakeNearPosition(Bomb bomb,Snake snake) {
		int[][] near_position = {{0,2},{-1,1},{0,1},{1,1},{-2,0},{-1,0},{0,0},{1,0},{2,0},{-1,-1},{0,-1},{1,-1},{0,-2}};
		int[] head= {snake.getPosition().get(snake.getPosition().size()-1)[0],snake.getPosition().get(snake.getPosition().size()-1)[1]};
		for(int[] near:near_position) {
			if(head[0]+near[0]==bomb.getPosition()[0] && head[1]+near[1]==bomb.getPosition()[1]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean IsGG(Snake snake1,Snake snake2) {
		int[] head=snake1.getPosition().get(snake1.getPosition().size()-1);
		if(head[0]<0 || head[0]>=block || head[1]<0 || head[1]>=block) {
			PlaySound.playHitWallSound();
			return true;
		}
		for(int i=0;i<snake1.getPosition().size()-1;i++) {
			int[] body=snake1.getPosition().get(i);
			if(head[0]==body[0] && head[1]==body[1]){
				PlaySound.playHitBodySound();
				return true;
			}
		}
		for(int i=0;i<snake2.getPosition().size();i++) {
			int[] body=snake2.getPosition().get(i);
			if(head[0]==body[0] && head[1]==body[1]){
				PlaySound.playHitBodySound();
				return true;
			}
		}
		return false;
	}
	
	private int block=30;
	private int scoreP1=0;
	private Label scoreboardP1=new Label();
	private int scoreP2=0;
	private Label scoreboardP2=new Label();
	private Boolean[] snakeAlive= {true,true};
	private MapUI map=new MapUI();
	private Snake snakeP1=new Snake("P1");
	private Snake snakeP2=new Snake("P2");
	private Food[] food= {
			new Food(block/3,block/3),new Food(block/3,block/3*2-1),
			new Food(block/3*2-1,block/3),new Food(block/3*2-1,block/3*2-1),
			new Food(0,block-1),new Food(block-1,0)
			};
	private Bomb[] bomb= {
			new Bomb(block/2-1,block/2),new Bomb(block/2,block/2-1),
			new Bomb(block/2-1,block/2-1),new Bomb(block/2,block/2),
			new Bomb(block/2-2,block/2+1),new Bomb(block/2+1,block/2-2),
			new Bomb(block/2-2,block/2-2),new Bomb(block/2+1,block/2+1),
			};
	private Label TouchToStart=new Label("Click Mouse to Start");
    private Label pauseLabel=new Label("Pause");
	private StackPane mapPane=new StackPane();
	private BorderPane pane = new BorderPane();
	private Scene scene = new Scene(pane,1000,600);
	private static Stage stage=new Stage();
	private Label timerLabel = new Label();
    private int secondsElapsed = 0;
    private double speed = 0.5;
    private Timeline timelineTimer;
    private Timeline timelineSnake;
    private Timeline timelineBomb;
    private boolean isPaused = false;
    boolean startGameCheck = false;
    boolean firstPauseCheck = true;
    private SettingsData settData=new SettingsData();
    private VBox rightPane;
    
    Game2P(SettingsData settDataInput) {
		settData=settDataInput;
		snakeP1.setColor(settData.getbodyColor(), settData.getheadColor());
		snakeP2.setColor(settData.getbodyColor(), settData.getheadColor());
		map.setColor(settData.getmapColor1(),settData.getmapColor2());
		for(Food f:food) {
			f.setfoodID(settData.getfoodID());
		}
		scoreP1=0;
		scoreboardP1.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,30));
		scoreboardP1.setTextFill(Color.DARKRED);
		scoreboardP1.setText("P1 Score : "+scoreP1);
		scoreP2=0;
		scoreboardP2.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,30));
		scoreboardP2.setTextFill(Color.DARKRED);
		scoreboardP2.setText("P2 Score : "+scoreP2);
		timerLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
        timerLabel.setTextFill(Color.DARKRED);
        updateTimerLabel();
		
		TouchToStart.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,30));
		TouchToStart.setTextFill(Color.RED);
		
		pauseLabel.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,30));
		
		mapPane.getChildren().add(map);
		for(Food f:food) {
			mapPane.getChildren().add(f);
		}
		mapPane.getChildren().add(snakeP1);
		mapPane.getChildren().add(snakeP2);
		for(Bomb b:bomb) {
			mapPane.getChildren().add(b);
		}
		mapPane.getChildren().add(TouchToStart);
		
		rightPane = new VBox(10,timerLabel,scoreboardP1,scoreboardP2);
        rightPane.setAlignment(Pos.CENTER_LEFT);
        rightPane.setPrefWidth(300);
		
        pane.setRight(rightPane);
		pane.setCenter(mapPane);
		pane.getStyleClass().add("pane-background");
		File cssFile = new File("style.css");
        scene.getStylesheets().add(cssFile.toURI().toString());
	}
	private void updateTimerLabel() {
        int minutes = secondsElapsed / 60;
        int seconds = secondsElapsed % 60;
        timerLabel.setText(String.format("Time: %02d:%02d", minutes, seconds));
    }
	
	private void showGG() {
		rightPane.setPrefWidth(0);
		rightPane.setVisible(false);
		PlaySound.musicPlayer.stop();
		mapPane.setVisible(false);
		Label lab1=new Label("GAME");
		Label lab2=new Label("OVER");
		Label lab3=new Label();
		Label lab4=new Label();
		if(snakeAlive[0]) {
			lab3.setText("P2 is Dead");
			lab4.setText("P1 WIN");
		}else if(snakeAlive[1]) {
			lab3.setText("P1 is Dead");
			lab4.setText("P2 WIN");
		}else {
			lab3.setText("No One is Alive");
			if(scoreP1>scoreP2) {
				lab4.setText("P1 WIN");
			}else if(scoreP2>scoreP1) {
				lab4.setText("P2 WIN");
			}else {
				lab4.setText("TIE");
			}
		}

		lab1.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,50));
		lab2.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,50));
		lab3.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,50));
		lab4.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,50));
		lab4.setTextFill(Color.DARKRED);
		VBox GG=new VBox(10,lab1,lab2,lab3,lab4,scoreboardP1,scoreboardP2);
		GG.setAlignment(Pos.CENTER);
		pane.setCenter(GG);
		
		Button[] GGbtn= {new Button("Retry"),
						new Button("Back To Menu")};
		HBox hb=new HBox(10,GGbtn[0],GGbtn[1]);
		hb.setAlignment(Pos.BOTTOM_CENTER);
		pane.setBottom(hb);
		File cssFile = new File("style.css");
		for(int i=0;i<2;i++) {
			GGbtn[i].getStyleClass().add("btn");
			GGbtn[i].getStylesheets().add(cssFile.toURI().toString());
			GGbtn[i].setPrefWidth(200);
			GGbtn[i].setPrefHeight(50);
			GGbtn[i].setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,25));
		}
		
		GGbtn[0].setOnMouseClicked(e->{
			stage.close();
			Game2P NewGame=new Game2P(settData);
			NewGame.show();
		});
		
		GGbtn[1].setOnMouseClicked(e->{
			stage.close();
			Menu NewMenu=new Menu(settData);
			NewMenu.show();
			PlaySound.playMenuMusic();
		});
		timelineTimer.stop();
        timelineSnake.stop();
        timelineBomb.stop();
	}
	
	public void show() {
		map.setOpacity(0.3);
		snakeP1.setOpacity(0.3);
		snakeP2.setOpacity(0.3);
		for(Food f:food) {
			f.setOpacity(0.3);
		}
		for(Bomb b:bomb) {
			b.setOpacity(0.3);
		}
		
		stage.setTitle("Game2P");
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();
		PlaySound.menuMusicPlayer.stop();
		pane.setOnMouseClicked(e->{
			if (startGameCheck == false){
				map.setOpacity(0.8);
				snakeP1.setOpacity(1);
				snakeP2.setOpacity(1);
				for(Food f:food) {
					f.setOpacity(1);
				}
				for(Bomb b:bomb) {
					b.setOpacity(1);
				}
				TouchToStart.setVisible(false);
                startGame();
                PlaySound.playMusic();
                startGameCheck = true;
            }
		});
	}
	
	private void startGame() {
		final String[] currentMoveDirection1 = {"Right","Right"};
		final String[] currentMoveDirection2 = {"Left","Left"};
		
		EventHandler<KeyEvent> eventfilter = new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (!isPaused){
					switch(currentMoveDirection1[0]) {
                    case "Up":
                        switch(event.getCode()) {
                        case W:currentMoveDirection1[1] = "Up";break;
                        case D:currentMoveDirection1[1] = "Right";break;
                        case A:currentMoveDirection1[1] = "Left";break;
                        default: break;
                        }
                        break;
                    case "Down":                    	
                        switch(event.getCode()) {
                        case S:currentMoveDirection1[1] = "Down";break;
                        case D:currentMoveDirection1[1] = "Right";break;
                        case A:currentMoveDirection1[1] = "Left";break;
                        default:break;
                        }
                        break;
                    case "Right":
                    	switch(event.getCode()) {
                        case W:currentMoveDirection1[1] = "Up";break;
                        case S:currentMoveDirection1[1] = "Down";break;
                        case D:currentMoveDirection1[1] = "Right";break;
                        default:break;
                        }
                        break;
                    case "Left":
                        switch(event.getCode()) {
                        case W:currentMoveDirection1[1] = "Up";break;
                        case S:currentMoveDirection1[1] = "Down";break;
                        case A:currentMoveDirection1[1] = "Left";break;
                        default:break;
                        }
                        break;
                    default:
                        break;
                    }
                    
					switch(currentMoveDirection2[0]) {
                    case "Up":
                        switch(event.getCode()) {
                        case UP:	currentMoveDirection2[1] = "Up";break;
                        case RIGHT:	currentMoveDirection2[1] = "Right";break;
                        case LEFT:	currentMoveDirection2[1] = "Left";break;
                        default: break;
                        }
                        break;
                    case "Down":                    	
                        switch(event.getCode()) {
                        case DOWN:	currentMoveDirection2[1] = "Down";break;
                        case RIGHT:	currentMoveDirection2[1] = "Right";break;
                        case LEFT:	currentMoveDirection2[1] = "Left";break;
                        default:break;
                        }
                        break;
                    case "Right":
                    	switch(event.getCode()) {
                        case UP:	currentMoveDirection2[1] = "Up";break;
                        case DOWN:	currentMoveDirection2[1] = "Down";break;
                        case RIGHT:	currentMoveDirection2[1] = "Right";break;
                        default:break;
                        }
                        break;
                    case "Left":
                        switch(event.getCode()) {
                        case UP:	currentMoveDirection2[1] = "Up";break;
                        case DOWN:	currentMoveDirection2[1] = "Down";break;
                        case LEFT:	currentMoveDirection2[1] = "Left";break;
                        default:break;
                        }
                        break;
                    default:
                        break;
                    }
                }
			}
		};
		scene.addEventFilter(KeyEvent.KEY_PRESSED, eventfilter);
		
		timelineSnake = new Timeline();
        KeyFrame moveFrame = new KeyFrame(Duration.seconds(speed), event -> {
        	switch (currentMoveDirection1[1]) {
            case "Up":snakeP1.move("up");break;
            case "Down":snakeP1.move("down");break;
            case "Right":snakeP1.move("right");break;
            case "Left":snakeP1.move("left");break;
        	}
        	currentMoveDirection1[0]=currentMoveDirection1[1];
            
        	switch (currentMoveDirection2[1]) {
            case "Up":snakeP2.move("up");break;
            case "Down":snakeP2.move("down");break;
            case "Right":snakeP2.move("right");break;
            case "Left":snakeP2.move("left");break;
        	}
        	currentMoveDirection2[0]=currentMoveDirection2[1];
            
            if (IsGG(snakeP1,snakeP2)) {
            	snakeAlive[0]=false;
            }
            if (IsGG(snakeP2,snakeP1)) {
            	snakeAlive[1]=false;
            }
            for(Bomb b:bomb) {
	            if(IsSnakeTouchBomb(snakeP1,b)) {
	            	PlaySound.playBombSound();
	            	snakeAlive[0]=false;
	            }
	            if(IsSnakeTouchBomb(snakeP2,b)) {
	            	PlaySound.playBombSound();
	            	snakeAlive[1]=false;
	            }
            }
            if(!snakeAlive[0] || !snakeAlive[1]) {
            	showGG();
            }
            
            for(int i=0;i<food.length;i++) {
	            if (IsSnakeTouchFood(snakeP1, food[i])) {
	                snakeP1.addlen();
	                scoreP1++;
	                scoreboardP1.setText("P1 Score : " + scoreP1);
	                while (true) {
	                	food[i].rotate();
	                    Boolean foodCheck=true;
	                    if(IsSnakeTouchFood(snakeP1, food[i])||IsSnakeTouchFood(snakeP2, food[i])) {
	                    	foodCheck=false;
	                    }
	                    for(Bomb b:bomb) {
		                    if(IsBombTouchFood(b,food[i])) {
		                    	foodCheck=false;
		                    }
	                    }
	                    for(int j=0;j<food.length;j++) {
	                    	if(i==j) {
	                    		continue;
	                    	}
	                    	if(IsFoodTouchFood(food[i],food[j])) {
		                    	foodCheck=false;
		                    }
	                    }
	                    
	                    if(foodCheck) {
	                    	break;
	                    }
	                }
	            }
	            
	            if (IsSnakeTouchFood(snakeP2, food[i])) {
	                snakeP2.addlen();
	                scoreP2++;
	               	scoreboardP2.setText("P2 Score : " + scoreP2);
	                
	                while (true) {
	                	food[i].rotate();
	                    Boolean foodCheck=true;
	                    if(IsSnakeTouchFood(snakeP1, food[i])||IsSnakeTouchFood(snakeP2, food[i])) {
	                    	foodCheck=false;
	                    }
	                    for(Bomb b:bomb) {
		                    if(IsBombTouchFood(b,food[i])) {
		                    	foodCheck=false;
		                    }
	                    }
	                    for(int j=0;j<food.length;j++) {
	                    	if(i==j) {
	                    		continue;
	                    	}
	                    	if(IsFoodTouchFood(food[i],food[j])) {
		                    	foodCheck=false;
		                    }
	                    }
	                    
	                    if(foodCheck) {
	                    	break;
	                    }
	                }
	            }
	            
            }
        });
        timelineSnake.getKeyFrames().add(moveFrame);
        timelineSnake.setCycleCount(Timeline.INDEFINITE);
        timelineSnake.play();

        timelineBomb = new Timeline(new KeyFrame(Duration.seconds(speed*5), event ->{
        	for(int i=0;i<bomb.length;i++) {
	        	while(true) {
	        		bomb[i].rotate();
	        		Boolean bombCheck=true;
	        		if(IsBombTouchSnakeNearPosition(bomb[i],snakeP1)||IsSnakeTouchBomb(snakeP1,bomb[i])) {
	        			bombCheck=false;
	        		}
	        		if(IsBombTouchSnakeNearPosition(bomb[i],snakeP2)||IsSnakeTouchBomb(snakeP2,bomb[i])) {
	        			bombCheck=false;
	        		}
	        		for(int j=0;j<bomb.length;j++) {
	        			if(i==j) {
	        				continue;
	        			}
	        			if(IsBombTouchBomb(bomb[i],bomb[j])) {
		        			bombCheck=false;
		        		}
	        		}
	        		for(Food f:food) {
		        		if(IsBombTouchFood(bomb[i],f)) {
		        			bombCheck=false;
		        		}
	        		}
	        		if(bombCheck) {
	        			break;
	        		}
	        	}
        	}
        }));
        timelineBomb.setCycleCount(Timeline.INDEFINITE);
        timelineBomb.play();
        
        timelineTimer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            secondsElapsed++;
            updateTimerLabel();
            if (secondsElapsed % 10 == 0) {
                if (secondsElapsed <= 60) {
                    speed -= 0.05;
                } else {
                    speed *= 0.95;
                }
                timelineSnake.stop();
                timelineSnake.getKeyFrames().clear();

                KeyFrame newMoveFrame = new KeyFrame(Duration.seconds(speed), moveFrame.getOnFinished());
                timelineSnake.getKeyFrames().add(newMoveFrame);
                timelineSnake.play();
            }
        }));
        timelineTimer.setCycleCount(Timeline.INDEFINITE);
        timelineTimer.play();

        EventHandler<KeyEvent> pauseFilter = new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
            	if (event.getCode() == KeyCode.SPACE) {
                    if(firstPauseCheck) {
                        mapPane.getChildren().add(pauseLabel);
                        firstPauseCheck = false;
                    }
                    if (isPaused) {
                        map.setOpacity(0.8);
                        snakeP1.setOpacity(1);
                        snakeP2.setOpacity(1);
                        for(Food f:food) {
                        	f.setOpacity(1);
                        }
                        for(Bomb b:bomb) {
                        	b.setOpacity(1);
                        }
                        timelineSnake.play();
                        timelineTimer.play();
                        timelineBomb.play();
                        pauseLabel.setVisible(false);
                        isPaused = false;
                    } else {
                        map.setOpacity(0.3);
                        snakeP1.setOpacity(0.3);
                        snakeP2.setOpacity(0.3);
                        for(Food f:food) {
                        	f.setOpacity(0.3);
                        }
                        for(Bomb b:bomb) {
                        	b.setOpacity(0.3);
                        }
                        timelineSnake.pause();
                        timelineTimer.pause();
                        timelineBomb.pause();
                        pauseLabel.setVisible(true);
                        isPaused = true;
                    }
                }
            }
        };
        scene.addEventFilter(KeyEvent.KEY_PRESSED, pauseFilter);
	}
}