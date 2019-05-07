public interface IMatrixAnalysis {
	public boolean matrixCompare(String mat1[][], String mat2[][]); /* Retorna true se as matrizes forem iguais */
	public int mostFalse(String matrix[][]); /* Retorna o index da coluna com o maior número de F */
	public int mostTrue(String matrix[][]); /* Retorna o index da coluna com o maior número de T */
	public String[][] count(String matrix[][]); /* Retorna uma matriz de N_Colunas*2, onde cada linha contém [n_True, n_False] */
}
