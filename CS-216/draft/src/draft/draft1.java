package draft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class draft1 {

	public static void main(String[] args) {
		Random rand = new Random();
		
		List<Integer> a = new ArrayList<Integer>();
		a.add(2);
		a.add(10);
		a.add(5);
		a.add(4);
		a.add(8);

		List<Integer> b = new ArrayList<Integer>();
		b.add(3);
		b.add(1);
		b.add(7);
		b.add(8);

		System.out.println(method(a, b));
	}
	
	public static List<Integer> method(List<Integer> tA, List<Integer> tB) {
        List<Integer> result = new ArrayList<Integer>();
        
        for (Integer b : tB) {
        	int count = 0;
        	for (Integer a : tA) {
        		if (a <= b)  count++;       		
        	}
        	result.add(count);
        }
        
        return result;
    }
	
	
}
