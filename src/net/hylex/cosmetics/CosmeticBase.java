package net.hylex.cosmetics;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import lombok.Getter;
import lombok.Setter;

public abstract class CosmeticBase implements Listener {

	@Getter
	private HylexCosmetics instance;
	
	@Getter
	@Setter
	private String name;
	
	public CosmeticBase(HylexCosmetics instance) {
		this.instance = instance;
		try {
			this.instance.getServer().getPluginManager().registerEvents(this, instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean hasActiveCosmetic(Player p) {
		CosmeticBase activeCosmetic = getInstance().getManager().getActiveCosmetic(p);
		return activeCosmetic != null && activeCosmetic.equals(this);
	}
	
}
