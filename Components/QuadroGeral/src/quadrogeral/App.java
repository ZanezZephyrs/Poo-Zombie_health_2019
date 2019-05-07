/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quadrogeral;

import java.util.Map;

/**
 *
 * @author ra222142
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IQuadroGeral qg = new QuadroGeral();
        String a[][] = new String[][] {{"doença"},{"a"},{"b"},{"A"},{"c"},{"B"},{"A"},{"A"},{"B"},{"c"},{"A"},{"C"}};

        Map<String, Double> dic = qg.porcentagem(a);
        dic.keySet().forEach((valor) -> {
            System.out.println(valor + " " + dic.get(valor));
        });
        
        qg.plotarGrafico(qg.ocorrencia(a));
    }
    
}
