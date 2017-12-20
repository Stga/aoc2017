import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DuetAnalyzer {
	private Map<String, Integer> registerValues;
	private List<Integer> previousFrequencies;
	private List<String> instructions;
	
	DuetAnalyzer() {
		registerValues = new HashMap<>();
		previousFrequencies = new ArrayList<>();
		instructions = new ArrayList<>();
	}
	
	private void setRegisterValue(String registerName, int newValue) {
		this.registerValues.put(registerName, newValue);
	}
	
	public void copyInstructions(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			while( (line=br.readLine()) != null) {
				this.instructions.add(line);

				String[] instructionComponents = line.split(" ");
				if(!this.registerValues.containsKey(instructionComponents[1])) {
					this.setRegisterValue(instructionComponents[1], 0);
				}
			}
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public int getFirstRecoveredFrequency() {
		int instructionPointer = 0;
		
		while(true) {
			String[] instructionComponents = this.instructions.get(instructionPointer).split(" ");

			System.out.println(this.instructions.get(instructionPointer));

			if(instructionComponents.length > 2) {
				boolean isNumber = instructionComponents[2].matches("[-+]?\\d*\\.?\\d+");
				if(!isNumber ) {
					instructionComponents[2] = "" + this.registerValues.get(instructionComponents[2]);
				}
			}
			
			switch(instructionComponents[0]) {
				case "snd":
					this.playSound(instructionComponents[1]);
					instructionPointer++;
					break;
					
				case "set":
					int newValue = Integer.parseInt(instructionComponents[2]);
					this.setRegisterValue(instructionComponents[1], newValue);
					instructionPointer++;
					break;
					
				case "add":
					int addValue = Integer.parseInt(instructionComponents[2]);
					this.add(instructionComponents[1], addValue);
					instructionPointer++;
					break;
					
				case "mul":
					int mulValue = Integer.parseInt(instructionComponents[2]);
					this.mul(instructionComponents[1], mulValue);
					instructionPointer++;
					break;
					
				case "mod":
					int modValue = Integer.parseInt(instructionComponents[2]);
					this.mod(instructionComponents[1], modValue);
					instructionPointer++;
					break;
					
				case "rcv":
					if(this.registerValues.get(instructionComponents[1]) > 0) {
						return this.previousFrequencies.get(previousFrequencies.size()-1);
					}
					instructionPointer++;
					break;
					
				case "jgz":
					if(this.shouldJgz(instructionComponents[1])) {
						instructionPointer += Integer.parseInt(instructionComponents[2]);
					} else {
						instructionPointer++;
					}
					break;
			}
		}
	}
	
	private void playSound(String register) {
		int soundFrequency = this.registerValues.get(register);
		this.previousFrequencies.add(soundFrequency);
	}
	
	private void add(String register, int addValue) {
		int currentRegisterValue = this.registerValues.get(register);
		this.registerValues.put(register, currentRegisterValue+addValue);
	}
	
	private void mul(String register, int mulValue) {
		int currentRegisterValue = this.registerValues.get(register);
		this.registerValues.put(register, currentRegisterValue*mulValue);
	}
	
	private void mod(String register, int modValue) {
		int currentRegisterValue = this.registerValues.get(register);
		this.registerValues.put(register, currentRegisterValue%modValue);
	}
	
	private boolean shouldJgz(String register) {
		return this.registerValues.get(register) > 0;
	}
}