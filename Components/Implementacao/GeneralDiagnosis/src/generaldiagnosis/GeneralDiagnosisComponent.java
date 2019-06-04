/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package generaldiagnosis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
    private PrintWriter writer;
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
        String oc[][] = occurrence(data);
        
        for (int i = 0; i < oc[0].length - 1; i++)
            oc[1][i] = (100 * Double.parseDouble(oc[1][i]) / Double.parseDouble(oc[1][oc[0].length - 1]) ) + "";
                 
        return oc;
    }

    @Override
    public String[][] occurrence(String[][] data) {
        String[] ordenado = new String[4];

        for (String[] data1 : data) {
            for (int j = 0; j < ordenado.length; j++) {
                if (ordenado[j] == null) {
                    ordenado[j] = data1[data[0].length -1];
                }
                if (data1[data[0].length - 1].equals(ordenado[j])) {
                    break;
                } else if (j == ordenado.length - 1) {
                    ordenado[j+1] = data1[data[0].length -1];
                }
            }
            if(ordenado[ordenado.length -1] != null){
                String[] aux = new String[ordenado.length*2];
                System.arraycopy(ordenado, 0, aux, 0, ordenado.length);
                ordenado = aux;
            }
        }
        
        int k = 1;
        for (String ordenado1 : ordenado) {
            if (ordenado1 == null) {
                break;
            }
            k++;
        }

        String[][] lista = new String[2][k];


        for(int i = 0; i < lista[0].length; i++)
            lista[1][i] = "0";
        System.arraycopy(ordenado, 0, lista[0], 0, k);

        for (int i = 0; i < lista[0].length - 1; i++){
            for (String[] data1 : data) {
                if (lista[0][i].equals(data1[data[0].length - 1])) {
                    lista[1][i] = Integer.toString(Integer.parseInt(lista[1][i]) + 1);
                }
            }
        }
        
        int total = 0;
        for (int i = 0; i < lista[0].length - 1; i++)
            total += Integer.parseInt(lista[1][i]);
        
        lista[0][lista[0].length - 1] = "total";
        lista[1][lista[0].length - 1] = total + "";

        return lista;
  
    }
        
    private void setFile(String filepath, int operation) throws IOException {
        this.file = new File(filepath);
        
        switch (operation) {
            case READING:    
                reader = new BufferedReader(new FileReader(file));
                break;
            case WRITING:
                writer = new PrintWriter(new FileWriter(file));
                break;
            default:
                reader = new BufferedReader(new FileReader(file));
                writer = new PrintWriter(new FileWriter(file));
        }
    }

    private boolean write() {
        try {
            setFile("occurrece.txt", WRITING);
            
            String ocorrencias[][] = new String[occ.size()][2];
            occ.toArray(ocorrencias);
            
            if (ocorrencias != null) {
                for (String[] ocorrencia : ocorrencias)
                    writer.println(ocorrencia[0] + ":" + ocorrencia[1]);
            }
            writer.close();
            
            setFile("percentage.txt", READING);
            
            String porcentagens[][] = new String[per.size()][2];
            per.toArray(porcentagens);
            
            if (porcentagens != null) {
                for (int i = 0; i < porcentagens.length; i++)
                    writer.println(porcentagens[i][0] + ":" + ocorrencias[i][1]);
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
