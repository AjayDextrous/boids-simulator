package com.ajayn.boidsim.screens;

import com.ajayn.boidsim.BoidSimulator2;
import com.ajayn.boidsim.Box2DModel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class SimulationScreen implements Screen {
    public static final int VP_WIDTH = 32, VP_HEIGHT = 24,
    VP_W_FACTOR = 5, VP_H_FACTOR = 5,
    COHESION_RADIUS = 50, SEPARATION_RADIUS = 1, ALIGNMENT_RADIUS = 10;
    public static final float DELTA_FACTOR_R1 = 0.9f;
    public static final float DELTA_FACTOR_R2 = 20f;
    public static final float DELTA_FACTOR_R3 = 1f/20;
    public static final float DELTA_FACTOR_R4 = 0.5f;

    private final BoidSimulator2 orchestrator;

    private final Box2DModel b2DModel;
    private final OrthographicCamera camera;
    private final Box2DDebugRenderer debugRenderer;

    private int boidsCount = 20;

    public SimulationScreen(BoidSimulator2 boidSimulator2, int boidsCount) {
        this.orchestrator = boidSimulator2;
        this.boidsCount = boidsCount;

        b2DModel = new Box2DModel(boidsCount);
        camera = new OrthographicCamera(VP_WIDTH*VP_W_FACTOR*3,VP_HEIGHT*VP_H_FACTOR*3);
        debugRenderer = new Box2DDebugRenderer(true,true,true,true,false,true);


        System.out.println("### SimulationScreen , bodiCount:  "+boidsCount);
    }



    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean keyDown(int keycode) {
                if(keycode == Input.Keys.ESCAPE){
                    orchestrator.changeScreen(BoidSimulator2.MENU);
                    return true;
                }
                return super.keyDown(keycode);
            }
        });
    }

    @Override
    public void render(float delta) {
        b2DModel.logicStep(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(b2DModel.world, camera.combined);
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
