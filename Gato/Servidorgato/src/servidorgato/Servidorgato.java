/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgato;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author A
 */
public class Servidorgato {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Servidor ser = new Servidor();
        Interfaz r = new Interfaz();
        String ficha;
        
        ser.servidor();
        ficha=ser.getFicha();
        
        System.out.println("Comenzado Juego");
        JOptionPane.showMessageDialog(null, "Tetoco la ficha "+ficha);
        
        r.setR(ser);
        r.setA(ficha);
        
        if(ficha.equals("O")){
            
            r.setVisible(true);
        }else{
            int z;
            JOptionPane.showMessageDialog(null, "Espere que el jugador seleccione:3 ");
        do{
            
            z=ser.recibir();
            
        }while(z == 0);
        r.asignar(z);
        r.setVisible(true);
        }

        
        
    
    }

}
