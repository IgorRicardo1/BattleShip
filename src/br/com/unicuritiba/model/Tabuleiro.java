package br.com.unicuritiba.model;

public class Tabuleiro {
	
	public static final String AGUA = " .";
	public static final String NAVIO = " ■";
	public static final String ACERTO = " X";
	public static final String AGUA_ATINGIDA = " ~";

	private String[][] Tabuleiro;

	public Tabuleiro(){
		this.Tabuleiro = new String[10][10];
		for (int linha = 0; linha < 10; linha++) {
			for (int coluna = 0; coluna < 10; coluna++) {
				this.Tabuleiro[linha][coluna] = AGUA;
			}
		}
	}

	public String getItemTabuleiro(int linha, int coluna) {
		return Tabuleiro[linha][coluna];
	}

	public void setTabuleiro(int linha, int coluna, String valorInserido) {
		this.Tabuleiro[linha][coluna] = valorInserido;
	}

	public void mostrarTabuleiro() {
		System.out.println("    0  1  2  3  4  5  6  7  8  9");
		for (int linha = 0; linha < 10; linha++) {
			System.out.print(linha + " |");
			for (int coluna = 0; coluna < 10; coluna++) {
				System.out.print(this.Tabuleiro[linha][coluna] + " ");
			}
			System.out.println();
		}
		System.out.println("");
	}

	public boolean verificarSeCabe(int linha, int coluna, int tamanho, String orientacao) {
		if (orientacao.equalsIgnoreCase("H")) {
			if (coluna + tamanho > 10) return false;
			for (int i = 0; i < tamanho; i++) {
				if (Tabuleiro[linha][coluna + i].equals(NAVIO)) {
					return false;
				}
			}
		} else if (orientacao.equalsIgnoreCase("V")) {
			if (linha + tamanho > 10) return false;
			for (int i = 0; i < tamanho; i++) {
				if (Tabuleiro[linha + i][coluna].equals(NAVIO)) {
					return false;
				}
			}
		} else {
            return false;
        }
		return true;
	}

	public void preencherTabuleiro(int linha, int coluna, Navio navio, String orientacao) {
		navio.setOrientacao(orientacao);
		if (orientacao.equalsIgnoreCase("H")) {
			for (int i = 0; i < navio.getTamanho(); i++) {
				Tabuleiro[linha][coluna + i] = NAVIO;
			}
		} else if (orientacao.equalsIgnoreCase("V")) {
			for (int i = 0; i < navio.getTamanho(); i++) {
				Tabuleiro[linha + i][coluna] = NAVIO;
			}
		}
	}

	public boolean tiroJaEfetuado(int linha, int coluna) {
		String alvo = Tabuleiro[linha][coluna];
		return alvo.equals(ACERTO) || alvo.equals(AGUA_ATINGIDA);
	}
}
