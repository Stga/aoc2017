import java.lang.Character;

public class Part2 {
  public static void main(String[] args) {
    String input = args[0];

    int sum = 0;
    int inputLength = input.length();
    int sizeHalfed = inputLength/2;

    for(int i=0; i<input.length(); i++) {
        int halfway = (i > sizeHalfed-1) ? (i-sizeHalfed) : (i+sizeHalfed);

	int firstValue = Character.getNumericValue(input.charAt(i));
        int secondValue = Character.getNumericValue(input.charAt(halfway));
	if(firstValue - secondValue == 0) {
	  sum += firstValue;
	}
    }

    System.out.println(sum);
  }
}
