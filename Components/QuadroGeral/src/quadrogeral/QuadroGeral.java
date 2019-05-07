/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quadrogeral;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ra222142
 */
public class QuadroGeral implements IQuadroGeral {
    
    public QuadroGeral() {
        
    }

    @Override
    public Map<String, Double> porcentagem(String matriz[][]) {
        Map<String, Double> porcentagem = new HashMap<>();
        int numOcorrencias = 0;
        int numPacientes = matriz.length - 1 /* não pode incluir o cabeçalho */;
        int posDiagnostico = matriz[0].length - 1;
        
        System.out.println("numPacientes: " + numPacientes);
        
        String doenca;
        for (int i = 1; i < matriz.length; i++) {
            doenca = matriz[i][posDiagnostico];
            
            if (!porcentagem.containsKey(doenca.toUpperCase())) {
                for (int j = 1; j < matriz.length; j++)
                    if (matriz[j][posDiagnostico].equalsIgnoreCase(doenca))
                        numOcorrencias++;
                System.out.println("Doenca: " + doenca + " | ocorr: " + numOcorrencias);
                porcentagem.put(doenca.toUpperCase(), (double) numOcorrencias/numPacientes);
            }
            numOcorrencias = 0;            
        }
                

        return porcentagem;
    }

    @Override
    public void plotarGrafico(String matriz[][]) {
        System.out.println("Gráfico!");
    }
}
