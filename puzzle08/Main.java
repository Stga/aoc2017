import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		try {
			HashMap<String, Integer> registerMap = new HashMap<String, Integer>();
			BufferedReader br = new BufferedReader(new FileReader("input.txt"));
			int largestValOccurred = Integer.MIN_VALUE;
			
			String command;
			while( (command=br.readLine()) != null) {
				String[] splitCommand = command.split(" ");
				
				//See if both registers are in the map, if not, add them.
				if(! registerMap.containsKey(splitCommand[0])) {
					registerMap.put(splitCommand[0], 0);
				}
				
				if(! registerMap.containsKey(splitCommand[4])) {
					registerMap.put(splitCommand[4], 0);
				}
				
				//Verify Conditional
				int operatingRegVal = registerMap.get(splitCommand[0]);
				int comparisonRegVal = registerMap.get(splitCommand[4]);
				final String operator = splitCommand[5];
				final int comparisonVal = Integer.parseInt(splitCommand[6]);
				
				final boolean eval = Main.executeConditional(comparisonRegVal, operator, comparisonVal);

				if(eval) {
					final int opVal = Integer.parseInt(splitCommand[2]);
					switch(splitCommand[1]) {
						case "inc": 
							operatingRegVal += opVal;
							break;
						case "dec":
							operatingRegVal -= opVal;
							break;
					}
					
					registerMap.put(splitCommand[0], operatingRegVal);
					if(operatingRegVal > largestValOccurred) {
						largestValOccurred = operatingRegVal;
					}
				}
			}
			
			//Get largest value in registers.
			int largestVal = Integer.MIN_VALUE;
			for(String key : registerMap.keySet()) {
				int regVal = registerMap.get(key);
				if (regVal > largestVal) {
					largestVal = regVal;
				}
			}
			System.out.println(largestVal);
			System.out.println(largestValOccurred);
		} catch(IOException e) {
			System.out.println(e);
		}
	}
	
	public static boolean executeConditional(int registerVal, String operator, int comparisonVal) {
		switch(operator) {
			case "<=": return registerVal <= comparisonVal;
			case "<":  return registerVal < comparisonVal;
			case "==": return registerVal == comparisonVal;
			case "!=": return registerVal != comparisonVal;
			case ">":  return registerVal > comparisonVal;
			case ">=": return registerVal >= comparisonVal;
		}
		
		return false;
	}
}