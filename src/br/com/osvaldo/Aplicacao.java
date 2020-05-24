package br.com.osvaldo;


import br.com.osvaldo.modelo.Tabuleiro;
import br.com.osvaldo.views.TabuleiroConsole;

public class Aplicacao {
	
	public static void main(String[] args) {
		
		Tabuleiro tabuleiro = new Tabuleiro(10,10,6);	
		new TabuleiroConsole(tabuleiro);
		
	}

}
