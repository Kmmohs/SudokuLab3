package pkgGame;
import java.util.List;

class shuffleArray {
	
	private List<Integer> shuffleArray(int[] ar) {
		
		List<Integer> list = new ArrayList<>;
		
		for (i : ar)
			list.add(i);
		
		Collections.shuffle(list);
		
		return list;
		
	}
}
