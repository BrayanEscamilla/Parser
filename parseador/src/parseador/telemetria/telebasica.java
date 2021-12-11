/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parseador.telemetria;

import java.io.IOException;
import java.util.Calendar;

/**
 *
 * @author braya
 */
public class telebasica {
    
    String telemetria;
    String indice;
    int codigo_evento;
    Calendar fecha_evento;
    String diaev;
    Calendar hora_evento;
    double latit, longi;
    float velm;
    float velk;
    int orientacio;
    String error="";
    
    public telebasica(String t) {
    	telemetria=t;
    }
    
    public void cambiar() throws IOException {
    	indice(telemetria.substring(1,2));
    	codigo_Evento(telemetria.substring(4,6));
    	FechaActual(telemetria.substring(6,10), telemetria.substring(10,11), telemetria.substring(11, 16));
    	DiaActual(telemetria.substring(10,11));
    	horaActual(telemetria.substring(11, 16));
    	latitud(telemetria.substring(16,24));
    	longitud(telemetria.substring(24,33));
    	velocidad(telemetria.substring(33,36));
    	orientacion(telemetria.substring(36,39));
    }
    
    public String error() {
    	return error;
    }
    
    public void getTelemetria_B() {
    	
    }
    
    public String getTelem() {
    	return telemetria;
    }
    
    public void indice(String val) {
    	if(val.toUpperCase().charAt(0)=='R'){
            this.indice="Response";
        }else{
            if(val.toUpperCase().charAt(0)=='Q'){
                this.indice="Query";
            }else{
                if(val.toUpperCase().charAt(0)=='S'){
                    this.indice="Set";
                }else{
                    this.indice="";
                    this.error="Error en indice";
                }
            }
        }
    }
    
    public void codigo_Evento(String val) throws IOException {
    	try {
    		this.codigo_evento = Integer.parseInt(val.trim());
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
    public void FechaActual(String val1, String val2, String val3)throws IOException {
    	try {
    		int v1, v2, v3, v4;
    		v1=Integer.parseInt(val1);
                v1=v1*7;
        	v2=Integer.parseInt(val2);
                v3=Integer.parseInt(val3);
        	v4=v1+v2;
        	Calendar c = Calendar.getInstance();
        	c.set(Calendar.YEAR, 1980);
        	c.set(Calendar.MONTH, Calendar.JANUARY);
        	c.set(Calendar.DAY_OF_MONTH, 6);	
        	fecha_evento = Calendar.getInstance();
        	fecha_evento.set(Calendar.YEAR, 1980);
        	fecha_evento.set(Calendar.MONTH, Calendar.JANUARY);
        	fecha_evento.set(Calendar.DAY_OF_YEAR, 6);
        	fecha_evento.add(Calendar.DAY_OF_YEAR, v1);
                fecha_evento.add(Calendar.DAY_OF_WEEK,v2);
                fecha_evento.set(Calendar.HOUR_OF_DAY, 0);
        	fecha_evento.set(Calendar.MINUTE, 0);
        	fecha_evento.set(Calendar.SECOND, 0);
                fecha_evento.add(Calendar.SECOND, v3);
                fecha_evento.add(Calendar.HOUR, -6);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void DiaActual(String val) throws IOException {
    	int v;
    	try {
    		v=Integer.parseInt(val.trim());
    		switch(v) {
    		case 1:
    			diaev="Lunes";
    			break;
    		case 2:
    			diaev="Martes";
    			break;
    		case 3:
    			diaev="Miercoles";
    			break;
    		case 4:
    			diaev = "Jueves";
    			break;
    		case 5:
    			diaev = "Viernes";
    			break;
    		case 6:
    			diaev = "Sabado";
    			break;
    		case 7:
    			diaev = "Domingo";
    			break;
    		default:
    			this.error ="Error dia incorrecto";
    			diaev="Error dia incorrecto";
    			break;
    		}
    	}catch(Exception e) {
    		this.error="error"+e;
    		e.printStackTrace();
    	}
    }
    
    public void horaActual(String val) throws IOException{
    	try {
    		int v;
    		v=Integer.parseInt(val);
    		hora_evento=Calendar.getInstance();
        	hora_evento.set(Calendar.HOUR_OF_DAY, 0);
        	hora_evento.set(Calendar.MINUTE, 0);
        	hora_evento.set(Calendar.SECOND, 0);
        	hora_evento.add(Calendar.SECOND, v);
                
                
                
    	}catch(Exception e) {
    		this.error="Error"+e;
    		e.printStackTrace();
    	}
    }
    
    public void latitud(String val) throws IOException {
    	try {
    		String val1, val2, val3;
        	val1=val.substring(0,3);
        	val2=val.substring(3,8);
        	val3=val1+"."+val2;
        	latit=Double.parseDouble(val3);
    	}catch(Exception e) {
    		this.error="error"+e;
    		e.printStackTrace();
    	}
    }
    
    public void longitud(String val) throws IOException{
    	try {
    		String val1, val2, val3;
        	val1=val.substring(0,4);
        	val2=val.substring(4,8);
        	val3=val1+"."+val2;
        	longi=Double.parseDouble(val3);
    	}catch(Exception e) {
    		this.error="error"+e;
    		e.printStackTrace();
    	}
    }
    
    public void velocidad(String val) throws IOException{
    	try {
    		String a="1.609";
        	this.velm=Float.parseFloat(val);
        	this.velk=velm*Float.parseFloat(a);
    	}catch(Exception e) {
    		this.error="Error"+e;
    		e.printStackTrace();
    	}
    }
    
    public void orientacion(String val) throws IOException{
    	try {
    		orientacio=Integer.parseInt(val);
    	}catch(Exception e) {
    		error="Error: "+e;
    		e.printStackTrace();
    	}
    }
    
    public String getError() {
    	return error;
    }
    
    public void EnviarTeleBasica() {
    	System.out.println("");
    	System.out.println("El paquete es: "+telemetria);
    	System.out.println("Indice:"+indice);
    	System.out.println("Codigo:"+codigo_evento);
    	System.out.println("Fecha:"+fecha_evento.getTime());
        System.out.println("Latitud: "+latit);
    	System.out.println("Longitud: "+longi);
    	System.out.println("Velocidad en millas por hora: "+velm+" Velocidad en Kilometros por hora: "+velk);
    	System.out.print("La orientacion es: "+orientacio+" ");
    	if(orientacio==0 || orientacio==360) {
    		System.out.print("Norte");
    	}
    	if(orientacio==180) {
    		System.out.println("Sur");
    	}
    	if(orientacio==90) {
    		System.out.println("Este");
    	}
    	if(orientacio==270) {
    		System.out.println("Oeste");
    	}
    	if((orientacio>0 && orientacio<90)) {
    		System.out.println("Noreste");
    	}
    	if(orientacio>90 && orientacio<180) {
    		System.out.println("Sureste");
    	}
    	if(orientacio>180 && orientacio<270) {
    		System.out.println("Suroeste");
    	}
    	if(orientacio>270 && orientacio<360) {
    		System.out.println("Noroeste");
    	}
    	System.out.println("");
        System.out.println("");
        System.out.println("");
        
    }
    
    public void EnviarTele_Ext() {
    	System.out.println("//////////////////////////////////////////////////");
    	System.out.println("El paquete de informacion es: "+telemetria);
    	System.out.println("Indice:"+indice);
    	System.out.println("Codigo:"+codigo_evento);
    	System.out.println("Fecha:"+fecha_evento.getTime());
        System.out.println("Latitud: "+latit);
    	System.out.println("Longitud: "+longi);
    	System.out.println("Velocidad en millas por hora: "+velm+" Velocidad en Kilometros por hora: "+velk);
    	System.out.print("La orientacion es: "+orientacio+" ");
    	if(orientacio==0 || orientacio==360) {
    		System.out.print("Norte");
    	}
    	if(orientacio==180) {
    		System.out.println("Sur");
    	}
    	if(orientacio==90) {
    		System.out.println("Este");
    	}
    	if(orientacio==270) {
    		System.out.println("Oeste");
    	}
    	if((orientacio>0 && orientacio<90)) {
    		System.out.println("Noreste");
    	}
    	if(orientacio>90 && orientacio<180) {
    		System.out.println("Sureste");
    	}
    	if(orientacio>180 && orientacio<270) {
    		System.out.println("Suroeste");
    	}
    	if(orientacio>270 && orientacio<360) {
    		System.out.println("Noroeste");
    	}
    	System.out.println("");
    }
}
