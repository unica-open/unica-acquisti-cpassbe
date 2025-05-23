package it.csi.cpass.cpassbe.ejb.mapper.rms;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.rms.CpassRRmsStatiRigaRms;
import it.csi.cpass.cpassbe.ejb.entity.rms.CpassTRmsRigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.RmsStatiRigaRms;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper
public interface StatiRigaRmsMapper extends BaseMapperInterface<RmsStatiRigaRms, CpassRRmsStatiRigaRms> {
	@Override
	@Mapping(source = "cpassTRmsRigaRms", target = "rigaRms")
	RmsStatiRigaRms toModel(CpassRRmsStatiRigaRms entity);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassRRmsStatiRigaRms toEntity(RmsStatiRigaRms model);

	@Override
	@IterableMapping(elementTargetType = RigaRms.class)
	List<RmsStatiRigaRms> toModels(Collection<CpassRRmsStatiRigaRms> entities);


	@Override
	@IterableMapping(elementTargetType = CpassTRmsRigaRms.class)
	List<CpassRRmsStatiRigaRms> toEntities(Collection<RmsStatiRigaRms> models);

}
