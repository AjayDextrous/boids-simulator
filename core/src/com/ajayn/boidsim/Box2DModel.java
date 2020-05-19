package com.ajayn.boidsim;

import com.ajayn.boidsim.screens.SimulationScreen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

public class Box2DModel {

    public World world;
    private Body bodyd;
    private Body bodys;
    private Body bodyk;

    private ArrayList<Boid> boids = new ArrayList<>();

    public Box2DModel(int boidsCount){
        world = new World(new Vector2(0,-10f), true);

//        createFloor();
//        createObject();
//        createMovingObject();

        for (int i = 0; i < boidsCount; i++) {
            boids.add(Boid.from(world));
        }
    }

    public void logicStep(float delta){
        Vector2 v1,v2,v3;
        for (Boid b : boids){
            v1 = rule1(b);
//            v2 = Vector2.Zero;
//            v3 = Vector2.Zero;
            v2 = rule2(b);
            v3 = rule3(b);
            Vector2 v = b.body.getLinearVelocity();
            b.body.setLinearVelocity(v.add(v1).add(v2).add(v3));

        }
        float v_average = 0;
        for (Boid b : boids){
            v_average += b.body.getLinearVelocity().len();
        }
        v_average /= boids.size();
        System.out.println("### Boid Average Velocity: "+v_average);
        world.step(delta , 3, 3);
    }

    private Vector2 rule3(Boid b) {
        Vector2 v = Vector2.Zero;
        for(Boid b_i : boids){
            if(b_i != b && b.body.getPosition().sub(b_i.body.getPosition()).len() < SimulationScreen.ALIGNMENT_RADIUS){
                v = v.add(b_i.body.getLinearVelocity());
            }
        }
        v = v.scl(1f/(boids.size()-1));
        v = v.scl(SimulationScreen.DELTA_FACTOR_R3);
        return v;
    }

    private Vector2 rule2(Boid b) {
        Vector2 v = Vector2.Zero;
        for(Boid b_i : boids){
            if(b_i != b && b.body.getPosition().sub(b_i.body.getPosition()).len() < SimulationScreen.SEPARATION_RADIUS){
                v = v.sub(b.body.getPosition().sub(b_i.body.getPosition()));
            }
        }
        v = v.scl(SimulationScreen.DELTA_FACTOR_R2);
        return v;
    }

    private Vector2 rule1(Boid b) {
        Vector2 v = Vector2.Zero;
        for(Boid b_i : boids){
            if(b_i != b){
                v = v.add(b_i.body.getPosition());
            }
        }
        v = v.scl(1f/(boids.size()-1)).sub(b.body.getPosition());
        v = v.scl(SimulationScreen.DELTA_FACTOR_R1);
        return v;
    }

    private void createObject(){

        //create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0,0);


        // add it to the world
        bodyd = world.createBody(bodyDef);

        // set the shape (here we use a box 1 meter wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        // set the properties of the object ( shape, weight, restitution(bouncyness)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        // create the physical object in our body)
        // without this our body would just be data in the world
        bodyd.createFixture(shape, 0.0f);

        // we no longer use the shape object here so dispose of it.
        shape.dispose();
    }

    private void createFloor() {
        // create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, -10);
        // add it to the world
        bodys = world.createBody(bodyDef);
        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50, 1);
        // create the physical object in our body)
        // without this our body would just be data in the world
        bodys.createFixture(shape, 0.0f);
        // we no longer use the shape object here so dispose of it.
        shape.dispose();
    }

    private void createMovingObject(){

        //create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(0,-12);


        // add it to the world
        bodyk = world.createBody(bodyDef);

        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        // set the properties of the object ( shape, weight, restitution(bouncyness)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        // create the physical object in our body)
        // without this our body would just be data in the world
        bodyk.createFixture(shape, 0.0f);

        // we no longer use the shape object here so dispose of it.
        shape.dispose();

        bodyk.setLinearVelocity(0.1f, 0.75f);
    }

//    private void createBoid(){
//
//        //create a new body definition (type and location)
//        BodyDef bodyDef = new BodyDef();
//        bodyDef.type = BodyDef.BodyType.KinematicBody;
//        bodyDef.position.set(0,-12);
//
//
//        // add it to the world
//        bodyk = world.createBody(bodyDef);
//
//        // set the shape (here we use a box 50 meters wide, 1 meter tall )
//        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(1,1);
//
//        // set the properties of the object ( shape, weight, restitution(bouncyness)
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.shape = shape;
//        fixtureDef.density = 1f;
//
//        // create the physical object in our body)
//        // without this our body would just be data in the world
//        bodyk.createFixture(shape, 0.0f);
//
//        // we no longer use the shape object here so dispose of it.
//        shape.dispose();
//
//        bodyk.setLinearVelocity(0.1f, 0.75f);
//    }
}
