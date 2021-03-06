package com.logicmaster63.tdgalaxy.map.region;

import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;

public abstract class Region {

    protected Vector3 pos, tempVector;

    public abstract btCollisionObject createCollisionObject();

    public Region(Vector3 pos) {
        this.pos = pos;
        tempVector = new Vector3();
    }

    public abstract boolean test(float x, float y, float z);

    public Vector3 getFacing(float x, float y, float z) {
        return tempVector.set(x, y, z).sub(pos).setLength(1);
    }

    public Quaternion getQuaternion(float x, float y, float z) {
        return new Quaternion(tempVector.set(x, y, z).sub(pos), 0);
    }

    public boolean test(Vector3 pos) {
        return test(pos.x, pos.y, pos.z);
    }

    public Vector3 getFacing(Vector3 pos) {
        return getFacing(pos.x, pos.y, pos.z);
    }
}
