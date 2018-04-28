import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Integer;
import java.util.*;
import java.text.*;
/**
*The SudokuGenerator class creates a random standard (9x9)
*Sudoku board through the use of permutation techniques.
*/
public class SudokuGenerator
{
	
	public static final int n = 3;
	public Writer fileOut;
	private ArrayList<Block> boardS;
	/**
	 *Constructor.  Resets board to zeros
	 */
   public SudokuGenerator() {
   	
   }
	
	/**
	 *Driver method for nextBoard.
	 *@param  difficult the number of blank spaces to insert
	 *@return board, a partially completed 9x9 Sudoku board
	 */
	public void generatingSudoku(String output1, int number_blanks)
	{
		LatinSquare major = LatinSquare.getRandom();
		List<LatinSquare> listMinor = new ArrayList<LatinSquare>();
		List<Integer> cell = new ArrayList<>();
		
		for (int i = 0; i < Block.SIZE; i++)
			for(int j = 0; j < Block.SIZE; j++){
				LatinSquare tmp = LatinSquare.getRandom();
				tmp.add(major.getValue(i,j)*Block.SIZE+1);
				listMinor.add(tmp);
			}
		for(int i = 0; i < Block.SIZE; i++){
			LatinSquare.swap_rows(listMinor.get(i),listMinor.get(3+i),1,0);
			LatinSquare.swap_rows(listMinor.get(3+i),listMinor.get(6+i),2,1);
			LatinSquare.swap_rows(listMinor.get(i),listMinor.get(6+i),2,0);
		}
		
		/*for (int j = 0; j < 9; j++){
			System.out.println(listMinor.get(j));
		}
		for(int i = 0; i < 3; i++){
			for (int row = 0; row < 3; row++){
				for (int j = (i+1)*3; j > i*3; j++)
					for (int col = 0; col < 3; col++)
						System.out.print(listMinor.get(j).getValue(col, row) + " ");
				}
		}*/
		
		for(int i = 0; i < 81; i++)
			cell.add(i);
		
		while(number_blanks > 0){
			int index = new Random().nextInt(cell.size());
			int pos = cell.get(index);
			listMinor.get(pos/9).setValue(pos%9, 0);
			cell.remove(index);
			number_blanks--;
		}
		
		writePuzzle(listMinor, output1);
	}
	
	public void writePuzzle(List<LatinSquare> boardS, String output_name)
	{
	  
	       try{
                fileOut = new FileWriter(output_name); 
				for (int i = 0; i < 9; i++){
					for (int j = 0; j < 9; j++){
						fileOut.write(boardS.get(i/3*3 + j/3).getValue(i%3,j%3) + " ");
					}
					fileOut.write("\n");
				}
	            fileOut.close();
            }catch(IOException e)
             {    System.out.println(e); }
                  
	}
	
	public static void main(String[] args)
	{
		SudokuGenerator sg = new SudokuGenerator();
		sg.generatingSudoku(args[0], Integer.parseInt(args[1]));
	}
}
