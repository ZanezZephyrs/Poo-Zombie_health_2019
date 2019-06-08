package interfaces;

import java.util.ArrayList;

public interface IMatrixAnalysis {
	public boolean matrixCompare(String[][] mat1,String[][] mat2) ;
	public int mostFalse(String[][] matrix);
	public int mostTrue(String[][] matrix);
	public ArrayList<ArrayList<String>> count(String[][] matrix);
	
}