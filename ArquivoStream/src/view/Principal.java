package view;

import controller.ISteamController;
import controller.SteamController;
import java.io.IOException;

public class Principal {
	public static void main(String[] args) {
		
		ISteamController arqCont = new SteamController();
		String path = "C:\\Users\\julia\\Downloads";
		String nomeArq = "SteamCharts.csv";
		String nomeNovo = "novo";
		int ano = 2020;
		String mes = "September";
		double media = 3000.50;
		
		try {
			arqCont.lerJogosAnoMes(ano, mes, media, path, nomeArq);
			arqCont.criarArquivo(ano, mes, path, nomeNovo, nomeArq);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
