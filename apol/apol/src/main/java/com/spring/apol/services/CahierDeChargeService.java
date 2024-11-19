package com.spring.apol.services;

import com.spring.apol.api.dto.CahierDeChargeDto;

import java.util.List;

public interface CahierDeChargeService {

    CahierDeChargeDto saveCahierDeCharge(CahierDeChargeDto cahierDeChargeDto);

    CahierDeChargeDto updateCahierDeCharge(Long id, CahierDeChargeDto cahierDeChargeDto);

    boolean deleteCahierDeCharge(Long id);

    CahierDeChargeDto getCahierDeChargeById(Long id);

    List<CahierDeChargeDto> getAllCahiersDeCharge();

    List<CahierDeChargeDto> getCahiersDeChargeByProfesseur(Long professeurId);

    List<CahierDeChargeDto> getCahiersDeChargeByChefClasse(Long chefClasseId);
}
