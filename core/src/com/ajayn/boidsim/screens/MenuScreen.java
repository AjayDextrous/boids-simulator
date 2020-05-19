package com.ajayn.boidsim.screens;

import com.ajayn.boidsim.BoidSimulator2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.HashMap;
import java.util.ResourceBundle;

public class MenuScreen implements Screen {
    private final BoidSimulator2 orchestrator;
    private final Stage stage;
    private int boidsCount = 200;

    public MenuScreen(BoidSimulator2 boidSimulator2) {
        System.out.println("### MenuScreen");
        this.orchestrator = boidSimulator2;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void show() {
// Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("skin/clean-crispy/skin/clean-crispy-ui.json"));

        TextButton run_simulation = new TextButton("Run Simulation", skin);
        TextButton exit = new TextButton("Exit", skin);

        final Label current_boid_count = new Label(Integer.toString(boidsCount),skin);
        current_boid_count.setFontScale(2.3f);
        final Slider boid_count_slider = new Slider(10,300,2,false,skin);
        boid_count_slider.setValue(boidsCount);
        boid_count_slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                boidsCount = (int) boid_count_slider.getValue();
                current_boid_count.setText(Integer.toString(boidsCount));
                System.out.println("### Boid Count: "+boidsCount);
            }
        });

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        run_simulation.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                HashMap<String,Object> params = new HashMap<>();
                params.put("BOIDS_COUNT",boidsCount);
                orchestrator.changeScreen(BoidSimulator2.SIMULATION, params);
            }
        });

        table.add(boid_count_slider).fillX().uniformX();
        table.add(current_boid_count).pad(10,10,10,10);
        table.row().pad(50, 0, 10, 0);
        table.add(run_simulation).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(exit).fillX().uniformX();


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
