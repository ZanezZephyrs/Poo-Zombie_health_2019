# Componente `QuadroGeralComponent`

Campo | Valor
----- | -----
Classe | `pt.c08componentes.s20catalog.s10ds.DataSetComponent`
Autores | `João` e `Matheus`
Objetivo | `Análise de dados`
Interface | `IQuadroGeral`
~~~
public interface IQuadroGeral {
    public Map<String, Double> porcentagem(String matriz[][]);
    public Map<String, Integer> ocorrencia(String matriz[][]);
    public void plotarGrafico(Map<String, Integer> dicionario);
}
~~~

## Detalhamento das Interfaces

### Interface `IQuadroGeral`
`<papel da interface>`.

Método | Objetivo
-------| --------
`porcentagem` | Retorna um dicionário que associa cada doença com a porcentagem de pacientes que a possuem, os dados são fornecidos pelo parâmetro `matriz`
`ocorerncia` | Retorna um dicionário que associa cada doença com o número de ocorrências, os dados são fornecidos pelo parâmetro `matriz`
`plotarGrafico` | Plota um gráfico de barras que relaciona cada doença com o número de ocorrências, os dados são fornecidos pelo parâmetro `dicionario`
