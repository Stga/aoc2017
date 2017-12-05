import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class JumpParser {
	private BufferedReader br;
	private ArrayList<Integer> jumpInstructions;
		
	public JumpParser(String instructionFile) {
		try {
			this.br = new BufferedReader(new FileReader(instructionFile));
			this.jumpInstructions = new ArrayList<Integer>();
			this.readJumpFileContents();
		} catch(FileNotFoundException e) {
			System.out.println(e);
		}
	}
	
	private void readJumpFileContents() {
		String line;
		try {
			while( (line=this.br.readLine()) != null) {
				int jumpInstruction = Integer.parseInt(line);
				this.jumpInstructions.add(jumpInstruction);
			}
		} catch(IOException e) {
			System.out.println(e);
		}
	}
	
	private int getStepsToExit() {
		int instructionPointer = 0;
		int jumpOffset = 0;
		int jumpCount = 0;
		boolean jumping = true;
		
		while(jumping) {
			if(instructionPointer > (this.jumpInstructions.size()-1)) {
				//System.out.println(String.format("IP: %d, Size-1: %d", instructionPointer, this.jumpInstructions.size()-1));
				jumping = false;
			} else {
				jumpOffset = this.jumpInstructions.get(instructionPointer);
				//System.out.println(String.format("JmpCount: %d, IP: %d, Offset: %d", jumpCount, instructionPointer, jumpOffset));
				
				this.jumpInstructions.set(instructionPointer, jumpOffset+=1);
				instructionPointer += jumpOffset-1;
				jumpCount++;
			}
		}
		
		return jumpCount;
	}
	
	private int getStepsToExitAlt() {
		int instructionPointer = 0;
		int jumpOffset = 0;
		int jumpCount = 0;
		boolean jumping = true;
		
		while(jumping) {
			if(instructionPointer > (this.jumpInstructions.size()-1)) {
				//System.out.println(String.format("IP: %d, Size-1: %d", instructionPointer, this.jumpInstructions.size()-1));
				jumping = false;
			} else {
				jumpOffset = this.jumpInstructions.get(instructionPointer);
				//System.out.println(String.format("JmpCount: %d, IP: %d, Offset: %d", jumpCount, instructionPointer, jumpOffset));
				
				int newOffset = (jumpOffset>= 3) ? jumpOffset-1 : jumpOffset+1;
				this.jumpInstructions.set(instructionPointer, newOffset);
				instructionPointer += jumpOffset;
				jumpCount++;
			}
		}
		
		return jumpCount;
	}
	
	public static void main(String[] args) {
		JumpParser jp = new JumpParser("jmpOffsets.txt");
		final int stepsToExit = jp.getStepsToExit();
		System.out.println(stepsToExit);
		
		JumpParser jpAlt = new JumpParser("jmpOffsets.txt");
		final int stepsToExitAlt = jpAlt.getStepsToExitAlt();
		System.out.println(stepsToExitAlt);
	}
}