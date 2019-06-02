# Componente `DateAnalysis`

Campo | Valor
----- | -----
Autores | `4ZHealth`
Objetivo | `Análise da progressão do numero de doenças em função do tempo'`
Interface | `IDate_Analysys'`
~~~
public interface iDate_Analysis {

    public void setPath(String path);
    public void Armazena(String[][] a, int month);
    public String[][] Request_date_table();
    public void Plot_date_graph();
    public void Reset_data();

}

~~~

## Detalhamento das Interfaces

### Interface `iDate_Analysis`

Método | Objetivo
-------| --------
`setPath` | Guarda na classe um path/caminho de um csv que sera utilizado nas demais funções, esse metodo deve ser chamado antes de qualquer outro para evitar erros.
`Armazena` | Utiliza o path setado em SetPath(se o csv não existe, o csv é criado), registra o numero de ocorrencias presente na tabela. Obs: A matriz passada deve ter como ultima coluna o nome da doença do paciente.
`Request_date_table` | Le as informações do csv e retorna como matriz String[][].
`Plot_date_graph` | Utiliza as Informações do csv para plotar um grafico(Mes x N_ocorrencias).
`Reset_data` | Apaga as informações guardadas no csv.



Exemplos:

    https://docs.google.com/document/d/1QQYM59Yar6uYP2JO2LzJOMrdEyrfzE2IwmeKpgHgBFU/edit?usp=sharing

