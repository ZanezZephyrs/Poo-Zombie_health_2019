/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package generaldiagnosis;

import data.DataSetComponent;
import interfaces.IDataSet;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ra222142
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IGeneralDiagnosis gd = new GeneralDiagnosisComponent();

        IDataSet dataset = new DataSetComponent();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            JFileChooser file = new JFileChooser(App.class.getResource("../data/tables").getPath());
            file.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileFilter csv = new FileNameExtensionFilter("CSV", "csv");
            file.addChoosableFileFilter(csv);
            file.setFileFilter(csv);
            file.setAcceptAllFileFilterUsed(false);
            file.showOpenDialog(frame);
            File f = file.getSelectedFile();

            if (f != null) {
                dataset.setDataSource(f.getAbsolutePath());

                System.out.println("OCORRENCIAS mat");
                System.out.println("---------------");
                String ola[][] = gd.occurrence(dataset.requestInstances());
                for (String[] ola1 : ola) {
                    for (String ola2 : ola1)
                        System.out.print(ola2 + " | ");
                    System.out.println();
                }

                System.out.println("\nPORCENTAGENS mat");
                System.out.println("----------------");
                ola = gd.percentage(dataset.requestInstances());
                for (String[] ola1 : ola) {
                    for (String ola2 : ola1)
                        System.out.print(ola2 + " | ");
                    System.out.println();
                }
                
                

                System.out.println("\nOCORRENCIAS interna");
                System.out.println("-------------------");
                ola = gd.occurrence();
                for (String[] ola1 : ola) {
                    for (String ola2: ola1)
                        System.out.print( ola2 + " | ");
                    System.out.println();
                }
                
                
                System.out.println("\nPERCENTAGE interna");
                System.out.println("------------------");
                ola = gd.percentage();
                for (String[] ola1 : ola) {
                    for (String ola2 : ola1)
                        System.out.print(ola2 + " | ");
                    System.out.println();
                }
                
                System.out.println("\n");
                gd.plotChart();

            } else {
                JOptionPane.showMessageDialog(frame, "Não selecionou nenhum arquivo");
            }         
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        } finally {    
            frame.dispose();
        }
        
    }
    
}
