package net.hylex.cosmetics.cosmetics.skeleton;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.hylex.cosmetics.CosmeticBase;
import net.hylex.cosmetics.HylexCosmetics;
import net.hylex.cosmetics.utils.disguise.Disguise;
import net.hylex.cosmetics.utils.disguise.Disguise.DisguiseType;

public class CSkeleton extends CosmeticBase {

	public CSkeleton(HylexCosmetics instance) {
		super(instance);
		setName("Fossil");
	}

	@EventHandler
	public void onInteract(PlayerInteractAtEntityEvent e) {
		if (!(e.getRightClicked() instanceof Player))
			return;

		Player p = e.getPlayer();
		Player target = (Player) e.getRightClicked();

		if (!hasActiveCosmetic(p))
			return;
		
		p.sendMessage("§b§lENGENHOCAS §e§l➜ §fVocê transformou " + target.getName() +  " em um fóssil vivo!");
		target.sendMessage("§b§lENGENHOCAS §e§l➜ §fVocê foi transformado por " + p.getName() + " em um fóssil vivo!");

		Disguise dis = new Disguise(DisguiseType.SKELETON, target);
		dis.disguiseToAll();
		new BukkitRunnable() {
			
			@Override
			public void run() {
				dis.removeDisguise();
			}
		}.runTaskLater(getInstance(), 1*60*20L);

	}

}