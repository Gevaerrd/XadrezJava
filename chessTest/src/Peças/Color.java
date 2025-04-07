/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */

package Peças;

/**
 *
 * @author Pichau
 */
public enum Color {

    BLACK(1),
    WHITE(2);

    private int code;

    private Color(int i) {
        this.code = i; // Instanciao do valor para o enum
    }

    public int getCode() {
        return code;
    }

    public Color getColor(int i) {
        for (Color c : Color.values()) {
            if (c.getCode() == i) {
                return c;
            }
        }
        return null;
    }

}
