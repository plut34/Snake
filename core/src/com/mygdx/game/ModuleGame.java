/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import java.util.Random;
import javax.swing.Timer;
/**
 *
 * @author Dmitriy
 */
public class ModuleGame implements Module{

    GameField gm;
    int keyDelay;
    @Override
    public void load(GameField gm, Module batch) {
        this.gm = gm;
        this.gm.setModule(batch);
    }

    @Override
    public int run() {
       Random rnd = new Random();
       int key = rnd.nextInt(4); //0-up, 1-down, 2-left, 3-right
        
        keyDelay++;
        if (keyDelay >= 5) {
            gm.snake.canMove = true;
            keyDelay = 0;
        }
        if (gm.snake.canMove == true) {

            if (key==0) {
                if (gm.snake.moveDirection == 1) {
                } else {
                    gm.snake.moveDirection = 0;
                    gm.snake.canMove = false;
                    keyDelay = 0;
                }
            }
            //вниз
            if (key==1) {
                if (gm.snake.moveDirection == 0) {
                } else {
                    gm.snake.moveDirection = 1;
                    gm.snake.canMove = false;
                    keyDelay = 0;
                }
            }
            //Влево
            if (key==2) {
                if (gm.snake.moveDirection == 3) {
                } else {
                    gm.snake.moveDirection = 2;
                    gm.snake.canMove = false;
                    keyDelay = 0;
                }
            }
            //Вправо
            if (key==3) {
                if (gm.snake.moveDirection == 2) {
                } else {
                    gm.snake.moveDirection = 3;
                    gm.snake.canMove = false;
                    keyDelay = 0;
                }
            }
            
        }
        return 0;
    }
   

    @Override
    public void unload() {
        System.out.println("unload");
    }
   
}
