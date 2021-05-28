package net.hylex.cosmetics.cosmetics.trampoline;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Data;
import net.hylex.cosmetics.HylexCosmetics;
import net.hylex.cosmetics.utils.bo3.BO3Common;
import net.hylex.cosmetics.utils.bo3.BO3Object;

@Data
public class TrampolineObj implements Cloneable {

	private BO3Object bo3Obj = null;
	private Location spawned = null;
	private List<Block> woolBlocks = new ArrayList<>();

	public TrampolineObj() {
		try {
			File file = new File(JavaPlugin.getPlugin(HylexCosmetics.class).getDataFolder(), "/trampoline.bo3");
			bo3Obj = BO3Common.parse(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void spawn(Location loc) {
		List<Block> pasted = bo3Obj.paste(loc);
		this.spawned = loc;
		woolBlocks = pasted.stream().filter(b -> b.getType() == Material.WOOL).collect(Collectors.toList());
	}

	public void destroy() {
		if (bo3Obj != null) {
			bo3Obj.undo();
		}
		this.spawned = null;
		this.woolBlocks = null;
	}
	
	public boolean isSpawned() {
		return spawned != null;
	}

	public Location getLoc() {
		return spawned;
	}
	
	@Override
	public TrampolineObj clone() {
		try {
			return (TrampolineObj) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

}
