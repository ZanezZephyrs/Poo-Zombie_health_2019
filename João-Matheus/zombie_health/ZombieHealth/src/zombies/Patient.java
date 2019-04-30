/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombies;

import interfaces.IPatient;
import interfaces.ITableProducer;
import java.util.Random;

/**
 *
 * @author Matheus
 */
public class Patient implements IPatient {
    public static final String YES = "yes";
    public static final String NO = "no";
    public static final String UNKNOWN = "unknown";
    
    private ITableProducer producer;
    
    private String name;
    private boolean info[];
    private String diagnostic;

    public Patient(String name) {
        producer = null;
               
        this.name = name;
        info = null;
        diagnostic = "";
    }

    public String getName() {
        return name;
    }
    
    @Override
    public String ask(String question) {
        String attributes[] = producer.requestAttributes();
        for (int a = 0; a < attributes.length-1; a++)
            if (question.toUpperCase().contains( (attributes[a]).toUpperCase()) ) {
                System.out.println("Senhore " + name + ": " + (info[a] ? YES : NO));
                return info[a] ? YES : NO;
            }
        
        System.out.println("Senhore " + name + ": " + UNKNOWN);
        return UNKNOWN;
    }

    @Override
    public boolean finalAnswer(String answer) {
        return answer.toUpperCase().contains(diagnostic.toUpperCase());
    }
    
    @Override
    public void connect(ITableProducer producer) {
        this.producer = producer;
        String instances[][] = this.producer.requestInstances();
        
        Random ran = new Random();
        int x = ran.nextInt(instances.length);
        
        info = new boolean[(instances[x]).length];
        
        for(int i = 0; i < (instances[x]).length - 1;i++){
            info[i] = (instances[x][i]).equalsIgnoreCase("t") || (instances[x][i]).equalsIgnoreCase("1");
        }
        this.diagnostic = instances[x][(instances[x]).length - 1];
    }
}
