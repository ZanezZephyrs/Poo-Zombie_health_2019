/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

/**
 *
 * @author ra222142
 */
public interface IGeneralDiagnosis extends IDiagnosis {
    public String[][] percentage();
    public String[][] occurrence();
    public void plotChart(); 
}