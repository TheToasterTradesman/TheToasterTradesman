package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends Game {
    @Override
    public void create() {
        // Set the initial screen to GameScreen
        this.setScreen(new GameScreen());
    }

    @Override
    public void render() {
        // Call super.render() to ensure the current screen's render method is called
        ScreenUtils.clear(Color.BLACK);
        super.render();
    }

    @Override
    public void dispose() {
        // Call super.dispose() to properly dispose of resources
        super.dispose();
    }
}
