/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayList;

/**
 *
 * @author Andrey
 */
public class Snake {

    //Размер
    public int snakeSize; 
    //Здоровье змейки
    public int health = 1; 
    //Скорость змейки, -1 - медлено, 0 - средне, 1 - быстро
    public int speed = 0; 
    //0 - змейка красная, 1 - змейка зеленая
    public int color_type=0; 
    //Спрайты
    public Array<Sprite> snake_body, snake_body1, snake_body2; 
    //Прямоугольники
    public Array<Rectangle> snake_bodyrt;
    public Rectangle snake_headrt;
    
    //Текстуры
    public Texture snake_headtx, snake_bodytx, snake_headtx2, snake_bodytx2;
    
    //Смена направления
    public int moveDirection;
    //Возможнность изменить длину. 1 - увеличивает, 0 - не изменяется, -1 - уменьшается
    public int canAddBody = 0;
    //Можно переместить
    public boolean canMove = true;
    private int keyDelay;
    //Последние координаты
    public ArrayList<Float> lastX;
    public ArrayList<Float> lastY;
    //Рамки
    private int frames;

    GameModel game;

    Subject apple1, apple2, apple3, stump, regionAcceleration, regionSlowdown, pear ;

    public Snake(GameModel game) {
        this.game = game;

    }
    
    //Начальные координаты
    public void startCoordinate() {
        lastX = new ArrayList<Float>();
        lastX.add((float) 100);
        lastY = new ArrayList<Float>();
        lastY.add((float) 100);
    }

    //Спрайты змейки
    public void snakeSprites() {
        
        //Тело змейки
        snake_body = new Array<Sprite>();
        
        //Красные текстуры змейки
        snake_headtx = new Texture("graphic/snake_head.png");
        snake_bodytx = new Texture("graphic/snake_body.png");
        
        //Зеленые текстуры змейки
        snake_headtx2 = new Texture("graphic/snake_head1.png");
        snake_bodytx2 = new Texture("graphic/snake_body1.png");
        
        //Изначально змейка красная
        snake_body.add(new Sprite(snake_headtx));//HEAD
        snake_body.add(new Sprite(snake_bodytx)); //FIRST BODY
        snake_body.add(new Sprite(snake_bodytx));
        snake_body.add(new Sprite(snake_bodytx));
        
     
    }
    
    
    //Изменение цвета змейки
    public void change_snake_color(){
        //Если змейка красная
        if(color_type==0){
            
            snake_body.get(0).setTexture(snake_headtx2);
            
            for(int i=1; i<snake_body.size; i++)
                snake_body.get(i).setTexture(snake_bodytx2);
                
            color_type=1;
        }else{
            
            snake_body.get(0).setTexture(snake_headtx);
            
            for(int i=1; i<snake_body.size; i++)
                snake_body.get(i).setTexture(snake_bodytx);
            
            color_type=0;
        }
    }

    //Прямоугольник тела змейки
    public void snakeBodyRectangle() {
        snake_bodyrt = new Array<Rectangle>();
        snake_bodyrt.add(new Rectangle());
        snake_bodyrt.add(new Rectangle());
    }

    //Прямоугольник головы змейки
    public void snakeHeadRectangle() {
        snake_headrt = new Rectangle();
        snake_headrt.width = 25;
        snake_headrt.height = 25;
    }
    
    //Пересечение головы с телом
    public void collisionWithBody() {
        for (int i = 1; i < snake_body.size; i++) {
            if (snake_bodyrt.get(i).overlaps(snake_headrt)) {
                health--;
            }
        }
    }

    
    public void passSubjects(Subject apple1, Subject apple2, Subject apple3, Subject stump, Subject regionAcceleration, Subject regionSlowdown, Subject pear) {
        this.apple1 = apple1;
        this.apple2 = apple2;
        this.apple3 = apple3;
        this.stump = stump;
        this.regionAcceleration = regionAcceleration;
        this.regionSlowdown = regionSlowdown;
        this.pear = pear;
    }
    
    public void bodyRectangle() {

        for (int i = 1; i < snake_body.size; i++) {
            snake_bodyrt.add(new Rectangle());
        }

        for (int i = 3; i < snake_body.size; i++) {
            snake_bodyrt.get(i).setX(snake_body.get(i).getX());
            snake_bodyrt.get(i).setY(snake_body.get(i).getY());
            snake_bodyrt.get(i).setWidth(1);
            snake_bodyrt.get(i).setHeight(1);
        }

    }

    //Пересечение с рамками
    public void collisionWithEdges() {
        if (snake_body.get(0).getX() <= -1) {
            snake_body.get(0).setPosition(1249, lastY.get(lastY.size()-1));
           // moveDirection = 0;
           // health--;
        }
        if ( snake_body.get(0).getX() >= 1250) {
            snake_body.get(0).setPosition(0, lastY.get(lastY.size()-1));
        }
        if (snake_body.get(0).getY() <= -1) {
            snake_body.get(0).setPosition(lastX.get(lastX.size()-1), 729);
           // moveDirection = 0;
            //health--;
        }
        if (snake_body.get(0).getY() >= 730) {
            snake_body.get(0).setPosition(lastX.get(lastX.size()-1), 0);
        }
    }

    //Пересечение с объектами
    public void collisionWithSubjects() {
       
        if (snake_headrt.overlaps(apple1.subjectrt)) {

            if (apple1.edible == true) {
                game.sc.setSc(game.sc.getSc() + apple1.score);
                apple1.spawnSubject = true;
                if(apple1.increase==1){
                    canAddBody = 1;
                }
                if(apple1.increase==-1){
                    canAddBody = -1;
                }
                if(apple1.speed == 0)
                {
                    speed+=apple1.speed;
                }
                if(apple1.speed == -1)
                {
                    if(speed<=-1)
                        speed = -1;
                    else
                        speed--;
                }
                if(apple1.speed == 1)
                {
                    if(speed>=1)
                        speed=1;
                    else
                        speed++;
                }
                
            } else {
                health--;
            }
            change_snake_color();

        }
        if (snake_headrt.overlaps(apple2.subjectrt)) {
            if (apple2.edible == true) {
                game.sc.setSc(game.sc.getSc() + apple2.score);
                apple2.spawnSubject = true;
                if(apple2.increase==1){
                    canAddBody = 1;
                }
                if(apple2.increase==-1){
                    canAddBody = -1;
                }
                if(apple2.speed == 0)
                {
                    speed+=apple2.speed;
                }
                if(apple2.speed == -1)
                {
                    if(speed<=-1)
                        speed = -1;
                    else
                        speed--;
                }
                if(apple2.speed == 1)
                {
                    if(speed>=1)
                        speed=1;
                    else
                        speed++;
                }
            } else {
                health--;
            }
            change_snake_color();
        }
        if (snake_headrt.overlaps(apple3.subjectrt)) {
            if (apple3.edible == true) {
                game.sc.setSc(game.sc.getSc() + apple3.score);
                apple3.spawnSubject = true;
                if(apple3.increase==1){
                    canAddBody = 1;
                }
                if(apple3.increase==-1){
                    canAddBody = -1;
                }
                if(apple3.speed == 0)
                {
                    speed+=apple3.speed;
                }
                if(apple3.speed == -1)
                {
                    if(speed<=-1)
                        speed = -1;
                    else
                        speed--;
                }
                if(apple3.speed == 1)
                {
                    if(speed>=1)
                        speed=1;
                    else
                        speed++;
                }
            } else {
                health--;
            }
            change_snake_color();
        }

        if (snake_headrt.overlaps(stump.subjectrt)) {
            if (stump.edible == true) {
                game.sc.setSc(game.sc.getSc() + stump.score);
                stump.spawnSubject = true;
                if(stump.increase==1){
                    canAddBody = 1;
                }
                if(stump.increase==-1){
                    canAddBody = -1;
                }
                if(stump.speed == 0)
                {
                    speed+=stump.speed;
                }
                if(stump.speed == -1)
                {
                    if(speed<=-1)
                        speed = -1;
                    else
                        speed--;
                }
                if(stump.speed == 1)
                {
                    if(speed>=1)
                        speed=1;
                    else
                        speed++;
                }
            } else {
                health--;
            }
            change_snake_color();
        }
        if (snake_headrt.overlaps(regionAcceleration.subjectrt)) {
            if (regionAcceleration.edible == true) {
                game.sc.setSc(game.sc.getSc() + regionAcceleration.score);
               // regionAcceleration.spawnSubject = true;
                if(regionAcceleration.increase==1){
                    canAddBody = 1;
                }
                if(regionAcceleration.increase==-1){
                    canAddBody = -1;
                }
                if(regionAcceleration.speed == 0)
                {
                    speed+=regionAcceleration.speed;
                }
                if(regionAcceleration.speed == -1)
                {
                    if(speed<=-1)
                        speed = -1;
                    else
                        speed--;
                }
                if(regionAcceleration.speed == 1)
                {
                    if(speed>=1)
                        speed=1;
                    else
                        speed++;
                }
            } else {
                health--;
            }
            
        }
        
        if (snake_headrt.overlaps(regionSlowdown.subjectrt)) {
            if (regionSlowdown.edible == true) {
                game.sc.setSc(game.sc.getSc() + regionSlowdown.score);
            //    regionSlowdown.spawnSubject = true;
                if(regionSlowdown.increase==1){
                    canAddBody = 1;
                }
                if(regionSlowdown.increase==-1){
                    canAddBody = -1;
                }
                if(regionSlowdown.speed == 0)
                {
                    speed+=regionSlowdown.speed;
                }
                if(regionSlowdown.speed == -1)
                {
                    if(speed<=-1)
                        speed = -1;
                    else
                        speed--;
                }
                if(regionSlowdown.speed == 1)
                {
                    if(speed>=1)
                        speed=1;
                    else
                        speed++;
                }
            } else {
                health--;
            }
        }
        
        if (snake_headrt.overlaps(pear.subjectrt)) {

            if (pear.edible == true) {
                game.sc.setSc(game.sc.getSc() + pear.score);
                pear.spawnSubject = true;
                if(pear.increase==1){
                    canAddBody = 1;
                }
                if(pear.increase==-1){
                    canAddBody = -1;
                }
                if(pear.speed == 0)
                {
                    speed+=pear.speed;
                }
                if(pear.speed == -1)
                {
                    if(speed<=-1)
                        speed = -1;
                    else
                        speed--;
                }
                if(pear.speed == 1)
                {
                    if(speed>=1)
                        speed=1;
                    else
                        speed++;
                }
                
            } else {
                health--;
            }
            change_snake_color();

        }
        
    }

    public void newBody() {
        
        //Если тело увеличивается
        if (canAddBody == 1) {
            //Если змейка была зеленая
            if(color_type==0)
                //Добавляем красный цвет
                snake_body.add(new Sprite(snake_bodytx));
            //Иначе
            else
                //Добавляем зеленый цвет
                snake_body.add(new Sprite(snake_bodytx2));
            canAddBody = 0;
        } 
        //Если тело уменьшается
        if(canAddBody == -1){
            if(snake_body.size>1){
                snake_body.removeIndex(snake_body.size-1);
            }
            canAddBody = 0;
        }
    }

    //Отрисовка тела
    public void drawBody() {

        for (int i = 0; i < snake_body.size; i++) {
            snake_body.get(i).draw(game.batch);
        }

        newBody();
    }
    
    //Нажата клавиша
    public void isPressed() {
        keyDelay++;
        if (keyDelay >= 5) {
            canMove = true;
            keyDelay = 0;
        }
        if (canMove == true) {

            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                if (moveDirection == 1) {
                } else {
                    moveDirection = 0;
                    canMove = false;
                    keyDelay = 0;
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
                if (moveDirection == 0) {
                } else {
                    moveDirection = 1;
                    canMove = false;
                    keyDelay = 0;
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                if (moveDirection == 3) {
                } else {
                    moveDirection = 2;
                    canMove = false;
                    keyDelay = 0;
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                if (moveDirection == 2) {
                } else {
                    moveDirection = 3;
                    canMove = false;
                    keyDelay = 0;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                game.setScreen(new MainMenu(game));
            }
        }

    }

    //Перемещение головы
    public void moveHead() {
        //Вверх        
        if (moveDirection == 0) {
            //Медленная скорость
            if(speed==-1){
            snake_body.get(0).setPosition(snake_body.get(0).getX(), snake_body.get(0).getY() + 5);
            snake_body.get(0).setRotation(0);
            }
            //Средняя скорость
            if(speed==0){
                snake_body.get(0).setPosition(snake_body.get(0).getX(), snake_body.get(0).getY() + 10);
                 snake_body.get(0).setRotation(0);
            }
            //Быстрая скорость
            if(speed==1){
            snake_body.get(0).setPosition(snake_body.get(0).getX(), snake_body.get(0).getY() + 15);
            snake_body.get(0).setRotation(0);
            }
        }
        
        //Вниз
        if (moveDirection == 1) {
            //Низкая скорость
            if(speed==-1){
            snake_body.get(0).setPosition(snake_body.get(0).getX(), snake_body.get(0).getY() - 5);
            snake_body.get(0).setRotation(180);
            }
            //Средняя скоррость
            if(speed==0){
            snake_body.get(0).setPosition(snake_body.get(0).getX(), snake_body.get(0).getY() - 10);
            snake_body.get(0).setRotation(180);
            }
            //Быстрая скорость
            if(speed==1){
            snake_body.get(0).setPosition(snake_body.get(0).getX(), snake_body.get(0).getY() - 15);
            snake_body.get(0).setRotation(180);
            }
        }
        //Влево
        if (moveDirection == 2) {
            //Низкая скорость
            if(speed==-1){
            snake_body.get(0).setPosition(snake_body.get(0).getX() - 5, snake_body.get(0).getY());
            snake_body.get(0).setRotation(90);
            }
            //Средняя скорость
            if(speed==0){
            snake_body.get(0).setPosition(snake_body.get(0).getX() - 10, snake_body.get(0).getY());
            snake_body.get(0).setRotation(90);
            }
            //Высокая скорость
            if(speed==1){
            snake_body.get(0).setPosition(snake_body.get(0).getX() - 15, snake_body.get(0).getY());
            snake_body.get(0).setRotation(90);
            }
            
        }
        //Вправо
        if (moveDirection == 3) {
            //Низкая скорость
            if(speed==-1){
            snake_body.get(0).setPosition(snake_body.get(0).getX() + 5, snake_body.get(0).getY());
            snake_body.get(0).setRotation(-90);
            }
            //Средняя скорость
            if(speed==0){
            snake_body.get(0).setPosition(snake_body.get(0).getX() + 10, snake_body.get(0).getY());
            snake_body.get(0).setRotation(-90);
            }
            //Высокая скорость
            if(speed==1){
            snake_body.get(0).setPosition(snake_body.get(0).getX() + 15, snake_body.get(0).getY());
            snake_body.get(0).setRotation(-90);
            }
        }
    }

    //Перемещение туловища
    public void moveBody() {

        for (int i = 1; i < snake_body.size && i < lastX.size(); i++) {
           snake_body.get(i).setPosition(lastX.get(lastX.size() - i), lastY.get(lastY.size() - i));
          
        }
    }

    public void frameTimer() {
        if(speed ==-1)
            frames += 1;
        if(speed ==0)
            frames += 2;
        if(speed ==1)
            frames += 3;
        
        if (frames >= 3) {
            lastX.add(snake_body.get(0).getX());
            lastY.add(snake_body.get(0).getY());
            frames = 0;
        }
    }
    
    //Смерть
    public boolean die() {
        if (health <= 0) {
            return true;
        } else {
            return false;
        }
    }
}
