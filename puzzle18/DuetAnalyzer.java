import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DuetAnalyzer extends Thread {
    private Map<String, Long> registerValues;
    private List<Long> previousFrequencies;
	private List<String> instructions;
    private ArrayDeque<Long> queue;
    private int programId;
    private DuetAnalyzer otherProgram;

    DuetAnalyzer(int pid) {
		registerValues = new HashMap<>();
		previousFrequencies = new ArrayList<>();
		instructions = new ArrayList<>();
        queue = new ArrayDeque<>();
        programId = pid;
    }

    public void setOtherProgram(DuetAnalyzer otherProgram) {
        this.otherProgram = otherProgram;
    }

    public void putInQueue(long l) {
        this.otherProgram.queue.addLast(l);
    }

    public int getProgramId() {
        return this.programId;
    }

    public void setRegisterValue(String registerName, long newValue) {
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

    public long getFirstRecoveredFrequency() {
		int instructionPointer = 0;
		
		while(true) {
			String[] instructionComponents = this.instructions.get(instructionPointer).split(" ");

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
                    long newValue = Integer.parseInt(instructionComponents[2]);
					this.setRegisterValue(instructionComponents[1], newValue);
					instructionPointer++;
					break;
					
				case "add":
                    long addValue = Integer.parseInt(instructionComponents[2]);
					this.add(instructionComponents[1], addValue);
					instructionPointer++;
					break;
					
				case "mul":
                    long mulValue = Integer.parseInt(instructionComponents[2]);
					this.mul(instructionComponents[1], mulValue);
					instructionPointer++;
					break;
					
				case "mod":
                    long modValue = Integer.parseInt(instructionComponents[2]);
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

    public void getProgramSendCount() {
        int sendCount = 0;

        int instructionPointer = 0;

        while (true) {
            String[] instructionComponents = this.instructions.get(instructionPointer).split(" ");

            if (instructionComponents.length > 2) {
                boolean isNumber = instructionComponents[2].matches("[-+]?\\d*\\.?\\d+");
                if (!isNumber) {
                    instructionComponents[2] = "" + this.registerValues.get(instructionComponents[2]);
                }
            }

            switch (instructionComponents[0]) {
                case "snd":
                    long regValue = this.registerValues.get(instructionComponents[1]);
                    this.putInQueue(regValue);
                    instructionPointer++;
                    sendCount++;
                    break;

                case "set":
                    long newValue = Integer.parseInt(instructionComponents[2]);
                    this.setRegisterValue(instructionComponents[1], newValue);
                    instructionPointer++;
                    break;

                case "add":
                    long addValue = Integer.parseInt(instructionComponents[2]);
                    this.add(instructionComponents[1], addValue);
                    instructionPointer++;
                    break;

                case "mul":
                    long mulValue = Integer.parseInt(instructionComponents[2]);
                    this.mul(instructionComponents[1], mulValue);
                    instructionPointer++;
                    break;

                case "mod":
                    long modValue = Integer.parseInt(instructionComponents[2]);
                    this.mod(instructionComponents[1], modValue);
                    instructionPointer++;
                    break;

                case "rcv":
                    while (queue.size() == 0) {
                        if (this.programId == 1) {
                            System.out.println(String.format("Program %d halted, SendCount %d", this.programId, sendCount));
                        }
                    }
                    registerValues.put(instructionComponents[1], queue.pop());
                    instructionPointer++;
                    break;

                case "jgz":
                    if (this.shouldJgz(instructionComponents[1])) {
                        instructionPointer += Integer.parseInt(instructionComponents[2]);
                    } else {
                        instructionPointer++;
                    }
                    break;
            }
        }
    }

	private void playSound(String register) {
        long soundFrequency = this.registerValues.get(register);
		this.previousFrequencies.add(soundFrequency);
	}

    private void add(String register, long addValue) {
        long currentRegisterValue = this.registerValues.get(register);
		this.registerValues.put(register, currentRegisterValue+addValue);
	}

    private void mul(String register, long mulValue) {
        long currentRegisterValue = this.registerValues.get(register);
		this.registerValues.put(register, currentRegisterValue*mulValue);
	}

    private void mod(String register, long modValue) {
        long currentRegisterValue = this.registerValues.get(register);
		this.registerValues.put(register, currentRegisterValue%modValue);
	}
	
	private boolean shouldJgz(String register) {
        return this.registerValues.get(register) > 0;
    }

    public void run() {
        this.getProgramSendCount();
    }
}