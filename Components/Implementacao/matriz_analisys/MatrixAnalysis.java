package matriz_analisys;
import java.util.ArrayList;
public class MatrixAnalysis implements IMatrixAnalysis{
	
	@Override
	public int mostFalse(String[][] matrix) {
			ArrayList<Integer> frequency = new ArrayList<Integer>();
			int i=0, j=0;
			int count = 0;
			for (j=0; j<matrix[i].length - 1; j++) {
				for (i=0; i<matrix.length - 1; i++) {
					if (matrix[i][j].equals("0")) {
						count ++;
					}
				}
				frequency.add(count);
				count = 0;
			}
			int mostF = 0;
			for(Integer valor:frequency) {
				if (valor>mostF) {
					mostF=valor;
				} 
			}
			return mostF;
			
		}


	@Override
	public int mostTrue(String[][] matrix) {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		int i=0, j=0;
		int count = 0;
		for (j=0; j<matrix[0].length - 1; j++) {
			for (i=0; i < (matrix.length); i++) {
				if (matrix[i][j].equals("1")) {
					count ++;
				}
			}
			frequency.add(count);
			count = 0;
		}
		int mostT = 0;
		for(Integer valor:frequency) {
			if (valor>mostT) {
				mostT=valor;
			} 
		}
		return mostT;
	}

	@Override
	public ArrayList<ArrayList<String>> count(String[][] matrix) {
		ArrayList<ArrayList<String>> listaDeListas = new ArrayList<ArrayList<String>>();
		int i = 0, countT = 0, countF = 0;
		for (int j=0; j<matrix[0].length-1; j++) {
			ArrayList<String> lista = new ArrayList<String>();
			for (i=0; i<matrix.length; i++) {
				if (matrix[i][j].equals("1")) {
					countT ++;
				}
				if (matrix[i][j].equals("0")) {
					countF ++;
				}
			}
			lista.add(Integer.toString(countT));
			lista.add(Integer.toString(countF));
			countT=0;
			countF=0;
			listaDeListas.add(lista);
		}
		return listaDeListas;
	}

	@Override
	public boolean matrixCompare(String[][] mat1, String[][] mat2) {
			for (int i = 0; i<mat1.length; i++) {
				if(!mat1[i].equals(mat2[i])) {
					return false;
				}
			}
			return true;

	}
}