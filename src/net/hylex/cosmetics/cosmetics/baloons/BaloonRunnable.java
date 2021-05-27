package net.hylex.cosmetics.cosmetics.baloons;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import net.hylex.cosmetics.HylexCosmetics;

public class BaloonRunnable extends BukkitRunnable {
	
	private static final Random random = new Random();
	
	private List<LivingEntity> baloons = new ArrayList<>();
	private Long endTime = -1L;
	private int endY = 0;
	
	public BaloonRunnable(List<LivingEntity> baloons, Long endTime, int endY) {
		this.baloons = baloons;
		this.endTime = endTime;
		this.endY = endY;
		runTaskTimer(HylexCosmetics.getInstance(), 0L, 0L);
	}

	@Override
	public void run() {
		if(System.currentTimeMillis() >= endTime) {
			baloons.forEach(b -> b.remove());
			baloons.clear();
			cancel();
			return;
		}
		
		Iterator<LivingEntity> it = baloons.iterator();
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
