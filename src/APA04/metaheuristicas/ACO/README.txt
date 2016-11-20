
 @author Robertson Lima - 11403755     (2016.1)
 
 Algoritmo de otimiza��o por colonia de formiga para obter solu��o de menor
 makespan para o problema de agendamento de tarefas (Flowshop).

 O algoritmo cria uma colonia com N formigas e itera durante o tempo especificado
 de modo que as formigas ir�o criar solu��es aleatoriamente, em seguida ir�o tentar
 melhor�-las e por fim, a formiga de melhor solu��o ir� deixar um rasto de ferom�nio
 na trilha. A matriz de trilhas tem todos os valores reduzidos a cada itera��o do loop
 de modo que apenas as trilhas criadas com boas rotas (neste caso, melhor tempo de 
 agendamento) ter�o os valores incrementados.

 O algoritmo conta com uma fun��o para o c�lculo do makespan que reaproveita
 caso a busca local tenha afetado apenas tarefas que n�o seja a primeira.
 Ou seja, caso a troca aconte�a no meio do vetor, os valores dos indices menores
 n�o precisar�o ser recalculados.




INSTRU��ES DE UTILIZA��O

1. PARA COMPILAR, EXECUTE O ARQUIVO makefile.bat

2. PARA EXECUTAR O TESTE, EXECUTE ARQUIVO iniciar.bat

OBS:
1. Os arquivos de entrada devem estar na pasta entrada.
2. Os parametros configur�veis est�o no arquivo configura�oes.txt na pasta entrada.
3. Para testar com outras entradas, abrir arquivo iniciar.bat com o editor de texto
   e mudar o nome do arquivo de teste e a sa�da desejada.