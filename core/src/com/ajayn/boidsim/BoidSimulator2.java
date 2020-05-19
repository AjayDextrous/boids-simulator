package com.ajayn.boidsim;

import com.ajayn.boidsim.screens.LoadingScreen;
import com.ajayn.boidsim.screens.MenuScreen;
import com.ajayn.boidsim.screens.SimulationScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import java.util.HashMap;

public class BoidSimulator2 extends Game {

    public final static int MENU = 0;
    public final static int SIMULATION = 1;

    private LoadingScreen loadingScreen;
    private MenuScreen menuScreen;
    private SimulationScreen simulationScreen;
    private InputProcessor defaultInputProcessor;

    @Override
    public void create() {
        loadingScreen = new LoadingScreen(this);
        setScreen(loadingScreen);
    }

    public void changeScreen(int screen) {
        switch (screen){
            case MENU:
                if(menuScreen == null) menuScreen = new MenuScreen(this); // added (this)
                this.setScreen(menuScreen);
                break;
            case SIMULATION:
                if(simulationScreen == null) simulationScreen = new SimulationScreen(this, 20); // added (this)
                this.setScreen(simulationScreen);
                break;
        }
    }

    public void changeScreen(int screen, HashMap<String, Object> params) {
        switch (screen){
            case MENU:
                if(menuScreen == null) menuScreen = new MenuScreen(this); // added (this)
                this.setScreen(menuScreen);
                break;
            case SIMULATION:
                int boidsCount = 20;
                if(params != null && params.containsKey("BOIDS_COUNT")){
                    boidsCount = (int) params.get("BOIDS_COUNT");
                }
                if(simulationScreen == null) simulationScreen = new SimulationScreen(this, boidsCount); // added (this)

                this.setScreen(simulationScreen);
                break;
        }
    }
}
