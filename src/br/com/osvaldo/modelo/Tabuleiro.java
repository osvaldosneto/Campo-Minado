package br.com.osvaldo.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import br.com.osvaldo.exception.ExplosaoException;

public class Tabuleiro {
	
	public int linhas;
	public int colunas;
	public int minas;
	
	public List<Campo> campos = new ArrayList<>();
	
	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;
		
		this.gerarCampos();
		this.associarVizinhos();
		this.sortearMinas();	
	}
	
	public void abrir(int linha, int coluna) {
		try {
			campos.stream().filter(c -> c.getLinha()==linha && c.getColuna()==coluna)
				.findFirst()
				.ifPresent(c -> c.abrir());
		} catch (ExplosaoException e){
			campos.forEach(c -> c.setAberto(true));
			throw e;
		}
	}
	
	public void alternarMarcacao(int linha, int coluna) {
		campos.stream().filter(c -> c.getLinha()==linha && c.getColuna()==coluna)
		.findFirst()
		.ifPresent(c -> c.alternarMarcacao());
	}
	
	public void gerarCampos() {
		for(int i=0; i<linhas; i++) {
			for(int j=0; j<colunas; j++) {	
				campos.add(new Campo(i,j));
			}
		}
	}
	
	public void associarVizinhos() {
		for(Campo c1 : campos) {
			for(Campo c2 : campos){
				c1.adicionarVizinho(c2);
			}
		}	
	}
	
	public void sortearMinas() {
		int minasArmadas = 0;
		Predicate<Campo> minado = c -> c.isMinado();
		
		while( minasArmadas <= this.minas) {
			//gerando numero aleatório n-1 tamanho da lista
			int n = (int) (Math.random() * campos.size());
			//minando o campo
			campos.get(n).setMinado(true);
			//capturando numero de minas
			minasArmadas = 1 + (int)(campos.stream().filter(minado).count());
		}
	}
	
	public boolean objetivoAlcancado() {
		Predicate<Campo> campo = c -> c.objetivoAlcancado();
		return campos.stream().allMatch(campo);
	}
	
	public void reiniciar() {
		campos.stream().forEach(c -> c.reiniciar());
		this.sortearMinas();
	}
	
	public String toString() {
		//stringbuilder
		StringBuilder sb = new StringBuilder();
		int cont = 0;
		
		sb.append("   ");
		for(int i=0; i<linhas; i++) {
			sb.append(" " + i + " ");
		}
		sb.append("\n");
		
		
		for(int i = 0; i<linhas; i++) {
			sb.append(" " + i + " ");
			for(int j=0; j<colunas; j++) {
				sb.append(" ");
				sb.append(campos.get(cont));
				sb.append(" ");
				cont ++;
			}
			sb.append("\n");
		}
		return sb.toString(); 
	} 
	
}
