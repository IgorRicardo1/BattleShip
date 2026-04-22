package br.com.unicuritiba;

import java.util.Scanner;

import br.com.unicuritiba.model.Bot;
import br.com.unicuritiba.model.Jogador;
import br.com.unicuritiba.model.Navio;
import br.com.unicuritiba.model.Tabuleiro;

public class Main {

	public static void main(String[] args) {

		Tabuleiro tabuleiroJogador = new Tabuleiro();
		Tabuleiro tabuleiroBot = new Tabuleiro();
		Tabuleiro tabuleiroBotVisivel = new Tabuleiro();
		Scanner scanner = new Scanner(System.in);
		
		int[] tamanhosNavios = {1, 3, 5, 3, 5};

		System.out.println("Bem Vindo ao Battle Extreme");
		System.out.println("Digite o nome do Jogador: ");
		String apelido = scanner.nextLine();
		Jogador jogador = new Jogador(apelido);
		
		System.out.println("Digite o nome do Robo Adversário: ");
		String apelidoDoRobo = scanner.nextLine();
		Bot roboAdversario = new Bot(apelidoDoRobo);
		
		System.out.println("Batalha declarada "+jogador.getNome()+" vs "+roboAdversario.getNome());

		System.out.println("");
		System.out.println("=========Campo Jogador=========");
		tabuleiroJogador.mostrarTabuleiro();

		System.out.println("Posicione sua frota!");
		
		for (int tamanho : tamanhosNavios) {
			boolean colocado = false;
			while (!colocado) {
				System.out.println("\nPosicionando navio de tamanho " + tamanho);
				System.out.println("Digite a coordenada inicial (ex: 00 a 99, onde o primeiro digito é a Linha e o segundo a Coluna):");
				String coordenada = scanner.nextLine();
				if (!coordenada.matches("\\d{2}")) {
					System.out.println("Coordenada inválida! Digite dois números (linha e coluna).");
					continue;
				}
				
				int linha = Character.getNumericValue(coordenada.charAt(0));
				int coluna = Character.getNumericValue(coordenada.charAt(1));
				
				String orientacao = "H";
				if (tamanho > 1) {
					System.out.println("Digite a orientação (H para horizontal, V para vertical):");
					orientacao = scanner.nextLine().toUpperCase();
					if (!orientacao.equals("H") && !orientacao.equals("V")) {
						System.out.println("Orientação inválida!");
						continue;
					}
				}
				
				if (tabuleiroJogador.verificarSeCabe(linha, coluna, tamanho, orientacao)) {
					Navio navio = new Navio(tamanho);
					tabuleiroJogador.preencherTabuleiro(linha, coluna, navio, orientacao);
					colocado = true;
					tabuleiroJogador.mostrarTabuleiro();
				} else {
					System.out.println("Não é possível colocar o navio nesta posição! Tente novamente.");
				}
			}
		}
		
		System.out.println("Frota posicionada com sucesso!");
		
		roboAdversario.colocarNaviosAleatorios(tabuleiroBot);

		// Loop do jogo
		boolean jogoAtivo = true;
		while (jogoAtivo) {
			System.out.println("\n-------------------------------------------------");
			System.out.println("===========Campo Bot===========");
			tabuleiroBotVisivel.mostrarTabuleiro();
			
			System.out.println("Sua vez de atirar, " + jogador.getNome() + "!");
			System.out.println("Digite a coordenada do tiro (ex: 00 a 99): ");
			String coordenadaTiro = scanner.nextLine();
			
			if (!coordenadaTiro.matches("\\d{2}")) {
				System.out.println("Coordenada inválida!");
				continue;
			}
			
			int tiroL = Character.getNumericValue(coordenadaTiro.charAt(0));
			int tiroC = Character.getNumericValue(coordenadaTiro.charAt(1));
			
			if (tabuleiroBot.tiroJaEfetuado(tiroL, tiroC)) {
				System.out.println("Você já atirou nesta posição! Escolha outra.");
				continue;
			}
			
			jogador.atirar(tiroL, tiroC, tabuleiroBot, tabuleiroBotVisivel, jogador);
			
			if (jogador.getAcertos() >= 17) {
				System.out.println("===============================");
				System.out.println(jogador.getNome()+ " ganhou com "+ jogador.getPontos()+ " pontos!");
				System.out.println("===============================");
				jogoAtivo = false;
				break;
			}
			
			System.out.println("\nVez do " + roboAdversario.getNome() + " atirar!");
			roboAdversario.tiroDoBot(tabuleiroJogador, roboAdversario);
			
			System.out.println("\n=========Campo Jogador=========");
			tabuleiroJogador.mostrarTabuleiro();
			
			if (roboAdversario.getAcertos() >= 17) {
				System.out.println("===============================");
				System.out.println(roboAdversario.getNome() +" humilhou " + jogador.getNome() + "!" + "\n" + "Você perdeu!");
				System.out.println("===============================");
				jogoAtivo = false;
				break;
			}
		}
		
		scanner.close();
	}
}
