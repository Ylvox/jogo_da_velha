package me.ylvox.main;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	static boolean continuar;
	static boolean gameover;
	static int jogadas;
	static String player1, player2;
	static String[][] jogo = new String[3][3];
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		
		continuar = true;
		
		do {
			jogadas = 0;
			gameover = false; 
			System.out.println("Informe o nome do jogador 1 (x)");
			player1 = scanner.next();
			System.out.println("Informe o nome do jogador 2 (o)");
			player2 = scanner.next();
			
			for(int i = 0; i<3; i++) {
				for(int j = 0; j<3; j++) {
					jogo[i][j] = "-";
				}
			}
			
			jogar();
			imprimirjogo();
			continuar();
			
		}while(continuar);	
		System.out.println("Obrigado por jogar!");
	}
	
	static void imprimirjogo() {
		
		for(int i = 0; i<3; i++) {
				System.out.println(jogo[i][0] + " | " + jogo[i][1] + " | " + jogo[i][2]);
		}
	}
	
	static void jogar() {
		
		int player = 1;
		boolean correto, vazio = true;
		
		do {
			if(player == 1) {
				int i, j;
				do {
					System.out.println("Vez de " + player1);
					imprimirjogo();
					System.out.println("Informe a casa (primeiro linha, depois coluna [1 ao 3])");
					
					do {
						i = scanner.nextInt() - 1;
						if(i < 0 || i > 2) {
							correto = false;
							System.out.println("Informe um valor de 1 a 3");
						}else {
							correto = true;
						}
					}while(!correto);
					do {
						j = scanner.nextInt() - 1;
						if(j < 0 || j > 2) {
							correto = false;
							System.out.println("Informe um valor de 1 a 3");
						}else {
							correto = true;
						}	
					}while(!correto);
					vazio = testevazio(i, j);
					if(!vazio) {
						System.out.println("Essa casa ja esta sendo usada");
					}
					
				}while(!vazio);
				jogo[i][j] = "x";
				jogadas++;
				verificarlinhasEcolunas();
				VerificarDiagonais();
				empate();
				player = 2;
			}
			
			if(player == 2 && !gameover) {
				int i, j;
				do {
					System.out.println("Vez de " + player2);
					imprimirjogo();
					System.out.println("Informe a casa (primeiro linha, depois coluna [1 ao 3])");
					
					do {
						i = scanner.nextInt() - 1;
						if(i < 0 || i > 2) {
							correto = false;
							System.out.println("Informe um valor de 1 a 3");
						}else {
							correto = true;
						}
					}while(!correto);
					do {
						j = scanner.nextInt() - 1;
						if(j < 0 || j > 2) {
							correto = false;
							System.out.println("Informe um valor de 1 a 3");
						}else {
							correto = true;
						}
					}while(!correto);
					vazio = testevazio(i, j);
					if(!vazio) {
						System.out.println("Essa casa ja esta sendo usada");
					}
					
				}while(!vazio);
				jogo[i][j] = "o";
				jogadas++;
				verificarlinhasEcolunas();
				VerificarDiagonais();
				empate();
				player = 1;
			}
			
		}while(!gameover);
	}
	
	static boolean testevazio(int i, int j) {
		if(jogo[i][j] != "-") {
			return false;
		}else {
			return true;
		}
	}
	
	static void continuar() {
		int op;
		do {
			System.out.println("Deseja continuar o jogo?");
			System.out.println("[1] - Sim");
			System.out.println("[2] - Nao");
			op = scanner.nextInt();
			if(op == 1) {
				continuar = true;
			}else {
				continuar = false;
			}
		}while(op != 1 && op != 2);
		
	}
	
	static void verificarlinhasEcolunas() {
		//Linhas
		for(int i = 0; i<3; i++) {
			if(jogo[i][0] == "x" && jogo[i][1] == "x" && jogo[i][2] == "x") {
				gameover = true;
				System.out.println("O jogador " + player1 + " venceu");
			}
		}
		for(int i = 0; i<3; i++) {
			if(jogo[i][0] == "o" && jogo[i][1] == "o" && jogo[i][2] == "o") {
				gameover = true;
				System.out.println("O jogador " + player2 + " venceu");
			}
		}
		//Colunas
		for(int j = 0; j<3; j++) {
			if(jogo[0][j] == "x" && jogo[1][j] == "x" && jogo[2][j] == "x") {
				gameover = true;
				System.out.println("O jogador " + player1 + " venceu");
			}
		}
		for(int j = 0; j<3; j++) {
			if(jogo[0][j] == "o" && jogo[1][j] == "o" && jogo[2][j] == "o") {
				gameover = true;
				System.out.println("O jogador " + player2 + " venceu");
			}
		}
	}
	
	static void VerificarDiagonais() {
		//Diagonal principal
		if(jogo[0][0] == "x" && jogo[1][1] == "x" && jogo[2][2] == "x") {
			gameover = true;
			System.out.println("O jogador " + player1 + " venceu");
		}
		if(jogo[0][0] == "o" && jogo[1][1] == "o" && jogo[2][2] == "o") {
			gameover = true;
			System.out.println("O jogador " + player2 + " venceu");
		}
		//Diagonal Secundaria
		if(jogo[0][2] == "x" && jogo[1][1] == "x" && jogo[2][0] == "x") {
			gameover = true;
			System.out.println("O jogador " + player1 + " venceu");
		}
		if(jogo[0][2] == "o" && jogo[1][1] == "o" && jogo[2][0] == "o") {
			gameover = true;
			System.out.println("O jogador " + player2 + " venceu");
		}
	}
	
	static void empate() {
		if(jogadas == 9 && !gameover) {
			gameover = true;
			System.out.println("Houve um empate");
		}
	}
}
