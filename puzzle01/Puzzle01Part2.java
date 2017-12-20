//*
//Part2 checks input x against its couterpart halfway through the input
//string. The input string is circular so the second half need to be tested
//against the first half due to wrap-around.
//*
public class Puzzle01Part2 {
  public static void main(String[] args) {
    long startTime = System.nanoTime();

    String input = args[0];
    int sum = 0;
    int inputLength = input.length();
    int lenHalved = inputLength/2;
    int[] inputValues = new int[inputLength];

    for(int i=0;i<inputLength; i++) {	  
      inputValues[i] = input.charAt(i) - 48; //adjust for ascii
    }

    //Testing a==b and b==a is redundant. Iterate through the first
    //half of the list and double any match.
    for(int i=0; i<lenHalved; i++) {
	if(inputValues[i] == inputValues[i+lenHalved]) {
	  sum += inputValues[i]*2;
	}
    }
    
    System.out.println(sum);
    long endTime = System.nanoTime();
    System.out.println(endTime - startTime);
  }
}
