/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.utils;

import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.text.StringSubstitutor;

public class StringUtility {

	public static void main(String[] args) {}

	public static String format (Map<String, Object> params, String message) {
		StringSubstitutor sub = new StringSubstitutor(params, "{", "}");
		return sub.replace(message);
	}
	
	public static boolean isEmpty(String value) {
        return value == null || value.trim().length() == 0;
    }

	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}
	
	public static String replace(String importoAnnoString, String oldChar, String newChar) {		
		String ris = importoAnnoString.replace(oldChar, newChar);
		return ris;
	}

	// https://stackoverflow.com/questions/11241690/regex-for-checking-if-a-string-is-strictly-alphanumeric/36079656#36079656	
	// se si vuole bonificare in senso piu' allargato ^[\\p{IsAlphabetic}\\p{IsDigit}]+$
	public static boolean hasSpecialChar (String cui) {
		CharSequence cs = cui;
		Pattern pattenXCui = Pattern.compile("[^a-zA-Z0-9]");	
		boolean hasSpecialChar = pattenXCui.matcher(cs).find();
		return hasSpecialChar;
	}
	
	public static  String bonificaStringaDaCaratteriSpeciali(String value) {
		String ris = "";
		String valueTrim = value.trim();
		char[] lista = valueTrim.toCharArray();
		String carattere= "";
		for(int i =0; i <lista.length; i++) {
			carattere = Character.toString(lista[i]);
			if(!hasSpecialChar (carattere)) {
				ris += carattere;
			}
		}
		//System.out.println("len "+ ris.length());
		return ris;
	} 
	
	public static String estraiStringa(String value,char separatore1,char separatore2) {
		char[] listaChar = value.toCharArray();
		Boolean concatenaStart=false;
		Boolean concatenaStop =false;
		String ris="";		
		Integer lunghezza =  value.length();
		for(int i=0;i<lunghezza;i++){
			//System.out.println("listaChar["+i+"] --> " + listaChar[i]);
			//System.out.println("concatenaStart --> " + concatenaStart);
			//System.out.println("concatenaStop --> " + concatenaStop);
			if(concatenaStart && !concatenaStop) {
				//System.out.println("aggiungo caratteri " + listaChar[i]);	
				//System.out.println("ris " + ris);			
				ris = ris + listaChar[i];
			}
			
			if(concatenaStart && listaChar[i]==separatore2) {
				//System.out.println("stop <--");	
				concatenaStop = true;
			}
			
			if(listaChar[i]==separatore1) {
				/*
				System.out.println("****************** ");		
				System.out.println("true ");		
				System.out.println("listaChar[i] " + listaChar[i]);		
				System.out.println("separatore1 --> " + separatore1);		
				System.out.println("****************** ");							
				*/
				concatenaStart = true;
			}else {
				/*
				System.out.println("****************** ");		
				System.out.println("false ");		
				System.out.println("listaChar[i] " + listaChar[i]);		
				System.out.println("separatore1 --> " + separatore1);		
				System.out.println("****************** ");	
				*/	
			}		
		}	
		if(ris.length()>1) {
			return ris.substring(0, ris.length()-1);
		}
		return "";
		
	}
	
	public static String estraiStringa(String value,char separatore1,int numeroseparatoriStart ,char separatore2,int numeroseparatoriStop) {
		char[] listaChar = value.toCharArray();
		Boolean concatenaStart=false;
		Boolean concatenaStop =false;
		int contatoreseparatoriStart = 1;
		int contatoreseparatoriStop  = 1;
		
		String ris="";
		
		Integer lunghezza =  value.length();
		for(int i=0;i<lunghezza;i++){
			//System.out.println("listaChar["+i+"] --> " + listaChar[i]);
			if(concatenaStart && !concatenaStop) {
				//System.out.println("aggiungo caratteri " + listaChar[i]);	
				ris = ris + listaChar[i];
				//System.out.println("ris " + ris);
			}
			
			if(concatenaStart && listaChar[i]==separatore2) {
				//System.out.println("contatoreseparatoriStop " + contatoreseparatoriStop);	
				//System.out.println("numeroseparatoriStop " + numeroseparatoriStop);	

				if(contatoreseparatoriStop == numeroseparatoriStop) {
					concatenaStop = true;
				}else {
					contatoreseparatoriStop++;
				}
			}
			//System.out.println("condizione " + (listaChar[i]==separatore1));
			//System.out.println(listaChar[i]);
			//System.out.println(separatore1);

			if(listaChar[i]==separatore1) {
				//System.out.println("contatoreseparatoriStart " + contatoreseparatoriStart);	
				//System.out.println("numeroseparatoriStart " + numeroseparatoriStart);	

				if(contatoreseparatoriStart == numeroseparatoriStart) {
					//System.out.println("start --> ");							
					concatenaStart = true;
				}else {
					contatoreseparatoriStart++;
				}
			}
		}
		
		
		if(ris.length()>1) {
			ris = ris.substring(0, ris.length()-1);;
			//System.out.println("ris --> " + ris);
			return ris;
		}
		return "";
		
	}
	
}
