package br.com.unicuritiba.model;

public class Jogador extends Participante {
	
	protected int pontos;
	
	public Jogador(String nome) {
		super(nome);
		this.pontos = 100;
	}
	
	public int getPontos() {
		return pontos;
	}
	
	public void diminuirPontos() {
		pontos--;
	}
	
	public void atirar(int tiroL, int tiroC, Tabuleiro matrizBot, Tabuleiro matrizBotVisivel, Jogador jogador) {
		if (Tabuleiro.NAVIO.equals(matrizBot.getItemTabuleiro(tiroL, tiroC))) {
			System.out.println("KABUM! Você acertou um tiro!");
			matrizBotVisivel.setTabuleiro(tiroL, tiroC, Tabuleiro.ACERTO);
			matrizBot.setTabuleiro(tiroL, tiroC, Tabuleiro.ACERTO);
			jogador.setAcertos();
		} else {
			System.out.println("JOGADOR acertou à água");
			matrizBotVisivel.setTabuleiro(tiroL, tiroC, Tabuleiro.AGUA_ATINGIDA);
			matrizBot.setTabuleiro(tiroL, tiroC, Tabuleiro.AGUA_ATINGIDA);
			jogador.diminuirPontos();
		}
	}

	@Override
	public void intimidar(Participante ameacador, Participante ameacado) {
		System.out.println("");
		System.out.println(ameacador.getNome() + " grita: ");
		System.out.println("- " + ameacado.getNome() + "vai dormir com os camarões hoje!");
		System.out.println("");
	}
	
}
