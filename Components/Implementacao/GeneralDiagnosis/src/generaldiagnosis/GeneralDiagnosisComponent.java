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
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ra222142
 */
public class GeneralDiagnosisComponent implements IGeneralDiagnosis {
    private final int READING = 0, WRITING = 1;
    private final String FILEPATH ="occurrence.txt";
    
    private File file;
    private BufferedReader reader;
    private PrintWriter writer;
    private ArrayList<String[]> occ = new ArrayList<>();
    
    public GeneralDiagnosisComponent() {
        
        if(!read()){
            write();
        }
    }

    @Override
    public String[][] percentage() {
        ArrayList<String[]> perc = getPercentage();
        String percentage[][] = new String[2][perc.size()];
        
        for (int i = 0; i < percentage.length; i++)
            for (int j = 0; i < percentage[0].length; i++)
                percentage[j][i] = perc.get(i)[j];
        
        return percentage;        
    }

    @Override
    public String[][] occurrence() {
        String[][] occurrence = new String[2][occ.size()];
        for (int i = 0; i < occurrence.length; i++)
            for (int j = 0; i < occurrence[0].length; i++)
                occurrence[j][i] = occ.get(i)[j]; 
        
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
            try (OutputStream arquivo = new FileOutputStream(FILEPATH + "occurrence.png")) {
                ChartUtilities.writeChartAsPNG(arquivo, grafico, 550, 400);
            }
            System.out.println("Criou arquivo");
        } catch(IOException e) {
            System.err.println("Não foi possível criar o gráfico.");
            e.getStackTrace();
        }

        ds.clear();
       
        ArrayList<String[]> per = getPercentage();
                
        per.forEach((data) -> {
            ds.addValue(Double.valueOf(data[1]), "", data[0]);
        });
     
        // cria o gráfico
        grafico = ChartFactory.createBarChart("Doenças X Porcentagem", "Doenças", 
            "Porcentagens", ds, PlotOrientation.VERTICAL, false, true, false);
        
        try {                       
            try (OutputStream arquivo = new FileOutputStream(FILEPATH + "percentage.png")) {
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
        
        int last = oc[0].length - 1;
        
        int tot = Integer.parseInt(oc[1][last]);
        
        for (int i = 0; i < oc[0].length - 1; i++) {
            double valor = Double.parseDouble(oc[1][i]);
            oc[1][i] = Double.toString(100 * (valor/tot));
        }
        
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

        addOccurrence(lista);
        write();
        return lista;  
    }
        
    private void prepareFile(int operation) throws IOException {       
        switch (operation) {
            case READING:    
                reader = new BufferedReader(new FileReader(FILEPATH));
                break;
            case WRITING:
                writer = new PrintWriter(new FileWriter(FILEPATH));
                break;
            default:
                reader = new BufferedReader(new FileReader(FILEPATH));
                writer = new PrintWriter(new FileWriter(FILEPATH));
        }
    }

    private boolean write() {
        try {
            prepareFile(WRITING);
            
            occ.forEach((o) -> {
                writer.println(o[0] + ":" + o[1]);
            });
            
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
            prepareFile(READING);
            
            occ.clear();
            for (String linha = reader.readLine(); linha != null; linha = reader.readLine())
                occ.add(linha.split(":"));
                                   
            reader.close();
            
            return true;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.getStackTrace();
            return false;
        }
    }
    
    private ArrayList<String[]> getPercentage() {
        ArrayList<String[]> perc = new ArrayList<>();
        
        int last = occ.size() - 1;
        int tot = Integer.parseInt(occ.get(last)[1]);
        
        String[] dados;
        for (int i = 0; i < last; i++) {
            dados = new String[2];
            dados[0] = occ.get(i)[0];
            dados[1] = Double.toString((100 * Double.parseDouble(occ.get(i)[1])/tot));
            perc.add(dados);
        }
                    
        perc.add(occ.get(last));
        
        return perc;
    }
    
    private void addOccurrence(String[][] oc) { 
        for(int i = 0; i < oc.length; i++){
            String[] a = new String[2];
            a[0] = oc[0][i];
            a[1] = oc[1][i];
            occ.add(a);
        } 
    }
    
    
}
