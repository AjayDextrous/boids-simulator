package com.ajayn.boidsim;

import com.ajayn.boidsim.screens.SimulationScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Boid {

    public Body body;

    public int pos_x, pos_y, direction;
    public static Texture sprite = new Texture("boid.png");
    public static int boid_w = 64, boid_h = 64;
    public static float boid_v = 0.75f;

    public static Boid from(World world){
        Boid boid = new Boid();
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(boid.pos_x,boid.pos_y);

        boid.body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f,0.5f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        boid.body.createFixture(shape, 0.0f);
        shape.dispose();
        float vX,vY;
        vY = (float) (Math.sin(Math.toRadians(boid.direction)) * boid_v);
        vX = (float) (Math.cos(Math.toRadians(boid.direction)) * boid_v);
        boid.body.setLinearVelocity(vX, vY);

        return boid;
    }


    public Boid(){
        pos_x = (int) (Math.random()* SimulationScreen.VP_WIDTH* SimulationScreen.VP_W_FACTOR) - SimulationScreen.VP_WIDTH * SimulationScreen.VP_W_FACTOR/2;
        pos_y = (int) (Math.random()* SimulationScreen.VP_HEIGHT* SimulationScreen.VP_W_FACTOR) - SimulationScreen.VP_HEIGHT * SimulationScreen.VP_H_FACTOR/2;
        direction = (int) (Math.random()* 360);
    }



//    public void draw(SpriteBatch batch){
//        batch.draw(sprite, pos_x, pos_y, boid_w/2, boid_h/2,boid_w,boid_h,1,1,direction,0,0,640,704, false,false);
//    }
}
