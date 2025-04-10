package Aplicacao;

import FuncoesParaSuporte.ConfiguracaoJogadorInicial;
import FuncoesParaSuporte.FuncoesDeComplementoTabuleiro;
import Jogador.Jogador;
import Peças.Bispo;
import Peças.Cavalo;
import Peças.Color;
import Peças.Dama;
import Peças.Peao;
import Peças.Peca;
import Peças.Position;
import Peças.Rei;
import Peças.Torre;
import Xadrez.Tabuleiro;
import java.util.ArrayList;
import java.util.List;

public class Partida {

    private List<Peca> pecasBrancas = new ArrayList<>();
    private List<Peca> pecasPretas = new ArrayList<>();
    private Tabuleiro tabuleiro;
    ConfiguracaoJogadorInicial funcaoDeEscolha = new ConfiguracaoJogadorInicial();
    Jogador[] jogadores = funcaoDeEscolha.escolherJogador(new Jogador(Color.BLACK), new Jogador(Color.WHITE));
    FuncoesDeComplementoTabuleiro funcoesSuporte;

    public Partida() {
        tabuleiro = new Tabuleiro(8, 8);
        funcoesSuporte = new FuncoesDeComplementoTabuleiro(tabuleiro);
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

    // Função para começar a partida instanciando as peças inicias
    public void organizacaoDasPecas() {
        // Inicializar as peças pretas
        for (int i = 0; i < 8; i++) { // Agora vai de 0 até 7
            // Criar os peões pretos na linha 7 (índice 6)
            Peao peaoPreto = new Peao(tabuleiro, Color.BLACK, new Position(6, i)); // Ajuste para linha 7 no
                                                                                   // tabuleiro
            tabuleiro.moverParaPosicaoNoTabuleiro(peaoPreto); // Inserir no tabuleiro
        }

        // Peças maiores pretas
        tabuleiro.moverParaPosicaoNoTabuleiro(new Torre(tabuleiro, Color.BLACK, new Position(7, 0))); // Linha 8,
                                                                                                      // Coluna
        // 1
        tabuleiro.moverParaPosicaoNoTabuleiro(new Torre(tabuleiro, Color.BLACK, new Position(7, 7))); // Linha 8,
                                                                                                      // Coluna
        // 8
        tabuleiro.moverParaPosicaoNoTabuleiro(new Cavalo(tabuleiro, Color.BLACK, new Position(7, 1))); // Linha 8,
        // Coluna 2
        tabuleiro.moverParaPosicaoNoTabuleiro(new Cavalo(tabuleiro, Color.BLACK, new Position(7, 6))); // Linha 8,
        // Coluna 7
        tabuleiro.moverParaPosicaoNoTabuleiro(new Bispo(tabuleiro, Color.BLACK, new Position(7, 2))); // Linha 8,
                                                                                                      // Coluna
        // 3
        tabuleiro.moverParaPosicaoNoTabuleiro(new Bispo(tabuleiro, Color.BLACK, new Position(7, 5))); // Linha 8,
                                                                                                      // Coluna
        // 6
        tabuleiro.moverParaPosicaoNoTabuleiro(new Dama(tabuleiro, Color.BLACK, new Position(7, 3))); // Linha 8,
                                                                                                     // Coluna
        // 4
        tabuleiro.moverParaPosicaoNoTabuleiro(new Rei(tabuleiro, Color.BLACK, new Position(7, 4))); // Linha 8,
                                                                                                    // Coluna 5

        // Inicializar as peças brancas
        for (int i = 0; i < 8; i++) { // Agora vai de 0 até 7
            // Criar os peões brancos na linha 2 (índice 1)
            Peao peaoBranco = new Peao(tabuleiro, Color.WHITE, new Position(1, i)); // Ajuste para linha 2 no
                                                                                    // tabuleiro
            tabuleiro.moverParaPosicaoNoTabuleiro(peaoBranco); // Inserir no tabuleiro
        }

        // Peças maiores brancas
        tabuleiro.moverParaPosicaoNoTabuleiro(new Torre(tabuleiro, Color.WHITE, new Position(0, 0))); // Linha 1,
                                                                                                      // Coluna
        // 1
        tabuleiro.moverParaPosicaoNoTabuleiro(new Torre(tabuleiro, Color.WHITE, new Position(0, 7))); // Linha 1,
                                                                                                      // Coluna
        // 8
        tabuleiro.moverParaPosicaoNoTabuleiro(new Cavalo(tabuleiro, Color.WHITE, new Position(0, 1))); // Linha 1,
        // Coluna 2
        tabuleiro.moverParaPosicaoNoTabuleiro(new Cavalo(tabuleiro, Color.WHITE, new Position(0, 6))); // Linha 1,
        // Coluna 7
        tabuleiro.moverParaPosicaoNoTabuleiro(new Bispo(tabuleiro, Color.WHITE, new Position(0, 2))); // Linha 1,
                                                                                                      // Coluna
        // 3
        tabuleiro.moverParaPosicaoNoTabuleiro(new Bispo(tabuleiro, Color.WHITE, new Position(0, 5))); // Linha 1,
                                                                                                      // Coluna
        // 6
        tabuleiro.moverParaPosicaoNoTabuleiro(new Dama(tabuleiro, Color.WHITE, new Position(0, 3))); // Linha 1,
                                                                                                     // Coluna
        // 4
        tabuleiro.moverParaPosicaoNoTabuleiro(new Rei(tabuleiro, Color.WHITE, new Position(0, 4))); // Linha 1,
                                                                                                    // Coluna 5
    }

    public void adicionarPecasAListaDePecasComida(Peca peca) {
        if (peca != null) { // Se a peca nao for null
            if (peca.getCor() == Color.BLACK) { // E for preta
                pecasPretas.add(peca); // Adiciona
            }

            else if (peca.getCor() == Color.WHITE) {
                pecasBrancas.add(peca);
            }

        }
    }

    public boolean checadorDeReiNasComidasParaVerXequeMate() {
        for (Peca peca : pecasPretas) { // Ve as pecas pretas comidas
            if (peca instanceof Rei) { // Se for um rei
                return true; // True acaba o jogo
            }
        }

        for (Peca peca : pecasBrancas) {
            if (peca instanceof Rei) {
                return true;
            }
        }

        return false;
    }

    public void darParabensAoGanhador(Jogador jogador) {
        System.out.println("Parabéns " + jogador + ", você ganhou!");
    }

    public void mostrarPecasBrancasEPretasComida() {
        System.out.println(pecasBrancas);
        System.out.println(pecasPretas);
    }

    public void jogada(Jogador jogador) throws InterruptedException {
        boolean jogada = false;

        while (!jogada) {

            tabuleiro.mostrarTabuleiro();
            System.out.println("\nVez de: " + jogador.getColor());

            // Pegando a peça escolhida pelo jogador
            Peca peca = funcoesSuporte.pegarPecaQueVaiSerMovida(jogador);

            // Checa se a pessoa tem movimentos possiveis para prosseguir
            if (!funcoesSuporte.checadorDeMovimentoParaPeca((Position[][]) peca.possiveisMovimentos())) {
                System.out.println("Essa peça não tem movimentos validos...");
                Thread.sleep(3000);
                continue;
            }

            if (tabuleiro.checadorXequeMate(peca)) {
                System.out.println("VOCÊ ESTÁ EM XEQUE...");
            }
            // Mostra os possiveis movimentos do jogador com a peça
            tabuleiro.mostrarTabuleiroComMovimentos(peca);

            // Chama a função de mover a peça pro destino escolhido, retorna uma peça(caso
            // tenha alguma no destino ou null)
            Peca pecaQueFoiComida = funcoesSuporte.moverPecaParaDestinoEscolhido(peca); // Passa a peça pra função

            adicionarPecasAListaDePecasComida(pecaQueFoiComida);
            mostrarPecasBrancasEPretasComida();
            jogada = true;

        }

    }

    public void comecarPartida() throws InterruptedException {

        // Função de mostrar o primeiro jogador e ele selecionar uma peça!!

        boolean rodarPartida = true;

        while (rodarPartida) { // Partida rodando
            jogada(jogadores[0]);
            if (checadorDeReiNasComidasParaVerXequeMate()) {
                darParabensAoGanhador(jogadores[0]);
                rodarPartida = false;
            }
            tabuleiro.checadorDePeaoUltimaColuna();

            jogada(jogadores[1]);
            if (checadorDeReiNasComidasParaVerXequeMate()) {
                darParabensAoGanhador(jogadores[1]);
                rodarPartida = false;
            }
            tabuleiro.checadorDePeaoUltimaColuna();
        }

    }
}