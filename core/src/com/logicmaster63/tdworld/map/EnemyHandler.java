package com.logicmaster63.tdworld.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionWorld;
import com.logicmaster63.tdworld.enemy.Enemy;
import com.logicmaster63.tdworld.screens.GameScreen;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;

public class EnemyHandler {

    private ArrayList<Enemy> enemies;
    private Vector3 pos;
    private boolean started = false;
    private double prevTime;
    private int enemyIndex;
    private HashMap<String, Class<?>> enemyClasses;
    private HashMap<String, Model> models;
    private ArrayList<Spawn> spawns;
    private ArrayList<Vector3> path;
    private btCollisionWorld world;
    private ArrayList<Integer> ids;

    public EnemyHandler(Vector3 pos, HashMap<String, Class<?>> enemyClasses, ArrayList<Spawn> spawns, HashMap<String, Model> models, ArrayList<Enemy> enemies, ArrayList<Vector3> path, btCollisionWorld world, ArrayList<Integer> ids) {
        this.enemies = enemies;
        this.pos = pos;
        this.enemyClasses = enemyClasses;
        this.models = models;
        this.spawns = spawns;
        this.path = path;
        this.world = world;
        this.ids = ids;
    }

    public EnemyHandler(Vector3 pos, HashMap<String, Class<?>> enemyClasses, ArrayList<Spawn> spawns, HashMap<String, Model> models, ArrayList<Vector3> path, btCollisionWorld world, ArrayList<Integer> ids) {
        this(pos, enemyClasses, spawns, models, new ArrayList<Enemy>(), path, world, ids);
    }

    public void tick(float delta, GameScreen screen) {
        if (!started && screen.isRunning()) {
            started = true;
            prevTime = System.currentTimeMillis();
        }
        if (enemyIndex < spawns.size() && System.currentTimeMillis() > prevTime + spawns.get(enemyIndex).getDelay()) {
            prevTime = System.currentTimeMillis();
            String name = spawns.get(enemyIndex).getName();
            ModelInstance instance = new ModelInstance(models.get(name));
            try {
                Class<?> c = enemyClasses.get(name);
                Constructor constructor = c.getConstructor(Vector3.class, double.class, int.class, int.class, int.class, ModelInstance.class, btCollisionWorld.class, ArrayList.class);
                enemies.add((Enemy) constructor.newInstance(pos, 10d, 10, 500, 0, instance, world, ids));
            } catch (Exception e) {
                Gdx.app.log("Error", e.toString());
            }
            enemyIndex++;
        }
        for (Enemy enemy : enemies)
            enemy.tick(delta, path);
    }

    public void render(float delta, ModelBatch batch) {
        for (Enemy enemy : enemies)
            enemy.render(delta, batch);
    }

    public Enemy add(Enemy enemy) {
        enemies.add(enemy);
        return enemy;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
