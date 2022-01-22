package com.cetc28.tank.cor;

import com.cetc28.tank.GameObject;
import com.cetc28.tank.PropertyMgr;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: WSC
 * @Date: 2022/1/22 - 01 - 22 - 20:17
 * @Description: com.cetc28.tank.cor
 * @version: 1.0
 */
public class ColliderChain implements Collider {
    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        String strColliders = PropertyMgr.getString("colliders");
        System.out.println(strColliders);
        String[] colliderArray = strColliders.split(",");
        for (String s : colliderArray){
            try {
                colliders.add((Collider)Class.forName(s).newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public ColliderChain add(Collider c){
        colliders.add(c);
        return this;
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
         for(Collider c : colliders){
             if(!c.collide(o1, o2)){
                 return false;
             }
         }
         return true;
    }
}
