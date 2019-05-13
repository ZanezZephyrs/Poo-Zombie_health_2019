/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quadrogeral;

import java.io.IOException;
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
        IQuadroGeral qg = new QuadroGeralComponent();
        String a[][] = new String[][] {{"a"},{"a"},{"b"},{"b"},{"c"},{"c"},{"d"},{"d"},{"e"},{"e"}};

        qg.gravarOcorrencia(qg.ocorrencia(a));
        Map<String, Integer> ocorrencia = qg.ocorrencia();
        qg.plotarGrafico(ocorrencia);
        qg.gravarPorcentagem(qg.porcentagem());
    }
    
}
