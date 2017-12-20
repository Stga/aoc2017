
public class Puzzle03 {
  public static void main(String[] args) {
    int input = Integer.parseInt(args[0]);    
    int expansion = 1;
    int corner = 1;
    int cnt=3;

    while(cnt<input) {
      int xcoord = expansion;
      int ycoord = 1-expansion;
      boolean c1=false,c2=false,c3=false,c4=false;

      while(! (c1&&c2&&c3&&c4)) {
        //Increment coords
        if(!c1) {
	        c1 = ycoord++==corner;
	    }
	    else if(!c2) {
	        c2 = xcoord--==(corner+1)*-1;
        }
	    else if(!c3) {
	        c3 = ycoord--==(corner+1)*-1;
	    }
	    else {
	        c4 = xcoord++==corner+1;
	    }

	    System.out.println(String.format("cnt: %d, x: %d, y: %d, c1: %s, c2: %s, c3: %s, c4: %s, expansion: %d", cnt, xcoord, ycoord, c1, c2, c3, c4, expansion));
	    cnt++;
      }
      
      System.out.println("");  

      if(expansion==1){
        corner+=2;
      } 
      else {
        corner+=1;
      }

      expansion++;

    }
  }
}
