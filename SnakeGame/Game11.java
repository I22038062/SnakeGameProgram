package SnakeGame;

import java.io.File;
import java.io.PrintWriter;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game11 {
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
					if(head[1] < 0)
						head[1] = block - 1;
					break;
				case "down":
					head[1]++;
					if(head[1] >= block)
						head[1] = 0;
					break;
				case "left":
					head[0]--;
					if(head[0] < 0)
						head[0] = block - 1;
					break;
				case "right":
					head[0]++;
					if(head[0] >= block)
						head[0] = 0;
					break;
				default:
					break;
			}
			position.add(head);
			position.remove(0); // tail
			add();
		}
	
		Snake(){
			int[] toadd={0,0};
			position.add(toadd);
			int[] toadd2={1,0};
			position.add(toadd2);
			int[] toadd3={2,0};
			position.add(toadd3);
		}
		
		private void add() {
			double squaresize= Math.min(getHeight(),getWidth())*0.8 / block;
			double centerX = getWidth() / 2;
	        double centerY = getHeight() / 2;
	        getChildren().clear();
	        for(int i=0;i<position.size()-1;i++) {
	        	if ((position.get(i)[0] == (block - 1) && position.get(i+1)[0] == 0)
	        			||(position.get(i)[1] == (block - 1) && position.get(i+1)[1] == 0)
	        			||(position.get(i+1)[0] == (block - 1) && position.get(i)[0] == 0)
	        			||(position.get(i+1)[1] == (block - 1) && position.get(i)[1] == 0))
	        		continue;
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
		
		Food(){
			position[0]=block/2;
			position[1]=block/2;
		}
		
		public void rotate(Snake snake,Bomb bomb) {
			int[][] near_position = {{1,0},{-1,0},{0,1},{0,-1}};
			int[] head= {snake.getPosition().get(snake.getPosition().size()-1)[0],snake.getPosition().get(snake.getPosition().size()-1)[1]};
			Random random = new Random();
			int position_x = random.nextInt(block);
			int position_y = random.nextInt(block);
			while(true) {
				boolean food_judge=true;
				for(int[] body:snake.getPosition() ) {
					if(body[0]==position_x && body[1]==position_y) {
						food_judge=false;
						break;
					}
				}
				for(int[] bomb_position:bomb.getPosition()) {					
					if(bomb_position[0]==position_x && bomb_position[1]==position_y) {
						food_judge=false;
					}
				}
				for(int[] near:near_position) {
					if(head[0]+near[0]==position_x && head[1]+near[1]==position_y) {
						food_judge=false;
					}
				}
				if(!food_judge) {
					position_x = random.nextInt(block);
					position_y = random.nextInt(block);
					continue;
				}
				else {
					break;
				}
			}
			position[0]=position_x;
			position[1]=position_y;
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
//		private int[] position=new int[2];
		private ArrayList<int[]> position_arr = new ArrayList<>();
		
		public ArrayList<int[]> getPosition(){
			return position_arr;
		}
		
		Bomb(){
			int[][] temarr = {{block/3,block/6},{block/3,block/2+6},{block/5,block/6+5},{block/4,block/7+2},{block/6,block/3+5}};
//			position[0]=block/3;
//			position[1]=block-6;
			for(int[] tem:temarr) {
				position_arr.add(tem);
			}
		}
		
		public void rotate(Snake snake,Food food) {
			int[][] near_position = {{1,0},{-1,0},{0,1},{0,-1}};
			int[] head= {snake.getPosition().get(snake.getPosition().size()-1)[0],snake.getPosition().get(snake.getPosition().size()-1)[1]};
			position_arr.clear();
			Random random = new Random();
			for(int i=0;i<5;i++) {			
				int position_x = random.nextInt(block);
				int position_y = random.nextInt(block);
				while(true) {
					boolean bomb_judge=true;
					for(int[] body:snake.getPosition() ) {
						if(body[0]==position_x && body[1]==position_y) {
							bomb_judge=false;
							break;
						}
					}
					if(food.getPosition()[0]==position_x && food.getPosition()[1]==position_y) {
						bomb_judge=false;
					}
					for(int[] near:near_position) {
						if(head[0]+near[0]==position_x && head[1]+near[1]==position_y) {
							bomb_judge=false;
						}
					}
					if(!bomb_judge) {
						position_x = random.nextInt(block);
						position_y = random.nextInt(block);
						continue;
					}
					else {
						break;
					}
				}
				int[] tem= {position_x,position_y};
				position_arr.add(tem);
			}
		}
		
		private void add() {
			double squaresize= Math.min(getHeight(),getWidth())*0.8 / block;
			double centerX = getWidth() / 2;
	        double centerY = getHeight() / 2;
	        getChildren().clear();
	        for(int[] position:position_arr) {   	
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
	
	private boolean IsSnakeTouchBomb(Snake snake,Bomb bomb) {
		for(int[] body:snake.getPosition()) {
			for(int[] bomb_position:bomb.getPosition()) {
				if(bomb_position[0]==body[0] && bomb_position[1]==body[1]) {
					PlaySound.playBombSound();
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean IsGG(Snake snake) {
		int[] head=snake.getPosition().get(snake.getPosition().size()-1);
		if(head[0]<0 || head[0]>=block || head[1]<0 || head[1]>=block) {
			PlaySound.playHitWallSound();
			return true;
		}
		for(int i=0;i<snake.getPosition().size()-1;i++) {
			int[] body=snake.getPosition().get(i);
			if(head[0]==body[0] && head[1]==body[1]){
				PlaySound.playHitBodySound();
				return true;
			}
		}
		return false;
	}
	
	private final int block=20;
	private int score=0;
	private Label scoreboard=new Label();
	private int highscore=0;
	private Label highscoreboard=new Label();
	private MapUI map=new MapUI();
	private Snake snake=new Snake();
	private Food food=new Food();
	private Bomb bomb=new Bomb();
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
    private static final String fileName = "SnakeGameResources/Record/Game11.txt";
    private VBox rightPane=new VBox();
	Game11(SettingsData settDataInput) {
		try { // 讀檔
    		File Record=new File(fileName);
			Scanner scanner=new Scanner(Record);
			String str=scanner.nextLine();
			highscore=Integer.parseInt(str);
			scanner.close();
		}catch(Exception e1) { // 讀檔有問題
			try { // 重新寫一個檔案為0
				PrintWriter writefile=new PrintWriter(fileName);
				writefile.println("0");
				writefile.close();
			}catch(Exception e2) {
				System.out.println(e2.getMessage());
			}
			highscore=0;
		}
		
		settData=settDataInput;
		snake.setColor(settData.getbodyColor(), settData.getheadColor());
		map.setColor(settData.getmapColor1(),settData.getmapColor2());
		food.setfoodID(settData.getfoodID());
		
		score=0;
		scoreboard.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,30));
		scoreboard.setTextFill(Color.DARKRED);
		scoreboard.setText("Score : "+score);
		scoreboard.setAlignment(Pos.TOP_LEFT);
		
		highscoreboard.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,30));
		highscoreboard.setTextFill(Color.DARKRED);
		highscoreboard.setText("High Score : "+highscore);
		highscoreboard.setAlignment(Pos.TOP_CENTER);
		
		timerLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
        timerLabel.setTextFill(Color.DARKRED);
        timerLabel.setAlignment(Pos.TOP_RIGHT);
        updateTimerLabel();
		
		TouchToStart.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,30));
		TouchToStart.setTextFill(Color.RED);
		
		pauseLabel.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,30));
		
		mapPane.getChildren().add(map);
		mapPane.getChildren().add(food);
		mapPane.getChildren().add(snake);
		mapPane.getChildren().add(bomb);
		mapPane.getChildren().add(TouchToStart);
		mapPane.setPrefWidth(snake.getHeight());
		
		rightPane = new VBox(10,timerLabel,scoreboard,highscoreboard);
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
		try { // GG 時更新最高紀錄
			PrintWriter writefile=new PrintWriter(fileName);
			writefile.println(highscore);
			writefile.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		PlaySound.musicPlayer.stop();
		mapPane.setVisible(false);
		Label lab1=new Label("GAME");
		Label lab2=new Label("OVER");
		lab1.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,50));
		lab2.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,50));
		
		VBox GG=new VBox(10,lab1,lab2,timerLabel,scoreboard,highscoreboard);
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
			Game11 Newgm=new Game11(settData);
			Newgm.show();
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
		snake.setOpacity(0.3);
		food.setOpacity(0.3);
		bomb.setOpacity(0.3);
		
		stage.setTitle("Game11");
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();
		
		pane.setOnMouseClicked(e->{
			if (startGameCheck == false){
				map.setOpacity(1);
				snake.setOpacity(1);
				food.setOpacity(1);
				bomb.setOpacity(1);
				TouchToStart.setVisible(false);
                startGame();
                PlaySound.playMusic();
                startGameCheck = true;
            }
		});
	}
	
	private void startGame() {
		final String[] currentMoveDirection = {"Right","Right"};
		EventHandler<KeyEvent> eventfilter = new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (!isPaused){
                    switch(currentMoveDirection[0]) {
                    case "Up":
                        switch(event.getCode()) {
                        case UP:
                        case W:
                            currentMoveDirection[1] = "Up";
                            break;
                        case RIGHT:
                        case D:
                            currentMoveDirection[1] = "Right";
                            break;
                        case LEFT:
                        case A:
                            currentMoveDirection[1] = "Left";
                            break;
                        default:
                            break;
                        }
                        break;
                    case "Down":                    	
                        switch(event.getCode()) {
                        case DOWN:
                        case S:
                            currentMoveDirection[1] = "Down";
                            break;
                        case RIGHT:
                        case D:
                            currentMoveDirection[1] = "Right";
                            break;
                        case LEFT:
                        case A:
                            currentMoveDirection[1] = "Left";
                            break;
                        default:
                            break;
                        }
                        break;
                    case "Right":
                    	switch(event.getCode()) {
                        case UP:
                        case W:
                            currentMoveDirection[1] = "Up";
                            break;
                        case DOWN:
                        case S:
                            currentMoveDirection[1] = "Down";
                            break;
                        case RIGHT:
                        case D:
                            currentMoveDirection[1] = "Right";
                            break;
                        default:
                            break;
                        }
                        break;
                    case "Left":
                        switch(event.getCode()) {
                        case UP:
                        case W:
                            currentMoveDirection[1] = "Up";
                            break;
                        case DOWN:
                        case S:
                            currentMoveDirection[1] = "Down";
                            break;
                        case LEFT:
                        case A:
                            currentMoveDirection[1] = "Left";
                            break;
                        default:
                            break;
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
        	switch (currentMoveDirection[1]) {
            case "Up":
                snake.move("up");
                break;
            case "Down":
                snake.move("down");
                break;
            case "Right":
                snake.move("right");
                break;
            case "Left":
                snake.move("left");
                break;
            }
            currentMoveDirection[0]=currentMoveDirection[1];
            if (IsGG(snake)) {
                showGG();
            }
            if(IsSnakeTouchBomb(snake,bomb)) {
            	showGG();
            }
            if (IsSnakeTouchFood(snake, food)) {
                snake.addlen();
                score++;
                scoreboard.setText("Score : " + score);
                if(score>highscore) {
                	highscore++;
                	highscoreboard.setText("High Score : " + highscore);
                	highscoreboard.setTextFill(Color.RED);
                }
                while (IsSnakeTouchFood(snake, food)) {
                    food.rotate(snake,bomb);
                }
            }
        });
        timelineSnake.getKeyFrames().add(moveFrame);
        timelineSnake.setCycleCount(Timeline.INDEFINITE);
        timelineSnake.play();

        timelineBomb = new Timeline(new KeyFrame(Duration.seconds(speed*25), event ->{
        	bomb.rotate(snake,food);
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
                        map.setOpacity(1);
                        snake.setOpacity(1);
                        food.setOpacity(1);
                        bomb.setOpacity(1);
                        timelineSnake.play();
                        timelineTimer.play();
                        timelineBomb.play();
                        pauseLabel.setVisible(false);
                        isPaused = false;
                    } else {
                        map.setOpacity(0.3);
                        snake.setOpacity(0.3);
                        food.setOpacity(0.3);
                        bomb.setOpacity(0.3);
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