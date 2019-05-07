# Componente `QuadroGeralComponent`

Campo | Valor
----- | -----
Classe | `Poo-Zombie_health_2019.Components.QuadroGeral.src.quadrogeral`
Autores | `João` e `Matheus`
Objetivo | `Análise de dados percentuais e do número de ocorrência das doenças`
Interface | `IQuadroGeral`
~~~
public interface IQuadroGeral {
    public Map<String, Double> porcentagem(String matriz[][]);
    public Map<String, Integer> ocorrencia(String matriz[][]);
    public void plotarGrafico(Map<String, Integer> ocorrencia);
}
~~~

## Detalhamento das Interfaces

### Interface `IQuadroGeral`
`Analisa uma tabela de doenças e fornece a relação entre a doença e a porcentagem de ocorrência bem como a relação entre a doença e o número absoluto de ocorrência (tanto lógica quanto gráfica)`.

Método | Objetivo
-------| --------
`porcentagem` | Retorna um dicionário (Map) que associa cada doença com a porcentagem de pacientes que a possuem, os dados são fornecidos pelo parâmetro `matriz`
`ocorrencia` | Retorna um dicionário (Map) que associa cada doença com o número de ocorrências, os dados são fornecidos pelo parâmetro `matriz`
`plotarGrafico` | Plota um gráfico de barras que relaciona cada doença com o número de ocorrências, os dados são fornecidos pelo parâmetro `ocorrencia`
