package pkgGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import pkgHelper.LatinSquare;

/**
 * Sudoku - This class extends LatinSquare, adding methods, constructor to
 * handle Sudoku logic
 * 
 * @version 1.2
 * @since Lab #2
 * @author Bert.Gibbons
 *
 */
public class Sudoku extends LatinSquare {

	/**
	 * 
	 * iSize - the length of the width/height of the Sudoku puzzle.
	 * 
	 * @version 1.2
	 * @since Lab #2
	 */
	private int iSize;

	/**
	 * iSqrtSize - SquareRoot of the iSize. If the iSize is 9, iSqrtSize will be
	 * calculated as 3
	 * 
	 * @version 1.2
	 * @since Lab #2
	 */

	private int iSqrtSize;

	/**
	 * Sudoku - for Lab #2... do the following:
	 * 
	 * set iSize If SquareRoot(iSize) is an integer, set iSqrtSize, otherwise throw
	 * exception
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @param iSize-
	 *            length of the width/height of the puzzle
	 * @throws Exception
	 *             if the iSize given doesn't have a whole number square root
	 */
	public Sudoku(int iSize) throws Exception {
		this.iSize = iSize;

		double SQRT = Math.sqrt(iSize);
		if ((SQRT == Math.floor(SQRT)) && !Double.isInfinite(SQRT)) {
			this.iSqrtSize = (int) SQRT;
		} else {
			throw new Exception("Invalid size");
		}
	}

	/**
	 * Sudoku - pass in a given two-dimensional array puzzle, create an instance.
	 * Set iSize and iSqrtSize
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @param puzzle
	 *            - given (working) Sudoku puzzle. Use for testing
	 * @throws Exception will be thrown if the length of the puzzle do not have a whole number square root
	 */
	public Sudoku(int[][] puzzle) throws Exception {
		super(puzzle);
		this.iSize = puzzle.length;
		double SQRT = Math.sqrt(iSize);
		if ((SQRT == Math.floor(SQRT)) && !Double.isInfinite(SQRT)) {
			this.iSqrtSize = (int) SQRT;
		} else {
			throw new Exception("Invalid size");
		}

	}

	/**
	 * getPuzzle - return the Sudoku puzzle
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @return - returns the LatinSquare instance
	 */
	public int[][] getPuzzle() {
		return super.getLatinSquare();
	}

	/**
	 * getRegion - figure out what region you're in based on iCol and iRow and call
	 * getRegion(int)<br>
	 * 
	 * Example, the following Puzzle:
	 * 
	 * 0 1 2 3 <br>
	 * 1 2 3 4 <br>
	 * 3 4 1 2 <br>
	 * 4 1 3 2 <br>
	 * 
	 * getRegion(0,3) would call getRegion(1) and return [2],[3],[3],[4]
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @param iCol
	 *            given column
	 * @param iRow
	 *            given row
	 * @return - returns a one-dimensional array from a given region of the puzzle
	 */
	public int[] getRegion(int iCol, int iRow) {

		int i = (iCol / iSqrtSize) + ((iRow / iSqrtSize) * iSqrtSize);

		return getRegion(i);
	}

	/**
	 * getRegion - pass in a given region, get back a one-dimensional array of the
	 * region's content<br>
	 * 
	 * Example, the following Puzzle:
	 * 
	 * 0 1 2 3 <br>
	 * 1 2 3 4 <br>
	 * 3 4 1 2 <br>
	 * 4 1 3 2 <br>
	 * 
	 * getRegion(2) and return [3],[4],[4],[1]
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @param r
	 *            given region
	 * @return - returns a one-dimensional array from a given region of the puzzle
	 */

	public int[] getRegion(int r) {

		int[] reg = new int[super.getLatinSquare().length];

		int j = (r % iSqrtSize) * iSqrtSize;
		int i = (r / iSqrtSize) * iSqrtSize;
		int jMax = j + iSqrtSize;
		int iMax = i + iSqrtSize;
		int iCnt = 0;

		for (; i < iMax; i++) {
			for (j = (r % iSqrtSize) * iSqrtSize; j < jMax; j++) {
				reg[iCnt++] = super.getLatinSquare()[i][j];
			}
		}

		return reg;
	}



	@Override
	public boolean hasDuplicates()
	{
		if (super.hasDuplicates())
			return true;

		for (int k = 0; k < this.getPuzzle().length; k++) {
			if (super.hasDuplicates(getRegion(k))) {
				return true;
			}
		}

		return false;
	}

	/**
	 * isPartialSudoku - return 'true' if...
	 * 
	 * It's a LatinSquare If each region doesn't have duplicates If each element in
	 * the first row of the puzzle is in each region of the puzzle At least one of
	 * the elemnts is a zero
	 * 
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @return true if the given puzzle is a partial sudoku
	 */
	public boolean isPartialSudoku() {

		this.setbIgnoreZero(true);

		if (hasDuplicates())
			return false;

		if (!ContainsZero()) {
			return false;
		}
		return true;

	}

	/**
	 * isSudoku - return 'true' if...
	 * 
	 * Is a partialSudoku Each element doesn't a zero
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @return - returns 'true' if it's a partialSudoku, element match (row versus column) and no zeros
	 */
	public boolean isSudoku() {

		this.setbIgnoreZero(false);

		if (hasDuplicates())
			return false;

		if (!super.isLatinSquare())
			return false;

		for (int i = 1; i < super.getLatinSquare().length; i++) {

			if (!hasAllValues(getRow(0), getRegion(i))) {
				return false;
			}
		}

		if (ContainsZero()) {
			return false;
		}

		return true;
	}

	/**
	 * isValidValue - test to see if a given value would 'work' for a given column /
	 * row
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @param iCol
	 *            puzzle column
	 * @param iRow
	 *            puzzle row
	 * @param iValue
	 *            given value
	 * @return - returns 'true' if the proposed value is valid for the row and column
	 */
	public boolean isValidValue(int iCol, int iRow, int iValue) {
		return false;
	}

	private void shuffleArray(int [] arr) {

		List<Integer> list = new ArrayList<>();
		for (int i : arr) {
			list.add(i);
		}
		Collections.shuffle(list);

		

	}


	private void SetRegion(int para1) {
		/* Fill values filling up the region for number 
		 * of spots in the region
		 */

		int ivalue=1;
		for (int i=(para1/iSqrtSize)*iSqrtSize;i<((para1/iSqrtSize)*iSqrtSize)+iSqrtSize;i++) {
			for (int j=(para1%iSqrtSize)*iSqrtSize;j<((para1%iSqrtSize)*iSqrtSize)+iSqrtSize;j++) {
				this.getPuzzle[i][j]=ivalue++;
			}
		}

	}

	private void ShuffleRegion(int para1) { 

		int [] region=getRegion(para1);
		shuffleArray(region);

		int value=0;
		for (int i=(para1/iSqrtSize)*iSqrtSize;i<((para1/iSqrtSize)*iSqrtSize)+iSqrtSize;i++) {
			for (int j=(para1%iSqrtSize)*iSqrtSize;j<((para1%iSqrtSize)*iSqrtSize)+iSqrtSize;j++) {
				this.getPuzzle[i][j]=region[value++];
			}
		}

	}

	public void PrintPuzzle() {
		for (int[] row : this.getPuzzle()) {
			System.out.println("");
			for (int element : row) {
				System.out.printf("% 2d", element);
			}
		}
	}

	public void fillDiagonalRegions() {

		int[][] puzzle = getPuzzle();

		Stack<Integer> stack1 = new Stack<>(), stack2 = new Stack<>();

		for (int i=1; i<10; i++) {
			stack1.add(i); stack2.add(i);
		}

		Collections.shuffle(stack1);
		Collections.shuffle(stack2);

		setRDiagonal(puzzle[0], stack1);
		setRDiagonal(puzzle[4], stack1);
		setRDiagonal(puzzle[8], stack1);

		setLDiagonal(puzzle[2], stack2);
		setLDiagonal(puzzle[4], stack2);
		setLDiagonal(puzzle[6], stack2);

	}

	public void setRDiagonal(int[] region, Stack<Integer> stack) {
		region[0] = (int) stack.pop();
		region[4] = (int) stack.pop();
		region[8] = (int) stack.pop();
	}

	public void setLDiagonal(int[] region, Stack<Integer> stack) {
		region[2] = (int) stack.pop();
		region[4] = (int) stack.pop();
		region[6] = (int) stack.pop();
	}

	public int getRegionNbr(int iCol, int iRow) {

		int j =(iCol / iSqrtSize) + ((iRow / iSqrtSize) * iSqrtSize);

		return j;
	}
}