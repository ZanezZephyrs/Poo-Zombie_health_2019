# GeneralDiangosisComponent - COMO USAR

Na documentação do projeto, você pode ver que nosso componente implementa a interface `IGenerealDiagnosis` que por sua vez estende `IDiagnosis`. Deste modo, você tem acesso a cinco métodos ao utilizar o `GeneralDiagnosisComponent`:

~~~
public String[][] percentage(String[][] data);
public String[][] occurrence(String[][] data);
public String[][] percentage();
public String[][] occurrence();
public void plotChart();
~~~

Veja como é **FÁCIL** utilizar nosso componente em seu projeto!

## 1º Passo:

Para criar uma instância do nosso componente:

```
GeneralDiagnosisComponent gd = new GeneralDiagnosisComponent();
```

## Métodos do `IDiagnosis`


Esses métodos precisam de uma fonte de dados para calcular o número de ocorrência e/ou porcentagem de cada doença.
Essa fonte é passada por parâmetro na forma de uma matriz de `String`. 
Ambos os métodos guardam o número total de ocorrências na última posição da matriz. 

Para que você seja capaz de usá-la com o `DataSetComponent` e obter os dados de arquivos em formato de tabela, como os de extensão `.csv`, utilizamos como padrão de entrada uma tabela semelhante àquela retornada pelo método `requestInstances()` do componente citado. Ou seja, espera-se que os diagnósticos de doenças esteja na última coluna da tabela.

Portanto, suponhamos a utilização de um arquivo `.csv` como fonte de dados e utilizemos o `DataSetComponent` para enteder o funcionamento do nosso componente.

```
DataSetComponet ds = new DataSetComponent();
ds.setDataSource('tutorial\zombie-health-new-cases20.csv'); // suponha o arquivo na mesma pasta do projeto

String data[][] = ds.requestInstances();
```

O conteúdo de `data`:
~~~
0, 1, 0, 1, 0, 0, 0, 1, Infecção Bacteriana
0, 0, 0, 1, 1, 0, 0, 0, Infecção Viral
0, 0, 0, 1, 1, 0, 0, 0, Infecção Viral
0, 0, 0, 1, 0, 1, 0, 0, Briga
0, 0, 0, 0, 0, 0, 0, 0, Briga
0, 0, 1, 0, 0, 1, 0, 1, T-Virus
0, 1, 1, 1, 0, 0, 0, 0, Zulombriga
0, 1, 0, 0, 0, 0, 0, 0, Zulombriga
1, 1, 0, 0, 0, 0, 0, 0, Infecção Bacteriana
0, 0, 0, 1, 1, 0, 0, 0, Infecção Viral
0, 0, 0, 1, 1, 1, 0, 0, Briga
0, 1, 1, 1, 0, 0, 0, 0, Zulombriga
0, 0, 1, 0, 0, 0, 0, 1, T-Virus
1, 0, 0, 1, 0, 0, 0, 1, Infecção Bacteriana
0, 0, 1, 0, 0, 1, 0, 1, T-Virus
1, 1, 0, 1, 0, 0, 0, 0, Infecção Bacteriana
0, 0, 1, 0, 0, 1, 0, 1, T-Virus
0, 1, 1, 1, 0, 0, 0, 0, Zulombriga
1, 1, 0, 1, 0, 0, 0, 1, Infecção Bacteriana
0, 0, 1, 0, 0, 0, 0, 0, Zulombriga
~~~

### método `occurrence`:

~~~
String ocorrencias[][] = gd.occurrence(data);

for (String[] a : ocorrencias) {
    for (String b : a)
        System.out.print(b +" | ");
    System.out.println();
}
~~~
#### saída:
~~~
Infecção Bacteriana | Infecção Viral | Briga | T-Virus | Zulombriga | total | 
5 | 3 | 3 | 4 | 5 | 20 | 
~~~

### método `percentage`:

~~~
String percentage[][] = gd.percentage(data);

for (String[] a : percentage) {
    for (String b : a)
        System.out.print(b +" | ");
    System.out.println();
}
~~~
#### saída:
~~~
Infecção Bacteriana | Infecção Viral | Briga | T-Virus | Zulombriga | total | 
25.0 | 15.0 | 15.0 | 20.0 | 25.0 | 20 | 
~~~


## Métodos do `IGeneralDiagnosis`


Esses métodos possuem fontes de dados internas (arquivos `.txt`) que guardam o número de ocorrência (`occurrence.txt`) e/ou porcentagem (`percentage.txt`) de cada doença. Essas fontes são utilizadas para as operações de todos os métodos exclusivos dessa interface.
Os métodos `occurrence()` e `percentage()` guardam o número total de ocorrências na última posição da matriz.


### método `occurrence`:

~~~
String ocorrencias[][] = gd.occurrence();

for (String[] a : ocorrencias) {
    for (String b : a)
        System.out.print(b +" | ");
    System.out.println();
}
~~~
#### saída:
~~~
Infecção Bacteriana | 5 | 
Infecção Viral | 3 | 
Briga | 3 | 
T-Virus | 4 | 
Zulombriga | 5 | 
total | 20 | 
~~~

### método `percentage`:

~~~
String percentage[][] = gd.percentage();

for (String[] a : percentage) {
    for (String b : a)
        System.out.print(b +" | ");
    System.out.println();
}
~~~
#### saída:
~~~
Infecção Bacteriana | 25.0 | 
Infecção Viral | 15.0 | 
Briga | 15.0 | 
T-Virus | 20.0 | 
Zulombriga | 25.0 | 
total | 20 |  
~~~

### método `plotChart`:
Esse método plota os gráficos de `doença X ocorrência` e `doença X porcentagem` e salva em dois arquivos de imagem distintos: `occurrence.png` e `percentage.png`
~~~
gd.plotChart();
~~~
#### saída:
![gráfico de ocorrência](tutorial\occurrence.png)<br />
![gráfico de porcentagem](tutorial\percentage.png)
