/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Peças;

import Xadrez.Tabuleiro;

public class Bispo extends Peca {

    // Bispo Diagonal (qualquer número de casas)

    public Bispo() {

    }

    public Bispo(Tabuleiro tabuleiro, Color cor, Position posicao) {
        super(tabuleiro, cor, posicao);
    }

    @Override
    public Position[][] possiveisMovimentos() {
        Tabuleiro tabuleiro = getTabuleiro();
        Position posicaoAtual = getPosicao();
        Position[][] posicoes = new Position[tabuleiro.getLinhas()][tabuleiro.getColunas()];

        // Movimento para a Diagonal Direita para cima

        // Nesse for, cada numero no I é uma linha que sobe na linha atual e coluna,
        // então ele vai na diagonal sempre

        for (int i = 1; i < tabuleiro.getLinhas(); i++) {
            int novaLinha = posicaoAtual.getLinha() - i;
            int novaColuna = posicaoAtual.getColuna() + i;

            // Verifica se está dentro dos limites do tabuleiro
            if (novaLinha >= 0 && novaLinha < tabuleiro.getLinhas() && novaColuna >= 0
                    && novaColuna < tabuleiro.getColunas()) {
                Position novaPosicao = new Position(novaLinha, novaColuna);
                Peca possivelPeca = tabuleiro.checadorDePecaNaPosicao(novaPosicao);

                // Se a posição estiver livre, o bispo pode se mover
                if (possivelPeca == null) {
                    posicoes[novaLinha][novaColuna] = novaPosicao;
                }

                else if (possivelPeca.getCor() != getCor()) {
                    // Se encontrar uma peça adversária, o bispo pode capturá-la e parar
                    posicoes[novaLinha][novaColuna] = novaPosicao;
                    break; // Após capturar, o movimento na direção da diagonal é interrompido
                }

                else {
                    // Se encontrar uma peça da mesma cor, o movimento é bloqueado
                    break;
                }
            }

            else {
                break; // Se a posição ultrapassar o limite do tabuleiro, interrompe o loop
            }
        }

        // Movimento para a Diagonal Direita para baixo
        for (int i = 1; i < tabuleiro.getLinhas(); i++) {
            int novaLinha = posicaoAtual.getLinha() + i;
            int novaColuna = posicaoAtual.getColuna() + i;

            // Verifica se está dentro dos limites do tabuleiro
            if (novaLinha >= 0 && novaLinha < tabuleiro.getLinhas() && novaColuna >= 0
                    && novaColuna < tabuleiro.getColunas()) {
                Position novaPosicao = new Position(novaLinha, novaColuna);
                Peca possivelPeca = tabuleiro.checadorDePecaNaPosicao(novaPosicao);

                // Se a posição estiver livre, o bispo pode se mover
                if (possivelPeca == null) {
                    posicoes[novaLinha][novaColuna] = novaPosicao;
                }

                else if (possivelPeca.getCor() != getCor()) {
                    // Se encontrar uma peça adversária, o bispo pode capturá-la e parar
                    posicoes[novaLinha][novaColuna] = novaPosicao;
                    break; // Após capturar, o movimento na direção da diagonal é interrompido
                }

                else if (possivelPeca.getCor() == getCor()) {
                    // Se encontrar uma peça da mesma cor, o movimento é bloqueado
                    break;
                }
            }

            else {
                break; // Se a posição ultrapassar o limite do tabuleiro, interrompe o loop
            }
        }

        // Movimento para a Diagonal Esquerda para cima
        for (int i = 1; i < tabuleiro.getLinhas(); i++) {
            int novaLinha = posicaoAtual.getLinha() - i;
            int novaColuna = posicaoAtual.getColuna() - i;

            // Verifica se está dentro dos limites do tabuleiro
            if (novaLinha >= 0 && novaLinha < tabuleiro.getLinhas() && novaColuna >= 0
                    && novaColuna < tabuleiro.getColunas()) {
                Position novaPosicao = new Position(novaLinha, novaColuna);
                Peca possivelPeca = tabuleiro.checadorDePecaNaPosicao(novaPosicao);

                // Se a posição estiver livre, o bispo pode se mover
                if (possivelPeca == null) {
                    posicoes[novaLinha][novaColuna] = novaPosicao;
                }

                else if (possivelPeca.getCor() != getCor()) {
                    // Se encontrar uma peça adversária, o bispo pode capturá-la e parar
                    posicoes[novaLinha][novaColuna] = novaPosicao;
                    break; // Após capturar, o movimento na direção da diagonal é interrompido
                }

                else {
                    // Se encontrar uma peça da mesma cor, o movimento é bloqueado
                    break;
                }
            }

            else {
                break; // Se a posição ultrapassar o limite do tabuleiro, interrompe o loop
            }
        }

        // Movimento para a Diagonal Esquerda para baixo
        for (int i = 1; i < tabuleiro.getLinhas(); i++) {
            int novaLinha = posicaoAtual.getLinha() + i;
            int novaColuna = posicaoAtual.getColuna() - i;

            // Verifica se está dentro dos limites do tabuleiro
            if (novaLinha >= 0 && novaLinha < tabuleiro.getLinhas() && novaColuna >= 0
                    && novaColuna < tabuleiro.getColunas()) {
                Position novaPosicao = new Position(novaLinha, novaColuna);
                Peca possivelPeca = tabuleiro.checadorDePecaNaPosicao(novaPosicao);

                // Se a posição estiver livre, o bispo pode se mover
                if (possivelPeca == null) {
                    posicoes[novaLinha][novaColuna] = novaPosicao;
                }

                else if (possivelPeca.getCor() != getCor()) {
                    // Se encontrar uma peça adversária, o bispo pode capturá-la e parar
                    posicoes[novaLinha][novaColuna] = novaPosicao;
                    break; // Após capturar, o movimento na direção da diagonal é interrompido
                }

                else {
                    // Se encontrar uma peça da mesma cor, o movimento é bloqueado
                    break;
                }
            }

            else {
                break; // Se a posição ultrapassar o limite do tabuleiro, interrompe o loop
            }
        }

        return posicoes;
    }

}