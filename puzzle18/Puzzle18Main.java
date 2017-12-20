public class Puzzle18Main {
	public static void main(String[] args) {
        DuetAnalyzer da = new DuetAnalyzer(0);
        da.copyInstructions("./puzzle18/puzzle18Input.txt");
        System.out.println(da.getFirstRecoveredFrequency());

        DuetAnalyzer da1 = new DuetAnalyzer(0);
        DuetAnalyzer da2 = new DuetAnalyzer(1);

        da1.copyInstructions("./puzzle18/puzzle18Input.txt");
        da2.copyInstructions("./puzzle18/puzzle18Input.txt");

        da1.setOtherProgram(da2);
        da2.setOtherProgram(da1);

        da1.setRegisterValue("p", da1.getProgramId());
        da2.setRegisterValue("p", da1.getProgramId());

        da1.start();
        da2.start();
	}
}