import java.util.*;

class LatinSquare extends Block{
	static List<LatinSquare> sq;
	static {
		sq = new ArrayList<LatinSquare>();
		sq.add(new LatinSquare(new int[] {0,1,2,1,2,0,2,0,1}));
		sq.add(new LatinSquare(new int[] {1,0,2,0,2,1,2,1,0}));
		sq.add(new LatinSquare(new int[] {2,1,0,0,2,1,1,0,2}));
		sq.add(new LatinSquare(new int[] {0,1,2,2,0,1,1,2,0}));
		sq.add(new LatinSquare(new int[] {1,0,2,2,1,0,0,2,1}));
		sq.add(new LatinSquare(new int[] {2,1,0,1,0,2,0,2,1}));
		sq.add(new LatinSquare(new int[] {0,2,1,1,0,2,2,1,0}));
		sq.add(new LatinSquare(new int[] {1,2,0,0,1,2,2,0,1}));
		sq.add(new LatinSquare(new int[] {2,0,1,0,1,2,1,2,0}));
		sq.add(new LatinSquare(new int[] {0,2,1,2,1,0,1,0,2}));
		sq.add(new LatinSquare(new int[] {1,2,0,2,0,1,0,1,2}));
		sq.add(new LatinSquare(new int[] {2,0,1,1,2,0,0,1,2}));
	}
	
	static LatinSquare getById(int n){
		LatinSquare lsNew = new LatinSquare();
		LatinSquare lsOld = sq.get(n);
		for(int i = 0; i < Block.SIZE; i++)
			for (int j = 0; j < Block.SIZE; j++)
				lsNew.setValue(i, j, lsOld.getValue(i, j));
		return lsNew;
	}
	
	static LatinSquare getRandom(){
		return getById(new Random().nextInt(sq.size()));
	}
	
	static void swap_rows(LatinSquare s1, LatinSquare s2, int r1, int r2){
		int tmp;
		for(int i = 0; i < Block.SIZE; i++){
			tmp = s1.getValue(r1, i);
			s1.setValue(r1, i, s2.getValue(r2, i));
			s2.setValue(r2, i, tmp);
		}
	}
	
	public LatinSquare(int[] v){
		super(v);
	}
	
	public LatinSquare(){
		super();
	}
	
	void add(int k){
		for(int i = 0; i< super.SIZE; i++)
                  for(int j = 0; j< super.SIZE; j++)
                       super.value[i][j] += k;
	}
}	