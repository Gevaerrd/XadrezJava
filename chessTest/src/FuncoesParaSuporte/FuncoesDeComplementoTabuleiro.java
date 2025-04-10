/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package FuncoesParaSuporte;

import Jogador.Jogador;
import Peças.Peca;
import Peças.Position;
import Xadrez.Tabuleiro;
import java.util.Scanner;

/**
 *
 * @author Pichau
 */
public class FuncoesDeComplementoTabuleiro {

    private Tabuleiro tabuleiro;
    Scanner scanner = new Scanner(System.in);

    public FuncoesDeComplementoTabuleiro(Tabuleiro tabuleiro) {

        this.tabuleiro = tabuleiro;

    }

    public void clear() {
        // Limpa a tela de acordo com o sistema operacional
        try {
            String sistemaOperacional = System.getProperty("os.name").toLowerCase();
            if (sistemaOperacional.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Erro ao limpar a tela.");
        }
    }

    public int transformarCharEmNumero(char letraDaColuna) {
        return letraDaColuna - 'A';

    }

    public int pegarLinhaComPeca(Scanner scanner, String frase, Peca peca) { // Pega a linha entre 1 a 8
        while (true) {
            clear(); // Apenas uma vez, antes de pedir a entrada
            tabuleiro.mostrarTabuleiroComMovimentos(peca);
            System.out.println(frase);

            if (scanner.hasNextInt()) {
                int linha = scanner.nextInt();

                if (linha >= 1 && linha <= 8) {
                    return linha - 1;
                }

                else {
                    scanner.nextLine();
                    clear();
                    tabuleiro.mostrarTabuleiroComMovimentos(peca);
                }
            }

            else {
                scanner.nextLine(); // Limpa a entrada inválida
                clear();
                System.out.println("Erro: Entrada inválida. Por favor, digite um número entre 1 e 8.");
            }
        }
    }

    public int pegarLinhaSemPeca(Scanner scanner, String frase) { // Pega a linha entre 1 a 8
        while (true) {
            clear(); // Apenas uma vez, antes de pedir a entrada
            tabuleiro.mostrarTabuleiro();
            System.out.println(frase);

            if (scanner.hasNextInt()) {
                int linha = scanner.nextInt();

                if (linha >= 1 && linha <= 8) {
                    return linha - 1;
                }

                else {
                    scanner.nextLine();
                    clear();
                    tabuleiro.mostrarTabuleiro();
                }
            }

            else {
                scanner.nextLine(); // Limpa a entrada inválida
                tabuleiro.mostrarTabuleiro();
                frase = "Você não digitou um número, vamos novamente!";
            }
        }
    }

    public int pegarEntradaEValidarColunaComPeca(Scanner scanner, String frase, Peca peca) {

        while (true) {
            clear();
            System.out.println(frase);
            String coluna = scanner.next().toUpperCase();
            char caracterValidado = coluna.charAt(0);
            for (char c = 'A'; c <= 'H'; c++) {
                if (caracterValidado == c) {
                    return transformarCharEmNumero(c);
                }

            }

            clear();
            tabuleiro.mostrarTabuleiroComMovimentos(peca);

        }
    }

    public int pegarEntradaEValidarColunaSemPeca(Scanner scanner, String frase) {

        while (true) {
            clear();
            System.out.println(frase);
            String coluna = scanner.next().toUpperCase();
            char caracterValidado = coluna.charAt(0);
            for (char c = 'A'; c <= 'H'; c++) {
                if (caracterValidado == c) {
                    return transformarCharEmNumero(c);
                }

            }

            clear();
            tabuleiro.mostrarTabuleiro();

        }
    }

    public Peca pegarPecaQueVaiSerMovida(Jogador jogador) throws InterruptedException {

        while (true) {

            String fraseParaEscolherPeca = jogador.getColor() + ", Digite a linha da peça que você quer mover: ";
            String fraseParaEscolherColuna = jogador.getColor() + ", Digite a coluna da peça que você quer mover: ";
            int coluna = pegarEntradaEValidarColunaSemPeca(scanner, fraseParaEscolherColuna);
            clear();
            int linha = pegarLinhaSemPeca(scanner, fraseParaEscolherPeca);

            Position posicao = new Position(linha, coluna);

            // Pega peça na posicao escolhida
            Peca peca = tabuleiro.pegarPosicaoEspecifica(posicao);

            // Se tiver algum conteudo e for igual a cor do jogador
            if (peca != null && peca.getCor() == jogador.getColor()) {
                // Retorna a peca na posicao especifica
                return tabuleiro.pegarPosicaoEspecifica(posicao);
            }
        }

    }

    public Position pegarEChecarPosicaoDestinoValida(Position[][] possiveisMovimentos, Peca peca) {

        while (true) {
            String fraseParaLinhaDestino = "Digite a linha do destino da peça: ";
            String fraseParaColunaDestino = "Digite a coluna do destino da peça";
            int coluna = pegarEntradaEValidarColunaComPeca(scanner, fraseParaColunaDestino, peca);
            int linha = pegarLinhaComPeca(scanner, fraseParaLinhaDestino, peca);

            Position posicaoEscolhida = new Position(linha, coluna);

            // Verificar se a posição escolhida é válida

            // Vai comparar a posição escolhida pra ver se é um possivel movimento
            for (Position[] linhas : possiveisMovimentos) {
                for (Position posicao : linhas) {
                    if (posicao != null) {
                        if (posicao.equals(posicaoEscolhida)) {
                            return posicaoEscolhida;
                        }
                    }
                }
            }
            clear();
            tabuleiro.mostrarTabuleiroComMovimentos(peca);
            System.out.println("Posição invalida...");
        }

    }

    public Peca moverPecaParaDestinoEscolhido(Peca peca) {

        // Possiveis movimentos da peça
        Position[][] possiveisMovimentos = peca.possiveisMovimentos();

        while (true) {

            Position posicaoEscolhida = pegarEChecarPosicaoDestinoValida(possiveisMovimentos, peca);

            Peca checadorDePecaNaPosicaoEscolhida = tabuleiro.pegarPosicaoEspecifica(posicaoEscolhida);

            peca.setPrimeiroMovimento(false);

            if (checadorDePecaNaPosicaoEscolhida != null) {

                tabuleiro.comerPeca(checadorDePecaNaPosicaoEscolhida, peca); // Vai mover a atual para o lugar desse
                // nova
                return checadorDePecaNaPosicaoEscolhida; // Retornar
            }

            else if (checadorDePecaNaPosicaoEscolhida == null) {
                tabuleiro.moverParaPosicaoNoTabuleiroComPosicao(peca, posicaoEscolhida);
                return null;
                // Fazer a função de mover para posição caso não tenha nada e possa movimentar
            }

            clear();
            tabuleiro.mostrarTabuleiroComMovimentos(peca);
            System.out.println("Você escolheu uma posição errada ou da mesma cor que a sua...");
        }
    }

    public boolean checadorDeMovimentoParaPeca(Position[][] possiveisMovimentos) {

        boolean temMovimento = false;

        for (Position[] p : possiveisMovimentos) {
            for (Position posicao : p) {
                if (posicao != null) {
                    temMovimento = true;
                }
            }
        }
        return temMovimento;
    }

}
