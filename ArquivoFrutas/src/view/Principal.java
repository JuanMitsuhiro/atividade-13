package view;

import controller.IArquivosController;
import controller.ArquivosController;
import java.io.IOException;

public class Principal {
	public static void main(String[] args) {
		
		IArquivosController arqCont = new ArquivosController();
		String path = "C:\\TEMP";
		String nome = "generic_food.csv";
		
		try {
			arqCont.readFrutas(path, nome);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
