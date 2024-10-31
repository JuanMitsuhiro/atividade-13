package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SteamController implements ISteamController{

	public SteamController(){
		super();
	}

	@Override
	public void lerJogosAnoMes(int ano, String mes, double media, String path, String nome) throws IOException {
		File arqSteam = new File(path,nome);
		if (arqSteam.exists() && arqSteam.isFile()) {
			FileInputStream fluxo = new FileInputStream(arqSteam);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			
			String linha = buffer.readLine();
			linha = buffer.readLine();
			
			while(linha!= null) {
				String[] linhaJogo = linha.split(",");
				
				String anoJogo = Integer.toString(ano);
				double mediaJogo = Double.parseDouble(linhaJogo[3]);

                if (linhaJogo[1].equals(anoJogo)  && linhaJogo[2].equalsIgnoreCase(mes) && mediaJogo >= media) {
					System.out.printf("%-50s%s%n", linhaJogo[0], linhaJogo[3]);
				}
                linha = buffer.readLine();
			}
			
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo inválido.");
		}
	}

	@Override
	public void criarArquivo(int ano, String mes, String path, String nomeNovo, String nomeArq) throws IOException {
		File dir = new File(path);
		File arq = new File(path, nomeNovo + ".csv");
		if (dir.exists() && dir.isDirectory()) {
			boolean existe = false;
			if(arq.exists()) {
				existe = true;
			}
			String conteudo = geraCsv(ano, mes, path, nomeNovo, nomeArq);
			FileWriter fileWriter = new FileWriter(arq, existe);
			PrintWriter print = new PrintWriter(fileWriter);
			print.write(conteudo);
			print.flush();
			print.close();
			fileWriter.close();
		} else {
			throw new IOException("Diretório inválido.");
		}
		
	}
	
	private String geraCsv(int ano, String mes, String path, String nomeNovo, String nomeArq) throws IOException{
		File arqSteam = new File(path,nomeArq);
		StringBuffer escreve = new StringBuffer();
		String linhaNovo = "";
		if (arqSteam.exists() && arqSteam.isFile()) {
			FileInputStream fluxo = new FileInputStream(arqSteam);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			
			String linha = buffer.readLine();
			linha = buffer.readLine();
			
			
			while(linha!= null) {
				String[] linhaJogo = linha.split(",");
				String anoJogo = Integer.toString(ano);

                if (linhaJogo[1].equals(anoJogo)  && linhaJogo[2].equalsIgnoreCase(mes)) {
            		linhaNovo = linhaJogo[0] + ";" + linhaJogo[3];
            		escreve.append(linhaNovo+"\n");
				}
                linha = buffer.readLine();
			}
			
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo inválido.");
		}
		return escreve.toString();
	}
	
}
