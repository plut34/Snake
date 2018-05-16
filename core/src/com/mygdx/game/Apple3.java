/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Andrey
 */
public class Apple3 extends Subject {
    public Apple3(){
        subjtx = new Texture("graphic/apple3.png");
        subject = new Sprite(subjtx);
        spawnSubject = true;
        
        subjectrt = new Rectangle();
        subjectrt.width = 16;
        subjectrt.height = 16;
        edible = true;
        speed=-1;
        increase=0;
        score = 1;
        
    }
}
