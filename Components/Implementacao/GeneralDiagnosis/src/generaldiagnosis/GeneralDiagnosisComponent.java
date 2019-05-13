/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package generaldiagnosis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ra222142
 * @param <Type>
 */
public class GeneralDiagnosisComponent<Type> implements IGeneralDiagnosis {
    private File file;
    private BufferedReader reader;
    private BufferedWriter writer;
    
    public GeneralDiagnosisComponent() {
        
    }
/*
    @Override
    public void plotChart() {
        
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        ocorrencia.keySet().forEach((valor) -> {
            ds.addValue(ocorrencia.get(valor), "", (Comparable) valor);
        });
        // cria o gráfico
        JFreeChart grafico = ChartFactory.createBarChart("Doenças X Ocorrências", "Doenças", 
            "Ocorrências", ds, PlotOrientation.VERTICAL, false, true, false);
        
        try {
            try (OutputStream arquivo = new FileOutputStream("grafico.png")) {
                ChartUtilities.writeChartAsPNG(arquivo, grafico, 550, 400);
            }
            System.out.println("Criou arquivo");
        } catch(IOException e) {
            System.err.println("Não foi possível criar o gráfico.");
            e.getStackTrace();
        }
        
    }
*/

    @Override
    public String[][] percentual() {
        String percentual[][] = null;
        
        try {
            setFile("percentual.txt");
            reader = new BufferedReader(new FileReader(file));
        } catch (IOException ex) {
            
        }
        
        return percentual;
    }

    @Override
    public String[][] occurrence() {
        String occurrence[][] = null;
        
        try {
            setFile("occuerrence.txt");
            reader = new BufferedReader(new FileReader(file));
        } catch (IOException ex) {
            
        }
        
        return occurrence;
    }

    @Override
    public void plotChart() {
        
    }

    @Override
    public String[][] percentual(String[][] data) {
        return data;
    }

    @Override
    public String[][] occurrence(String[][] data) {
        return data;
    }
    
    private void setFile(String filepath) {
        this.file = new File(filepath);
    }

    private void write(String info[][]) {
        
    }

    private void read() {
        
    }
}
