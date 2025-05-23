/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Set;

import it.csi.cpass.cpassbe.lib.dto.Cdc;

public class YmlGeneratore {
	// final static String PATH_ROOT = "D:\\workspace-cpass\\cpassbe\\docs\\definitions";
	final static String PATH_ROOT = "..\\docs\\definitions";

	final static String PATH_DECODIFICA = PATH_ROOT + "\\decodifica";
	final static String PATH_PBA = PATH_ROOT + "\\pba";
	final static String PATH_MAGAZZINO = PATH_ROOT + "\\magazzino";
	final static String PATH_SYSTEM = PATH_ROOT + "\\system";
	final static String PATH_COMMON = PATH_ROOT + "\\common";
	
	
	final static String PATH_CONSULTA = PATH_ROOT + "\\ord\\consultazioni";	
	final static String PATH_RMS = PATH_ROOT + "\\rms";
	final static String PATH_BO = PATH_ROOT + "\\bo";

	final static String PATH_EVASIONE = PATH_ROOT + "\\ord\\evasione";
	final static String PATH_ORDINE = PATH_ROOT + "\\ord";

	final static String ACAPO = "\n";

	public static void main(String[] args) {
		try {
			// oggetti system
			//go(Settore.class, PATH_SYSTEM);
			//go(RuoloUtenteSettore.class, PATH_SYSTEM);
			//go(UtenteSettore.class, PATH_SYSTEM);
			go(Cdc.class, PATH_SYSTEM);
			//go(StatiIntervento.class, PATH_PBA);
			//go(InterventoCig.class, PATH_PBA);
			
			//go(ProvvedimentoTipo.class, PATH_DECODIFICA);
			//go(Provvedimento.class, PATH_COMMON);
			//go(GestioneCampo.class, PATH_COMMON);
			//go(SettoreStorico.class, PATH_COMMON);
			//go(RigaOrdineWrapper.class, PATH_ORDINE);
			//go(Sezione.class, PATH_ORDINE);
			//go(ConsultazioniEvasione.class, PATH_CONSULTA);
			//go(ConsultazioniImpegno.class, PATH_CONSULTA);
			//go(ConsultazioniOrdine.class, PATH_CONSULTA);
			//go(ConsultazioniRipartizioneEvasione.class, PATH_CONSULTA);
			//go(RicercaXConsultazioni.class, PATH_CONSULTA);
			//go(SmistamentoManualeRms.class, PATH_BO);
			//go(Magazzino.class, PATH_MAGAZZINO);
			//go(MotiviEsclusioneCig.class, PATH_DECODIFICA);
			//go(ProtocolloOrdine.class, PATH_ORDINE);
			//go(DocumentiOrdine.class, PATH_ORDINE);
			//go(AooActa.class, PATH_ORDINE);
			//go(SettoreAooActa.class, PATH_ORDINE);
			//go(TipoProceduraOrd.class, PATH_ORDINE);
			//go(SettoreSerieActa.class, PATH_ORDINE);
			//go(OdsDatiContabili.class, PATH_ROOT);
			
			// oggetti decodifica
//			go(OggettiSpesa.class, PATH_DECODIFICA);

			// oggetti pba
			//go(CopiaInterventoWrapper.class, PATH_PBA);
//			go(TipoProceduraPba.class, PATH_PBA);
//			go(Intervento.class, PATH_PBA);
//			go(StoricoInterventoRup.class, PATH_PBA);
//			go(InterventoAltriDati.class, PATH_PBA);

			// oggetti evasione
//			go(TestataEvasione.class, PATH_EVASIONE);
//			go(DocumentoTrasporto.class, PATH_EVASIONE);
//			go(DocumentoTrasportoRiga.class, PATH_EVASIONE);
//			go(CausaleSospensioneEvasione.class, PATH_EVASIONE);
//			go(TipoEvasione.class, PATH_EVASIONE);
//			go(DestinatarioEvasione.class, PATH_EVASIONE);
//			go(RigaEvasione.class, PATH_EVASIONE);
//			go(ImpegnoEvasione.class, PATH_EVASIONE);
//			go(SubimpegnoEvasione.class, PATH_EVASIONE);
//			go(SalvaEvasione.class, PATH_EVASIONE);
//			go(SalvaImpegniEvasione.class, PATH_EVASIONE);

			// oggetti ordine
//			go(ImpegnoOrdine.class, PATH_ORDINE);
//			go(SubimpegnoOrdine.class, PATH_ORDINE);

			// non si può generare in automatico per OggettiSpesa chiamato Ods a FE, e campo impegniOrdine non presente a BE
			// go(RigaOrdine.class, PATH_ORDINE);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void go(Class<?> objClass, String path) throws FileNotFoundException, IOException {
		StringBuilder sbOut = new StringBuilder();
		sbOut.append(objClass.getSimpleName() + ":" + ACAPO);
		sbOut.append("  type: object" + ACAPO);
		sbOut.append("  properties:" + ACAPO);

		Hashtable<String, String> hstProperies = new Hashtable<String, String>();

		// Get the public methods associated with this class.
		Method[] methods = objClass.getMethods();
		for (Method method : methods) {
			// System.out.println("Public method found: " + method.toString());
			if (method.getName().startsWith("get")) {
				//System.out.println(method.getReturnType() + " - " + method.getName());
				String property = method.getName().substring(3, method.getName().length());
				property = property.substring(0, 1).toLowerCase() + property.substring(1);

				StringBuilder sb = new StringBuilder();
				if (method.getReturnType().getCanonicalName().equals("java.util.UUID")) {
					sb.append("    " + property + ":" + ACAPO);
					sb.append("      type: string" + ACAPO);
					sb.append("      format: uuid" + ACAPO);

				} else if (method.getReturnType().getCanonicalName().equals("java.lang.Object")) {
					sb.append("    " + property + ":" + ACAPO);
					sb.append("      type: string" + ACAPO);
					sb.append("      format: uuid" + ACAPO);

				} else if (method.getReturnType().getCanonicalName().equals("java.lang.String")) {
					sb.append("    " + property + ":" + ACAPO);
					sb.append("      type: string" + ACAPO);

				} else if (method.getReturnType().getCanonicalName().equals("java.util.Date")) {
					sb.append("    " + property + ":" + ACAPO);
					sb.append("      type: string" + ACAPO);
					sb.append("      format: date-time" + ACAPO);

				} else if (method.getReturnType().getCanonicalName().equals("java.math.BigDecimal")) {
					sb.append("    " + property + ":" + ACAPO);
					sb.append("      type: number" + ACAPO);

				} else if (method.getReturnType().getCanonicalName().equals("java.lang.Boolean")) {
					sb.append("    " + property + ":" + ACAPO);
					sb.append("      type: boolean" + ACAPO);
				} else if (method.getReturnType().getCanonicalName().equals("byte")) {
					sb.append("    " + property + ":" + ACAPO);
					sb.append("      type: File" + ACAPO);
				} else if (method.getReturnType().getCanonicalName().equals("java.lang.Integer")) {
					sb.append("    " + property + ":" + ACAPO);
					sb.append("      type: integer" + ACAPO);

				} else if (method.getReturnType().getCanonicalName().equals("java.util.List")) {
					String objName = method.getGenericReturnType().getTypeName();
					int iDot = objName.lastIndexOf(".");
					objName = objName.substring(iDot + 1, objName.length() - 1);

					sb.append("    " + property + ":" + ACAPO);
					sb.append("      type: array" + ACAPO);
					sb.append("      items:  " + ACAPO);
					sb.append("        $ref: '#/definitions/" + objName + "'" + ACAPO);

				} else if (method.getReturnType().getCanonicalName().startsWith("it.csi.cpass.cpassbe.lib.dto.")) {
					int iDot = method.getReturnType().getCanonicalName().lastIndexOf(".");
					String objName = method.getReturnType().getCanonicalName().substring(iDot + 1);
					// objName = objName.substring(0, 1).toLowerCase() + objName.substring(1);

					sb.append("    " + property + ":" + ACAPO);
					sb.append("      type: object" + ACAPO);
					sb.append("      $ref: '#/definitions/" + objName + "'" + ACAPO);

				} else {
					if (!property.equals("class")) {
						sb.append("    " + property + ":" + ACAPO);
						sb.append("      type: DA GESTIRE" + ACAPO);

					}
				}

				if (!sb.toString().trim().equals("")) {
					hstProperies.put(property, sb.toString());
				}
			}
		}

		//System.out.println("-----------------");
		// ordinamento properties
		Set<String> setProperties = hstProperies.keySet();
		ArrayList<String> listProperties = new ArrayList<String>();
		listProperties.addAll(setProperties);
		Collections.sort(listProperties, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});

		for (String property : listProperties) {
			sbOut.append(hstProperies.get(property));
		}

		//System.out.println(sbOut);

		String filename = "";
		String name = objClass.getSimpleName().substring(0, 1).toLowerCase() + objClass.getSimpleName().substring(1);
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			String sc = "" + c;
			if (c >= 'A' && c <= 'Z') {
				filename += "-" + sc.toLowerCase();
			} else {
				filename += c;
			}
		}

		if (path.startsWith(".")) {
			String current = new java.io.File(".").getCanonicalPath();
			path = current + "\\" + path;
		}

		File file = new File(path + "\\" + filename + ".yml");
		//System.out.println("output: " + file.getAbsolutePath());
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(sbOut.toString().getBytes());
		fos.close();
	}

}
