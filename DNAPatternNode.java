/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author Simon
 */
public class DNAPatternNode {
    public String pattern;
    public int frequency;
    public Lista positions;
    public DNAPatternNode next; // Para tabla hash con encadenamiento
    
    public DNAPatternNode(String pattern, int position) {
        this.pattern = pattern;
        this.frequency = 1;
        this.positions = new Lista();
        this.positions.add(position);
        this.next = null;
    }
    
    public void addPosition(int position) {
        this.positions.add(position);
        this.frequency++;
    }
    
    public String getInfo() {
        return String.format("%s: frequency=%d, positions=%s", 
                           pattern, frequency, positions.toString());
    }
}

