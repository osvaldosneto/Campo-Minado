package br.com.osvaldo.views;

import java.util.Scanner;

import br.com.osvaldo.exception.ExplosaoException;
import br.com.osvaldo.exception.SairException;
import br.com.osvaldo.modelo.Tabuleiro;

public class TabuleiroConsole {
	
	private Tabuleiro tabuleiro;
	private Scanner in = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;		
			while(continuar) {
				cicloDoJogo();
				System.out.println("Deseja iniciar uma nova partida? (S/N)");
				String resposta = in.nextLine();
				
				if(resposta.equalsIgnoreCase("N")) {
					continuar = false;
				} else {
					tabuleiro.reiniciar();
				}	
			}		
		} catch (SairException e) {
			System.out.println("Tchau!!!");
		} finally {
			in.close();
		}	
	}

	private void cicloDoJogo() {
		try {
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro.toString());
				String digitado [] = this.capturaValorDigitado("Digite (x, y): ").split(",");			
				
				String acao = capturaValorDigitado("1 - Abrir ou 2 - (Des)Marcar");
				if(acao.equalsIgnoreCase("1")) {
					tabuleiro.abrir(Integer.parseInt(digitado[0]), Integer.parseInt(digitado[1]));
				} else if (acao.equalsIgnoreCase("2")) {
					tabuleiro.alternarMarcacao(Integer.parseInt(digitado[0]), Integer.parseInt(digitado[1]));
				}
			}
			System.out.println("Você ganhou!!!");
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro.toString());
			System.out.println("Você perdeu");
		}
	}
	
	private String capturaValorDigitado(String texto) {
		System.out.println(texto);
		String digitado = in.nextLine();
		
		if(digitado.equalsIgnoreCase("sair")) {
			throw new SairException();
		}		
		return digitado;
	}

}
