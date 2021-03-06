package date_analysis;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.*;

import interfaces.iDate_Analysis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Dimension;


public class DateAnalysis implements iDate_Analysis {
	String path;
	JFreeChart graf;
	ChartPanel panel;
	

	public DateAnalysis() {
		this.path=null;
	}
	
	public DateAnalysis(String path) {
		this.path=path;
	}
	
	public void setPath(String path) {
		this.path=path;
	}
	
	public void Reset_data() {
		File file = new File(path);
		if(file.exists()) {
			file.delete();
		}
	}
	
	
	
		
	/*-----------------------------------------------------------------*/	
	
		public String[][] numeroDoencas(String[][] Instances){
	        
	        String[] ordenado = new String[4];

	        for (int i = 0; i < Instances.length; i++) {
	            for (int j = 0; j < ordenado.length; j++) {
	                if(ordenado[j] == null)
	                    ordenado[j] = Instances[i][Instances[0].length -1];
	                if(Instances[i][Instances[0].length - 1].equals(ordenado[j]))
	                    break;
	                else
	                    if(j == ordenado.length - 1){
	                        ordenado[j+1] = Instances[i][Instances[0].length -1];
	                    }
	            }
	            if(ordenado[ordenado.length -1] != null){
	                String[] aux = new String[ordenado.length*2];
	                for(int x = 0; x < ordenado.length; x++) {
	                    aux[x] = ordenado[x];
	                }
	                ordenado = aux;
	            }
	        }
	        
	        int k = 0;
	        for (int i = 0; i < ordenado.length; i++) {
	            if(ordenado[i] == null)
	                break;
	            k++;
	        }

	        String[][] lista = new String[2][k];


	        for(int i = 0; i < lista[0].length; i++)
	            lista[1][i] = "0";
	        for(int i = 0; i < k; i++)
	            lista[0][i] = ordenado[i];

	        for (int i = 0; i < lista[0].length; i++){
	            for (int j = 0; j < Instances.length; j++) {
	                if (lista[0][i].equals(Instances[j][Instances[0].length - 1])) {
	                    lista[1][i] = Integer.toString(Integer.parseInt(lista[1][i]) + 1);
	                }

	            }
	        }

	        return lista;
	    }
		
		/*-----------------------------------------------------------------*/	
		
		private String[][] deleteCollum(String[][] matrix, int index) {
			ArrayList<ArrayList<String>> aux= new ArrayList<ArrayList<String>>();
			for(int i=0;i<matrix.length;i++) {
				ArrayList<String> line=new ArrayList<String>();
				for(int j=0;j<matrix[i].length;j++) {
					line.add(matrix[i][j]);
				}
				aux.add(line);
			}
			
			ArrayList<String[]> resp= new ArrayList<String[]>();
			for(int i=0;i<matrix.length;i++) {
				aux.get(i).remove(index);
				resp.add(aux.get(i).toArray(new String[aux.get(i).size()]));
				
			}
			
			return (String[][])resp.toArray(new String[aux.size()][]);
			
		}
		
		private int procura(String[] lista, String linha) {
			for(int i=0;i<lista.length;i++) {
				if(linha.toLowerCase().contains(lista[i].toLowerCase())) {
					return i;
				}
			}
			return -1;
		}
		
/*-----------------------------------------------------------------*/	
		private int N_virgulas(String linha) {
			int total=0;
			for(int i=0;i<linha.length();i++) {
				if(linha.charAt(i)==',') {
					total++;
				}
			}
			return total;
		}
		/*-----------------------------------------------------------------*/	

	@Override
	public void Armazena(String[][] a, int month) {
        /*System.out.println(new java.io.File("").getAbsolutePath());*/
        String write;
        boolean novo_arquivo;
		BufferedWriter bw = null;
		FileWriter fw = null;
		BufferedReader leitor=null;
	      try {

			File file = new File(path);
			File file2 = new File(path+".csv");

		
			  if (!file.exists()) {
			      file.createNewFile();
				  fw = new FileWriter(file);
				  novo_arquivo=true;

			  }else {
				  file2.createNewFile();
				  fw = new FileWriter(file2);
				  novo_arquivo=false;
				  leitor = new BufferedReader(new FileReader(path));


			  }
			  
			  bw = new BufferedWriter(fw);
			  String[][] aux=numeroDoencas(a);


			  if(novo_arquivo) {
				
				  
				  bw.write("Doenças,"+month);
				  bw.newLine();
					  for(int j=0;j<aux[0].length;j++) {
						  write=aux[0][j]+",";
						  write+=aux[1][j];
						  bw.write(write);
						  bw.newLine();
					  }
			  }else {
				  
				  int index, tam;
				  write=leitor.readLine();
				  tam=this.N_virgulas(write);
				  write+=","+month;
				  bw.write(write);
				  bw.newLine();
				  write=leitor.readLine();
				  while(write!=null){
					  index=procura(aux[0], write);
					  if(index==-1) {
						  write+=",No_Data";
					  }else {
						  write+=","+aux[1][index];
						  aux=this.deleteCollum(aux, index);
					  }
					  
					  bw.write(write);
					  bw.newLine();
					  write=leitor.readLine();
				  }
				  for(int i=0;i<aux[0].length;i++) {
					  write=aux[0][i];
					  for(int j=0;j<tam;j++) {
						  write+=",No_Data";
					  }
					  write+=","+aux[1][i];
					  bw.write(write);
					  bw.newLine();
					 
				  }
			  bw.close();
			  leitor.close();
			  
			  leitor = new BufferedReader(new FileReader(path+".csv"));
			  bw=new BufferedWriter(new FileWriter(file));
			  write=leitor.readLine();
			  do {
			  bw.write(write);
			  bw.newLine();
			  write=leitor.readLine();
			  }while(write!=null);
			  
			  file2.delete();
			  
			  bw.close();
			  leitor.close();
			  }
	      } catch (IOException ioe) {
		   ioe.printStackTrace();
		}finally{ 
		   try{
		      if(bw!=null)
			 bw.close();
		    
		   }catch(Exception ex){
		       System.out.println("Error in closing the BufferedWriter"+ex);
		    }
		}
	   }
	
	
	@Override
	public String[][] Request_date_table() {
		ArrayList<String[]> res = new ArrayList<String[]>();
		String[][] matriz=null;
	    try {
	      BufferedReader file = new BufferedReader(new FileReader(path));
	      String linha = file.readLine();
	      while (linha != null) {
	          String[] instLine = linha.split(",");
	          res.add(instLine);
	          linha = file.readLine();
	        }
	        matriz = res.toArray(new String[0][]);
		    file.close();
		    return matriz;

	      }
	        
	     catch (IOException erro) {
	      erro.printStackTrace();
	    }
	    
	    return matriz;
	}
	
	
	private XYDataset create_data(double[] x, ArrayList<double[]> y, ArrayList<String> labels) {
		DefaultXYDataset dataset=new DefaultXYDataset();
		
		
		for(int i=0;i<y.size();i++) {
			double [][] mat= {x,y.get(i)};
			dataset.addSeries(labels.get(i), mat);
			
		}
	

		return dataset;
		
	}
	
	public ChartPanel request_panel() {
		return this.panel;
	}
	
	

	@Override
	public void Plot_date_graph() {
		ArrayList<double[]> y = new ArrayList<double[]>();
		ArrayList<String> labels = new ArrayList<String>();
		String linha;
		double[] aux;
		double[] x;
	    try {
		      BufferedReader file = new BufferedReader(new FileReader(path));
		      linha=file.readLine();
		      String[] temp=linha.split(",");
	          x=new double[temp.length-1];
		      for(int i=1;i<temp.length;i++) {
		    	  x[i-1]=Double.parseDouble(temp[i]);
		      }
		      
		      
		      linha=file.readLine();

		      while (linha != null) {
		          String[] valores = linha.split(",");
		          aux=new double[valores.length-1];
		          labels.add(valores[0]);
		          for(int i=1;i<valores.length;i++) {
		        	  if(valores[i].equalsIgnoreCase("No_Data")) {
			        	  aux[i-1]=0;

		        	  }else {
		        	  aux[i-1]=Double.parseDouble(valores[i]);
		        	  }
		        	    
		          }
		          y.add(aux);
		          linha = file.readLine();
		        }
		      
		      
			    file.close();
			    XYDataset dados=this.create_data(x, y, labels);
				this.graf=ChartFactory.createScatterPlot("Progressão de Doenças ao decorrer do tempo","Mês", "Casos Registrados", dados, PlotOrientation.VERTICAL, true, true, true);
				this.panel=new ChartPanel(graf);
				this.panel.setPreferredSize(new Dimension(1000,1000));
				XYPlot plot = (XYPlot) graf.getPlot();
		        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		        renderer.setSeriesLinesVisible(0, true);
		        plot.setRenderer(renderer);
			    Janela_grafico janela=new Janela_grafico(this.panel);
				janela.setVisible(true);
			    


		      }
	    catch (IOException erro) {
		      erro.printStackTrace();
	    }
	    
		    
	}
	
	
	
}
