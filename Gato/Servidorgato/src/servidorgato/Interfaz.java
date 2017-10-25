/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgato;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
/**
 *
 * @author A
 */
public class Interfaz extends JFrame {
    protected JButton[] bt;
    protected GridLayout tablero;
    public Servidor r;
    public String a,br;

    public void setA(String a) {
        if(a.equals("O")){
            br = "X";
        }else{
            br="O";
        }
        this.a = a;
    }
    
    
    public void setR(Servidor r) {
        this.r = r;
    }
    

    
    public Interfaz(){
    config();
    }
 
    public void config(){
     
    tablero = new GridLayout(3,3);
    bt=new JButton [9];
        for (int i = 0; i < 9; i++) {
            bt[i]= new JButton(""+i);
        }
    //agregar elementos
        for (int i = 0; i <9; i++) {
            this.add(bt[i]);
        }
        this.setLayout(tablero);
        this.setSize(300,300);
      //  juego.setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.white);
        this.setTitle("Gato");
    //  eventos del juego
        for (int i = 0; i < 9; i++) {
        bt[i].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try {
                    funcion1(ae.getSource());
                } catch (IOException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        }
    }
    public void funcion1(Object l) throws IOException{
     
    JButton p = (JButton)l;
    if(p.getText().equals(a)||p.getText().equals(br)){
        JOptionPane.showMessageDialog(null, "Seleccione Otro boton");
    }
    else{
          int ar = Integer.parseInt(p.getText());
        p.setText(a);
        for (int i = 0; i < 9; i++) {
            bt[i].setEnabled(false);
        }
        verificar();
        enviar(ar);
            
            }
    
    }
    
    public void enviar (int p) throws IOException{
        if (p<10) {
            try {
                r.enviar(p);
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(p == 10){
            try {
                r.enviar(p);
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        int z = 0;
        do{
        z = recibir();
        }while(z == 0);
    }
    
    public int recibir() throws IOException{
    int p;
        try {
            p=r.recibir();
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            p=0;
        }
        if(p == 10){
            JOptionPane.showMessageDialog(null, "Te gano jajajaja");
            r.cerrar();
        
        
        }else{
            colocar(p);
        }
        
    return p;
    }
    
    public void colocar(int p){
        for (int i = 0; i < 9; i++) {
            bt[i].setEnabled(true);
        }
    bt[p].setText(br);
    
    }
    
    public void verificar() throws IOException{
        //Vertical
        if (bt[0].getText().equals(a)&& bt[1].getText().equals(a) && bt[2].getText().equals(a)) {
           JOptionPane.showMessageDialog(null, "Ganaste");
           enviar(10);
//           System.out.println("Verificacion vertical1");
           this.dispose();
        
        }
        if (bt[3].getText().equals(a)&&bt[4].getText().equals(a)&&bt[5].getText().equals(a)) {
           JOptionPane.showMessageDialog(null, "Ganaste");
//           System.out.println("Verificacion vertical2");
             enviar(10);
           this.dispose();
        }
           
        if (bt[6].getText().equals(a)&&bt[7].getText().equals(a)&&bt[8].getText().equals(a)) {
           JOptionPane.showMessageDialog(null, "Ganaste");
//           System.out.println("Verificacion vertical3");
            enviar(10);
           this.dispose();
        }
        
        //Columna
        for (int i = 0; i < 3;) {
            if (bt[i].getText().equals(this.a)) {
                if (bt[i+3].getText().equals(this.a)) {
                    if (bt[i+6].getText().equals(this.a)) {
                        JOptionPane.showMessageDialog(null, "Ganaste");
//                         System.out.println("Verificacion columna");
                            enviar(10);
                            this.dispose();
                    }
                    
                }
            }
                i++;
            
        }
        if (bt[0].getText().equals(a)) {
            if (bt[4].getText().equals(a)) {
                if (bt[8].getText().equals(a)) {
                    JOptionPane.showMessageDialog(null, "Ganaste");
//                     System.out.println("Verificacion dia1");
                        enviar(10);
                        this.dispose();
                }
                
            }
            
        }
        if (bt[2].getText().equals(a)) {
            if (bt[4].getText().equals(a)) {
                if (bt[6].getText().equals(a)) {
                    JOptionPane.showMessageDialog(null, "Ganaste");
//                    System.out.println("Verificacion dia2");
                        enviar(10);
                        this.dispose();
               
                }
                
            }
            
        }
    
    }
    public void asignar(int xr){
    bt[xr].setText(br);
    }
}