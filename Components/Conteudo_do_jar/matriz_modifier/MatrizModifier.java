package matriz_modifier;
import java.util.ArrayList;

import interfaces.Imatrix_modifiers;

public class MatrizModifier implements Imatrix_modifiers{
	
	public MatrizModifier() {
		
	}

	@Override
	public void matrixReplace(String[][] matrix, String original, String novo, boolean ignore) {
		int i;
		for(i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[i].length;j++) {
				if(ignore) {
					if(matrix[i][j].equalsIgnoreCase(original)) {
						matrix[i][j]=novo;
					}
				}else {
					if(matrix[i][j].equals(original)) {
						matrix[i][j]=novo;
					}
				}
			}
		}
	}

	@Override
	public String[][] matrixClean(String[][] matrix) {
		ArrayList<String[]> aux= new ArrayList();
		for(int i=0;i<matrix.length;i++) {
			aux.add(matrix[i]);
		}
		for(int i=0 ;i<matrix.length-1;i++) {
			for(int j=i+1;j<aux.size();j++) {
				if(compara_vetor(aux.get(i), aux.get(j))) {
					aux.remove(j);
					j--;
				}
			}
		}
		
		return (String [][])aux.toArray(new String[aux.size()][]);
				
	}
	
	private boolean compara_vetor(String[] vet1, String[] vet2) {
		if(vet1.length!=vet2.length) {
			return false;
		}
		for(int i=0;i<vet1.length;i++) {
			if(!vet1[i].equals(vet2[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String[][] deleteCollum(String[][] matrix, int index) {
		ArrayList<ArrayList<String>> aux= new ArrayList();
		for(int i=0;i<matrix.length;i++) {
			ArrayList<String> line=new ArrayList();
			for(int j=0;j<matrix[i].length;j++) {
				line.add(matrix[i][j]);
			}
			aux.add(line);
		}
		
		ArrayList<String[]> resp= new ArrayList();
		for(int i=0;i<matrix.length;i++) {
			aux.get(i).remove(index);
			resp.add(aux.get(i).toArray(new String[aux.get(i).size()]));
			
		}
		
		return (String[][])resp.toArray(new String[aux.size()][]);
		
	}

	@Override
	public String[][] deleteLine(String[][] matrix, int index) {
		ArrayList<String[]> aux= new ArrayList();
		for(int i=0;i<matrix.length;i++) {
			aux.add(matrix[i]);
		}
		aux.remove(index);
		return (String [][])aux.toArray(new String[aux.size()][]);	
	}
	
	
	
}

