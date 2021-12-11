/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parseador.telemetria;
import java.io.*;
/**
 *
 * @author braya
 */
public class teleavanzada extends telebasica{
String te;
int ignicion=0, ext_pwr=0, aceleracion=0, idle=0, vo=0, ioi, iof,
pai, paf, pidlei, pidlef, pvoi, pvof, pidi, pidf, valores=1;
String id;

boolean telemetria_Ex;
boolean creado_c;
String io="", ac="", cl="", vos="", ids="";
boolean x=false;
boolean tipoT;
	public teleavanzada(String tele) {
		super(tele);
	}
	
	public void Generar() throws IOException {
		if(this.telemetria.length()>=42) {
			if(this.telemetria.charAt(0)=='>' && this.telemetria.charAt(this.telemetria.length()-1)=='<') {
				if(this.telemetria.contains("REV")) {
					this.cambiar();
					if(this.telemetria.charAt(41)=='<') {
						telemetria_Ex=false;
						setCreado();
					}else {
						if(this.telemetria.charAt(41)==';') {
							telemetria_Ex=true;
							if(this.telemetria.contains(";IO")) {
								ioi=this.telemetria.indexOf(";IO")+4;
								iof=ioi+1;
								IO_Evento(telemetria.substring(ioi,iof));
							}else {
								io="no contiene IO";
							}
							if(this.telemetria.contains(";AC")) {
								pai=this.telemetria.indexOf(";AC")+4;
								paf=this.telemetria.indexOf(";", pai);
								Aceleracion_Evento(telemetria.substring(pai,paf));
							}else {
								ac="no contiene AC";
							}
							if(this.telemetria.contains(";CL")) {
								pidlei=this.telemetria.indexOf(";CL")+4;
								pidlef=this.telemetria.indexOf(";", pidlei);
								IDLE_Evento(telemetria.substring(pidlei, pidlef));
							}else {
								cl="no contiene IDLE";
							}
							if(this.telemetria.contains(";VO")) {
								pvoi=this.telemetria.indexOf(";VO")+4;
								pvof=this.telemetria.indexOf(";", pvoi);
								VO_Evento(telemetria.substring(pvoi,pvof));
							}else {
								vos="no contiene VO";
							}
							if(this.telemetria.contains(";ID")) {
								pidi=this.telemetria.indexOf(";ID")+4;
								pidf=this.telemetria.indexOf("<", pidi);
								ID_Evento(telemetria.substring(pidi,pidf));
							}else {
								ids="no contiene ID";
							}
							setCreado();
						}else {
							this.error="Error caracter no valido";
						}
					}
				}else {
					this.error="Tipo de linea no valida";
				}
			}else {
				this.error="No se encuentran el valor inicio o cierre de la linea";
			}
		}else {
			this.error="Error de tamanio";
		}
	}
	
	public void IO_Evento(String val) {
		try {
			int v= Integer.parseInt(val);
			switch(v) {
			case 0:
				ignicion=0;
				ext_pwr=0;
				break;
			case 1:
				ignicion=1;
				ext_pwr=0;
				break;
			case 2:
				ignicion=0;
				ext_pwr=1;
				break;
			case 3:
				ignicion=1;
				ext_pwr=1;
				break;
			default:
				this.error="Error valor no encontrado";
				ignicion=0;
				ext_pwr=0;
				break;
			}
		}catch(Exception e) {
			this.error="Error"+e;
			e.printStackTrace();
		}
	}
	
	public void Aceleracion_Evento(String val) throws IOException{
		try {
			aceleracion=Integer.parseInt(val);
		}catch(Exception e) {
			this.error="Error: "+e;
			e.printStackTrace();
		}
		
	}
	
	public void IDLE_Evento(String val) {
		try {
			idle=Integer.parseInt(val);
		}catch(Exception e) {
			this.error="Error"+e;
			e.printStackTrace();
		}
	}
	
	public void VO_Evento(String val) {
		try {
			vo=Integer.parseInt(val);
		}catch(Exception e) {
			e.printStackTrace();
			this.error="Error"+e;
		}
	}
	
	public void ID_Evento(String val) {
		try {
			id=val;
		}catch(Exception e) {
			e.printStackTrace();
			this.error="Error"+e;
		}
	}
	
	public void GetTelem() {
		this.EnviarTele_Ext();
		if(io=="") {
			System.out.print("Ignicion: "+ignicion);
			if(ignicion==1) {
				System.out.println(" Activo");
			}else {
				System.out.println(" Inactivo");
			}
			System.out.print("Fuente de alimentaci�n principal: "+ext_pwr);
			if(ext_pwr==1) {
				System.out.println(" EXT-PWR");
			}else {
				System.out.println(" BACKUP-BATTERY");
			}
		}else {
			System.out.println(io);
		}
		if(ac=="") {
			System.out.println("Aceleracion: "+aceleracion+" Millas/Horas");
			System.out.println("Aceleracion: "+aceleracion*3600+" Millas/Segundos");
		}else {
			System.out.println(ac);
		}
		
		if(cl=="") {
			System.out.println("Idle: "+idle);
		}else {
			System.out.println(cl);
		}
		
		if(vos=="") {
			System.out.println("Odometro Virtual: "+vo+"M");
		}else {
			System.out.println(vos);
		}
		
		if(ids=="") {
			System.out.println("ID: "+id);
		}else {
			System.out.println(ids);
		}
		
		System.out.println("");
	}
	
	public void setCreado() {
		if(error=="") {
			creado_c=true;
		}else {
			creado_c=false;
		}
	}
	
	public boolean getCreado() {
		return creado_c;
	}
	
	public void mostrartipo(){
		if(telemetria_Ex) {
			GetTelem();
		}else {
			EnviarTeleBasica();
		}
	}
}
