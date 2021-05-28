package net.hylex.cosmetics.cosmetics.trampoline;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TrampolineRunnable extends BukkitRunnable {
	
	private CTrampoline cTrampoline;
	private UUID uuid;
	private TrampolineObj trampolineObj; 
	private Long endTime = -1L;

	@Override
	public void run() {
		if(System.currentTimeMillis() >= endTime) {
			trampolineObj.destroy();
			cTrampoline.trampolines.remove(uuid);
			cancel();
			return;
		}
		for(Entity entity : trampolineObj.getLoc().getWorld().getNearbyEntities(trampolineObj.getLoc(), 8D, 8D, 8D)) {
			if(!(entity instanceof Player))
				continue;
			
			Player p = (Player) entity;
			Block block = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
			if(block.getType() != Material.WOOL)
				continue;
			
			if(!trampolineObj.getWoolBlocks().contains(block))
				continue;
			
			p.setVelocity(p.getVelocity().setY(1.5D));
				
		}
	}

}