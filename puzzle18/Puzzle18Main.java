public class Puzzle18Main {
	public static void main(String[] args) {
		DuetAnalyzer da = new DuetAnalyzer();
		da.copyInstructions("puzzle18Input.txt");
		System.out.println(da.getFirstRecoveredFrequency()); 
	}
}