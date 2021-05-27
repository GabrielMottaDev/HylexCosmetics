package net.hylex.cosmetics.cosmetics.trampoline;

import java.io.File;

import org.bukkit.Location;

import lombok.Data;
import net.hylex.cosmetics.HylexCosmetics;
import net.hylex.cosmetics.utils.bo3.BO3Common;
import net.hylex.cosmetics.utils.bo3.BO3Object;

@Data
public class TrampolineObj {
	
	private BO3Object bo3ObjBase = null;
	
	private Location loc;
	private BO3Object bo3Obj = null;
	
	private boolean spawned = false;
	
	public TrampolineObj(Location loc) {
		this.loc = loc;
		if(bo3ObjBase == null) {
			try {
				File file = new File(HylexCosmetics.getInstance().getDataFolder(), "/trampoline.bo3");
				bo3ObjBase = BO3Common.parse(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		bo3Obj = bo3ObjBase.clone();
	}
	
	public void spawn() {
		bo3Obj.paste(loc);		
		this.spawned = true;
	}
	
	public void destroy() {
		if(bo3Obj != null) {
			bo3Obj.undo();
		}
		this.spawned = false;
	}

}
