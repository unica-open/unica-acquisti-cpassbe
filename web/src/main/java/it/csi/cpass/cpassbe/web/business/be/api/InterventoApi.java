/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
/**********************************************
 * CSI PIEMONTE
 **********************************************/
package it.csi.cpass.cpassbe.web.business.be.api;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import it.csi.cpass.cpassbe.ejb.util.mime.MimeTypeContainer.MimeType;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.pba.CopiaInterventoWrapper;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.web.dto.InterventiDaCopia;
import it.csi.cpass.cpassbe.web.dto.RicercaInterventiPerCopia;
import it.csi.cpass.cpassbe.web.dto.WebInterventoFileHolder;
import it.csi.cpass.cpassbe.web.util.annotation.LoadSettore;


/**
 * API interface for /pba/intervento path
 */
@Path("pba/intervento")
@Produces({MediaType.APPLICATION_JSON})
public interface InterventoApi {

	/**
	 * Deletes an Intervento by its id
	 * @param id the id
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@DELETE
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteInterventoById(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	/**
	 *
	 */
	@DELETE
	@Path("/physically/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteInterventoPhysicallyById(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	/**
	 * Gets the Interventi by the query params
	 * @param offset the offset
	 * @param limit the limit
	 * @param sort the sort
	 * @param direction the direction
	 * @param intervento the intervento
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@POST
	//@Path("ricerca/settore/{id-settore}")
	@Path("ricerca")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRicercaInterventi(
			@Min(0) @QueryParam("offset") Integer offset,
			@Min(1) @Max(100) @QueryParam("limit") Integer limit,
			@QueryParam("sort") @DefaultValue("") String sort,
			@QueryParam("direction") @DefaultValue("asc") String direction,
			Intervento intervento,
			//@PathParam("id-settore") UUID settoreId,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets the Interventi by the query params
	 * @param offset the offset
	 * @param limit the limit
	 * @param sort the sort
	 * @param direction the direction
	 * @param idProgrammaOld
	 * @param idProgrammaNew
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("ricerca-per-copia/programma-old-id/{id-programma-old}/programma-new-id/{id-programma-new}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRicercaInterventiXCopia(
			@Min(0) @QueryParam("offset") Integer offset,
			@Min(1) @Max(100) @QueryParam("limit") Integer limit,
			@QueryParam("sort") @DefaultValue("") String sort,
			@QueryParam("direction") @DefaultValue("asc") String direction,
			@PathParam("id-programma-old") UUID idProgrammaOld,
			@PathParam("id-programma-new") UUID idProgrammaNew,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets the Interventi by the query params
	 * @param offset the offset
	 * @param limit the limit
	 * @param sort the sort
	 * @param direction the direction
	 * @param ricercaInterventiPerCopia
	 * @param idProgrammaNew
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@POST
	@Path("post-ricerca-per-copia")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postRicercaInterventiXCopia(
			@Min(0) @QueryParam("offset") Integer offset,
			@Min(1) @Max(100) @QueryParam("limit") Integer limit,
			@QueryParam("sort") @DefaultValue("") String sort,
			@QueryParam("direction") @DefaultValue("asc") String direction,
			RicercaInterventiPerCopia ricercaInterventiPerCopia,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


	/**
	 * Gets an Intervento by its id
	 * @param id the id
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getInterventoById(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/cui-futuro/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getInterventoInProgrammaFuturoById(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	@GET
	@Path("lista/cui/{cui}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getInterventiByCui(
			@PathParam("cui") String cui,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	/**
	 * Gets an Intervento by its cui
	 * @param cui the cui
	 * @param idProgramma
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("cui/{cui}/programma/{id-programma}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getInterventoByCui(
			@PathParam("cui") String cui,
			@PathParam("id-programma") UUID idProgramma,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


	/**
	 * Gets the InterventoImporti by Intervento id
	 * @param id the id
	 * @param offset the offset
	 * @param limit the limit
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("{id}/intervento-importi")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getInterventoImportiByIntervento(
			@PathParam("id") UUID id,
			@Min(0) @QueryParam("offset") Integer offset,
			@Min(1) @Max(100) @QueryParam("limit") Integer limit,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Posts an Intervento
	 * @param intervento the intervento to save
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postIntervento(
			Intervento intervento,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


	/**
	 * Posts an Intervento
	 * @param interventiDaCopia
	 * @param idProgramma
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@POST
	@Path("copia-lista-int/programma/{id-programma}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postInterventiDaCopia(
			InterventiDaCopia interventiDaCopia,
			@PathParam("id-programma") UUID idProgramma,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Posts an Intervento
	 * @param interventiDaCopia
	 * @param idProgramma
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@POST
	@Path("copia-lista-int-v2/programma/{id-programma}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postInterventiDaCopiaV2(
			List<CopiaInterventoWrapper> listCopiaInterventoWrapper,
			@PathParam("id-programma") UUID idProgramma,
			@QueryParam("stato") String stato,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);



	/**
	 * Puts an Intervento by its id
	 * @param id the id
	 * @param intervento the intervento to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@PUT
	@Path("{id}")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response putInterventoById(
			@PathParam("id") UUID id,
			Intervento intervento,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	/**
	 * Prints the allegato scheda B intervento as an Excel
	 * @param idProgramma the id programma
	 * @param formatFile the file format
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("stampa/allegato-scheda-b/{id-programma}/{formatFile}")
	@Produces({MimeType.EXCEL_WORKBOOK, MimeType.PDF})
	public Response stampaAllegatoIntervento(
			@PathParam("id-programma") UUID idProgramma,
			@PathParam("formatFile") String formatFile,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


	/**
	 * Puts an stato Intervento by its id
	 * @param id the id
	 * @param intervento the intervento to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@PUT
	@LoadSettore
	@Path("stato/annullato/{id}/settore/{id-settore}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response putInterventoStatoAnnullatoById(
			@PathParam("id") UUID id,
			@PathParam("id-settore") UUID settoreId,
			Intervento intervento,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


	/**
	 * Puts an stato Intervento by its id
	 * @param id the id
	 * @param intervento the intervento to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@PUT
	@Path("stato/approvato/{id}")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response putInterventoStatoApprovatoById(
			@PathParam("id") UUID id,
			Intervento intervento,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Puts an stato of Interventi by its id
	 * @param intervento the intervento to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@PUT
	@Path("stato/approvato")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response putInterventiStatoApprovato(
			List<Intervento> intervento,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	//postControlloCapofilaXCopiaInterventi
	/**
	 * Puts an stato of Interventi by its id
	 * @param intervento the intervento to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@PUT
	@Path("stato/annullato/settore/{id-settore}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putInterventiStatoAnnullato(
			@PathParam("id-settore") UUID settoreId,
			List<Intervento> intervento,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);



	/**
	 * Posts the upload of a CSV
	 * @param fileHolder the file holder
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@POST
	@Path("upload/csv")
	@LoadSettore
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response postUploadCsv(
			@MultipartForm WebInterventoFileHolder fileHolder,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/cpvs/intervento/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getCpvsByInterventoId(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/ultimo-stato-info/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getUltimoStatoInfoByInterventoId(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/lista-stati/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getStatiInterventoByInterventoId(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("/intervento-cpv/{id-intervento}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response postCpvsByInterventoId(
			@PathParam("id-intervento") UUID idIntervento,
			List<Cpv> cpvs,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/storico/rups/intervento/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getStoricoRupsByInterventoId(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("/voltura/{id-settore}/{id-rup}")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response putInterventoVoltura(
			@PathParam("id-settore") UUID idSettore,
			@PathParam("id-rup") UUID idRup,
			List<Intervento> interventi,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Puts an stato of Interventi by its id
	 * @param intervento the intervento to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@PUT
	@Path("stato/visto")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putInterventiStatoVisto(
			List<Intervento> intervento,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	/**
	 * Puts an stato of Interventi by its id
	 * @param intervento the intervento to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@PUT
	@Path("stato/bozza-da-rifiuto")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putInterventiStatoBozzaDaRifiuto(
			List<Intervento> intervento,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	/**
	 * Puts an stato of Interventi by its id
	 * @param intervento the intervento to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@PUT
	@Path("stato/visto-e-validato")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putInterventiStatoVistoEValidato(
			List<Intervento> intervento,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("/prendi-in-carico")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putInterventoPrendiInCarico(
			List<Intervento> interventi,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Puts an stato of Interventi by its id
	 * @param intervento the intervento to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@PUT
	@Path("stato/visto-ragioneria")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putInterventiStatoVistoRagioneria(
			List<Intervento> intervento,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	/**
	 * Puts an stato of Interventi by its id
	 * @param intervento the intervento to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@PUT
	@Path("stato/rifiuto-visto-ragioneria")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putInterventiStatoRifiutoVistoRagioneria(
			List<Intervento> intervento,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("interventi-capofila/programma/{id-programma}")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response getInterventiCapofila(
			@PathParam("id-programma") UUID idProgramma,
			Intervento intervento,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("interventi-capofila-associati/{id-int-capofila}")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response getInterventibyCapofilaId(
			@PathParam("id-int-capofila") UUID capofilaId,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("ricerca-per-copia/controllo-capofila")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response postControlloCapofilaXCopiaInterventi(
			List<Intervento> interventi,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@LoadSettore
	@Path("controllo/stato/cancellazione")
	@Produces({MediaType.APPLICATION_JSON})
	public Response postControlloCancellazioneIntervento(
			List<Intervento> interventi,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/lista-cig/{id-intervento}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getCigByInterventoId(
			@PathParam("id-intervento") UUID idIntervento,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Puts an stato of Interventi by its id
	 * @param intervento the intervento to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@PUT
	@Path("stato/riporta-in-bozza")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putInterventiRiportaInBozza(
			List<Intervento> intervento,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	/**
	 * Puts an stato Intervento by its id
	 * @param id the id
	 * @param intervento the intervento to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@PUT
	@LoadSettore
	@Path("avvia-intervento/{id-intervento}/{motivo-esclusione-cig}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response putAvviaInterventoById(
			@PathParam("id-intervento") UUID idIntervento,
			@PathParam("motivo-esclusione-cig") Integer motivoEsclusioneCigId,
			List<String> cigs,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@LoadSettore
	@Path("intervento-non-avviato/{id-intervento}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response putInterventoNonAvviatoById(
			@PathParam("id-intervento") UUID idIntervento,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

}

