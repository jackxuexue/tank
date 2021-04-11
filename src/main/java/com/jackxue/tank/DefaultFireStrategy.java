package com.jackxue.tank;

import com.jackxue.tank.net.BulletNewMsg;
import com.jackxue.tank.net.Client;

public class DefaultFireStrategy implements FireStrategy<Tank>{
    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        Bullet b = new Bullet(tank.getId(), bX, bY, tank.getDir(), tank.getGroup(), tank.getTf());

        TankFrame.INSTANCE.bullets.add(b);

        Client.INSTANCE.send(new BulletNewMsg(b));

        if(tank.getGroup() == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
