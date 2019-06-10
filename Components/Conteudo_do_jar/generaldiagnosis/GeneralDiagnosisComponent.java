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

import interfaces.IGeneralDiagnosis;

/**
 *
 * @author ra222142
 */
public class GeneralDiagnosisComponent implements IGeneralDiagnosis {
    private final int READING = 0, WRITING = 1;
    private final String FILEPATH ="occurrence.txt";
    
    private File arqocc;
    private File arqpor;
    
    private File file;
    private BufferedReader reader;
    private PrintWriter writer;
    private ArrayList<String[]> occ = new ArrayList<>();
    
    public GeneralDiagnosisComponent() {
        ResetAllData();
    }

    @Override
    public String[][] percentage() {
        ArrayList<String[]> matriz = new ArrayList<>();
        
        try {
            BufferedReader read =  new BufferedReader(new FileReader("percentage.txt"));
            String linha = read.readLine();
            while(linha != null){
                String[] Args = linha.split(":");
                matriz.add(Args);
                linha = read.readLine();
            }
            read.close();
                
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.getStackTrace();
        }
        
        String[][] res = new String[matriz.size()][2];
        for (int i = 0; i < matriz.size(); i++) {
            res[i][0] = matriz.get(i)[0];
            res[i][1] = matriz.get(i)[1];
        }
        
        return res;       
    }

    @Override
    public String[][] occurrence() {
        String[][] matriz = new String[0][2];
        
        try {
            BufferedReader read =  new BufferedReader(new FileReader("occurrence.txt"));

            while(read.ready()){
                String linha = read.readLine();
                String[] Args = linha.split(":");
                String[][] aux = new String[matriz.length + 1][2];
                for(int i = 0; i < matriz.length; i++){
                    aux[i][0] = matriz[i][0];
                    aux[i][1] = matriz[i][1];
                }
                aux[matriz.length] = Args;
                matriz = aux;
            }
                
            read.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.getStackTrace();
        }
        
        return matriz;
    }

    @Override
    public void plotChart() {
        try {
            DefaultCategoryDataset ds = new DefaultCategoryDataset();

            BufferedReader read =  new BufferedReader(new FileReader("occurrence.txt"));

            while(read.ready()){
                String linha = read.readLine();
                String[] args = linha.split(":");
                if (!args[0].equalsIgnoreCase("total"))
                    ds.addValue(Integer.parseInt(args[1]), "", args[0]);
            }
            read.close();
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

            read =  new BufferedReader(new FileReader("percentage.txt"));
            
            int tot=0;
            while(read.ready()){
                String linha = read.readLine();
                String[] args = linha.split(":");
                if (!args[0].equalsIgnoreCase("total"))
                    ds.addValue(Double.parseDouble(args[1]), "", args[0]);
                else
                    tot = Integer.parseInt(args[1]);
            }
          
            // cria o gráfico
            grafico = ChartFactory.createBarChart("Doenças X Porcentagem\n(Total: "+tot+")", "Doenças", 
                "Porcentagens ", ds, PlotOrientation.VERTICAL, false, false, false);

            try {                       
                try (OutputStream arquivo = new FileOutputStream("percentage.png")) {
                    ChartUtilities.writeChartAsPNG(arquivo, grafico, 550, 400);
                }
                System.out.println("Criou arquivo");
            } catch(IOException e) {
                System.err.println("Não foi possível criar o gráfico.");
                e.getStackTrace();
            } 
        } catch (IOException | NumberFormatException ex) {
            System.err.println("Problema na leitura dos dados.");
            ex.getStackTrace();
        }
    }

    @Override
    public String[][] percentage(String[][] data) {
        String oc[][] = occurrence(data);
        
        int last = oc[0].length - 1;
        
        int tot = Integer.parseInt(oc[1][last]);
        
        for (int i = 0; i < oc[0].length - 1; i++) {
            double valor = Double.parseDouble(oc[1][i]);
            oc[1][i] = Double.toString(100 * (valor/ (double) tot));
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
        Save();
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
            dados[1] = Double.toString((100 * Double.parseDouble(occ.get(i)[1])/ (double) tot));
            perc.add(dados);
        }
                    
        perc.add(occ.get(last));
        
        return perc;
    }
    
    /**
     *
     */
    public void Save(){
        try {
            BufferedReader aocc = new BufferedReader(new FileReader("occurrence.txt"));
            boolean a = false;
            String[] last = new String[1];
            if (!occ.isEmpty()){
                a = true;
                last = occ.get(occ.size() - 1);
                occ.remove(occ.size() - 1);
            }
            
            if(arqocc.exists() ){
                while(aocc.ready()){
                    String linha = aocc.readLine();
                    String[] Args = linha.split(":");
                    for (int i = 0; i < occ.size(); i++){
                        if( Args[0].equalsIgnoreCase(occ.get(i)[0]) ){
                            occ.get(i)[1] = String.valueOf( Integer.parseInt(occ.get(i)[1]) + Integer.parseInt(Args[0]) );
                            break;
                        }
                        if (i == occ.size() - 1){
                            occ.add(Args);
                            break;
                        }
                    }
                }
                aocc.close();
            }
            if (a){
                occ.add(last);
            }
            
            
            FileWriter arquivo = new FileWriter("occurrence.txt", false);
            PrintWriter format = new PrintWriter(arquivo);
            for (int i = 0; i < occ.size(); i++){
                format.print(occ.get(i)[0]);
                format.print(":");
                format.println(occ.get(i)[1]);         
            }
            format.close();
            arquivo.close();
        
            FileWriter arquivo2 = new FileWriter("percentage.txt", false);
            PrintWriter format2 = new PrintWriter(arquivo2);
            for (int i = 0; i < occ.size() - 1; i++){
                format2.print(occ.get(i)[0]);
                format2.print(":");
                format2.println( Double.toString( 100 * (Double.parseDouble(occ.get(i)[1]) / Double.parseDouble(occ.get(occ.size() - 1)[1] ))));                
            }
            format2.print(occ.get(occ.size()-1)[0]);
            format2.print(":");
            format2.println(occ.get(occ.size()-1)[1]);
            
            format2.close();
            arquivo2.close();

        
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.getStackTrace();
        }
        
        occ.clear();
    }
    
    private void ResetAllData(){
        arqocc = new File("");
        arqpor = new File("");
        try{
            FileWriter a = new FileWriter("occurrence.txt");
            FileWriter b = new FileWriter("percentage.txt");
        }catch(IOException erro){
            
        }
    }
    
    private void addOccurrence(String[][] oc) { 
        for(int i = 0; i < oc[0].length; i++){
            String[] a = new String[2];
            a[0] = oc[0][i];
            a[1] = oc[1][i];
            occ.add(a);
        } 
    }
    
    
}
