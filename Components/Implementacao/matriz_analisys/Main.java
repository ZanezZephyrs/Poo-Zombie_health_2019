package matriz_analisys;
public class Main {		
		public static void Print_matriz(String [][] mat) {
			for(int i=0;i<mat.length;i++) {
				for(int j=0;j<mat[i].length;j++) {
					System.out.printf(mat[i][j]+",");
				}
				System.out.println();
			}
		}
		
		public static void main(String[] args) {
		
			String[][] matriz={ {"1", "0", "1","resfriado"}, 
								{"1", "0", "1","resfriado"}, 
								{"1", "1", "1","febre"},
								{"1", "0", "1","resfriado"},
								{"1", "1", "1","febre"},
								{"1", "0", "1", "febre"},
								{"1", "0", "1", "febre"}};;
			IMatrixAnalysis mod=new MatrixAnalysis();
			System.out.println(mod.mostTrue(matriz));
			System.out.println(mod.count(matriz));
			Print_matriz(matriz);
		}

	}