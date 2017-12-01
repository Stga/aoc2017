public class Testing {
  public static void main(String[] args) {
    long startTime = System.nanoTime();

    String input = args[0];
    int sum = 0;
    int inputLength = input.length();
    int lenHalved = inputLength/2;
    int[] inputValues = new int[inputLength];

    for(int i=0;i<inputLength; i++) {
      inputValues[i] = input.charAt(i) - 48;
    }

    for(int i=0; i<inputLength; i++) {
        int halfway = (i > lenHalved-1) ? (i-lenHalved) : (i+lenHalved);

	if(inputValues[i] == inputValues[halfway]) {
	  sum += inputValues[i];
	}
    }
    
    System.out.println(sum);
    long endTime = System.nanoTime();
    System.out.println(endTime - startTime);
  }
}
