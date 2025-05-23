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
package it.csi.cpass.cpassbe.ejb.business.be.facade;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import it.csi.cpass.cpassbe.ejb.BaseTest;

/**
 * Test class for DecodificaFacade.
 */
@RunWith(Arquillian.class)
public class DecodificaFacadeTest extends BaseTest {

	/**
	 * Arquillian entry point
	 * @return the Archive
	 */
	@Deployment
	public static EnterpriseArchive createDeployment() {
		return BaseTest.createDeployment();
	}

	//@Inject private DecodificaDad decodificaDad;
	//@Inject private ConfigurationHelper configurationHelper;

	/**
	 * Test for {@link DecodificaFacade#getModalitaAffidamento()}
	 */
	@Test
	public void getModalitaAffidamento() {
		//GetModalitaAffidamentoRequest req = new GetModalitaAffidamentoRequest();
		//GetModalitaAffidamentoService service = new GetModalitaAffidamentoService(configurationHelper, decodificaDad);
		//GetModalitaAffidamentoResponse res = service.execute(req);
		//Assert.assertNotNull(res);
		//Assert.assertTrue(res.getApiErrors().isEmpty());
		//Assert.assertFalse(res.getModalitaAffidamentos().isEmpty());
	}

}
