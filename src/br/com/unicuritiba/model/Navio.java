package br.com.unicuritiba.model;

public class Navio {
	
	private int tamanho;
	private String orientacao;
	private int posicao;
	
	public Navio(int tamanhoDoNavio) {
		this.tamanho = tamanhoDoNavio;
		
	}
	
	public int getTamanho() {
		return tamanho;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	public String getOrientacao() {
		return orientacao;
	}
	public void setOrientacao(String orientacao) {
		this.orientacao = orientacao;
	}
	public int getPosicao() {
		return posicao;
	}
	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	
	
}
