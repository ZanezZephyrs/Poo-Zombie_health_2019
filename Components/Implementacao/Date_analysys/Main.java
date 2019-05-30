package Date_analysys;

public class Main {
	public static void main(String[] args) {
		Date_Analysis a=new Date_Analysis();

		DataSetComponent dados=new DataSetComponent();
		dados.setDataSource("/Users/thales/eclipse-workspace/Santanche/src/Date_Analysis/dados.csv");
		String[][] matriz_csv=dados.requestInstances();
		dados.setDataSource("/Users/thales/eclipse-workspace/Santanche/src/Date_Analysis/dados2.csv");
		String[][] matriz_csv2=dados.requestInstances();
		a.setPath("/Users/thales/eclipse-workspace/Santanche/src/Date_Analysis/a.csv");
		
		
		a.Reset_data();
		a.Armazena(matriz_csv, 1 );
		a.Armazena(matriz_csv2, 2);
		a.Armazena(matriz_csv, 3);
		a.Armazena(matriz_csv2, 4);
		a.Armazena(matriz_csv, 5);
		a.Armazena(matriz_csv, 10);
		a.Plot_date_graph();
	}
}
