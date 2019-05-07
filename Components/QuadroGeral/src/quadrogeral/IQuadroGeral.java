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
public interface IQuadroGeral extends IQuadroEspecifico, IArquivoDados {
    /* Lê o arquivo porcentagem.txt e retorna um dicionário (map) relacionando as porcentagem com as doenças */
    public Map<String, Double> porcentagem();
    /* Lê o arquivo ocorrencia.txt e retorna um dicionário (map) relacionando o número de ocorrência com as doenças */
    public Map<String, Integer> ocorrencia();
    /* Plota um gráfico de barras que relaciona cada doença com o número de ocorrências */
    public void plotarGrafico(Map<String, Integer> ocorrencia); 
}