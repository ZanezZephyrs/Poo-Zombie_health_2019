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
        
        if (read())        
            per.toArray(percentage);
        
        return percentage;        
    }

    @Override
    public String[][] occurrence() {
        String occurrence[][] = null;
        
        if (read())
            occ.toArray(occurrence);
        
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
            try (OutputStream arquivo = new FileOutputStream("occurrence.png")) {
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
            try (OutputStream arquivo = new FileOutputStream("percentage.png")) {
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
        String oc[][] = new String[2][data[0].length+1];
        
        int tot = data.length;
        int diagPos = data[0].length - 1;
        int current = 1;
        
        oc[0][0] = data[diagPos][0];
        oc[1][0] = 1 + "";
        
        for (int i = 0; i < tot; i++) {
            for (int j = 0; j < current; j++)
                if (data[diagPos][i].equalsIgnoreCase(oc[0][j]))
                    oc[1][j] = (Integer.parseInt(oc[1][j])+1) + "";
                else {
                    oc[0][current] = data[diagPos][i];
                    oc[1][current] = 1 + "";
                    current++;
                }
        }
        
        oc[0][oc[0].length-1] = "total";
        oc[1][oc[0].length-1] = tot + "";
        
        return oc;
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
                for (String[] ocorrencia : ocorrencias) {
                    writer.write(ocorrencia[0] + ":" + ocorrencia[1]);
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
