package Date_analysis;

public interface iDate_Analysis {
	
	public void Armazena(String[][] a, int[] date, String path);
	/*armazena as informações passadas em A em um csv*/
	public String[][] Request_date_table(String path);
	/*le um csv com as informações em relação a data, retorna a matriz*/
	public void Plot_date_graph(String path);
	/*plota um grafico de acordo com a tabela obtida*/
}
