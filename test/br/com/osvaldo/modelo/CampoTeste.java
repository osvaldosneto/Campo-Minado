package br.com.osvaldo.modelo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.Test;

public class CampoTeste {
	
	private Campo campo = new Campo(2,2);
	
	@Test
	void testeVizinhoDistancia() {
		
		Campo vizinho = new Campo(3, 2);
		boolean add = campo.adicionarVizinho(vizinho);
		assertEquals(true, add);
		
		Campo v2 = new Campo(4,2);
		add = campo.adicionarVizinho(v2);
		assertEquals(false, add);
		
		Campo v3 = new Campo(4,5);
		add = campo.adicionarVizinho(v3);
		assertEquals(false, add);
		
		Campo v4 = new Campo(3,3);
		add = campo.adicionarVizinho(v4);
		assertEquals(true, add);
		
		Campo v5 = new Campo(3, 1);
		add = campo.adicionarVizinho(v5);
		assertEquals(true, add);
	}
	
	@Test
	void testeAlternaMarcacao() {
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAlternaMarcacao2() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}
	
	
	
	@Test
	void testeAbrir() {
		Campo v1 = new Campo(1,1);
		boolean add = campo.adicionarVizinho(v1);
		Campo v2 = new Campo(1,2);
		add = campo.adicionarVizinho(v2);
		Campo v3 = new Campo(1,3);
		add = campo.adicionarVizinho(v3);
		Campo v4 = new Campo(2,1);
		add = campo.adicionarVizinho(v4);
		Campo v5 = new Campo(2,3);
		add = campo.adicionarVizinho(v5);
		Campo v6 = new Campo(3,1);
		add = campo.adicionarVizinho(v6);
		Campo v7 = new Campo(3,2);
		add = campo.adicionarVizinho(v7);
		Campo v8 = new Campo(3,3);
		v8.setMinado(true);
		add = campo.adicionarVizinho(v8);
		
		assertTrue(campo.abrir());
	}
	
	@Test
	void testeAbirVizinho() {
		Campo v1 = new Campo(1,1);
		boolean add = campo.adicionarVizinho(v1);
		Campo v2 = new Campo(1,2);
		add = campo.adicionarVizinho(v2);
		Campo v3 = new Campo(1,3);
		add = campo.adicionarVizinho(v3);
		Campo v4 = new Campo(4,1);
		add = campo.adicionarVizinho(v4);
		Campo v5 = new Campo(2,3);
		add = campo.adicionarVizinho(v5);
		Campo v6 = new Campo(3,1);
		add = campo.adicionarVizinho(v6);
		Campo v7 = new Campo(3,2);
		add = campo.adicionarVizinho(v7);
		Campo v8 = new Campo(5,3);
		v8.setMinado(true);
		add = campo.adicionarVizinho(v8);
	
		campo.abrir();
		assertTrue(campo.isAberto());
		assertTrue(v1.isAberto());
		assertTrue(v2.isAberto());
		assertTrue(v3.isAberto());
		assertFalse(v4.isAberto());
		assertTrue(v5.isAberto());
		assertTrue(v6.isAberto());
		assertTrue(v7.isAberto());
		assertFalse(v8.isAberto());	
	}
	
	@Test
	void testeToString() {
		Campo c = new Campo(2,2);
		Campo c2 = new Campo(2,3);
		
		assertEquals("?", c.toString());
		
		c.setMarcado(true);
		assertEquals("x", c.toString());
		
		c.setMarcado(false);
		c.setAberto(true);
		c.setMinado(true);
		assertEquals("*", c.toString());
		
		c.setMinado(false);
		assertEquals(" ", c.toString());
		
		c2.setMinado(true);
		c.setMinado(false);
		c.adicionarVizinho(c2);
		assertEquals("1", c.toString());

	}
}
