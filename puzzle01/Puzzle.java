public class Puzzle {
  public static void main(String[] args) {
    String input = args[0];
    int sum = 0;
    int inputLength = input.length();
    int[] inputValues = new int[inputLength];

    for(int i=0;i<inputLength; i++) {
        inputValues[i] = input.charAt(i) - 48;
    }

    //The data wraps-around, check the first and last ints
    if(inputValues[0] == inputValues[inputLength-1]) {
      sum += inputValues[0];
    }

    //Sum the rest of the data.
    for(int i=0; i<inputLength-1; i++) {
        if(inputValues[i] == inputValues[i+1]) {
	    sum += inputValues[i];
	}
    }

    System.out.println(sum);
  }
}
