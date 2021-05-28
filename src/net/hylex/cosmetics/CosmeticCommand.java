package net.hylex.cosmetics;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CosmeticCommand implements CommandExecutor {

	@NonNull
	private CosmeticsManager manager;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player))
			return true;

		Player p = (Player) sender;
		CosmeticBase activeCosmetic = manager.getActiveCosmetic(p);
		if (args.length == 1) {
			String arg = args[0].toLowerCase();
			switch (arg) {
			case "ajuda":
				sendHelp(p);
				break;
			case "lista":
				sendCosmeticsList(p);
				break;
			case "remover":
				if (activeCosmetic == null) {
					p.sendMessage("§c§lERRO!§r §cNenhuma engenhoca equipada");
					return true;
				}
				manager.removeCosmetic(p);
				p.sendMessage("§b§lENGENHOCAS§r §f§l➜§r §3Engenhoca removida");
				break;
			case "status":
				if (activeCosmetic != null) {
					p.sendMessage("§b§lENGENHOCAS§r §f§l➜§r §3Engenhoca equipada: " + activeCosmetic.getName());
				} else {
					p.sendMessage("§b§lENGENHOCAS§r §f§l➜§r §3Nenhuma engenhoca equipada");
				}
				break;
			default:
				String cosmeticName = arg;
				CosmeticBase cosmetic = manager.getCosmeticByName(cosmeticName);

				if (cosmetic == null) {
					p.sendMessage("§c§lERRO!§r §cEsta engenhoca não existe, use: /engenhoca lista");
					return true;
				}

				if (activeCosmetic != null && activeCosmetic.equals(cosmetic)) {
					p.sendMessage(
							"§c§lERRO!§r §cEsta engenhoca já está equipada, para remover use: /engenhoca remover");
					return true;
				}

				manager.setCosmetic(p, cosmetic);
				p.sendMessage("§b§lENGENHOCAS§r §f§l➜§r §3Engenhoca equipada: " + cosmetic.getName());
				break;
			}
		} else {
			sendError(p);
		}

		return false;
	}

	private void sendCosmeticsList(Player p) {
		p.sendMessage("§b§lENGENHOCAS§r §3Lista:");
		manager.getCosmetics().values().forEach(c -> p.sendMessage("§3-§b " + c.getName()));
	}

	private void sendHelp(Player p) {
		p.sendMessage("§b§lENGENHOCAS§r §3Comandos:");
		p.sendMessage("§3-§b /engenhoca ajuda");
		p.sendMessage("§3-§b /engenhoca status");
		p.sendMessage("§3-§b /engenhoca lista");
		p.sendMessage("§3-§b /engenhoca remover");
		p.sendMessage("§3-§b /engenhoca <nome-da-engenhoca>");
	}

	private void sendError(Player p) {
		p.sendMessage("§c§lERRO!§r §cPara ver a lista de comandos use: /engenhoca ajuda");
	}

}