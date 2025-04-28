package SnakeGame;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlaySound {
    private static final String BOMB_SOUND_FILE = "SnakeGameResources/bomb.mp3";
    private static final String EAT_FOOD_SOUND_FILE = "SnakeGameResources/eatFood.mp3";
    private static final String MUSIC_FILE = "SnakeGameResources/BGM.mp3";
    private static final String MENU_MUSIC_FILE = "SnakeGameResources/MenuMusic.mp3";
    private static final String HIT_WALL_SOUND_FILE = "SnakeGameResources/HitWall.mp3";
    private static final String HIT_BODY_SOUND_FILE = "SnakeGameResources/HitBody.mp3";
    static MediaPlayer bombPlayer;
    static MediaPlayer eatFoodPlayer;
    static MediaPlayer musicPlayer;
    static MediaPlayer menuMusicPlayer;
    static MediaPlayer hitWallPlayer;
    static MediaPlayer hitBodyPlayer;
    static {
        Media bombSound = new Media(new File(BOMB_SOUND_FILE).toURI().toString());
        bombPlayer = new MediaPlayer(bombSound);
        
        Media eatFoodSound = new Media(new File(EAT_FOOD_SOUND_FILE).toURI().toString());
        eatFoodPlayer = new MediaPlayer(eatFoodSound);
        
        Media music = new Media(new File(MUSIC_FILE).toURI().toString());
        musicPlayer = new MediaPlayer(music);
        
        Media menuMusic = new Media(new File(MENU_MUSIC_FILE).toURI().toString());
        menuMusicPlayer = new MediaPlayer(menuMusic);
        
        Media hitWallSound = new Media(new File(HIT_WALL_SOUND_FILE).toURI().toString());
        hitWallPlayer = new MediaPlayer(hitWallSound);
        
        Media hitBodySound = new Media(new File(HIT_BODY_SOUND_FILE).toURI().toString());
        hitBodyPlayer = new MediaPlayer(hitBodySound);
    }
    public static void playMusic() {
    	musicPlayer.stop();
    	musicPlayer.seek(musicPlayer.getStartTime());
    	musicPlayer.play();
    	musicPlayer.setOnEndOfMedia(() -> {
    		musicPlayer.seek(musicPlayer.getStartTime());
    		musicPlayer.play();
        });
    }
    public static void playMenuMusic() {
    	menuMusicPlayer.stop();
    	menuMusicPlayer.seek(menuMusicPlayer.getStartTime());
    	menuMusicPlayer.play();
    	menuMusicPlayer.setOnEndOfMedia(() -> {
    		menuMusicPlayer.seek(menuMusicPlayer.getStartTime());
    		menuMusicPlayer.play();
        });
    }
    public static void playBombSound() {
        bombPlayer.stop();
        bombPlayer.seek(bombPlayer.getStartTime());
        bombPlayer.play();
    }
    public static void playEatFoodSound() {
        eatFoodPlayer.stop();
        eatFoodPlayer.seek(eatFoodPlayer.getStartTime());
        eatFoodPlayer.play();
    }
    public static void playHitWallSound() {
    	hitWallPlayer.stop();
    	hitWallPlayer.seek(hitWallPlayer.getStartTime());
    	hitWallPlayer.play();
    }
    public static void playHitBodySound() {
    	hitBodyPlayer.stop();
    	hitBodyPlayer.seek(hitBodyPlayer.getStartTime());
    	hitBodyPlayer.play();
    }
}
