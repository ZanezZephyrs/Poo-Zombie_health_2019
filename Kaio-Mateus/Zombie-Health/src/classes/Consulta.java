package classes;
import interfaces.*;
import java.util.ArrayList;

public class Consulta implements IConsulta{
	private ITableProducer producer;
	private IDoctor Doctor;
	private IPatient Patient;

	public void connect(ITableProducer producer) {
    		this.producer = producer;
	}

	public void connect(IDoctor Doctor){
		this.Doctor = Doctor;
		Doctor.startInterview();
	}
	
	public void connect(IPatient Patient) {
		this.Patient = Patient;
	}
	
	private int Pos_Max(double Vet[]){
	    double max = 0;
	    int pos = 0;
	    for(int i = 0; i < Vet.length; i++){
	    	if(Vet[i] > max){
	    		max = Vet[i];
	    		pos = i;
	        }
	    }
	    return pos;
	}
	
	public void Diagnostico(){
	        String instances[][] = producer.requestInstances();
	        ArrayList <String> Possiveis_Diagnosticos = Doctor.getDiagnostic();
	        String Diagnostico;
	        
	        if(Possiveis_Diagnosticos.size() != 1) {
		        double Resultados[] = new double[Possiveis_Diagnosticos.size()];
		        double ocorrencia;
		        
		        for(int k = 0; k < Possiveis_Diagnosticos.size(); k++){
		            ocorrencia = 0;
		            for(int i = 0; i < instances.length;i++){
		                    if((Possiveis_Diagnosticos.get(k)).equals(instances[i][instances[i].length-1]) ){
		                    	ocorrencia++;
		                    }
		            }	    Resultados[k] = ocorrencia/instances.length;
		        }	       
		        Diagnostico = Possiveis_Diagnosticos.get(Pos_Max(Resultados));
	        }
	        
	        else {
	        	Diagnostico = Possiveis_Diagnosticos.get(0);
	        }
            boolean result = Patient.finalAnswer(Diagnostico);
            System.out.println("Result: " + ((result) ? "I am right =)" : "I am wrong =("));
	
	 }
}
