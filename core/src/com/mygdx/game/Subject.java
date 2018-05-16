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
abstract  public class Subject {
    
    protected Texture subjtx;
    protected Sprite subject; 
    
    protected boolean spawnSubject;
    protected Rectangle subjectrt;
    
    protected boolean edible;
    protected int speed; //0 - изменений нет, -1 - уменьшение, 1 - увеличение
    protected int increase; //0 - изменений нет, -1 - уменьшение, 1 - увеличение
    protected int score;
     
    
}
