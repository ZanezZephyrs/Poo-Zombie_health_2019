package Date_analysis;

public class Main {
	public static void main(String[] args) {
		Date_Analysis a=new Date_Analysis();
		
		int data[]= {25,03,2019};
		int data2[]= {28,03,2019};
		int data3[]= {30,03,2019};


		String[][] matriz={ {"1", "0", "1","resfriado"}, 
				{"1", "0", "1","resfriado"}, 
				{"1", "1", "1","febre"},
				{"1", "0", "1","resfriado"},
				{"1", "1", "1","febre"},
				{"1", "0", "1", "febre"},
				{"1", "0", "1", "febre"}};
		
		String[][] matriz2={ {"1", "0", "1","Febre"}, 
				{"1", "0", "1","Resfriado"}, 
				{"1", "1", "1","IFGW"},
				{"1", "0", "1","Febre"},
				{"1", "1", "1","IFGW"},
				{"1", "0", "1", "Resfriado"},
				{"1", "0", "1", "IFGW"}};
		
		String[][] matriz3={ {"1", "0", "1","Feec"}, 
				{"1", "0", "1","Feec"}, 
				{"1", "1", "1","Imeec"},
				{"1", "0", "1","Imeec"},
				{"1", "1", "1","IFGW"},
				{"1", "0", "1", "Feec"},
				{"1", "0", "1", "IFGW"}};
		
		a.Armazena(matriz,data, "/Users/thales/eclipse-workspace/Santanche/src/Date_Analysis/a.csv");
		a.Armazena(matriz2,data2, "/Users/thales/eclipse-workspace/Santanche/src/Date_Analysis/a.csv");
		a.Armazena(matriz3,data3, "/Users/thales/eclipse-workspace/Santanche/src/Date_Analysis/a.csv");

	}
}
