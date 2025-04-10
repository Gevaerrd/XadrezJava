package Xadrez;

import Aplicacao.UI;
import Peças.Bispo;
import Peças.Cavalo;
import Peças.Color;
import Peças.Dama;
import Peças.Peao;
import Peças.Peca;
import Peças.Position;
import Peças.Rei;
import Peças.Torre;
import java.util.Arrays;
import java.util.Scanner;

public class Tabuleiro {

    private int linhas;
    private int colunas;
    private UI ui;

    Peca[][] pecas;
    public String frase;

    public Tabuleiro() {

    }

    public Tabuleiro(int linhas, int colunas) {

        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Peca[linhas][colunas];
        ui = new UI();

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

    // Função para registrar uma peça na matriz do começo
    public void moverParaPosicaoNoTabuleiro(Peca peca) { // Vai receber uma peca
        Position posicaoDaPeca = peca.getPosicao(); // Pega a posicao instaciada nela
        if (!checadorDePecaNaPosicaoBooleano(posicaoDaPeca)) {

            pecas[posicaoDaPeca.getLinha()][posicaoDaPeca.getColuna()] = peca; // Adiciona na matriz essa peca
        }

    }

    public void moverParaPosicaoNoTabuleiroComPosicao(Peca peca, Position posicao) { // Vai receber uma peca
        Position posicaoAntiga = peca.getPosicao(); // Pegando posição antiga
        pecas[posicaoAntiga.getLinha()][posicaoAntiga.getColuna()] = null; // Deixando ela null
        peca.setPosicao(posicao); // Dando uma nova posicao
        Position posicaoNova = peca.getPosicao(); // Pegando ela
        pecas[posicaoNova.getLinha()][posicaoNova.getColuna()] = peca; // Adiciona na matriz essa peca
    }

    public void mostrarTabuleiro() { // Função para mostrar o tabuleiro no momento atual dele
        clear();
        System.out.print("   ");
        for (char c = 'A'; c <= 'H'; c++) {
            System.out.print(c + "  ");
        }
        System.out.println();
        for (int i = 7; i >= 0; i--) { // Índices 0 a 7 para as linhas

            System.out.print(i + 1 + "  "); // Exibir a linha numerada de 1 a 8 (não 0 a 7)

            for (int y = 0; y < 8; y++) { // Índices 0 a 7 para as colunas
                Position position = new Position(i, y);
                Peca peca = pegarPosicaoEspecifica(position);

                ui.printPiece(peca);
            }
            System.out.println(); // Quebra de linha após imprimir cada linha
        }
    }

    public void mostrarTabuleiroComMovimentos(Peca pecaParaMostrar) {
        // Função para mostrar o tabuleiro no momento atual dele, com os possíveis
        // movimentos da peça.
        Position[][] possiveisMovimentos = pecaParaMostrar.possiveisMovimentos();
        clear();

        // Cabeçalho com as colunas (A-H)
        System.out.print("   ");
        for (char c = 'A'; c <= 'H'; c++) {
            System.out.print(c + "  ");
        }
        System.out.println();

        // Loop para as linhas (de 7 a 0)
        for (int i = 7; i >= 0; i--) { // Índices 0 a 7 para as linhas
            System.out.print(i + 1 + "  "); // Exibir a linha numerada de 1 a 8 (não 0 a 7)

            // Loop para as colunas (de 0 a 7)
            for (int y = 0; y < 8; y++) { // Índices 0 a 7 para as colunas
                Position posicaoParaChecar = new Position(i, y);
                boolean movimentoValido = false; // Variável para marcar se o movimento é válido

                // Verifica se a posição está nos possíveis movimentos da peça
                for (Position[] linhas : possiveisMovimentos) {
                    for (Position posicao : linhas) {
                        if (posicao != null && posicao.equals(posicaoParaChecar)) {
                            movimentoValido = true; // Marca o movimento como válido
                            break; // Sai do loop quando encontrar a posição válida
                        }
                    }
                }

                // Se o movimento for válido, imprime o símbolo de "check" (✔)
                if (movimentoValido) {
                    System.out.print("*  "); // Exibe o símbolo de certo (✔)
                }

                else {
                    // Se não for movimento válido, imprime a peça ou um "-".
                    Peca peca = pegarPosicaoEspecifica(posicaoParaChecar);
                    ui.printPiece(peca);
                }
            }
            System.out.println(); // Quebra de linha após imprimir cada linha
        }
    }

    public void promoverPeao(Peca peao) {
        Scanner scanner = new Scanner(System.in);
        String[] opcoes = { "D", "T", "B", "C" };
        boolean rodarChamada = true;

        while (rodarChamada) {

            System.out.println("Escolhe a peça que o peão vai virar:");
            System.out.println("D. Dama\nT. Torre\nB. Bispo\nC. Cavalo");
            String opcao = scanner.nextLine();

            if (Arrays.asList(opcoes).contains(opcao.toUpperCase())) {
                Position posicaoAtualDoPeao = peao.getPosicao();
                Color corDoPeao = peao.getCor();

                pecas[posicaoAtualDoPeao.getLinha()][posicaoAtualDoPeao.getColuna()] = null;
                switch (opcao.toUpperCase()) {
                    case "D":
                        pecas[posicaoAtualDoPeao.getLinha()][posicaoAtualDoPeao.getColuna()] = new Dama(this, corDoPeao,
                                posicaoAtualDoPeao);
                        rodarChamada = false;
                        break;
                    case "T":
                        pecas[posicaoAtualDoPeao.getLinha()][posicaoAtualDoPeao.getColuna()] = new Torre(this,
                                corDoPeao, posicaoAtualDoPeao);
                        rodarChamada = false;
                        break;
                    case "B":
                        pecas[posicaoAtualDoPeao.getLinha()][posicaoAtualDoPeao.getColuna()] = new Bispo(this,
                                corDoPeao, posicaoAtualDoPeao);
                        rodarChamada = false;
                        break;
                    case "C":
                        pecas[posicaoAtualDoPeao.getLinha()][posicaoAtualDoPeao.getColuna()] = new Cavalo(this,
                                corDoPeao, posicaoAtualDoPeao);
                        rodarChamada = false;
                        break;
                    default:
                        throw new AssertionError();
                }

            }

            else {
                scanner.nextLine();
                System.out.println("Não digitou nenhuma opção válida!");
            }

        }

    }

    public void checadorDePeaoUltimaColuna() {
        for (int i = 0; i <= getLinhas(); i = i + 7) {
            for (int coluna = 0; coluna < getColunas(); coluna++) {
                Position checandoPeao = new Position(i, coluna);
                Peca possivelPeao = pegarPosicaoEspecifica(checandoPeao);
                if (possivelPeao != null && i == 0 && possivelPeao.getCor() == Color.BLACK
                        && possivelPeao instanceof Peao) {
                    promoverPeao(possivelPeao);

                }

                else if (possivelPeao != null && i == 7 && possivelPeao.getCor() == Color.WHITE
                        && possivelPeao instanceof Peao) {
                    promoverPeao(possivelPeao);

                }

            }
        }
    }

    public void comerPeca(Peca pecaComida, Peca pecaNova) { // Vai receber uma peca

        Position posicaoDaPecaComida = pecaComida.getPosicao(); // Pega a posicao instaciada nela
        Position posicaoDaPecaNova = pecaNova.getPosicao();
        // Deixando a posicao da peca que vai se mexer null
        pecas[posicaoDaPecaNova.getLinha()][posicaoDaPecaNova.getColuna()] = null;
        pecas[posicaoDaPecaComida.getLinha()][posicaoDaPecaComida.getColuna()] = null;
        pecaNova.setPosicao(posicaoDaPecaComida);
        // Movendo para a posição da peça antiga
        moverParaPosicaoNoTabuleiro(pecaNova);
    }

    public Peca pegarPosicaoEspecifica(Position posicao) {
        return pecas[posicao.getLinha()][posicao.getColuna()]; // Recebe uma posicao especifica e retorna o que nele
    }

    public boolean checadorDePecaNaPosicaoBooleano(Position posicao) {
        Peca peca = pecas[posicao.getLinha()][posicao.getColuna()];
        if (peca != null) {
            return true;
        }
        return false;
    }

    public boolean checadorXequeMate(Peca peca) {

        Position[][] possiveisMovimentos = peca.possiveisMovimentos();

        for (Position[] p : possiveisMovimentos) {
            for (Position linhas : p) {
                if (linhas != null) {

                    boolean temPeca = checadorDePecaNaPosicaoBooleano(linhas);
                    if (temPeca) {
                        Peca pecaDaPosicao = pegarPosicaoEspecifica(linhas);
                        if (pecaDaPosicao instanceof Rei) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;

    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public Peca[][] getPecas() {
        return pecas;
    }

}
