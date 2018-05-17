package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class GameField implements Screen {

    final float appWidth = 1280;
    final float appHeight = 720;

    private Texture grasstx;
    private Sprite grass;
    private BitmapFont score;
    
    private Preferences pref;

    GameModel game;
    
    public Module mg;
    
    public void setModule(Module module){
        mg = module;
    }
    
    private boolean debugpos = false;

    
    private float width = Gdx.graphics.getWidth();
    private float height = Gdx.graphics.getHeight();

    public GameField(GameModel game) {
        this.game = game;
    }

    Snake snake;
    Apple1 apple1;
    Apple2 apple2;
    Apple3 apple3;
    Stump stump;
    RegionAcceleration regionAcceleration;
    RegionSlowdown regionSlowdown;
    Pear pear;

    @Override
    public void show() {

        pref = Gdx.app.getPreferences("Score");

        game.sc = new Score();
        score = new BitmapFont();
        score.setColor(Color.RED);

        grasstx = new Texture("graphic/grass.png");
        grass = new Sprite(grasstx);

        snake = new Snake(game);
        snake.snakeBodyRectangle();
        snake.startCoordinate();

        apple1 = new Apple1();
        apple2 = new Apple2();
        apple3 = new Apple3();
        stump = new Stump();
        regionAcceleration = new RegionAcceleration();
        regionSlowdown = new RegionSlowdown();
        pear = new Pear();
     
        snake.passSubjects(apple1,apple2,apple3,stump,regionAcceleration, regionSlowdown, pear);
        
        snake.snakeHeadRectangle();

        snake.snakeSprites();

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyPressed(Input.Keys.B)) {
            String[] arr = new String[1];
            arr[0] = "C:\\libGDX\\core\\build\\classes\\main\\com\\mygdx\\game\\";
            ModuleEngine.main(arr, this);
        }
       if(mg!=null)
            mg.run();

        game.batch.begin(); //BEGIN

        drawGrass();
        spawnSubjects();
        drawScore();
        snake.drawBody();
        snake.moveHead();
        snake.moveBody();
        snake.bodyRectangle();
        rectanglesPosition();
        snake.isPressed();
        snake.collisionWithSubjects();
        snake.collisionWithEdges();
        snake.collisionWithBody();
        snake.frameTimer();
        snake.snakeSize = snake.snake_body.size;
        gameOver();
        game.batch.end();

    }

    public void rectanglesPosition() {
        snake.snake_headrt.x = snake.snake_body.get(0).getX();
        snake.snake_headrt.y = snake.snake_body.get(0).getY();

        apple1.subjectrt.x = apple1.subject.getX();
        apple1.subjectrt.y = apple1.subject.getY();

        apple2.subjectrt.x = apple2.subject.getX();
        apple2.subjectrt.y = apple2.subject.getY();

        apple3.subjectrt.x = apple3.subject.getX();
        apple3.subjectrt.y = apple3.subject.getY();
        
        stump.subjectrt.x = stump.subject.getX();
        stump.subjectrt.y = stump.subject.getY();

        regionAcceleration.subjectrt.x = regionAcceleration.subject.getX();
        regionAcceleration.subjectrt.y = regionAcceleration.subject.getY();
        
        regionSlowdown.subjectrt.x = regionSlowdown.subject.getX();
        regionSlowdown.subjectrt.y = regionSlowdown.subject.getY();
        
        pear.subjectrt.x = pear.subject.getX();
        pear.subjectrt.y = pear.subject.getY();
    }

    public void spawnSubjects() {
        Random rnd = new Random();
        int rndx = rnd.nextInt(1250);
        int rndy = rnd.nextInt(700) + 10;

        if (apple1.spawnSubject == true) {
            apple1.spawnSubject = false;
            rndx = rnd.nextInt(1250);
            rndy = rnd.nextInt(710) + 10;
            apple1.subject.setPosition(rndx, rndy);

        }
        if (apple2.spawnSubject == true) {
            apple2.spawnSubject = false;
            rndx = rnd.nextInt(1250);
            rndy = rnd.nextInt(710) + 10;
            apple2.subject.setPosition(rndx, rndy);

        }
        if (apple3.spawnSubject == true) {
            apple3.spawnSubject = false;
            rndx = rnd.nextInt(1250);
            rndy = rnd.nextInt(710) + 10;
            apple3.subject.setPosition(rndx, rndy);
        }
        
        if (stump.spawnSubject == true) {
            stump.spawnSubject = false;
            rndx = rnd.nextInt(1250);
            rndy = rnd.nextInt(710) + 10;
            stump.subject.setPosition(rndx, rndy);
        }
        
        if (regionAcceleration.spawnSubject == true) {
            regionAcceleration.spawnSubject = false;
            rndx = rnd.nextInt(1250);
            rndy = rnd.nextInt(710) + 10;
            regionAcceleration.subject.setPosition(rndx, rndy);
        }
        
        if (regionSlowdown.spawnSubject == true) {
            regionSlowdown.spawnSubject = false;
            rndx = rnd.nextInt(1250);
            rndy = rnd.nextInt(710) + 10;
            regionSlowdown.subject.setPosition(rndx, rndy);
        }
        
        if (pear.spawnSubject == true) {
            pear.spawnSubject = false;
            rndx = rnd.nextInt(1250);
            rndy = rnd.nextInt(710) + 10;
            pear.subject.setPosition(rndx, rndy);

        }
       // Timer timer = new Timer();
       // timer.schedule(new TimerTask() {
        /* @Override
         public void run() {
         // Your database code here
            apple1.subject.draw(game.batch);
         }
        }, 100, 100);*/
        
        int show = rnd.nextInt(10);
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0;
        if(show!=1 && show !=2 && show !=4 ){
        while (elapsedTime < 20) {
            //perform db poll/check
            elapsedTime = (new Date()).getTime() - startTime;
            apple1.subject.draw(game.batch);
        }
        
        }
       
        apple2.subject.draw(game.batch);
        apple3.subject.draw(game.batch);
        stump.subject.draw(game.batch);
        regionAcceleration.subject.draw(game.batch);
        regionSlowdown.subject.draw(game.batch);
        pear.subject.draw(game.batch);
   //     snake.change_snake_color();
      

    }
    
    

     public void gameOver() {
        if (snake.die()) {
            pref.putFloat("score", game.sc.getSc());
            game.setScreen(new GameOverScreen(game));
        }
    }

    public void drawScore() {
        score.draw(game.batch, "Score: " + game.sc.getSc(), appWidth / 2 - 20, 720);
    }


    public void drawGrass() {
        grass.setPosition(0, 0);
        grass.draw(game.batch);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        snake.snake_headtx.dispose();
        apple1.subjtx.dispose();
        apple2.subjtx.dispose();
        apple3.subjtx.dispose();
        stump.subjtx.dispose();
        regionAcceleration.subjtx.dispose();
        regionSlowdown.subjtx.dispose();
        pear.subjtx.dispose();
        grasstx.dispose();
        score.dispose();
    }

}



