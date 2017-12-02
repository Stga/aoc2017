import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class P1 {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
	    int total = 0;
	    String line;

	    while((line = br.readLine()) != null) {
	        String[] lineValues = line.split("\\s+");
		int min = Integer.parseInt(lineValues[0]);
		int max = min;
		for(int i=0; i<lineValues.length; i++) {
		    int currentValAsInt = Integer.parseInt(lineValues[i]);
                    if(currentValAsInt < min){min=currentValAsInt;}
		    if(currentValAsInt > max){max=currentValAsInt;}
		}
		total += max-min;
	    }

	    System.out.println(total);
	    long endTime = System.nanoTime();
	    System.out.println(endTime - startTime);
	}
	catch(FileNotFoundException e) {
	    System.out.println(e);
	}
	catch(IOException e) {
	    System.out.println(e);
	}

    }
}
