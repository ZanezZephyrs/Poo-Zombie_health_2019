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
public interface IQuadroGeral {
    public Map<String, Double> porcentagem(String matriz[][]);  /* Retorna um dicionário que associa cada doença com a porcentagem de pacientes que a possuem */
    public Map<String, Integer> ocorrencia(String matriz[][]);  /* Retorna um dicionário que associa cada doença com o número de ocorrências */
    public void plotarGrafico(Map<String, Integer> ocorrencia); /* Plota um gráfico de barras que relaciona cada doença com o número de ocorrências */
}
