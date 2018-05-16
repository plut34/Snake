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
public class RegionSlowdown extends Subject{
    
    public RegionSlowdown(){
        subjtx = new Texture("graphic/regionSlowdown.png");
        subject = new Sprite(subjtx);
        spawnSubject = true;
        
        subjectrt = new Rectangle();
        subjectrt.width = 120;
        subjectrt.height = 120;
        edible = true;
        speed=-1;
        increase=0;
        score = 0;
    }
}
