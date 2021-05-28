package net.hylex.cosmetics;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.hylex.cosmetics.cosmetics.balloons.CBalloons;
import net.hylex.cosmetics.cosmetics.skeleton.CSkeleton;
import net.hylex.cosmetics.cosmetics.trampoline.CTrampoline;

@Data
@RequiredArgsConstructor
public class CosmeticsManager {

	@NonNull
	private HylexCosmetics instance;
	
	private Map<String, CosmeticBase> cosmetics = new HashMap<>();
	private Map<UUID, CosmeticBase> playerCosmetic = new HashMap<>();
	
	public CosmeticBase getCosmeticByName(String cosmeticName) {
		return cosmetics.get(cosmeticName.toLowerCase());
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
		registerEngenhoca(new CBalloons(instance));
		registerEngenhoca(new CTrampoline(instance));
		registerEngenhoca(new CSkeleton(instance));
	}
	
	private void registerEngenhoca(CosmeticBase cosmetic) {
		cosmetics.put(cosmetic.getName().toLowerCase(), cosmetic);
	}

}