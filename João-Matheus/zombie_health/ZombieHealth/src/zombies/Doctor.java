/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombies;

import interfaces.IDoctor;
import interfaces.IResponder;
import interfaces.ITableProducer;

/**
 *
 * @author Matheus
 */
public class Doctor implements IDoctor {
    private ITableProducer producer;
    private IResponder responder;
    
    private String name;
    private String info[];
    private String diagnostic;
    
    public Doctor(String name) {
        producer = null;
        responder = null;
        
        this.name = name;
        info = null;
        diagnostic = "";
    }
    
    private String getDiagnostic() {
        String reference[][] = producer.requestInstances();
        String diag;
        int differences = 0; 
        for (int i = 0; i < reference.length; i++) {
            for (int j = 0; j < reference[i].length-1; j++)
                if (info[j].equalsIgnoreCase(Patient.UNKNOWN) || !(reference[i][j].equalsIgnoreCase(info[j].equalsIgnoreCase(Patient.YES) ? "1" : "0") || reference[i][j].equalsIgnoreCase(info[j].equalsIgnoreCase(Patient.YES) ? "t" : "f"))) {
                    differences++;
                    break;
                }
            
            if (differences == 0) {
                diag = reference[i][reference[0].length - 1];
                if (!diagnostic.toUpperCase().contains(diag.toUpperCase())) {
                    if (!diagnostic.equals(""))
                        diagnostic += " or ";
                    diagnostic += diag;
                }
            } else
                differences = 0;
        }
         
        if (diagnostic == "")
            diagnostic = Patient.UNKNOWN;
        return diagnostic;
    }
    
    @Override
    public void startInterview() {
        if (responder != null) {
            String attributes[] = producer.requestAttributes();
            for (int i = 0; i < attributes.length-1; i++) {
                System.out.println("Doctor " + name + ": Do you have " + producer.requestAttributes()[i] + "?");
                info[i] = responder.ask(producer.requestAttributes()[i]);
            }
            
            String guess = getDiagnostic();
            System.out.println("Doctor " + name + ": I think you've got " + guess);
            System.out.println("Doctor " + name + ": " + (responder.finalAnswer(guess) ? "I'm right =)" : "I'm wrong =("));
        } 
    }

    @Override
    public void connect(IResponder responder) {
        this.responder = responder;
    }
    
    @Override
    public void connect(ITableProducer producer) {
        this.producer = producer;
        
        info = new String[producer.requestAttributes().length - 1];
        for (int i = 0; i < info.length; i++)
            info[i] = Patient.UNKNOWN;
    }
}
