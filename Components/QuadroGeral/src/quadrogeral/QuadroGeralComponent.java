/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quadrogeral;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ra222142
 */
public class QuadroGeralComponent implements IQuadroGeral {
    
    public QuadroGeralComponent() {
        
    }

    @Override
    public Map<String, Double> porcentagem(String matriz[][]) {
        Map<String, Double> porcentagem = new HashMap<>();
        int numOcorrencias = 0;
        int numPacientes = matriz.length;
        int posDiagnostico = matriz[0].length - 1;
        
        String doenca;
        for (int i = 0; i < matriz.length; i++) {
            doenca = matriz[i][posDiagnostico];
            
            if (!porcentagem.containsKey(doenca.toUpperCase())) {
                for (int j = 0; j < matriz.length; j++)
                    if (matriz[j][posDiagnostico].equalsIgnoreCase(doenca))
                        numOcorrencias++;
                
                porcentagem.put(doenca.toUpperCase(), (double) numOcorrencias/numPacientes);
            }
            numOcorrencias = 0;            
        }
                

        return porcentagem;
    }
    
    @Override
    public Map<String, Integer> ocorrencia(String[][] matriz) {
        Map<String, Integer> ocorrencia = new HashMap<>();
        int numOcorrencias = 0;
        int posDiagnostico = matriz[0].length - 1;
        
        String doenca;
        for (int i = 0; i < matriz.length; i++) {
            doenca = matriz[i][posDiagnostico];
            
            if (!ocorrencia.containsKey(doenca.toUpperCase())) {
                for (int j = 0; j < matriz.length; j++)
                    if (matriz[j][posDiagnostico].equalsIgnoreCase(doenca))
                        numOcorrencias++;
                ocorrencia.put(doenca.toUpperCase(), numOcorrencias);
            }
            numOcorrencias = 0;            
        }
                

        return ocorrencia;
    }

    @Override
    public void plotarGrafico(Map<String, Integer> ocorrencia) {
        
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        ocorrencia.keySet().forEach((valor) -> {
            ds.addValue((double) ocorrencia.get(valor), "", (Comparable) valor);
        });
        // cria o gráfico
        JFreeChart grafico = ChartFactory.createBarChart("Doenças X Ocorrências", "Doenças", 
            "Ocorrências", ds, PlotOrientation.VERTICAL, false, true, false);
        
        try {
            OutputStream arquivo = new FileOutputStream("grafico.png");
            ChartUtilities.writeChartAsPNG(arquivo, grafico, 550, 400);
            arquivo.close();
            System.out.println("Criou arquivo");
        } catch(IOException e) {
            System.err.println("Cagou tufdo");
        }
    }
}
