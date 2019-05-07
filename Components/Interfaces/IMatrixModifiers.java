public interface IMatrixModifiers {	
	public void matrixReplace(String matrix[][], String original, String novo); /* Substitui todas as ocorrencias de "original" da matriz por "novo" */
	public void matrixClean(String matrix[][]); /* Retira linhas iguais da matrix */
	public void deleteCollum(String matrix[][], int index); /* Deleta a coluna da matriz */
	public void deleteLine(String matrix[][]); /* Deleta a linha da matriz */
}

