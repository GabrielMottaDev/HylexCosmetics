package net.hylex.cosmetics.cosmetics.trampoline;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import net.hylex.cosmetics.CosmeticBase;
import net.hylex.cosmetics.HylexCosmetics;

public class CTrampoline extends CosmeticBase {
	
	public HashMap<UUID, TrampolineObj> trampolines = new HashMap<>();
	private TrampolineObj trampolineObjBlueprint = null;
	
	public CTrampoline(HylexCosmetics instance) {
		super(instance);
		setName("Pula-pula");
		this.trampolineObjBlueprint = new TrampolineObj();
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
		
		TrampolineObj trampolineObj = trampolineObjBlueprint.clone();
		
		if(!canBuildArea(loc, trampolineObj)) {
			p.sendMessage("§c§lERRO!§r §cEscolha outro local para utilizar essa engenhoca.");
			return;
		}
		
		p.sendMessage("§b§lENGENHOCAS§r §f§l➜§r §3Pula-pula spawnado!");
		trampolineObj.spawn(loc);
		trampolines.put(uuid, trampolineObj);
		
		long endTime = System.currentTimeMillis()+(15*1000L);
		new TrampolineRunnable(this, uuid, trampolineObj, endTime).runTaskTimer(getInstance(), 0L, 3L);
		
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
	
}