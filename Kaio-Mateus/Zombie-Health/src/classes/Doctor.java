package classes;
import interfaces.* ;
import java.util.ArrayList;

public class Doctor implements IDoctor {
    private ITableProducer producer;
    private IResponder responder;
    private ArrayList<String> diagnostic =  null;

    public void connect(ITableProducer producer) {
        this.producer = producer;
    }
    
    public void connect(IResponder responder) {
        this.responder = responder;
    }
    
    public ArrayList<String> getDiagnostic(){
    	return diagnostic;
    }
        
    public void startInterview(){
        String attributes[] = producer.requestAttributes();
        String instances[][] = producer.requestInstances();
        diagnostic = new ArrayList<String>();
        String[] patientanswers = new String[attributes.length];
        
        for (int a = 0; a < attributes.length - 1; a++){
            String b = responder.ask(attributes[a]);
            System.out.println("[Doctor]"+ attributes[a]+ "?\n[Patient]" + b);
            patientanswers[a] = (b.equals("yes")) ? "t" : "f";
        }
        
        for(int i = 0; i < instances.length; i++){                    //Percorre a Matriz e compara os sintomas com cada instancia
            for(int j = 0; j < attributes.length -1 ; j++){
                if(!(instances[i][j]).equals(patientanswers[j]))    //Caso um sintoma seja diferente da instancia atuak ele vai verificar em outra instancia
                    break;
                else if(j == attributes.length -2){                  //Se todos os sintomas sao iguais
                    diagnostic.add(instances[i][attributes.length - 1]);
                }      
                
            }
        }

    }
    
}