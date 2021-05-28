package net.hylex.cosmetics.utils.bo3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import org.bukkit.Location;
import org.bukkit.block.Block;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BO3Object {
	
	@NonNull
	@Getter
	private List<BO3Block> blocks;
	@Getter
	private List<Block> pasted = new ArrayList<>();
	
	private Location lastPaste;
	
	public List<Block> getPasteSpaceBlocks(Location location){
		List<Block> bs = new ArrayList<>();
		blocks.forEach(b -> bs.add(location.clone().add(b.getX(), b.getY(), b.getZ()).getBlock()));
		return bs;
	}

	@SuppressWarnings("deprecation")
	public List<Block> paste(Location location) {
		blocks.forEach(new Consumer<BO3Block>() {

			@Override
			public void accept(BO3Block b) {
				Block block = location.clone().add(b.getX(), b.getY(), b.getZ()).getBlock();
				block.setTypeIdAndData(b.getMaterial().getId(), b.getData(), true);
				pasted.add(block);
			}
			
		});
		lastPaste = location.clone();
		return pasted;
	}

	public void undo() {
		if (lastPaste != null) {
			Collections.reverse(pasted);
			pasted.forEach(
					b -> b.setTypeIdAndData(0, (byte) 0, true));
			lastPaste = null;
			pasted = new ArrayList<>();
		}
	}
	
}
