
public class AdjacentEqual {
	
	public String[][] s; 
	
	public static boolean containsAdjacentEqual(String[][] stringArr) {
		for (int row = 0; row < stringArr.length; row++) {
			for (int col = 0; col < stringArr[row].length; col++) {
				String a = stringArr[row][col];
                if (row - 1 >= 0 && col - 1 >= 0 && a.compareTo(stringArr[row - 1][col - 1])==0) {	// up-left
                    return true;
                }
                if (row - 1 >= 0 && a.compareTo(stringArr[row - 1][col])==0) {	// up
                    return true;
                }
                if (row - 1 >= 0 && col + 1 < stringArr[0].length && a.compareTo(stringArr[row - 1][col + 1])==0) {	// up-right
                    return true;
                }
                if (col - 1 >= 0 && a.compareTo(stringArr[row][col - 1])==0) {	// left
                    return true;
                }
                if (col + 1 < stringArr[0].length && a.compareTo(stringArr[row][col + 1])==0) {	// right
                    return true;
                }
                if (row + 1 < stringArr.length && col - 1 >= 0 && a.compareTo(stringArr[row + 1][col - 1])==0) {	// down left
                    return true;
                }
                if (row + 1 < stringArr.length && a.compareTo(stringArr[row + 1][col])==0) {	// down
                    return true;
                }
                if (row + 1 < stringArr.length && col + 1 < stringArr[0].length && a.compareTo(stringArr[row + 1][col + 1])==0) {	// down-right
                    return true;
                }
			}
		}
		
		return false;
	}
	
	
	
	
	
//	public static void main(String[] args) {
//
//	}

}
