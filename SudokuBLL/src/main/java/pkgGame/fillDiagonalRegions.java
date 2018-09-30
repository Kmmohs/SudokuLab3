package pkgGame;
import java.util.List;

class fillDiagonalRegions {
	
	public void fillDiagonalRegions() {
		
		int[][] puzzle = getPuzzle();
		
		Stack<Integer> stack1, stack2 = new Stack<>();
		
		for (int i=1; i<10; i++) {
			stack1.add(i); stack2.add(i);
		
		Collections.shuffle(list1);
		Collections.shuffle(list2);
		
		setRDiagonal(puzzle[0], stack);
		setRDiagonal(puzzle[4], stack);
		setLDiagonal(puzzle[4], stack);
		setRDiagonal(puzzle[8], stack);
		setLDiagonal(puzzle[2], stack);
		setLDiagonal(puzzle[4], stack);
		setLDiagonal(puzzle[6], stack);
		
	}
	
	public void setRDiagonal(int[] region, Stack stack) {
		region[0] = stack.pop();
		region[4] = stack.pop();
		region[8] = stack.pop();
	}
	
	public void setLDiagonal(int[] region, Stack stack) {
		region[2] = stack.pop();
		region[4] = stack.pop();
		region[6] = stack.pop();
	}
}
