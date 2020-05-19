package com.ajayn.boidsim;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import java.util.ArrayList;

public class BoidSimulator extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	int angle = 0;
	int pos_x = 320,pos_y = 320;
	ArrayList<Boid> boids;

	private static final int boid_w = 64, boid_h = 64;
	private int boid_count = 30;
	private Box2DModel model;
	private OrthographicCamera cam;
	private Box2DDebugRenderer debugRenderer;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("boid.png");

		boids = new ArrayList(10);
		for (int i = 0; i < boid_count; i++) {
			boids.add(new Boid());
		}

		model = new Box2DModel(20);
		cam = new OrthographicCamera(32,24);
		debugRenderer = new Box2DDebugRenderer(true,true,true,true,false,true);

	}

	@Override
	public void render () {
//		model.logicStep(delta);
		Gdx.gl.glClearColor(80f/255, 175f/255, 228f/225, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		debugRenderer.render(model.world, cam.combined);
//		batch.begin();
////		batch.draw(img, pos_x, pos_y, boid_w/2, boid_h/2,boid_w,boid_h,1,1,angle,0,0,640,704, false,false);
//		for (Boid b : boids){
//			b.draw(batch);
//		}
//		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		Boid.sprite.dispose();
	}
}
