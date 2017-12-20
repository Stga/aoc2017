import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Puzzle11Main {
	public static void main(String[] args) {
		try {
			Hex hex = new Hex();
			BufferedReader br = new BufferedReader(new FileReader("./puzzle11/puzzle11Input.txt"));
			String line;
			line = br.readLine();
			
			String[] directions = line.split(",");
			
			for(String direction : directions) {
				switch(direction) {
					case "n": hex.move_n(); break;
					case "s": hex.move_s(); break;
					
					case "ne": hex.move_ne(); break;
					case "sw": hex.move_sw(); break;
					
					case "se": hex.move_se(); break;
					case "nw": hex.move_nw(); break;
				}
				
				int absX = Math.abs(hex.getX());
				int absY = Math.abs(hex.getY());
				int absZ = Math.abs(hex.getZ());
				
				int currentMaxDistance = Math.max(absX, Math.max(absY, absZ));
				if(hex.getMaxDistance() < currentMaxDistance) {
					hex.setMaxDistance(currentMaxDistance);
				}
			}
			
			System.out.println(hex.toString());
			System.out.println(hex.distance());
			System.out.println(hex.getMaxDistance());
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}

