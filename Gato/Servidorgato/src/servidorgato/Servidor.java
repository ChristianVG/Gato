/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgato;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author A
 */
public class Servidor {
    final int puerto=400;
    ServerSocket matenme;
    Socket  J1;
    int sendturn,boton;
    public String ficha;

    public String getFicha() {
        return ficha;
    }
    byte[] b;
    
    public void servidor() throws IOException{
    
            matenme=new ServerSocket(puerto);
            J1 = new Socket();

            System.out.println("Esperandos compas");
            J1=matenme.accept();
            numero();
            ficha();
    }
    
    public void numero(){
   int num=(int)(Math.random()*500)+1; 
        if (num%2==0) {
       try {
           System.out.println("servidor elige");
           sendturn=2;
           J1.getOutputStream().write(sendturn);
       } catch (IOException ex) {
           Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
       }
        }else{
       try {
           System.out.println("Elije cliente");
           sendturn=1;
           J1.getOutputStream().write(sendturn);
       } catch (IOException ex) {
           Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        }
    
    }
    public void ficha(){
        if (sendturn==2) {
            ficha ="O";
        }else{
            ficha="X";
        }
    
    }
    public void enviar(int art) throws IOException{
        J1.getOutputStream().write(art);
    }
    public int recibir() throws IOException{
        int ra = J1.getInputStream().read();
        return ra;
    }
    public void cerrar() throws IOException{
    J1.close();
    }
    
}
