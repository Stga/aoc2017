import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Puzzle02Part1 {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        try {
            BufferedReader br = new BufferedReader(new FileReader("./puzzle02/puzzle02Input.txt"));
	    int total = 0;
	    String line;

	    while((line = br.readLine()) != null) {
	        String[] lineValues = line.split("\\s+");
		int min = Integer.parseInt(lineValues[0]);
		int max = min;
		for(String value : lineValues) {
		    int currentValAsInt = Integer.parseInt(value);
            if(currentValAsInt < min){min=currentValAsInt;}
		    if(currentValAsInt > max){max=currentValAsInt;}
		}
		total += max-min;
	    }

	    System.out.println(total);
	    long endTime = System.nanoTime();
	    System.out.println(endTime - startTime);
	}
	catch(IOException e) {
	    System.out.println(e.getMessage());
	}
    }
}
