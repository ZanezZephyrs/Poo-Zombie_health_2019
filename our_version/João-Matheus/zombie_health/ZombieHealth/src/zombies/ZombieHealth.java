/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombies;

import data.DataSetComponent;
import interfaces.IDataSet;
import interfaces.IDoctor;
import interfaces.IPatient;

/**
 *
 * @author ra222142
 */
public class ZombieHealth {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IDataSet dataset = new DataSetComponent();
        dataset.setDataSource("/home/ec2018-fra/ra222142/Documents/mc322/zombie/zombie_health/ZombieHealth/src/data/tables/zombie-health-new-cases500.csv");
        
        // instanciando o componente paciente
        IPatient aPatient = new Patient("Santanche");

        // conectando-o no componente DataSet
        aPatient.connect(dataset);

        // instanciando o componente doutor louco
        IDoctor cDoctor = new Doctor("Marcos");

        // conectando-o ao componente DataSet
        cDoctor.connect(dataset);

        // conectando-o ao componente paciente
        cDoctor.connect(aPatient);
        
        // executando a entrevista
        cDoctor.startInterview();
    }
    
}
