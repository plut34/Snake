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
public class Pear extends Subject {
    public Pear(){
        subjtx = new Texture("graphic/pear.png");
        subject = new Sprite(subjtx);
        spawnSubject = true;
        
        subjectrt = new Rectangle();
        subjectrt.width = 16;
        subjectrt.height = 16;
        edible = true;
        speed=0;
        increase=-1;
        score = 2;
    }
}
