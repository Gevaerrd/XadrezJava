/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Peças;

import Xadrez.Tabuleiro;
import java.util.Objects;

/**
 *
 * @author Pichau
 */
public abstract class Peca {

    private Tabuleiro tabuleiro;
    private Color cor;
    private Position posicao;
    public boolean primeiroMovimento = true;

    public Peca() {

    }

    public Peca(Tabuleiro tabuleiro, Color cor, Position posicao) {

        this.posicao = posicao;
        this.tabuleiro = tabuleiro;
        this.cor = cor;

    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public Color getCor() {
        return cor;
    }

    public Position getPosicao() {
        return posicao;
    }

    public void setPosicao(Position posicao) {
        this.posicao = posicao;
    }

    public String getInicialPeca(Peca peca) {
        if (peca instanceof Peao) {
            return "P";
        } else if (peca instanceof Torre) {
            return "T";
        } else if (peca instanceof Cavalo) {
            return "C";
        } else if (peca instanceof Bispo) {
            return "B";
        } else if (peca instanceof Dama) {
            return "D";
        } else if (peca instanceof Rei) {
            return "R";
        } else {
            return " "; // Caso a peça não seja reconhecida, retorna um espaço
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.cor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Peca other = (Peca) obj;
        return this.cor == other.cor;
    }

    public abstract Position[][] possiveisMovimentos();

    public void setPrimeiroMovimento(boolean primeiroMovimento) {
        this.primeiroMovimento = primeiroMovimento;
    }

    public boolean isPrimeiroMovimento() {
        return primeiroMovimento;
    }

}
