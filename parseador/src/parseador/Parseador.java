/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parseador;

/**
 *
 * @author braya
 */
import parseador.telemetria.telebasica;
import parseador.telemetria.teleavanzada;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parseador {
    
    
    public static void main(String[] args) {
        try {
            String[] val;
            teleavanzada tele;
            Scanner teclado = new Scanner (System.in);
            String valo, direccion, rtx;
            int a=0, b=0;
            char c, r;
            ArrayList<teleavanzada> correcto = new ArrayList<teleavanzada>();
            ArrayList<teleavanzada> incorrecto = new ArrayList<teleavanzada>();
            File documento = new File ("D:\\taip.txt");
            BufferedReader o;
            System.out.println("Ruta: ");
            direccion= teclado.nextLine();
            documento = new File (direccion);
            o = new BufferedReader(new FileReader(documento));
            try {
                while ((valo = o.readLine()) != null) {
                    val = Div(valo);
                    for(int x=0; x<val.length; x++){
                        rtx = Evaluar(val[x]);
                        tele = new teleavanzada(rtx);
                        tele.Generar();
                        if (tele.getCreado()) {
                            correcto.add(tele);
                        }else {
                            incorrecto.add(tele);
                        }				
                    }
                }
                System.out.println("Correctos: ");
                for (int i = 0; i < correcto.size(); i++) {
                    correcto.get(i).mostrartipo();
                }
        
                System.out.println("Incorrectos: ");
                for (int i = 0; i < incorrecto.size(); i++) {
                    System.out.println(incorrecto.get(i).getError());
                }
                
                a=correcto.size();
                b=incorrecto.size();
		System.out.println("Creados correctos: "+a);
		System.out.println("Creados incorrectos: "+b);
		c = teclado.next().charAt(0);
                
            } catch (IOException ex) {
                Logger.getLogger(Parseador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Parseador.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    static String[] Div(String valor){
        String[] val = valor.split("<");
		for(int i = 0; i<val.length; i++){
			val[i]=val[i]+"<";
		}
		return val;
    }
    
    static String Evaluar(String valor){
        if (valor.contains("RTX")){
			if(valor.contains("EV")){
				String[] val = valor.split("EV");
				String devolver=">REV"+val[1];
				return devolver;
			}else{
				return valor;
			}
		}else{
			return valor;
		}        
    }
}
