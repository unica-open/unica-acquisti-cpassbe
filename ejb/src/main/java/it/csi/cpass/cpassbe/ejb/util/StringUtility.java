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
package it.csi.cpass.cpassbe.ejb.util;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.text.StringSubstitutor;

import it.csi.cpass.cpassbe.lib.dto.custom.ImpegniCsvAdapter;

public class StringUtility {

	public static void main(String[] args) {
		String pippo = "aaaa	bbbbb";
		pippo = pippo.replaceAll ( "\r | \n | \t " , "-");
		//pippo = pippo.replaceAll ( "\t" , "-");
		//pippo = bonificaStringaDaCaratteriSpeciali(pippo);
		//System.out.println("ris --> " + pippo );
	}
	/**
	 * 
	 * @param params
	 * @param message
	 * @return
	 */
	public static String format (Map<String, Object> params, String message) {
		final StringSubstitutor sub = new StringSubstitutor(params, "{", "}");
		return sub.replace(message);
	}
	/**
	 * 
	 * @param <T>
	 * @param value
	 * @return
	 */
	public static <T> boolean isListEmpty(List<T> value) {
		return value == null || value.size() == 0;
	}
	/**
	 * 
	 * @param importoAnnoString
	 * @param oldChar
	 * @param newChar
	 * @return
	 */
	public static String replace(String importoAnnoString, String oldChar, String newChar) {
		final String ris = importoAnnoString.replace(oldChar, newChar);
		return ris;
	}

	// https://stackoverflow.com/questions/11241690/regex-for-checking-if-a-string-is-strictly-alphanumeric/36079656#36079656
	// se si vuole bonificare in senso piu' allargato ^[\\p{IsAlphabetic}\\p{IsDigit}]+$
	public static boolean hasSpecialChar (String cui) {
		final CharSequence cs = cui;
		final Pattern pattenXCui = Pattern.compile("[^a-zA-Z0-9]");
		final boolean hasSpecialChar = pattenXCui.matcher(cs).find();
		return hasSpecialChar;
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static  String bonificaStringaDaCaratteriSpeciali(String value) {
		StringBuilder ris = new StringBuilder();
		final String valueTrim = value.trim();
		final char[] lista = valueTrim.toCharArray();
		String carattere= "";
		for (char element : lista) {
			carattere = Character.toString(element);
			if(!hasSpecialChar (carattere)) {
				ris.append(carattere);
			}
		}
		//System.out.println("len "+ ris.length());
		return ris.toString();
	}
	/**
	 * 
	 * @param value
	 * @param separatore1
	 * @param separatore2
	 * @return
	 */
	public static String estraiStringa(String value,char separatore1,char separatore2) {
		final char[] listaChar = value.toCharArray();
		boolean concatenaStart=false;
		boolean concatenaStop =false;
		String ris="";
		final Integer lunghezza =  value.length();
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
	/**
	 * 
	 * @param value
	 * @param separatore1
	 * @param numeroseparatoriStart
	 * @param separatore2
	 * @param numeroseparatoriStop
	 * @return
	 */
	public static String estraiStringa(String value,char separatore1,int numeroseparatoriStart ,char separatore2,int numeroseparatoriStop) {
		final char[] listaChar = value.toCharArray();
		boolean concatenaStart=false;
		boolean concatenaStop =false;
		int contatoreseparatoriStart = 1;
		int contatoreseparatoriStop  = 1;
		String ris="";
		final Integer lunghezza =  value.length();
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
			ris = ris.substring(0, ris.length()-1);
			//System.out.println("ris --> " + ris);
			return ris;
		}
		return "";

	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static String stringNoTab(String value) {
		final String valuetrim = value == null ? "" :value.trim();
		final String ris = valuetrim.replace ( '\t' , ' ');
		return ris;
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static String trim(String value) {
		if(value!=null) {
			value = value.trim();
		}
		return value;
	}
	/**
	 * 
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static Boolean isEqualsString(String value1,String value2) {
		value1 = value1 != null ? value1.trim().toUpperCase() : "";
		value2 = value2 != null ? value2.trim().toUpperCase() : "";
		if(value1.equalsIgnoreCase(value2)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	/**
	 * 
	 * @param csvadapter
	 * @param value
	 * @param lunghezza
	 * @return
	 */
	public static String troncaStringa(ImpegniCsvAdapter csvadapter,String value,int lunghezza) {
		String ris = "";
		if(value!=null) {
			value = value.trim();//.replace('"', ' ').replace("'", " ");
			if(value.length()>lunghezza) {
				//log.warn("", "riga su file non corretta anno imp " + csvadapter.getAnnoImpegno() + " numero imp "+ csvadapter.getNumImpegno() + " len: " + value.length() + " valore "+ value.trim()   );
				ris = value.substring(0, lunghezza-1);
			}
		}
		return ris;
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		return value == null || value.trim().length() == 0;
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}

}
