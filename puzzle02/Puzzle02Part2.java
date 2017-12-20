import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Puzzle02Part2 {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        try {
            BufferedReader br = new BufferedReader(new FileReader("./puzzle02/puzzle02Input.txt"));
	    int total = 0;
	    String line;

	    while((line = br.readLine()) != null) {
	        String[] lineValues = line.split("\\s+");

		//Check divisibility if there's more than one item.
		if(lineValues.length>1) {
		    for(int i=0; i<lineValues.length-1; i++) {
		        int currentVal = Integer.parseInt(lineValues[i]);
		        
			//Check the currentIndex/allSubsequent & allSubsequent/currentIndex.
			//break if found as there is only one per line.
		        for(int x=i+1; x<lineValues.length; x++) {
			    int secondVal = Integer.parseInt(lineValues[x]);
			    if(currentVal % secondVal == 0) {total += currentVal / secondVal; break;}
			    if(secondVal % currentVal == 0) {total += secondVal / currentVal; break;}
		        }
	  	    }
	        }
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
