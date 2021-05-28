package net.hylex.cosmetics.cosmetics.balloons;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class BalloonRunnable extends BukkitRunnable {
	
	private static final Random random = new Random();
	
	private List<LivingEntity> balloons = new ArrayList<>();
	private Long endTime = -1L;
	private int endY = 0;
	
	public BalloonRunnable(List<LivingEntity> balloons, Long endTime, int endY) {
		this.balloons = balloons;
		this.endTime = endTime;
		this.endY = endY;
	}

	@Override
	public void run() {
		if(System.currentTimeMillis() >= endTime) {
			balloons.forEach(b -> b.remove());
			balloons.clear();
			cancel();
			return;
		}
		
		Iterator<LivingEntity> it = balloons.iterator();
		if(!it.hasNext()) {
			cancel();
			return;
		}
		while(it.hasNext()) {
			LivingEntity entity = it.next();
			if (entity.getLocation().getBlockY() >= endY) {
				entity.remove();
				it.remove();
				continue;
			}
			
			entity.setVelocity(entity.getVelocity().setY((0.25D)+(random.nextDouble()/3)));
		}		
	}

}
