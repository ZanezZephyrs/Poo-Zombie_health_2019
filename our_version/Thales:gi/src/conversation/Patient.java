    
package conversation;
import interfaces.*;
import java.util.Random;




public class Patient implements IResponder {
    private int patientN = 0;
    private ITableProducer producer;
    private String attributes[];
    private String patientInstance[];
    
    public void connect(ITableProducer producer) {
        this.producer = producer;

        attributes = producer.requestAttributes();
        String instances[][] = producer.requestInstances();

        patientN = (int)(Math.random() * instances.length);
        patientInstance = instances[patientN];
        
        System.out.println("Patient selected: " + patientN);
        System.out.println("Patient's disease: " + patientInstance[attributes.length - 1]);
    }
    
    public String ask(String question) {
        String result=null;

        for (int a = 0; a < attributes.length-1 ; a++){
            if (question.toLowerCase().equalsIgnoreCase(attributes[a].toLowerCase())){
                if (patientInstance[a].equalsIgnoreCase("1")){
                    result = "1";
                }
                else if(patientInstance[a].equalsIgnoreCase("0")){
                    result = "0";
                }
                else{
                    result = "unknown";
                }
            }
        }

        return result;
    }

    public boolean finalAnswer(String answer) {
        boolean result = false;
        if (answer.equalsIgnoreCase(patientInstance[attributes.length - 1]))
            result = true;
        return result;
    }
 }
