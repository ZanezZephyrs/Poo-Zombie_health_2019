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
public interface IArquivoDados {
    /* Armazena as porcentagens de pacientes infectados para cada doença no arquivo porcentagem.txt */
    public void gravarPorcentagem(Map<String, Double> porcentagem);
    /* Armazena o número de ocorrência de doença no arquivo ocorrencia.txt */
    public void gravarOcorrencia(Map<String, Integer> ocorrencia);
}

