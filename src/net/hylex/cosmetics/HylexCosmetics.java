package net.hylex.cosmetics;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;

public class HylexCosmetics extends JavaPlugin {
	
	@Getter
	private CosmeticsManager manager;
	
	@Override
	public void onEnable() {
		this.manager = new CosmeticsManager(this);
		this.manager.start();
	}
	
}