/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quadrogeral;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
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
    public final static String OCORRENCIA = "ocorrencia";
    
    
    public QuadroGeralComponent() {
        
    }

    @Override
    public Map<String, Double> porcentagem(String matriz[][]) {
        Map<String, Double> porcentagem = new HashMap<>();
        int numOcorrencias = 0;
        int numPacientes = matriz.length;
        int posDiagnostico = matriz[0].length - 1;
        
        String doenca;
        for (String[] matriz2 : matriz) {
            doenca = matriz2[posDiagnostico];
            if (!porcentagem.containsKey(doenca.toUpperCase())) {
                for (String[] matriz1 : matriz) {
                    if (matriz1[posDiagnostico].equalsIgnoreCase(doenca)) {
                        numOcorrencias++;
                    }
                }
                
                porcentagem.put(doenca.toUpperCase(), (double) numOcorrencias/numPacientes);
            }
            numOcorrencias = 0;            
        }
                
        porcentagem.put(QuadroGeralComponent.OCORRENCIA, (double) numPacientes);
        return porcentagem;
    }
    
    @Override
    public Map<String, Integer> ocorrencia(String[][] matriz) {
        Map<String, Integer> ocorrencia = new HashMap<>();
        int numOcorrencias = 0;
        int posDiagnostico = matriz[0].length - 1;
        
        String doenca;
        for (String[] matriz2 : matriz) {
            doenca = matriz2[posDiagnostico];
            if (!ocorrencia.containsKey(doenca.toUpperCase())) {
                for (String[] matriz1 : matriz) {
                    if (matriz1[posDiagnostico].equalsIgnoreCase(doenca)) {
                        numOcorrencias++;
                    }
                }
                ocorrencia.put(doenca.toUpperCase(), numOcorrencias);
            }
            numOcorrencias = 0;            
        }
                

        return ocorrencia;
    }
    
    @Override
    public Map<String, Double> porcentagem(){
        Map<String, Double> porcentagem = new HashMap<>();
        
        Map<String, Integer> ocorrencia = ocorrencia();
        
        Integer casos[] = new Integer[ocorrencia.size()];
        ocorrencia.values().toArray(casos);
        
        String doenca[] = new String[ocorrencia.size()];
        ocorrencia.keySet().toArray(doenca);
              
        int totalCasos = 0;
        for (Integer i : casos)
            totalCasos += i;
        
        for (int i = 0; i < ocorrencia.size(); i++)
            porcentagem.put(doenca[i], (double) (casos[i]/totalCasos));
        
        porcentagem.put(QuadroGeralComponent.OCORRENCIA, (double) totalCasos);
        return porcentagem;
    }
    
    @Override
    public Map<String, Integer> ocorrencia() {
        Map<String, Integer> ocorrencia = new HashMap<>();
        
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader("ocorrencia.txt"))) {
                while (reader.ready()) {
                    String linha = reader.readLine();
                    String[] dados = linha.split(":");
                    ocorrencia.put(dados[0], Integer.parseInt(dados[1]));
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.getStackTrace();
        } 
        
        return ocorrencia;
    }

    @Override
    public void plotarGrafico(Map<String, Integer> ocorrencia) {
        
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

    @Override
    public void gravarPorcentagem(Map<String, Double> porcentagem) {        
        try {
            File arq = new File("porcentagem.txt");
            
            if (!arq.exists())
                arq.createNewFile();
            
            Map<String, Integer> novasOcorrencias = new HashMap<>(porcentagem.size() - 1);
            
            porcentagem.keySet().forEach((valor) -> {
                novasOcorrencias.put(valor, (int)(porcentagem.get(valor) * porcentagem.get(QuadroGeralComponent.OCORRENCIA)));
            });
            
            gravarOcorrencia(novasOcorrencias);
            Map<String, Integer> ocorrencia = ocorrencia();
            
            Integer casos[] = new Integer[ocorrencia.size()];
            ocorrencia.values().toArray(casos);

            String doenca[] = new String[ocorrencia.size()];
            ocorrencia.keySet().toArray(doenca);

            int totalCasos = 0;
            for (Integer i : casos)
                totalCasos += i;
            
            porcentagem.clear();

            for (int i = 0; i < ocorrencia.size(); i++) 
                porcentagem.put(doenca[i], (double) (casos[i]/totalCasos));
                        
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arq))) {
                porcentagem.keySet().forEach((valor) -> {
                    try {
                        writer.append(valor + ":" + porcentagem.get(valor));                        
                        writer.newLine();
                    } catch (IOException ex) {
                        System.err.println("Não foi possível gravar nesse arquivo.");
                        ex.getStackTrace();
                    }
                });
            }
        }
        catch (IOException ex) {          
            System.err.println("Não foi possível abrir ou criar o arquivo.");
            ex.getStackTrace();
        }
    }

    @Override
    public void gravarOcorrencia(Map<String, Integer> ocorrencia) {
        try {
            File arq = new File("ocorrencia.txt");
            
            if (!arq.exists())
                arq.createNewFile();
            
            Map<String, Integer> ocorr = ocorrencia();
            ocorrencia.replaceAll((t, u) -> {
                int retorno;
                if (ocorr.containsKey(t)) {
                    retorno = u + ocorr.get(t);
                    ocorr.remove(t);
                } else
                    retorno = u;
                
                return retorno;
            });
            ocorrencia.putAll(ocorr);
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arq))) {
                ocorrencia.keySet().forEach((valor) -> {
                    try {                        
                        writer.append(valor + ":" + ocorrencia.get(valor));                        
                        writer.newLine();
                    } catch (IOException ex) {
                        System.err.println("Não foi possível gravar nesse arquivo.");
                        ex.getStackTrace();
                    }
                });
            }
        }
        catch (IOException ex) {          
            System.err.println("Não foi possível abrir ou criar o arquivo.");
            ex.getStackTrace();
        }
    }
}
