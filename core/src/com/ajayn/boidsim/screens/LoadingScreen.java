package com.ajayn.boidsim.screens;

import com.ajayn.boidsim.BoidSimulator2;
import com.badlogic.gdx.Screen;

public class LoadingScreen implements Screen {
    private final BoidSimulator2 orchestrator;

    public LoadingScreen(BoidSimulator2 boidSimulator2) {
        System.out.println("### LoadingScreen");
        this.orchestrator = boidSimulator2;
    }

    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {
        System.out.println("### LoadingScreen.render");
        orchestrator.changeScreen(BoidSimulator2.MENU);
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

    }
}
