package Peças;

public class Position {

    private int linha;
    private int coluna;

    public Position() {

    }

    public Position(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;

    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + linha;
        result = prime * result + coluna;
        return result;
    }

    @Override
    public String toString() {
        return "Position [linha=" + linha + ", coluna=" + coluna + "]";
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
        final Position other = (Position) obj;
        if (this.linha != other.linha) {
            return false;
        }
        return this.coluna == other.coluna;
    }

}
