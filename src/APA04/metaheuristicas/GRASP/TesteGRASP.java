

import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;

public class TesteGRASP {
	
	
	public static int[][] carregarFlowshop(String dir){
        File arquivo = new File(dir);
	   int[][] novo;
        try {
            FileReader fr = new FileReader(arquivo); //faz a leitura do arquivo
            String linha;
            BufferedReader br = new BufferedReader(fr);
            StringTokenizer st;
			int tarefas=0 , maquinas =0;
            while ((linha = br.readLine()) != null) {
                st = new StringTokenizer(linha);
		
                if (linha.startsWith("number")) {
                    linha = br.readLine();
                    st = new StringTokenizer(linha);

                    tarefas = Integer.parseInt(st.nextElement().toString());
                    maquinas = Integer.parseInt(st.nextElement().toString());
                    
                    System.out.println("Indices inseridos.");

                } else if (linha.startsWith("processing")) {
                    novo = new int[maquinas][tarefas];
                    linha = br.readLine();
                    for (int i = 0; i < maquinas; i++) {
                        st = new StringTokenizer(linha);
                        for (int j = 0; j < tarefas; j++) {
                            novo[i][j] = Integer.parseInt(st.nextElement().toString());
                        }
                        linha = br.readLine();

                    }
                 return novo;   
                }
            }
            br.close();
		return null;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
return null;
    }
	
	 public static void main(String[] args){
		String diretorio = System.getProperty("user.dir")+"\\entrada\\";
		
		int[][] problema = carregarFlowshop(diretorio+args[0]);
		GRASP test = new GRASP(problema.length,problema.length,0.5,60000,problema);
		Map<Integer,Integer> result = test.search();
		System.out.println(result);
	 }	 
	 

}
