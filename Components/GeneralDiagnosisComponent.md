# Componente `GeneralDiagnosisComponent`

Campo | Valor
----- | -----
Classe | `Poo-Zombie_health_2019.Components.Implementacao.GeneralDiagnosis`
Autores | `4ZHealth`
Objetivo | `Análise de dados percentuais e do número de ocorrência das doenças`
Interface | `IGeneralDiagnosis, IDiagnosis, IDataFile`
~~~
public interface IGeneralDiagnosis extends IDiagnosis {
    public String[][] percentual();
    public String[][] occurrence();
    public void plotChart(); 
}
~~~

## Detalhamento das Interfaces

### Interface `IGeneralDiagnosis`
Fornece as relações entre uma doença e os números absoluto e relativo (percentual) de ocorrência da doença.

Método | Objetivo
-------| --------
`percentual` | Retorna uma tabela que associa cada doença com a porcentagem de pacientes que foram diagnosticados com tal doença, os dados são fornecidos pelo arquivo texto interno percentual.txt.
`occurrence` | Retorna uma tabela que associa cada doença com o respectivo número de ocorrências, os dados são fornecidos pelo arquivo texto interno occurrence.txt.
`plotChart` | Plota dois gráficos de barras. Um relaciona cada doença com o número de ocorrências e o outro relaciona cada doença com o percentual de ocorrência. O resultado é gravado em arquivo de imagem.

### Interface `IDiagnosis`
Analisa uma tabela de doenças e fornece a relação entre a doença e a porcentagem de ocorrência bem como a relação entre a doença e o número absoluto de ocorrência.

Método | Objetivo
-------| --------
`percentual` | Retorna uma tabela que associa cada doença com a porcentagem de pacientes que foram diagnosticados com tal doença, os dados são fornecidos pela matriz `data`.
`occurrence` | Retorna uma tabela que associa cada doença com o respectivo número de ocorrências, os dados são fornecidos pela matriz `data`.
