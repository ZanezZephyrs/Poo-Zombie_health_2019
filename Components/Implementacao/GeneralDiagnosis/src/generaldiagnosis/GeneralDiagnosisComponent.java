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
import java.util.ArrayList;
import javax.swing.JFileChooser;
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
    private final int READING = 0, WRITING = 1;
    
    private File file;
    private BufferedReader reader;
    private BufferedWriter writer;
    private ArrayList<String[]> occ, per;
    
    public GeneralDiagnosisComponent() {
        
    }

    @Override
    public String[][] percentage() {
        String percentage[][] = null;
        
        if (read());
        
        return percentage;
    }

    @Override
    public String[][] occurrence() {
        String occurrence[][] = null;
        
        if (read());
        
        return occurrence;
    }

    @Override
    public void plotChart() {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        occ.forEach((data) -> {
            ds.addValue(Integer.valueOf(data[1]), "", data[0]);
        });
     
        // cria o gráfico
        JFreeChart grafico = ChartFactory.createBarChart("Doenças X Ocorrências", "Doenças", 
            "Ocorrências", ds, PlotOrientation.VERTICAL, false, true, false);
        
        try {
            if (file == null)
                file = new File(System.getProperties().getProperty("user.dir")+"/a");
            JFileChooser fil = new JFileChooser(file.getParent());
            fil.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            try (OutputStream arquivo = new FileOutputStream(fil.getSelectedFile().getAbsolutePath() + "/occurrence.png")) {
                ChartUtilities.writeChartAsPNG(arquivo, grafico, 550, 400);
            }
            System.out.println("Criou arquivo");
        } catch(IOException e) {
            System.err.println("Não foi possível criar o gráfico.");
            e.getStackTrace();
        }
        
        ds.clear();
        per.forEach((data) -> {
            ds.addValue(Integer.valueOf(data[1]), "", data[0]);
        });
     
        // cria o gráfico
        grafico = ChartFactory.createBarChart("Doenças X Porcentagem", "Doenças", 
            "Porcentagens", ds, PlotOrientation.VERTICAL, false, true, false);
        
        try {
            if (file == null)
                file = new File(System.getProperties().getProperty("user.dir")+"/a");
            
            JFileChooser fil = new JFileChooser(file.getParent());
            fil.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            try (OutputStream arquivo = new FileOutputStream(fil.getSelectedFile().getAbsolutePath() + "/percentage.png")) {
                ChartUtilities.writeChartAsPNG(arquivo, grafico, 550, 400);
            }
            System.out.println("Criou arquivo");
        } catch(IOException e) {
            System.err.println("Não foi possível criar o gráfico.");
            e.getStackTrace();
        }
    }

    @Override
    public String[][] percentage(String[][] data) {
        return data;
    }

    @Override
    public String[][] occurrence(String[][] data) {
        return data;
    }
    
    private void setFile(String filepath, int operation) throws IOException {
        this.file = new File(filepath);
        
        switch (operation) {
            case READING:    
                reader = new BufferedReader(new FileReader(file));
                break;
            case WRITING:
                writer = new BufferedWriter(new FileWriter(file));
                break;
            default:
                reader = new BufferedReader(new FileReader(file));
                writer = new BufferedWriter(new FileWriter(file));
        }
    }

    private boolean write() {
        try {
            setFile("occurrece.txt", WRITING);
            
            String ocorrencias[][] = new String[occ.size()][2];
            occ.toArray(ocorrencias);
            
            if (ocorrencias != null) {
                for (int i = 0; i < ocorrencias.length; i++) {
                    writer.write(ocorrencias[i][0] + ":" + ocorrencias[i][1]);
                    writer.newLine();
                }
            }
            writer.close();
            
            setFile("percentage.txt", READING);
            
            String porcentagens[][] = new String[per.size()][2];
            per.toArray(porcentagens);
            
            if (porcentagens != null) {
                for (int i = 0; i < porcentagens.length; i++) {
                    writer.write(porcentagens[i][0] + ":" + ocorrencias[i][1]);
                    writer.newLine();
                }
            }
            
            writer.close();
            
            return true;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.getStackTrace();
            return false;
        }
    }

    private boolean read() {
        try {
            setFile("occurrence.txt", READING);
            String linha, dados[];
            while((linha = reader.readLine()) != null) {
                dados = linha.split(":");
                
                occ.add(dados);
            }
            
            reader.close();
            
            setFile("percentage.txt", READING);
            while((linha = reader.readLine()) != null) {
                dados = linha.split(":");
                per.add(dados);
            }
            
            reader.close();
            
            return true;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.getStackTrace();
            return false;
        }
    }
}
