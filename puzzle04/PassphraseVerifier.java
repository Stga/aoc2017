import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Arrays;

public class PassphraseVerifier {
  public PassphraseVerifier() {}
  
  public static void main(String[] args) {
    long startTime = System.nanoTime();
    try {
	  PassphraseVerifier pv = new PassphraseVerifier();
	  BufferedReader br = new BufferedReader(new FileReader("passphrases.txt"));
	  String line;
	  int validPassphraseCount = 0;
	  int validAntiAnagramCount = 0;
	  while((line=br.readLine()) != null) {
	    String[] lineValues = line.split(" ");
		int comparisonResult = pv.checkDuplicateWords(lineValues);
		
		//If no duplicates were found after checking the passphrase.
        if(comparisonResult!=0) {
		  validPassphraseCount +=1;
		  if(!pv.containsAnagram(lineValues)) {
		    validAntiAnagramCount +=1;
		  }
		}
	  }
	  
	  System.out.println(validPassphraseCount);
	  System.out.println(validAntiAnagramCount);
	  long endTime = System.nanoTime();
	  System.out.println(String.format("Took %d nanoseconds", endTime-startTime));
	}
	
	catch(FileNotFoundException e) {
	  System.out.println(e);
	}
	catch(IOException e) {
	  System.out.println(e);
	}
  }
  
  private int checkDuplicateWords(String[] lineValues) {
    int comparisonResult = 0;

	//Break using outer when a duplicate is found as this invalidates
	//the entire passphrase i.e. no need to keep iterating.
	outer:
	for(int i=0; i<lineValues.length-1; i++) {
	  String currentValue = lineValues[i];
	  
	  for(int x=i+1; x<lineValues.length; x++) {
		String compareValue = lineValues[x];
		comparisonResult = currentValue.compareTo(compareValue);
		if(comparisonResult==0) {break outer;}
	  }
	}
	
	return comparisonResult;
  }
  
  private boolean containsAnagram(String[] lineValues) {
	HashSet<String> h = new HashSet<String>();

	for(int i=0; i<lineValues.length; i++) {
	  char[] currentValue = lineValues[i].toCharArray();
	  Arrays.sort(currentValue);
	  String stringValue = new String(currentValue);
	  h.add(stringValue);
	}

	return h.size() != lineValues.length;
  }
}
