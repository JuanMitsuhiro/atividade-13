package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ArquivosController implements IArquivosController{

	public ArquivosController(){
		super();
	}

	@Override
	public void readFrutas(String path, String nome) throws IOException {
		File arq = new File(path,nome);
		
		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			
			String linha = buffer.readLine();
			
			while (linha != null) {
				String[] linhaFruta = linha.split(",");
				if (linhaFruta[2].equals("Fruits")){
					System.out.printf("%-35s%-50s%s%n", linhaFruta[0], linhaFruta[1], linhaFruta[3]);
				}
				linha = buffer.readLine();
			}
			
			buffer.close();
			leitor.close();
			fluxo.close();
			
		} else {
			throw new IOException("Arquivo invalido.");
		}
	}
}
