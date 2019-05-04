package matriz_modifier;
import java.util.Scanner;
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
		Matriz_modifier mod=new Matriz_modifier();
		System.out.println(matriz.length);
		mod.matrixReplace(matriz, "1", "t", true);
		mod.matrixReplace(matriz, "0", "f", true);
		matriz=mod.matrixClean(matriz);
		
		Print_matriz(matriz);
	}
	
	

}
