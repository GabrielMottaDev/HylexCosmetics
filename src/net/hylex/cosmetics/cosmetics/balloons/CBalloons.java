package net.hylex.cosmetics.cosmetics.balloons;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import net.hylex.cosmetics.CosmeticBase;
import net.hylex.cosmetics.HylexCosmetics;

public class CBalloons extends CosmeticBase {

	public CBalloons(HylexCosmetics instance) {
		super(instance);
		setName("Baloes");
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;
		
		Player p = e.getPlayer();
		if(!hasActiveCosmetic(p))
			return;

		p.sendMessage("§b§lENGENHOCAS§r §f§l➜§r §3Lançando balões!");
		BalloonObj balloonObj = new BalloonObj(p.getLocation());
		balloonObj.spawn();
		
	}
	
	@EventHandler
	public void damage(PlayerInteractAtEntityEvent e) {
		if(e.getRightClicked().hasMetadata("balloon")) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void damage(EntityDamageEvent e) {
		if(e.getEntity().hasMetadata("balloon")) {
			e.setCancelled(true);
		}
	}

}