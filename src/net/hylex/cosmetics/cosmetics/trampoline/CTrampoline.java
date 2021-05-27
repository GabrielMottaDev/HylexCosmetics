package net.hylex.cosmetics.cosmetics.trampoline;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.hylex.cosmetics.CosmeticBase;
import net.hylex.cosmetics.HylexCosmetics;

public class CTrampoline extends CosmeticBase {
	
	private static final int TRAMPOLINE_TIME = 15;
	
	private HashMap<UUID, TrampolineObj> trampolines = new HashMap<>();

	public CTrampoline(HylexCosmetics instance) {
		super(instance);
		setName("Pula-pula");
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;
		
		Player p = e.getPlayer();
		UUID uuid = p.getUniqueId();
		if(!hasActiveCosmetic(p))
			return;
		
		if(trampolines.containsKey(p.getUniqueId())) {
			p.sendMessage("§c§lERRO!§r §cVocê deve esperar o pula-pula sumir para usar novamente.");
			return;
		}

		Location loc = e.getClickedBlock().getRelative(BlockFace.UP).getLocation();
		
		TrampolineObj trampolineObj = new TrampolineObj(loc);
		
		if(!canBuildArea(loc, trampolineObj)) {
			p.sendMessage("§c§lERRO!§r §cEscolha outro local para utilizar essa engenhoca.");
			return;
		}
		
		p.sendMessage("§b§lENGENHOCAS§r §f§l➜§r §3Pula-pula spawnado!");
		trampolineObj.spawn();
		trampolines.put(uuid, trampolineObj);
		new BukkitRunnable() {
			
			@Override
			public void run() {
				trampolineObj.destroy();
				trampolines.remove(uuid);
			}
		}.runTaskLater(getInstance(), TRAMPOLINE_TIME*20L);
	}
	
	@EventHandler
	public void move(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		
		Location loc = e.getTo();
		loc = loc.getBlock().getRelative(BlockFace.DOWN).getLocation();
		
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		
		boolean jump = false;
		for(TrampolineObj trampoline : trampolines.values()) {
			if(!trampoline.isSpawned())
				continue;
			
			List<Block> blocks = trampoline.getBo3Obj().getPasteSpaceBlocks(trampoline.getLoc());
			for(Block b : blocks) {
				if(b.getX() == x && b.getY() == y && b.getZ() == z) {
					if(b.getType() == Material.WOOL) {
						jump = true;
						break;
					}
				}
			}
		}
		
		if(jump) {
			p.setVelocity(p.getVelocity().setY(1.5D));
		}
		
	}
	
	private boolean canBuildArea(Location loc, TrampolineObj trampolineObj) {
		List<Block> spaceBlocks = trampolineObj.getBo3Obj().getPasteSpaceBlocks(loc);
		
		for(Block b : spaceBlocks) {
			if(b.getType() != Material.AIR) {
				return false;
			}
		}
		
		return true;
	}
	
	@Deprecated
	private boolean canBuildArea(Location loc) {
		World world = loc.getWorld();
		
		int locX = loc.getBlockX();
		int locY = loc.getBlockY();
		int locZ = loc.getBlockZ();
		
		int minX = locX-8;
		int minY = locY;
		int minZ = locZ-8;
		
		int maxX = locX+8;
		int maxY = locY+8;
		int maxZ = locZ+8;
		
		for(int x = minX; x < maxX; x++) {
			for(int y = minY; y < maxY; y++) {
				for(int z = minZ; z < maxZ; z++) {
					Block b = world.getBlockAt(x, y, z);
					if(b.getType() != Material.AIR) {
						return false;
					}
				}
			}
		}
		return true;
	}

}