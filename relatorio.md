#Introdução
O objectivo deste projecto foi a realização de um programa que calculasse o bioritmo dado uma data de nascimento ou, caso já existissem medições prévias, fazer o cálculo com os ajustes.
___
#Funcionamento
Este projecto está dividido em duas partes:  a **classe** **Bioritmo** que, quando instanciada, se receber um *long* que representa uma data em milissegundos, irá trata-lo como a data de nascimento, efectuar o respectivo cálculo e salvar num ficheiro os respectivos *Arraylist* com objectos *Medicao*. Caso receba uma *String*, esta será o caminho até um ficheiro de dados.
A **classe** **Menu** tem como finalidade a de pedir ao utilizador e verificar a informação necessária para os ajustes, fornecê-la à classe bioritmo e pedir as consequentes respostas.



O calculo do bioritmo é feito no método **calcula()**, que recebe ao receber um *Calendar* calculará os dias passados desde a data de nascimento até à data actual do sistema utilizando o método **getDias()** para serem usados pelo método **formula()** que tem a formula de calculo do bioritmo ((2 x *PI* x dias_passados)/período).

O método **ajusta()** verificará se o valor está na fase ascendente ou descendente e ajustará os períodos conforme o input do utilizador e a fase em questão.

O ficheiro com as medições e ajustes será guardado no método **save()** e no caso de ja existir medição nesta data, substituirá os ajustes feitos.

___
#Referências
http://en.wikipedia.org/wiki/Biorhythm
http://docs.oracle.com/javase/6/docs/api/
