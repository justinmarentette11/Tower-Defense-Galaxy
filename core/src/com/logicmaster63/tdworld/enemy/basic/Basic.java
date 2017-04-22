package com.logicmaster63.tdworld.enemy.basic;

import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionWorld;
import com.badlogic.gdx.physics.bullet.collision.btCompoundShape;
import com.logicmaster63.tdworld.enemy.Enemy;
import com.logicmaster63.tdworld.object.Object;

import java.util.List;
import java.util.Map;

public class Basic extends Enemy{

    private static final int HP = 20;
    private static final float COOLDOWN = 1f;
    private static final int RANGE = 3000;
    private static final String ATTACK_ANIMATION = "Spider_Armature|Attack";
    private static final int TYPES = 0;
    private static final double SPEED = 10;

    private AnimationController.AnimationListener listener;

    public Basic(Vector3 position, ModelInstance instance, btCollisionWorld world, Map<Integer, Object> objects) {
        this(position, SPEED, HP, COOLDOWN, TYPES, instance, 0, world, objects);
    }

    public Basic(Vector3 pos, double speeed, int health, float coolDown, int types, ModelInstance instance, int effects, btCollisionWorld world, Map<Integer, Object> objects) {
        super(pos, speeed, HP, health, RANGE, coolDown, types, instance, new btCompoundShape(), effects, world, objects, ATTACK_ANIMATION);
        ((btCompoundShape)shape).addChildShape(new Matrix4(new Vector3(0, 50, 30), new Quaternion().setEulerAngles(0, 0, 0), new Vector3(1, 1, 1)), new btBoxShape(new Vector3(15, 10, 45)));
        ((btCompoundShape)shape).addChildShape(new Matrix4(new Vector3(0, 45, -25), new Quaternion().setEulerAngles(0, 0, 0), new Vector3(1, 1, 1)), new btBoxShape(new Vector3(10, 10, 10)));
        System.out.println(getModelInstance().getAnimation("Spider_Armature|walk_ani_vor").id);
        listener = new AnimationController.AnimationListener() {
            @Override
            public void onEnd(AnimationController.AnimationDesc animationDesc) {

            }

            @Override
            public void onLoop(AnimationController.AnimationDesc animationDesc) {

            }
        };
        //animation.setAnimation("Spider_Armature|walk_ani_vor");
        //animation.animate("Spider_Armature|walk_ani_vor", -1);
        //animation.action("Spider_Armature|walk_ani_vor", 0, 1000, -1, 1, listener, 0);
        //animation.animate("Spider_Armature|Attack", 0, 1000, 1, 1, listener, 0);
        //animation.queue("Spider_Armature|walk_ani_vor", 0, 1000, -1, 1, listener, 0);
    }

    @Override
    public void tick(float delta, List<Vector3> path) {
        super.tick(delta, path);
    }

    @Override
    public void render(float delta, ModelBatch modelBatch) {
        super.render(delta, modelBatch);
    }
}
