package net.hylex.cosmetics.utils.bo3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BO3Object implements Cloneable {
	
	@NonNull
	@Getter
	private List<BO3Block> blocks;
	private Location lastPaste;
	
	public List<Block> getPasteSpaceBlocks(Location location){
		List<Block> bs = new ArrayList<>();
		blocks.forEach(b -> bs.add(location.clone().add(b.getX(), b.getY(), b.getZ()).getBlock()));
		return bs;
	}

	@SuppressWarnings("deprecation")
	public void paste(Location location) {
		blocks.forEach(b -> location.clone().add(b.getX(), b.getY(), b.getZ()).getBlock()
				.setTypeIdAndData(b.getMaterial().getId(), b.getData(), true));
		lastPaste = location.clone();
	}

	@SuppressWarnings("deprecation")
	public void undo(Location location) {
		Collections.reverse(blocks);
		blocks.forEach(
				b -> location.clone().add(b.getX(), b.getY(), b.getZ()).getBlock().setTypeIdAndData(0, (byte) 0, true));
		
	}

	public void undo() {
		if (lastPaste != null) {
			undo(lastPaste);
			lastPaste = null;
		}
	}
	
	@Override
	public BO3Object clone() {
		try {
			return (BO3Object) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
}
