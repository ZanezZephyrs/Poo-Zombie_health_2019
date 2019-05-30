package Date_analysys;

public interface iDate_Analysis {
	
	public void Armazena(String[][] a, int month);
	/*armazena as informações passadas em A em um csv*/
	public String[][] Request_date_table();
	/*le um csv com as informações em relação a data, retorna a matriz*/
	public void Plot_date_graph();
	/*plota um grafico de acordo com a tabela obtida*/
}
