/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author Simon
 */
public class DNAHashTable {
    private static final int TABLE_SIZE = 256;
    private DNAPatternNode[] table;
    private int collisionCount;
    private int elementCount;
    
    public DNAHashTable() {
        table = new DNAPatternNode[TABLE_SIZE];
        collisionCount = 0;
        elementCount = 0;
    }
    
    private int hash(String pattern) {
        // Función de hash simple para cadenas de ADN
        int hash = 0;
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            int value = (c == 'A') ? 1 : 
                       (c == 'C') ? 2 : 
                       (c == 'G') ? 3 : 
                       (c == 'T') ? 4 : 0;
            hash = (hash * 5 + value) % TABLE_SIZE;
        }
        return hash;
    }
    
    public void insert(String pattern, int position) {
        int index = hash(pattern);
        
        if (table[index] == null) {
            table[index] = new DNAPatternNode(pattern, position);
            elementCount++;
            return;
        }
        
        // Manejar colisiones con encadenamiento
        DNAPatternNode current = table[index];
        DNAPatternNode prev = null;
        boolean found = false;
        
        while (current != null) {
            if (current.pattern.equals(pattern)) {
                current.addPosition(position);
                found = true;
                break;
            }
            prev = current;
            current = current.next;
        }
        
        if (!found) {
            prev.next = new DNAPatternNode(pattern, position);
            collisionCount++;
            elementCount++;
        }
    }
    
    public DNAPatternNode get(String pattern) {
        int index = hash(pattern);
        DNAPatternNode current = table[index];
        
        while (current != null) {
            if (current.pattern.equals(pattern)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
    
    public DNAPatternNode[] getAllPatterns() {
        DNAPatternNode[] patterns = new DNAPatternNode[elementCount];
        int index = 0;
        
        for (int i = 0; i < TABLE_SIZE; i++) {
            DNAPatternNode current = table[i];
            while (current != null) {
                patterns[index++] = current;
                current = current.next;
            }
        }
        
        return patterns;
    }
    
    public int getCollisionCount() {
        return collisionCount;
    }
    
    public String getCollisionReport() {
        StringBuilder report = new StringBuilder();
        report.append("Collision Report:\n").append("Hash\tPatterns\n");
        
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (table[i] != null && table[i].next != null) {
                report.append(i).append("\t");
                DNAPatternNode current = table[i];
                while (current != null) {
                    report.append(current.pattern).append(" (").append(current.frequency).append("), ");
                    current = current.next;
                }
                report.append("\n");
            }
        }
        
        if (collisionCount == 0) {
            report.append("No collisions detected.\n");
        }
        
        report.append("Total collisions: ").append(collisionCount).append("\n");
        return report.toString();
    }
}

