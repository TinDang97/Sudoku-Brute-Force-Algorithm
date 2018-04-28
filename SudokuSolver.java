import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.text.*;
public class SudokuSolver
{
	private Writer fileOut;
	private String filename;
	private int nBlock = 9;
	private int level = 0;
	private List<SudokuBlock> boardS = new ArrayList<>();
	private List<SudokuBlock> basic = new ArrayList<>();
	
   public SudokuSolver(String filename)
   {
		this.filename = filename;
   }
   
	public boolean readSudoku()
   {
	   int[][] vals = new int[9][9];
	   try{
		   Scanner sc = new Scanner(new File(filename));
		   for (int i = 0; i < 9; i++)
			   for(int j = 0; j < 9; j++)
					vals[i/3*3+j/3][i%3*3+j%3] = sc.nextInt();
	   }
		catch(Exception e){
			System.out.println(e);
			return false;
		}
		 
		 
		for (int i = 0; i < 9; i++){
			SudokuBlock sb = new SudokuBlock(vals[i]);
			SudokuBlock sb2 = new SudokuBlock(vals[i]);
			boardS.add(sb);
			basic.add(sb2);
		}
		
       return true;
   }
   
   public boolean solve()
   {
        if(level==nBlock)
   	        return true;
		
		for(Iterator<SudokuBlock> it = boardS.get(level).getIterator(); it.hasNext();){
			SudokuBlock sb = it.next();
			if(check(sb, level)){
				boardS.set(level, sb);
				level++;
				if(solve())
					return true;
				else{
					for(int i = level; i < nBlock; i++){
						for(int k = 0; k < 9; k++)
							boardS.get(i).setValue(k,basic.get(i).getValue(k));
					}
				}
			}
			
		}
		level--;
   	    return false;
   }
   
   public boolean check(SudokuBlock sb, int pos){
	   for(int i = 0; i < Block.SIZE ; i++){
		   for(int j = 0; j < Block.SIZE; j++){
				for(int k = 0; k < nBlock; k++){
					if(k == level)
						continue;
					if(k/3 == pos/3){
						for(int j2 = 0; j2 < 3; j2++)
							if(sb.getValue(i,j) == boardS.get(k).getValue(i, j2))
								return false;
					}
					
					if(k%3 == pos%3){
						for(int i2 = 0; i2 < 3; i2++)
							if(sb.getValue(i,j) == boardS.get(k).getValue(i2, j))
								return false;
					}
				}
		   }
	   }
	   return true;
   }
   
   public void writeSudoku(File out){
	   try{
               fileOut = new FileWriter(out); 
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
   
   public static void main(String args[])
   {
		SudokuSolver sol = new SudokuSolver(args[0]);
		sol.readSudoku();
		sol.solve();
		sol.writeSudoku(new File(args[1]));
   }
}
