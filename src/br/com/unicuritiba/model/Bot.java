package br.com.unicuritiba.model;

import java.util.Random;

public class Bot extends Participante {

	Random random = new Random();

	public Bot(String nome) {
		super(nome);
	}

	public void colocarNaviosAleatorios(Tabuleiro tabuleiroBot) {
		int[] tamanhos = {1, 3, 5, 3, 5};
		for (int tamanho : tamanhos) {
			boolean colocado = false;
			while (!colocado) {
				int linha = random.nextInt(10);
				int coluna = random.nextInt(10);
				String orientacao = random.nextBoolean() ? "H" : "V";
				
				if (tabuleiroBot.verificarSeCabe(linha, coluna, tamanho, orientacao)) {
					Navio navio = new Navio(tamanho);
					tabuleiroBot.preencherTabuleiro(linha, coluna, navio, orientacao);
					colocado = true;
				}
			}
		}
	}
	
	public void tiroDoBot(Tabuleiro tabuleiroJogador, Bot bot) {

		int tiroBotL = random.nextInt(10);
		int tiroBotC = random.nextInt(10);

		while(tabuleiroJogador.tiroJaEfetuado(tiroBotL, tiroBotC)){
			tiroBotL = random.nextInt(10);
			tiroBotC = random.nextInt(10);
		}

		if (Tabuleiro.NAVIO.equals(tabuleiroJogador.getItemTabuleiro(tiroBotL, tiroBotC))) {
			System.out.println("-> O BOT atirou na posição " + tiroBotL + "" + tiroBotC + " e acertou um navio!");
			tabuleiroJogador.setTabuleiro(tiroBotL, tiroBotC, Tabuleiro.ACERTO);
			bot.setAcertos();
		} else {
			System.out.println("-> O BOT atirou na posição " + tiroBotL + "" + tiroBotC + " e acertou à Água!");
			tabuleiroJogador.setTabuleiro(tiroBotL, tiroBotC, Tabuleiro.AGUA_ATINGIDA);
		}
	}

	@Override
	public void intimidar(Participante ameacador, Participante ameacado) {
		System.out.println(ameacador.getNome() + " grita: ");
		System.out.println("- Destruirei todos seus navios "+ameacado.getNome()+"!");
	}

}