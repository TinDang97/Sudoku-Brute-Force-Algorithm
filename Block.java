import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;
public class Block
{
    int [][] value;
    public static final int SIZE = 3;
    public  Block()
    {
         value = new int[SIZE][SIZE];
         for(int i = 0; i< SIZE; i++)
                  for(int j = 0; j< SIZE; j++)
                       value[i][j] = 0;
    }
	public  Block(int[] number)
    {
		this();
		int k = 0;
         for(int i = 0; i< SIZE; i++)
			 for(int j = 0; j< SIZE; j++){
                       value[i][j] = number[k];
						k++;
			}  
    }
	
	public int getValue(int i, int j){
		return value[i][j];
	}
	
	public int getValue(int k){
		return getValue(k/SIZE, k%SIZE);
	}
	
	public void setValue(int i, int j, int val){
		value[i][j] = val;
	}
	
	public void setValue(int k, int val){
		setValue(k/SIZE, k%SIZE, val);
	}
	
    public String toString(){
		String result = "";
		for(int i = 0; i< SIZE; i++){
			for(int j = 0; j< SIZE; j++)
                       result += String.valueOf(value[i][j]) + " ";
				   result += "\n";
		}
		return result;
	}
}
