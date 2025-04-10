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

    public Position pegarOrigem(Scanner scanner, Jogador jogador) {

        String frase = "Origem " + jogador.getColor() + ": ";

        while (true) {

            clear();
            System.out.println(frase);
            String source = scanner.nextLine().toUpperCase(); // Garantir que a entrada seja em maiúsculas

            // Verifica se a entrada tem pelo menos dois caracteres
            if (source.length() < 2) {
                tabuleiro.mostrarTabuleiro();
                System.out.println("Entrada inválida, tente novamente.");
                continue;
            }

            // Verifica se o caractere da coluna está entre 'A' e 'H'
            if (source.charAt(0) >= 'A' && source.charAt(0) <= 'H') {

                // Converte a coluna de 'A' até 'H' para números (0 a 7)
                int coluna = transformarCharEmNumero(source.charAt(0));

                // Tenta obter a linha (pode ser um número de 1 a 9 ou maior)
                int linha;
                try {
                    linha = Integer.parseInt(source.substring(1)); // Pega tudo depois do primeiro caractere (a linha)
                    linha = linha - 1;
                }

                catch (NumberFormatException e) {
                    System.out.println("Linha inválida...");
                    continue;
                }

                // Verifica se a posição é válida no tabuleiro
                if (tabuleiro.checadorDePecaNaPosicaoBooleano(new Position(linha, coluna))) {
                    return new Position(linha, coluna);
                }

                else {
                    tabuleiro.mostrarTabuleiro();
                    System.out.println("Posição inválida...");
                }
            } else {
                tabuleiro.mostrarTabuleiro();
                System.out.println("Posição inválida...");
            }
        }
    }

    public Position pegarDestino(Scanner scanner, Peca peca) {

        String frase = "Destino " + peca.getCor() + ": ";

        while (true) {

            clear();
            System.out.println(frase);
            String source = scanner.nextLine().toUpperCase(); // Garantir que a entrada seja em maiúsculas

            // Verifica se a entrada tem pelo menos dois caracteres
            if (source.length() < 2) {
                System.out.println("Entrada inválida, tente novamente.");
                continue;
            }

            // Verifica se o caractere da coluna está entre 'A' e 'H'
            if (source.charAt(0) >= 'A' && source.charAt(0) <= 'H') {

                // Converte a coluna de 'A' até 'H' para números (0 a 7)
                int coluna = transformarCharEmNumero(source.charAt(0));

                // Tenta obter a linha (pode ser um número de 1 a 9 ou maior)
                int linha;
                try {
                    linha = Integer.parseInt(source.substring(1)); // Pega tudo depois do primeiro caractere (a linha)
                    linha = linha - 1;
                }

                catch (NumberFormatException e) {
                    System.out.println("Linha inválida...");
                    continue;
                }

                return new Position(linha, coluna);

            }

            else {
                tabuleiro.mostrarTabuleiroComMovimentos(peca);
                System.out.println("Posição inválida...");
            }
        }
    }

    public Peca pegarPecaQueVaiSerMovida(Jogador jogador) throws InterruptedException {

        while (true) {
            Position posicao = pegarOrigem(scanner, jogador);

            // Pega peça na posicao escolhida
            Peca peca = tabuleiro.pegarPosicaoEspecifica(posicao);

            // Se tiver algum conteudo e for igual a cor do jogador
            if (peca != null && peca.getCor() == jogador.getColor()) {
                // Retorna a peca na posicao especifica
                return peca;
            }

            if (peca != null && peca.getCor() != jogador.getColor()) {
                tabuleiro.mostrarTabuleiro();
                System.out.println("Essa peça não é sua...");
            }
        }

    }

    public Position pegarEChecarPosicaoDestinoValida(Position[][] possiveisMovimentos, Peca peca) {

        while (true) {

            Position posicaoEscolhida = pegarDestino(scanner, peca);

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
