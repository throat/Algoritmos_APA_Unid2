
 @author Robertson Lima - 11403755     (2016.1)
 
 Algoritmo de otimização por colonia de formiga para obter solução de menor
 makespan para o problema de agendamento de tarefas (Flowshop).

 O algoritmo cria uma colonia com N formigas e itera durante o tempo especificado
 de modo que as formigas irão criar soluções aleatoriamente, em seguida irão tentar
 melhorá-las e por fim, a formiga de melhor solução irá deixar um rasto de feromônio
 na trilha. A matriz de trilhas tem todos os valores reduzidos a cada iteração do loop
 de modo que apenas as trilhas criadas com boas rotas (neste caso, melhor tempo de 
 agendamento) terão os valores incrementados.

 O algoritmo conta com uma função para o cálculo do makespan que reaproveita
 caso a busca local tenha afetado apenas tarefas que não seja a primeira.
 Ou seja, caso a troca aconteça no meio do vetor, os valores dos indices menores
 não precisarão ser recalculados.




INSTRUÇÕES DE UTILIZAÇÃO

1. PARA COMPILAR, EXECUTE O ARQUIVO makefile.bat

2. PARA EXECUTAR O TESTE, EXECUTE ARQUIVO iniciar.bat

OBS:
1. Os arquivos de entrada devem estar na pasta entrada.
2. Os parametros configuráveis estão no arquivo configuraçoes.txt na pasta entrada.
3. Para testar com outras entradas, abrir arquivo iniciar.bat com o editor de texto
   e mudar o nome do arquivo de teste e a saída desejada.