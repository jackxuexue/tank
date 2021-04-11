package com.jackxue.tank;

import com.jackxue.tank.net.BulletNewMsg;
import com.jackxue.tank.net.Client;

public class FourBulletFireStrategy implements FireStrategy<Tank> {
    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        Dir[] values = Dir.values();
        System.out.println("jack======================");
        for (int i = 0; i < 4; i++) {
            Bullet b = new Bullet(tank.getId(), bX, bY, values[i], tank.getGroup(), tank.getTf());
            TankFrame.INSTANCE.bullets.add(b);
            Client.INSTANCE.send(new BulletNewMsg(b));
            System.out.println("jack:" + values[i]);
        }
        if(tank.getGroup() == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
