package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameModel extends Game {

    public final int WIDTH = 1366;
    public final int HEIGHT = 768;

    SpriteBatch batch;
    public Score sc;
    
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new MainMenu(this));
    }

    @Override
    public void render() {
        super.render();
    }
    
    
    

}
