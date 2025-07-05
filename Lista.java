/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author Simon
 */
public class Lista {
    private Nodo head;
    private int size;
    
    public Lista() {
        head = null;
        size = 0;
    }
    
    public void add(int position) {
        Nodo newNode = new Nodo(position);
        if (head == null) {
            head = newNode;
        } else {
            Nodo current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    
    public int getSize() {
        return size;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Nodo current = head;
        int count = 0;
        
        while (current != null && count < 10) {
            if (count > 0) sb.append(", ");
            sb.append(current.position);
            current = current.next;
            count++;
        }
        
        if (size > 10) {
            sb.append(", ... (total ").append(size).append(")");
        }
        
        return sb.toString();
    }
    
    public int[] toArray() {
        int[] array = new int[size];
        Nodo current = head;
        int index = 0;
        
        while (current != null) {
            array[index++] = current.position;
            current = current.next;
        }
        
        return array;
    }
}

