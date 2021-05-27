package net.hylex.cosmetics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.hylex.cosmetics.cosmetics.baloons.CBaloons;
import net.hylex.cosmetics.cosmetics.skeleton.CSkeleton;
import net.hylex.cosmetics.cosmetics.trampoline.CTrampoline;

@Data
@RequiredArgsConstructor
public class CosmeticsManager {

	@NonNull
	private HylexCosmetics instance;
	
	private List<CosmeticBase> cosmetics = new ArrayList<>();
	private HashMap<UUID, CosmeticBase> playerCosmetic = new HashMap<>();
	
	public CosmeticBase getCosmeticByName(String cosmeticName) {
		for(CosmeticBase cosmetic : cosmetics) {
			if(cosmetic.getName().equalsIgnoreCase(cosmeticName)) {
				return cosmetic;
			}
		}
		return null;
	}
	
	public CosmeticBase getActiveCosmetic(Player p) {
		return playerCosmetic.get(p.getUniqueId());
	}
	
	public void setCosmetic(Player p, CosmeticBase cosmetic) {
		playerCosmetic.put(p.getUniqueId(), cosmetic);
	}
	
	public void removeCosmetic(Player p) {
		playerCosmetic.remove(p.getUniqueId());
	}
	
	public void start() {
		instance.getCommand("engenhoca").setExecutor(new CosmeticCommand(this));
		cosmetics.add(new CBaloons(instance));
		cosmetics.add(new CTrampoline(instance));
		cosmetics.add(new CSkeleton(instance));
	}

}