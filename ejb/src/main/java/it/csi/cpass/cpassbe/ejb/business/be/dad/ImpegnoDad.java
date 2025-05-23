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
package it.csi.cpass.cpassbe.ejb.business.be.dad;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTFlussoAnomalieDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTFlussoImpegniEsterniDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTFlussoSubImpegniEsterniDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTImpegnoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTSubimpegnoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdImpegnoOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdSubimpegnoOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassVOrdineDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.CpassTFlussoAnomalie;
import it.csi.cpass.cpassbe.ejb.entity.CpassTFlussoImpegniEsterni;
import it.csi.cpass.cpassbe.ejb.entity.CpassTFlussoSubimpegniEsterni;
import it.csi.cpass.cpassbe.ejb.entity.CpassTImpegno;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSubimpegno;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTFornitore;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdImpegnoOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdRigaOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSubimpegnoOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassVOrdine;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.DateUtility;
import it.csi.cpass.cpassbe.lib.dto.FlussoAnomalie;
import it.csi.cpass.cpassbe.lib.dto.FlussoImpegniEsterni;
import it.csi.cpass.cpassbe.lib.dto.FlussoSubimpegniEsterni;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.ord.ImpegnoOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.SubimpegnoOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.VOrdine;

@ApplicationScoped
public class ImpegnoDad extends BaseDad {

	@Inject
	private CpassTOrdImpegnoOrdineDao cpassTOrdImpegnoOrdineDao;
	@Inject
	private CpassTOrdSubimpegnoOrdineDao cpassTOrdSubimpegnoOrdineDao;
	@Inject
	private CpassTImpegnoDao cpassTImpegnoDao;
	@Inject
	private CpassVOrdineDao cpassVOrdineDao;
	@Inject
	private CpassTSubimpegnoDao cpassTSubimpegnoDao;
	@Inject
	private CpassTFlussoImpegniEsterniDao cpassTFlussoImpegniEsterniDao;
	@Inject
	private CpassTFlussoSubImpegniEsterniDao cpassTFlussoSubImpegniEsterniDao;
	@Inject
	private CpassTFlussoAnomalieDao cpassTFlussoAnomalieDao;
	/**
	 *
	 * @param impegno
	 * @return
	 */
	public Impegno update(Impegno impegno) {
		final CpassTImpegno cpassTImpegno = CpassMappers.IMPEGNO.toEntity(impegno);
		final Impegno result = CpassMappers.IMPEGNO.toModel(cpassTImpegnoDao.update(cpassTImpegno));
		return result;
	}
	/**
	 *
	 * @param subimpegno
	 * @return
	 */
	public Subimpegno update(Subimpegno subimpegno) {
		final CpassTSubimpegno cpassTSubimpegno = CpassMappers.SUBIMPEGNO.toEntity(subimpegno);
		final Subimpegno result = CpassMappers.SUBIMPEGNO.toModel(cpassTSubimpegnoDao.update(cpassTSubimpegno));
		return result;
	}
	/**
	 *
	 * @param testataOrdineId
	 * @return
	 */
	public List<CpassTOrdImpegnoOrdine> getImpegniCollegati(UUID testataOrdineId) {
		return cpassTOrdImpegnoOrdineDao.getImpegniCollegati(testataOrdineId);
	}
	/**
	 *
	 * @param impegnoId
	 * @param impegnoAnnoEsercizio
	 * @return
	 */
	public BigDecimal calcolaOrdinato(UUID impegnoId,Integer impegnoAnnoEsercizio) {
		return calcolaOrdinato( impegnoId,impegnoAnnoEsercizio, null);
	}
	/**
	 *
	 * @param impegnoId
	 * @param impegnoAnnoEsercizio
	 * @param testataOrdineId
	 * @return
	 */
	public BigDecimal calcolaOrdinato(UUID impegnoId,Integer impegnoAnnoEsercizio, UUID testataOrdineId) {
		return cpassTOrdImpegnoOrdineDao.calcolaOrdinato(impegnoId, impegnoAnnoEsercizio, testataOrdineId);
	}
	/**
	 *
	 * @param annoEsercizio
	 * @param annoImpegno
	 * @param numeroImpegno
	 * @param enteId
	 * @return
	 */
	public BigDecimal calcolaOrdinatoImpegno(Integer annoEsercizio,Integer annoImpegno,Integer numeroImpegno, UUID enteId) {
		return cpassTOrdImpegnoOrdineDao.calcolaOrdinatoImpegno( annoEsercizio, annoImpegno, numeroImpegno,null,  enteId);
	}
	/**
	 *
	 * @param subimpegnoId
	 * @param annoEsercizio
	 * @return
	 */
	public BigDecimal calcolaSubimpegnoOrdinato(UUID subimpegnoId,Integer annoEsercizio) {
		return cpassTOrdSubimpegnoOrdineDao.calcolaSubimpegnoOrdinato(subimpegnoId, annoEsercizio);
	}
	/**
	 *
	 * @param annoEsercizio
	 * @param anno
	 * @param numero
	 * @param enteId
	 * @return
	 */
	public List<Impegno> getImpegnoByChiaveLogica(Integer annoEsercizio, Integer anno, Integer numero, UUID enteId) {
		final List<CpassTImpegno> cpassTImpegno = cpassTImpegnoDao.getImpegnoByChiaveLogica(annoEsercizio, anno, numero, enteId);
		final List<Impegno> impegni = CpassMappers.IMPEGNO.toModels(cpassTImpegno);
		return impegni;
	}
	/**
	 * 
	 * @param idSubimpegno
	 * @return
	 */
	public Subimpegno getById(UUID idSubimpegno) {
		final Optional<CpassTSubimpegno> cpassTSubimpegnoOptional = cpassTSubimpegnoDao.findOne(idSubimpegno);
		if (cpassTSubimpegnoOptional.isPresent()) {
			final CpassTSubimpegno cpassTSubimpegno = cpassTSubimpegnoOptional.get();
			return CpassMappers.SUBIMPEGNO.toModel(cpassTSubimpegno);
		}
		return null;
	}
	/**
	 *
	 * @param annoEsercizio
	 * @param anno
	 * @param numero
	 * @param enteId
	 * @param subimpegnoAnno
	 * @param subimpegnoNumero
	 * @return
	 */

	public Subimpegno getSubimpegnoByChiaveLogica(Integer annoEsercizio, Integer anno, Integer numero, UUID enteId, Integer subimpegnoAnno,Integer subimpegnoNumero) {
		final List<Subimpegno> lista = getListSubimpegnoByChiaveLogica( annoEsercizio,  anno,  numero,  enteId,  subimpegnoAnno, subimpegnoNumero);
		if (lista == null || lista.size() == 0) {
			return null;
		}
		return lista.get(0);
	}
	/**
	 * 
	 * @param annoEsercizio
	 * @param anno
	 * @param numero
	 * @param enteId
	 * @param subimpegnoAnno
	 * @param subimpegnoNumero
	 * @return
	 */
	public List<Subimpegno> getListSubimpegnoByChiaveLogica(Integer annoEsercizio, Integer anno, Integer numero, UUID enteId, Integer subimpegnoAnno,Integer subimpegnoNumero) {
		final List<CpassTSubimpegno> lista = cpassTSubimpegnoDao.getSubimpegnoByChiaveLogica(annoEsercizio, anno, numero, enteId, subimpegnoAnno, subimpegnoNumero);
		final List<Subimpegno> subimpegni = CpassMappers.SUBIMPEGNO.toModels(lista);
		return subimpegni;
	}

	/**
	 *
	 * @param rigaOrdine
	 * @param listImpegno
	 * @param cpassTFornitore
	 * @param cpassTEnte
	 */
	public void insertImpegni(RigaOrdine rigaOrdine, List<Impegno> listImpegno, CpassTFornitore cpassTFornitore, CpassTEnte cpassTEnte) {
		final CpassTOrdRigaOrdine cpassTOrdRigaOrdine = CpassMappers.RIGA_ORDINE.toEntity(rigaOrdine);

		// cancello le vecchie relazioni
		final List<CpassTOrdImpegnoOrdine> impegnoOrdines = cpassTOrdImpegnoOrdineDao.getImpegni(cpassTOrdRigaOrdine.getRigaOrdineId());
		if (impegnoOrdines != null) {
			for (final CpassTOrdImpegnoOrdine cpassTOrdImpegnoOrdine : impegnoOrdines) {

				final List<CpassTOrdSubimpegnoOrdine> subimpegnoOrdines = cpassTOrdSubimpegnoOrdineDao.getSubimpegni(cpassTOrdImpegnoOrdine.getId());
				if (subimpegnoOrdines != null) {
					for (final CpassTOrdSubimpegnoOrdine cpassTOrdSubimpegnoOrdine : subimpegnoOrdines) {
						cpassTOrdSubimpegnoOrdineDao.delete(cpassTOrdSubimpegnoOrdine.getId());
					}
				}
			}
		}
		cpassTOrdImpegnoOrdineDao.deleteByRiga(cpassTOrdRigaOrdine.getRigaOrdineId());
		cpassTOrdImpegnoOrdineDao.flushAndClear();

		// inserisco nuove relazioni
		if (listImpegno != null && listImpegno.size() > 0) {
			int impegnoProgressivo = 0;
			for (final Impegno impegno : listImpegno) {
				impegnoProgressivo++;

				final CpassTImpegno cpassTImpegno = CpassMappers.IMPEGNO.toEntity(impegno);
				// controllo lunghezza massima
				if (cpassTImpegno.getImpegnoDescrizione() != null && cpassTImpegno.getImpegnoDescrizione().length() > 150) {
					cpassTImpegno.setImpegnoDescrizione(cpassTImpegno.getImpegnoDescrizione().substring(0, 150));
				}
				cpassTImpegno.setCpassTEnte(cpassTEnte);

				if (impegno.getFornitore() != null && impegno.getFornitore().getCodice() != null) {
					cpassTImpegno.setCpassTFornitore(cpassTFornitore);
				} else {
					cpassTImpegno.setCpassTFornitore(null);
				}
				//CONTROLLARE LA FONTE
				final List<CpassTImpegno> cpassTImpegnoPresente = cpassTImpegnoDao.getImpegnoByChiaveLogica(cpassTImpegno.getImpegnoAnnoEsercizio(),cpassTImpegno.getImpegnoAnno(), cpassTImpegno.getImpegnoNumero(), cpassTImpegno.getCpassTEnte().getId());
				if (cpassTImpegnoPresente.isEmpty()) {
					final CpassTImpegno impNew = cpassTImpegnoDao.insert(cpassTImpegno);
					cpassTImpegnoPresente.add(impNew);
				}

				// associato
				final CpassTOrdImpegnoOrdine cpassTOrdImpegnoOrdine = new CpassTOrdImpegnoOrdine();
				cpassTOrdImpegnoOrdine.setCpassTImpegno(cpassTImpegnoPresente.get(0));
				cpassTOrdImpegnoOrdine.setCpassTOrdRigaOrdine(cpassTOrdRigaOrdine);

				cpassTOrdImpegnoOrdine.setImpegnoAnno(cpassTImpegnoPresente.get(0).getImpegnoAnno());
				cpassTOrdImpegnoOrdine.setImpegnoAnnoEsercizio(cpassTImpegnoPresente.get(0).getImpegnoAnnoEsercizio());
				cpassTOrdImpegnoOrdine.setImpegnoNumero(cpassTImpegnoPresente.get(0).getImpegnoNumero());

				cpassTOrdImpegnoOrdine.setImpegnoProgressivo(impegnoProgressivo);
				cpassTOrdImpegnoOrdine.setImporto(impegno.getImporto());

				cpassTOrdImpegnoOrdineDao.insert(cpassTOrdImpegnoOrdine);

				// subimpegno
				if (impegno.getSubimpegni() != null && impegno.getSubimpegni().size() > 0) {
					for (final Subimpegno subimpegno : impegno.getSubimpegni()) {
						final CpassTSubimpegno cpassTSubimpegno = CpassMappers.SUBIMPEGNO.toEntity(subimpegno);

						cpassTSubimpegno.setCpassTEnte(cpassTEnte);

						if (subimpegno.getFornitore() != null && subimpegno.getFornitore().getCodice() != null) {
							cpassTSubimpegno.setCpassTFornitore(cpassTFornitore);
						} else {
							cpassTSubimpegno.setCpassTFornitore(null);
						}

						cpassTSubimpegno.setCpassTImpegno(cpassTImpegnoPresente.get(0));

						cpassTSubimpegno.setImpegnoAnno(impegno.getAnno());
						cpassTSubimpegno.setImpegnoAnnoEsercizio(impegno.getAnnoEsercizio());
						cpassTSubimpegno.setImpegnoNumero(impegno.getNumero());

						final List<CpassTSubimpegno> cpassTSubimpegnoPresente = cpassTSubimpegnoDao.getSubimpegnoByChiaveLogica(cpassTSubimpegno.getImpegnoAnnoEsercizio(),
								cpassTSubimpegno.getImpegnoAnno(), cpassTSubimpegno.getImpegnoNumero(), cpassTSubimpegno.getCpassTEnte().getId(),
								cpassTSubimpegno.getSubimpegnoAnno(), cpassTSubimpegno.getSubimpegnoNumero());

						if (cpassTSubimpegnoPresente.size() == 0) {
							cpassTSubimpegnoPresente.add (cpassTSubimpegnoDao.insert(cpassTSubimpegno));
						}

						// associato
						final CpassTOrdSubimpegnoOrdine cpassTOrdSubimpegnoOrdine = new CpassTOrdSubimpegnoOrdine();
						cpassTOrdSubimpegnoOrdine.setCpassTOrdImpegnoOrdine(cpassTOrdImpegnoOrdine);
						cpassTOrdSubimpegnoOrdine.setCpassTSubimpegno(cpassTSubimpegnoPresente.get(0));

						cpassTOrdSubimpegnoOrdine.setImpegnoAnno(cpassTImpegnoPresente.get(0).getImpegnoAnno());
						cpassTOrdSubimpegnoOrdine.setImpegnoAnnoEsercizio(cpassTImpegnoPresente.get(0).getImpegnoAnnoEsercizio());
						cpassTOrdSubimpegnoOrdine.setImpegnoNumero(cpassTImpegnoPresente.get(0).getImpegnoNumero());

						cpassTOrdSubimpegnoOrdine.setSubimpegnoAnno(cpassTSubimpegnoPresente.get(0).getSubimpegnoAnno());
						cpassTOrdSubimpegnoOrdine.setSubimpegnoImporto(cpassTSubimpegnoPresente.get(0).getImportoAttuale());
						cpassTOrdSubimpegnoOrdine.setSubimpegnoNumero(cpassTSubimpegnoPresente.get(0).getSubimpegnoNumero());

						cpassTOrdSubimpegnoOrdine.setSubimpegnoImporto(subimpegno.getImporto());

						cpassTOrdSubimpegnoOrdineDao.insert(cpassTOrdSubimpegnoOrdine);
					}
				}
			}
		}
	}
	/**
	 *
	 * @param rigaOrdineId
	 * @return
	 */
	public List<ImpegnoOrdine> getImpegnoOrdineByRiga(UUID rigaOrdineId) {
		final List<CpassTOrdImpegnoOrdine> cpassTOrdImpegnoOrdines = cpassTOrdImpegnoOrdineDao.getImpegni(rigaOrdineId);
		final List<ImpegnoOrdine> impegnoOrdines = CpassMappers.IMPEGNO_ORDINE.toModels(cpassTOrdImpegnoOrdines);
		return impegnoOrdines;
	}
	/**
	 *
	 * @param rigaOrdineId
	 * @param rigaEvasioneId
	 * @return
	 */
	public List<ImpegnoOrdine> getImpegnoOrdineByRigaNonPresentiEvasione(UUID rigaOrdineId, UUID rigaEvasioneId) {
		final List<CpassTOrdImpegnoOrdine> cpassTOrdImpegnoOrdines = cpassTOrdImpegnoOrdineDao.getImpegniNonPresentiEvasione(rigaOrdineId, rigaEvasioneId);
		final List<ImpegnoOrdine> impegnoOrdines = CpassMappers.IMPEGNO_ORDINE.toModels(cpassTOrdImpegnoOrdines);
		return impegnoOrdines;
	}
	/**
	 *
	 * @param ImpegnoOrdineId
	 * @return
	 */
	public List<SubimpegnoOrdine> getSubimpegnoOrdineByImpegnoOrdineId(UUID ImpegnoOrdineId) {
		final List<CpassTOrdSubimpegnoOrdine> cpassTOrdSubimpegnoOrdines = cpassTOrdSubimpegnoOrdineDao.getSubimpegni(ImpegnoOrdineId);
		final List<SubimpegnoOrdine> subimpegnoOrdines = CpassMappers.SUBIMPEGNO_ORDINE.toModels(cpassTOrdSubimpegnoOrdines);
		return subimpegnoOrdines;
	}
	/**
	 *
	 * @param idRigaOrdine
	 * @return
	 */
	public List<Impegno> getImpegniByRiga(UUID idRigaOrdine) {
		final List<Impegno> impegnos = new ArrayList<>();
		// impegni
		final List<CpassTOrdImpegnoOrdine> impegnoOrdines = cpassTOrdImpegnoOrdineDao.getImpegni(idRigaOrdine);
		if (impegnoOrdines != null) {
			for (final CpassTOrdImpegnoOrdine cpassTOrdImpegnoOrdine : impegnoOrdines) {
				final Impegno impegno = CpassMappers.IMPEGNO.toModel(cpassTOrdImpegnoOrdine.getCpassTImpegno());
				impegno.setImporto(cpassTOrdImpegnoOrdine.getImporto());
				impegnos.add(impegno);

				// subimpegni
				final List<CpassTOrdSubimpegnoOrdine> subimpegnoOrdines = cpassTOrdSubimpegnoOrdineDao.getSubimpegni(cpassTOrdImpegnoOrdine.getImpegnoOrdineId());
				if (subimpegnoOrdines != null) {
					for (final CpassTOrdSubimpegnoOrdine cpassTOrdSubimpegnoOrdine : subimpegnoOrdines) {
						final Subimpegno subimpegno = CpassMappers.SUBIMPEGNO.toModel(cpassTOrdSubimpegnoOrdine.getCpassTSubimpegno());
						subimpegno.setImporto(cpassTOrdSubimpegnoOrdine.getSubimpegnoImporto());
						impegno.getSubimpegni().add(subimpegno);
					}
				}
			}
		}
		return impegnos;
	}
	/**
	 *
	 * @param idRiga
	 */
	public void annullaImpegniByRiga(UUID idRiga) {
		final List<CpassTOrdImpegnoOrdine> impegniOrdine = cpassTOrdImpegnoOrdineDao.getImpegni(idRiga);
		if (impegniOrdine != null) {
			for (final CpassTOrdImpegnoOrdine impegnoOrdine : impegniOrdine) {
				impegnoOrdine.setImporto(BigDecimal.ZERO);
				cpassTOrdImpegnoOrdineDao.update(impegnoOrdine);

				for (final CpassTOrdSubimpegnoOrdine subimpegnoOrdine : impegnoOrdine.getCpassTOrdSubimpegnoOrdines()) {
					subimpegnoOrdine.setSubimpegnoImporto(BigDecimal.ZERO);
					cpassTOrdSubimpegnoOrdineDao.update(subimpegnoOrdine);
				}
			}
		}
	}
	/**
	 *
	 * @param idRiga
	 */
	public void deleteImpegniByRiga_old(UUID idRiga) {
		final List<CpassTOrdImpegnoOrdine> impegni = cpassTOrdImpegnoOrdineDao.getImpegni(idRiga);
		if (impegni != null) {
			for (final CpassTOrdImpegnoOrdine impegno : impegni) {

				if (impegno.getCpassTOrdSubimpegnoOrdines() != null) {
					for (final CpassTOrdSubimpegnoOrdine subImpegno : impegno.getCpassTOrdSubimpegnoOrdines()) {
						cpassTOrdSubimpegnoOrdineDao.delete(subImpegno.getSubimpegnoOrdineId());
					}
					cpassTOrdSubimpegnoOrdineDao.flushAndClear();
				}
				cpassTOrdImpegnoOrdineDao.delete(impegno.getId());
			}
		}

	}
	/**
	 * 
	 * @param idRiga
	 */
	public void deleteImpegniByRiga(UUID idRiga) {
		//List<CpassTOrdImpegnoOrdine> impegni = cpassTOrdImpegnoOrdineDao.getImpegni(idRiga);
		cpassTOrdSubimpegnoOrdineDao.deleteFromRiga(idRiga);
		cpassTOrdSubimpegnoOrdineDao.flush();
		cpassTOrdImpegnoOrdineDao.deleteByRiga(idRiga);
	}

	/**
	 *
	 * @param idImpegnoOrdine
	 */
	public void deleteSubimpegniByImpegnoOrdine(UUID idImpegnoOrdine) {
		final List<CpassTOrdSubimpegnoOrdine> subimpegni = cpassTOrdSubimpegnoOrdineDao.getSubimpegni(idImpegnoOrdine);
		for (final CpassTOrdSubimpegnoOrdine subimpegno : subimpegni) {
			cpassTOrdSubimpegnoOrdineDao.delete(subimpegno.getSubimpegnoOrdineId());
		}
	}
	/**
	 *
	 * @param idTestataOrdine
	 * @return
	 */
	public List<VOrdine> getListImpegniRiepilogoByOrdineId(UUID idTestataOrdine) {
		final List<CpassVOrdine> lista = cpassVOrdineDao.getListImpegniRiepilogoByOrdineId(idTestataOrdine);
		final List<VOrdine> ris = CpassMappers.VORDINE.toModels(lista);
		return ris;
	}
	/**
	 *
	 * @param impegno
	 * @return
	 */
	public Impegno saveImpegno(Impegno impegno) {
		CpassTImpegno cpassTImpegno = CpassMappers.IMPEGNO.toEntity(impegno);
		// controllo lunghezza massima
		if (cpassTImpegno.getImpegnoDescrizione() != null && cpassTImpegno.getImpegnoDescrizione().length() > 150) {
			cpassTImpegno.setImpegnoDescrizione(cpassTImpegno.getImpegnoDescrizione().substring(0, 150));
		}
		cpassTImpegno = cpassTImpegnoDao.save(cpassTImpegno);
		return CpassMappers.IMPEGNO.toModel(cpassTImpegno);
	}

	public void flush() {
		cpassTImpegnoDao.flush();
	}

	/**
	 *
	 * @param subimpegno
	 * @return
	 */
	public Subimpegno saveSubimpegno(Subimpegno subimpegno) {
		CpassTSubimpegno cpassTSubimpegno = CpassMappers.SUBIMPEGNO.toEntity(subimpegno);
		cpassTSubimpegno = cpassTSubimpegnoDao.save(cpassTSubimpegno);
		return CpassMappers.SUBIMPEGNO.toModel(cpassTSubimpegno);
	}

	/**
	 *
	 * @param subimpegno
	 * @return
	 */
	public void saveSubimpegno(Integer annoCorrente,
			Integer annoImpegno,
			Integer numImpegno,
			Subimpegno subimpegno,
			UUID enteId) {
		CpassTSubimpegno cpassTSubimpegno = CpassMappers.SUBIMPEGNO.toEntity(subimpegno);

		List<Impegno> impegno = new ArrayList<>();
		if(cpassTSubimpegno.getCpassTImpegno()==null || cpassTSubimpegno.getCpassTImpegno().getId()==null) {
			impegno  = getImpegnoByChiaveLogica(annoCorrente,
					subimpegno.getImpegno().getAnno(),
					subimpegno.getImpegno().getNumero(),
					enteId);
			final CpassTImpegno cpassTimpegno = CpassMappers.IMPEGNO.toEntity(impegno.get(0));
			cpassTSubimpegno.setCpassTImpegno(cpassTimpegno);
		}

		if((impegno!=null && impegno.size()==1) || (cpassTSubimpegno.getCpassTImpegno()!=null && cpassTSubimpegno.getCpassTImpegno().getId()!=null)) {
			cpassTSubimpegno = cpassTSubimpegnoDao.save(cpassTSubimpegno);
		}else {
			log.error("elaboraSubImpegniCaricati", "impegno inesistente o duplice con chiave logica anno esercizio "+annoCorrente +" anno imp "+ annoImpegno +" num imp "+ numImpegno);
		}
	}

	/**
	 *
	 * @param enteId
	 * @param esito
	 * @param limit
	 * @param offset
	 * @param dataOdierna
	 * @param numelab
	 * @return
	 */
	public List<FlussoImpegniEsterni> getListFlussoImpegniEsterniByEnte(UUID enteId,String esito,Integer limit,Integer offset,Date dataOdierna,Integer numelab) {
		final List<CpassTFlussoImpegniEsterni> lista = cpassTFlussoImpegniEsterniDao.getFlussoImpegniEsterniByEnte(enteId,esito,limit,offset, dataOdierna, numelab);
		final List<FlussoImpegniEsterni> ris = CpassMappers.FlussoImpegniEsterni.toModels(lista);
		return ris;
	}
	/**
	 *
	 * @param enteId
	 * @param esito
	 * @param limit
	 * @param offset
	 * @param dataOdierna
	 * @param numelab
	 * @return
	 */
	public List<FlussoImpegniEsterni> getFlussoImpegniEsterniElaborabili(UUID enteId,String esito,Integer limit,Integer offset,Date dataOdierna,Integer numelab) {
		final List<CpassTFlussoImpegniEsterni> lista = cpassTFlussoImpegniEsterniDao.getFlussoImpegniEsterniElaborabili(enteId, esito, limit, offset, dataOdierna, numelab);
		final List<FlussoImpegniEsterni> ris = CpassMappers.FlussoImpegniEsterni.toModels(lista);
		return ris;
	}
	/**
	 *
	 * @param enteId
	 * @param esito
	 * @param limit
	 * @param offset
	 * @param dataOdierna
	 * @param numelab
	 * @return
	 */
	public List<FlussoSubimpegniEsterni> getListFlussoSubImpegniEsterniByEnte(UUID enteId,String esito,Integer limit, Integer offset,Date dataOdierna,Integer numelab) {
		final List<CpassTFlussoSubimpegniEsterni> lista = cpassTFlussoSubImpegniEsterniDao.getFlussoSubImpegniEsterniByEnte(enteId,esito, limit,  offset,  dataOdierna, numelab);
		final List<FlussoSubimpegniEsterni> ris = CpassMappers.FlussoSubImpegniEsterni.toModels(lista);
		return ris;
	}
	/**
	 *
	 * @param impegnoEsterni
	 * @return
	 */
	public FlussoImpegniEsterni saveFlussoImpegniEsterni(FlussoImpegniEsterni impegnoEsterni) {
		CpassTFlussoImpegniEsterni cpassTimpegnoEsterni = CpassMappers.FlussoImpegniEsterni.toEntity(impegnoEsterni);
		cpassTimpegnoEsterni = cpassTFlussoImpegniEsterniDao.save(cpassTimpegnoEsterni);
		return CpassMappers.FlussoImpegniEsterni.toModel(cpassTimpegnoEsterni);
	}
	/**
	 *
	 * @param subimpegno
	 * @return
	 */
	public FlussoSubimpegniEsterni saveFlussoSubImpegniEsterni(FlussoSubimpegniEsterni subimpegno) {
		CpassTFlussoSubimpegniEsterni cpassTSubimpegno = CpassMappers.FlussoSubImpegniEsterni.toEntity(subimpegno);
		cpassTSubimpegno = cpassTFlussoSubImpegniEsterniDao.save(cpassTSubimpegno);
		return CpassMappers.FlussoSubImpegniEsterni.toModel(cpassTSubimpegno);
	}
	/**
	 *
	 * @param flussoAnomalie
	 * @return
	 */
	public FlussoAnomalie saveFlussoAnomalie(FlussoAnomalie flussoAnomalie) {
		CpassTFlussoAnomalie cpassTFlussoAnomalie = CpassMappers.FlussoAnomalie.toEntity(flussoAnomalie);
		cpassTFlussoAnomalie = cpassTFlussoAnomalieDao.save(cpassTFlussoAnomalie);
		return CpassMappers.FlussoAnomalie.toModel(cpassTFlussoAnomalie);
	}
	/**
	 *
	 * @param giorniStorico
	 */
	public void delFlussoSubImpegniEsterni(int giorniStorico) {
		cpassTFlussoSubImpegniEsterniDao.deleteAll(DateUtility.addDays(new Date(), -giorniStorico));
	}
	/**
	 *
	 * @param giorniStorico
	 */
	public void delFlussoImpegniEsterni(int giorniStorico) {
		cpassTFlussoImpegniEsterniDao.deleteAll(DateUtility.addDays(new Date(), -giorniStorico));
	}
	/**
	 *
	 */
	public void resetSequenceFlussoImpegniEsterni() {
		cpassTFlussoImpegniEsterniDao.resetSequence();

	}
	/**
	 *
	 */
	public void resetSequenceFlussoSubImpegniEsterni() {
		cpassTFlussoSubImpegniEsterniDao.resetSequence();
	}
	/**
	 * 
	 * @param testataOrdineId
	 */
	public void deleteSubImpFromTestataordine(UUID testataOrdineId) {
		cpassTOrdSubimpegnoOrdineDao.deleteFromTestataordine(testataOrdineId);
	}
	/**
	 * 
	 * @param testataOrdineId
	 */
	public void deleteImpFromTestataordine(UUID testataOrdineId) {
		cpassTOrdImpegnoOrdineDao.deleteFromTestataordine(testataOrdineId);
	}

}
