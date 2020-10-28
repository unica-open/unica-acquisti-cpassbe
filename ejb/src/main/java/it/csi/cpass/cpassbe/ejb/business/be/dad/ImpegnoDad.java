/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.dad;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTImpegnoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTSubimpegnoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdImpegnoOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdSubimpegnoOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassVOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdImpegnoEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdSubimpegnoEvasioneDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.CpassTImpegno;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSubimpegno;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTFornitore;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdImpegnoOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdRigaOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSubimpegnoOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassVOrdine;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
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
	private CpassTOrdImpegnoEvasioneDao cpassTOrdImpegnoEvasioneDao;
	@Inject
	private CpassTOrdSubimpegnoEvasioneDao cpassTOrdSubimpegnoEvasioneDao;
	@Inject
	private CpassTImpegnoDao cpassTImpegnoDao;
	@Inject
	private CpassVOrdineDao cpassVOrdineDao;
	@Inject
	private CpassTSubimpegnoDao cpassTSubimpegnoDao;

	public Impegno update(Impegno impegno) {
		CpassTImpegno cpassTImpegno = CpassMappers.IMPEGNO.toEntity(impegno);
		Impegno result = CpassMappers.IMPEGNO.toModel(cpassTImpegnoDao.update(cpassTImpegno));
		return result;
	}

	public Subimpegno update(Subimpegno subimpegno) {
		CpassTSubimpegno cpassTSubimpegno = CpassMappers.SUBIMPEGNO.toEntity(subimpegno);
		Subimpegno result = CpassMappers.SUBIMPEGNO.toModel(cpassTSubimpegnoDao.update(cpassTSubimpegno));
		return result;
	}
	
	public List<CpassTOrdImpegnoOrdine> getImpegniCollegati(UUID testataOrdineId) {
		return cpassTOrdImpegnoOrdineDao.getImpegniCollegati(testataOrdineId);
	}

	public BigDecimal calcolaOrdinato(UUID impegnoId, UUID testataOrdineId) {
		return cpassTOrdImpegnoOrdineDao.calcolaOrdinato(impegnoId, testataOrdineId);
	}

	public BigDecimal calcolaSubimpegnoOrdinato(UUID subimpegnoId) {
		return cpassTOrdSubimpegnoOrdineDao.calcolaSubimpegnoOrdinato(subimpegnoId);
	}

	public Impegno getImpegnoByChiaveLogica(Integer annoEsercizio, Integer anno, Integer numero, UUID enteId) {
		CpassTImpegno cpassTImpegno = cpassTImpegnoDao.getImpegnoByChiaveLogica(annoEsercizio, anno, numero, enteId);
		Impegno impegno = CpassMappers.IMPEGNO.toModel(cpassTImpegno);
		return impegno;
	}

	public Subimpegno getById(UUID idSubimpegno) {
		CpassTSubimpegno cpassTSubimpegno = cpassTSubimpegnoDao.findOne(idSubimpegno).get();
		return cpassTSubimpegno != null ? CpassMappers.SUBIMPEGNO.toModel(cpassTSubimpegno) : null;
	}

	public Subimpegno getSubimpegnoByChiaveLogica(Integer annoEsercizio, Integer anno, Integer numero, UUID enteId, Integer subimpegnoAnno,
			Integer subimpegnoNumero) {
		CpassTSubimpegno cpassTSubimpegno = cpassTSubimpegnoDao.getSubimpegnoByChiaveLogica(annoEsercizio, anno, numero, enteId, subimpegnoAnno, subimpegnoNumero);
		Subimpegno subimpegno = CpassMappers.SUBIMPEGNO.toModel(cpassTSubimpegno);
		return subimpegno;
	}

	public void insertImpegni(RigaOrdine rigaOrdine, List<Impegno> listImpegno, CpassTFornitore cpassTFornitore, CpassTEnte cpassTEnte) {
		CpassTOrdRigaOrdine cpassTOrdRigaOrdine = CpassMappers.RIGA_ORDINE.toEntity(rigaOrdine);

		// cancello le vecchie relazioni
		List<CpassTOrdImpegnoOrdine> impegnoOrdines = cpassTOrdImpegnoOrdineDao.getImpegni(cpassTOrdRigaOrdine.getRigaOrdineId());
		if (impegnoOrdines != null) {
			for (CpassTOrdImpegnoOrdine cpassTOrdImpegnoOrdine : impegnoOrdines) {

				List<CpassTOrdSubimpegnoOrdine> subimpegnoOrdines = cpassTOrdSubimpegnoOrdineDao.getSubimpegni(cpassTOrdImpegnoOrdine.getId());
				if (subimpegnoOrdines != null) {
					for (CpassTOrdSubimpegnoOrdine cpassTOrdSubimpegnoOrdine : subimpegnoOrdines) {
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
			for (Impegno impegno : listImpegno) {
				impegnoProgressivo++;

				CpassTImpegno cpassTImpegno = CpassMappers.IMPEGNO.toEntity(impegno);
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

				CpassTImpegno cpassTImpegnoPresente = cpassTImpegnoDao.getImpegnoByChiaveLogica(cpassTImpegno.getImpegnoAnnoEsercizio(),
						cpassTImpegno.getImpegnoAnno(), cpassTImpegno.getImpegnoNumero(), cpassTImpegno.getCpassTEnte().getId());
				if (cpassTImpegnoPresente == null) {
					cpassTImpegnoPresente = cpassTImpegnoDao.insert(cpassTImpegno);
				}

				// associato
				CpassTOrdImpegnoOrdine cpassTOrdImpegnoOrdine = new CpassTOrdImpegnoOrdine();
				cpassTOrdImpegnoOrdine.setCpassTImpegno(cpassTImpegnoPresente);
				cpassTOrdImpegnoOrdine.setCpassTOrdRigaOrdine(cpassTOrdRigaOrdine);

				cpassTOrdImpegnoOrdine.setImpegnoAnno(cpassTImpegnoPresente.getImpegnoAnno());
				cpassTOrdImpegnoOrdine.setImpegnoAnnoEsercizio(cpassTImpegnoPresente.getImpegnoAnnoEsercizio());
				cpassTOrdImpegnoOrdine.setImpegnoNumero(cpassTImpegnoPresente.getImpegnoNumero());

				cpassTOrdImpegnoOrdine.setImpegnoProgressivo(impegnoProgressivo);
				cpassTOrdImpegnoOrdine.setImporto(impegno.getImporto());

				cpassTOrdImpegnoOrdineDao.insert(cpassTOrdImpegnoOrdine);

				// subimpegno
				if (impegno.getSubimpegni() != null && impegno.getSubimpegni().size() > 0) {
					for (Subimpegno subimpegno : impegno.getSubimpegni()) {
						CpassTSubimpegno cpassTSubimpegno = CpassMappers.SUBIMPEGNO.toEntity(subimpegno);

						cpassTSubimpegno.setCpassTEnte(cpassTEnte);

						if (impegno.getFornitore() != null && impegno.getFornitore().getCodice() != null) {
							cpassTSubimpegno.setCpassTFornitore(cpassTFornitore);
						} else {
							cpassTSubimpegno.setCpassTFornitore(null);
						}

						cpassTSubimpegno.setCpassTImpegno(cpassTImpegnoPresente);

						cpassTSubimpegno.setImpegnoAnno(impegno.getAnno());
						cpassTSubimpegno.setImpegnoAnnoEsercizio(impegno.getAnnoEsercizio());
						cpassTSubimpegno.setImpegnoNumero(impegno.getNumero());

						CpassTSubimpegno cpassTSubimpegnoPresente = cpassTSubimpegnoDao.getSubimpegnoByChiaveLogica(cpassTSubimpegno.getImpegnoAnnoEsercizio(),
								cpassTSubimpegno.getImpegnoAnno(), cpassTSubimpegno.getImpegnoNumero(), cpassTSubimpegno.getCpassTEnte().getId(),
								cpassTSubimpegno.getSubimpegnoAnno(), cpassTSubimpegno.getSubimpegnoNumero());
						if (cpassTSubimpegnoPresente == null) {
							cpassTSubimpegnoPresente = cpassTSubimpegnoDao.insert(cpassTSubimpegno);
						}

						// associato
						CpassTOrdSubimpegnoOrdine cpassTOrdSubimpegnoOrdine = new CpassTOrdSubimpegnoOrdine();
						cpassTOrdSubimpegnoOrdine.setCpassTOrdImpegnoOrdine(cpassTOrdImpegnoOrdine);
						cpassTOrdSubimpegnoOrdine.setCpassTSubimpegno(cpassTSubimpegnoPresente);

						cpassTOrdSubimpegnoOrdine.setImpegnoAnno(cpassTImpegnoPresente.getImpegnoAnno());
						cpassTOrdSubimpegnoOrdine.setImpegnoAnnoEsercizio(cpassTImpegnoPresente.getImpegnoAnnoEsercizio());
						cpassTOrdSubimpegnoOrdine.setImpegnoNumero(cpassTImpegnoPresente.getImpegnoNumero());

						cpassTOrdSubimpegnoOrdine.setSubimpegnoAnno(cpassTSubimpegnoPresente.getSubimpegnoAnno());
						cpassTOrdSubimpegnoOrdine.setSubimpegnoImporto(cpassTSubimpegnoPresente.getImportoAttuale());
						cpassTOrdSubimpegnoOrdine.setSubimpegnoNumero(cpassTSubimpegnoPresente.getSubimpegnoNumero());

						cpassTOrdSubimpegnoOrdine.setSubimpegnoImporto(subimpegno.getImporto());

						cpassTOrdSubimpegnoOrdineDao.insert(cpassTOrdSubimpegnoOrdine);
					}
				}
			}
		}
	}

	public List<ImpegnoOrdine> getImpegnoOrdineByRiga(UUID rigaOrdineId) {
		List<CpassTOrdImpegnoOrdine> cpassTOrdImpegnoOrdines = cpassTOrdImpegnoOrdineDao.getImpegni(rigaOrdineId);
		List<ImpegnoOrdine> impegnoOrdines = CpassMappers.IMPEGNO_ORDINE.toModels(cpassTOrdImpegnoOrdines);
		return impegnoOrdines;
	}
	
	public List<ImpegnoOrdine> getImpegnoOrdineByRigaNonPresentiEvasione(UUID rigaOrdineId, UUID rigaEvasioneId) {
		List<CpassTOrdImpegnoOrdine> cpassTOrdImpegnoOrdines = cpassTOrdImpegnoOrdineDao.getImpegniNonPresentiEvasione(rigaOrdineId, rigaEvasioneId);
		List<ImpegnoOrdine> impegnoOrdines = CpassMappers.IMPEGNO_ORDINE.toModels(cpassTOrdImpegnoOrdines);
		return impegnoOrdines;
	}

	public List<SubimpegnoOrdine> getSubimpegnoOrdineByImpegnoOrdineId(UUID ImpegnoOrdineId) {
		List<CpassTOrdSubimpegnoOrdine> cpassTOrdSubimpegnoOrdines = cpassTOrdSubimpegnoOrdineDao.getSubimpegni(ImpegnoOrdineId);
		List<SubimpegnoOrdine> subimpegnoOrdines = CpassMappers.SUBIMPEGNO_ORDINE.toModels(cpassTOrdSubimpegnoOrdines);
		return subimpegnoOrdines;
	}

	public List<Impegno> getImpegniByRiga(UUID idRigaOrdine) {
		List<Impegno> impegnos = new ArrayList<Impegno>();

		// impegni
		List<CpassTOrdImpegnoOrdine> impegnoOrdines = cpassTOrdImpegnoOrdineDao.getImpegni(idRigaOrdine);
		if (impegnoOrdines != null) {
			for (CpassTOrdImpegnoOrdine cpassTOrdImpegnoOrdine : impegnoOrdines) {
				Impegno impegno = CpassMappers.IMPEGNO.toModel(cpassTOrdImpegnoOrdine.getCpassTImpegno());
				impegno.setImporto(cpassTOrdImpegnoOrdine.getImporto());
				impegnos.add(impegno);

				// subimpegni
				List<CpassTOrdSubimpegnoOrdine> subimpegnoOrdines = cpassTOrdSubimpegnoOrdineDao.getSubimpegni(cpassTOrdImpegnoOrdine.getImpegnoOrdineId());
				if (subimpegnoOrdines != null) {
					for (CpassTOrdSubimpegnoOrdine cpassTOrdSubimpegnoOrdine : subimpegnoOrdines) {
						Subimpegno subimpegno = CpassMappers.SUBIMPEGNO.toModel(cpassTOrdSubimpegnoOrdine.getCpassTSubimpegno());
						subimpegno.setImporto(cpassTOrdSubimpegnoOrdine.getSubimpegnoImporto());
						impegno.getSubimpegni().add(subimpegno);
					}
				}
			}
		}

		return impegnos;
	}

	public void annullaImpegniByRiga(UUID idRiga) {
		List<CpassTOrdImpegnoOrdine> impegniOrdine = cpassTOrdImpegnoOrdineDao.getImpegni(idRiga);
		if (impegniOrdine != null) {
			for (CpassTOrdImpegnoOrdine impegnoOrdine : impegniOrdine) {
				impegnoOrdine.setImporto(BigDecimal.ZERO);
				cpassTOrdImpegnoOrdineDao.update(impegnoOrdine);

				for (CpassTOrdSubimpegnoOrdine subimpegnoOrdine : impegnoOrdine.getCpassTOrdSubimpegnoOrdines()) {
					subimpegnoOrdine.setSubimpegnoImporto(BigDecimal.ZERO);
					cpassTOrdSubimpegnoOrdineDao.update(subimpegnoOrdine);
				}
			}
		}
	}

	public void deleteImpegniByRiga(UUID idRiga) {

		List<CpassTOrdImpegnoOrdine> impegni = cpassTOrdImpegnoOrdineDao.getImpegni(idRiga);

		if (impegni != null) {
			for (CpassTOrdImpegnoOrdine impegno : impegni) {

				if (impegno.getCpassTOrdSubimpegnoOrdines() != null) {
					for (CpassTOrdSubimpegnoOrdine subImpegno : impegno.getCpassTOrdSubimpegnoOrdines()) {
						cpassTOrdSubimpegnoOrdineDao.delete(subImpegno.getSubimpegnoOrdineId());
					}
				}

				cpassTOrdImpegnoOrdineDao.deleteByRiga(idRiga);
			}
		}

	}

	public void deleteSubimpegniByImpegnoOrdine(UUID idImpegnoOrdine) {

		List<CpassTOrdSubimpegnoOrdine> subimpegni = cpassTOrdSubimpegnoOrdineDao.getSubimpegni(idImpegnoOrdine);

		for (CpassTOrdSubimpegnoOrdine subimpegno : subimpegni) {
			cpassTOrdSubimpegnoOrdineDao.delete(subimpegno.getSubimpegnoOrdineId());
		}
	}


	public List<VOrdine> getListImpegniRiepilogoByOrdineId(UUID idTestataOrdine) {
		List<CpassVOrdine> lista = cpassVOrdineDao.getListImpegniRiepilogoByOrdineId(idTestataOrdine);
		List<VOrdine> ris = CpassMappers.VORDINE.toModels(lista);		
		return ris;
	}

	
	
	public Impegno saveImpegno(Impegno impegno) {
		CpassTImpegno cpassTImpegno = CpassMappers.IMPEGNO.toEntity(impegno);
		// controllo lunghezza massima
		if (cpassTImpegno.getImpegnoDescrizione() != null && cpassTImpegno.getImpegnoDescrizione().length() > 150) {
			cpassTImpegno.setImpegnoDescrizione(cpassTImpegno.getImpegnoDescrizione().substring(0, 150));
		}
		cpassTImpegno = cpassTImpegnoDao.insert(cpassTImpegno);
		return CpassMappers.IMPEGNO.toModel(cpassTImpegno);
	}

	public Subimpegno saveSubimpegno(Subimpegno subimpegno) {
		CpassTSubimpegno cpassTSubimpegno = CpassMappers.SUBIMPEGNO.toEntity(subimpegno);
		cpassTSubimpegno = cpassTSubimpegnoDao.insert(cpassTSubimpegno);
		return CpassMappers.SUBIMPEGNO.toModel(cpassTSubimpegno);
	}

}
