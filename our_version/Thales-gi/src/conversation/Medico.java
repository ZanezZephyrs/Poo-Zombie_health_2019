package conversation;
import interfaces.*;

import java.util.ArrayList;


public class Medico implements IResponderReceptacle, IEnquirer {
		private ITableProducer producer;
		private IResponder paciente_em_atendimento;
	 	private String[] respostas;
	    private ArrayList<String> diagnostic;
	    public Medico() {
	        this.diagnostic=new ArrayList<String>();
	        this.paciente_em_atendimento=null;
	    }
	    
		public void connect(IResponder responder) {
			this.paciente_em_atendimento=responder;
			
		}
		
		public void connect(ITableProducer producer) {
		     this.producer = producer;
		}
	    
	    public void Mostra_diagnostico() {
	        System.out.println("possiveis diagnosticos:");
	        for(int i=0;i<this.diagnostic.size();i++) {
	            System.out.println(this.diagnostic.get(i));
	        }
	    }
	    
	    public void Diagnosticar(String [][] mat) {
	     
	        Mostra_diagnostico();
	    }
	
	
	public void startInterview() {
		if(this.paciente_em_atendimento==null) {
			System.out.println("Nenhum paciente em atendimento");
			return;
		}
		String attributes[] = producer.requestAttributes();
        String instances[][] = producer.requestInstances();
        this.respostas=new String[attributes.length];

		for(int i=0;i<attributes.length-1;i++) {
			this.respostas[i]=this.paciente_em_atendimento.ask(attributes[i]);
            /*System.out.println(this.paciente_em_atendimento.ask(attributes[i]));*/
		}
		   for(int i=0;i<instances.length;i++) {
	            for(int j=0;j<instances[i].length-1;j++) {   
                    System.out.println("instancia i j");
                    System.out.println(instances[i][j]);
                    System.out.println(this.respostas[j]);

                    
	                if(instances[i][j].equalsIgnoreCase(this.respostas[j])==false) {
	                    break;
	                }if(j==instances[i].length-2 ) {
	                    this.diagnostic.add(instances[i][j+1]);
	                }
	            }
	        }
		
		System.out.println(this.diagnostic.size());
        for(int i=0;i<diagnostic.size();i++) {
            if(this.paciente_em_atendimento.finalAnswer(diagnostic.get(i))){
                System.out.println("yes, it's correct");
            }
        }
		
	}



}

