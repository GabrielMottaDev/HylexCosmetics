package net.hylex.cosmetics.utils.bo3;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bukkit.Material;

public class BO3Common {
	
	public static BO3Object parse(File file) throws Exception {
		return parse(new FileInputStream(file));
	}

	public static BO3Object parse(InputStream stream) throws Exception {
		List<BO3Block> blocks = new ArrayList<>();

		try (Scanner scanner = new Scanner(stream)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();

				if (line.startsWith("Block(") && line.endsWith(")")) {
					String[] data = line.replace("Block(", "").replace(")", "").split(",");

					int x = Integer.parseInt(data[0]);
					int y = Integer.parseInt(data[1]);
					int z = Integer.parseInt(data[2]);

					String[] block = data[3].split(":");
					Material bType = Material.valueOf(block[0]);
					byte bData = block.length > 1 ? Byte.parseByte(block[1]) : 0;

					blocks.add(new BO3Block(x, y, z, bType, bData));
				}
			}
		}

		return new BO3Object(blocks);
	}
}
