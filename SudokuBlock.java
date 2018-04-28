import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SudokuBlock  extends Block{
	
		private List<Integer> free;
		private List<Integer> missing;
		public SudokuBlock(int[] val){
			
			super(val);
			free = new ArrayList<Integer>();
			for(int k =0; k < 9; k++){
				if(val[k]==0) free.add(k);
			}
			
			missing = new ArrayList<Integer>();
			for(int k = 1; k <= 9; k++){
				missing.add(k);
			}
			for(int k =0; k < 9; k++){
				missing.remove(new Integer(val[k]));
			}
		}
		
		public List<Integer> getFree(){
			return Collections.unmodifiableList(free);
		}
		
		
		public List<Integer> getMissing(){
			return missing;
		}
		
		public Iterator getIterator() {
			return new SudokuBlockIterator(this);
		}
		
		private class SudokuBlockIterator  implements Iterator {

			int[] permutation;
			
			private SudokuBlock block;
			
			public SudokuBlockIterator(SudokuBlock block){
				this.block = block;
				permutation = new int[block.free.size()];
				for(int k = 0; k < permutation.length; k++){
					permutation[k]=0;
				}
			}
			
			@Override
			public boolean hasNext() {
				return permutation[permutation.length - 1] != 1;
			}

			@Override
			public Object next() {
				// TODO Auto-generated method stub
				List<Integer> tmp = new ArrayList<>(missing);
				for(int k = 0; k< free.size(); k++){
					block.setValue(free.get(k), tmp.get(permutation[k]));
					tmp.remove(permutation[k]);
				}
				
				for(int k = 0; k< permutation.length; k++){
					permutation[k]++;
					if(permutation[k] == permutation.length - k && k != permutation.length -1){
						permutation[k] = 0;
					}else{
						break;
					}
				}
				
				return block;
			}
			
		}
}
