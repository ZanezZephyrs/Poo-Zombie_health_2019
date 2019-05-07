/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadrogeral;

import java.util.Map;

/**
 *
 * @author ra222142
 */
public interface IQuadroEspecifico {
    /* Retorna um dicionário (map) que associa cada doença com a porcentagem de pacientes que a possuem, dada uma tabela */
    public Map<String, Double> porcentagem(String matriz[][]);
    /* Retorna um dicionário (map) que associa cada doença com o número de ocorrências, dada uma tabela */
    public Map<String, Integer> ocorrencia(String matriz[][]);
    
}
