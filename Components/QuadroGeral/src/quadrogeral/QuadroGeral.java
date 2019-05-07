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
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        ds.addValue(40.5, "maximo", "dia 1");
        ds.addValue(38.2, "maximo", "dia 2");
        ds.addValue(37.3, "maximo", "dia 3");
        ds.addValue(31.5, "maximo", "dia 4");
        ds.addValue(35.7, "maximo", "dia 5");
        ds.addValue(42.5, "maximo", "dia 6");

        // cria o gráfico
        JFreeChart grafico = ChartFactory.createBarChart("Meu Grafico", "Dia", 
            "Valor", ds, PlotOrientation.VERTICAL, true, true, false);
        
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
