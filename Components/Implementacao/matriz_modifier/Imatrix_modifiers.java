package matriz_modifier;

public interface Imatrix_modifiers {
	 	
	 	public void matrixReplace(String matrix[][], String original, String novo, boolean ignore);
	 	/* Substitui todas as ocorrencias de "original" da matriz por "novo" */
	 	
	 	public String[][] matrixClean(String matrix[][]);
	 	/* Retira linhas iguais da matrix */
	 	
	 	public String[][] deleteCollum(String matrix[][],
	int index);
	 	/* Deleta a coluna da matriz */
	 	
	 	public String[][] deleteLine(String matrix[][], int index);
	 	/* Deleta a linha da matriz */
	 
}


