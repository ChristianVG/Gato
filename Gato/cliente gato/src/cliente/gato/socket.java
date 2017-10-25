/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.gato;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author ANNA
 */
public class socket {
    Socket cliente;
    int sendturn,boton;
    public String ficha;

    public String getFicha() {
        return ficha;
    }
    
    public void socket() throws IOException{
            cliente = new Socket("192.168.84.134",400);
            System.out.println("Ingresado con exito");
            recibirficha();
            }
    
    public void recibirficha() throws IOException{
        int p = cliente.getInputStream().read();
        if (p==1) {
            ficha = "O";
        }else{
            ficha = "X";
        }
    }
    
    public void enviar(int a) throws IOException{
        cliente.getOutputStream().write(a);
    }
    
    public int recivir() throws IOException{
    int p = cliente.getInputStream().read();
    return p;
    }
    public void cerrar() throws IOException{
    cliente.close();
    }
}
