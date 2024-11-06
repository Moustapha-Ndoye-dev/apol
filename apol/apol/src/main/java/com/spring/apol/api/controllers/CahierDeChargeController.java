package com.spring.apol.api.controllers;

import com.spring.apol.api.dto.CahierDeChargeDto;
import com.spring.apol.services.CahierDeChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cahiers-de-charge")
public class CahierDeChargeController {

    private final CahierDeChargeService cahierDeChargeService;

    @Autowired
    public CahierDeChargeController(CahierDeChargeService cahierDeChargeService) {
        this.cahierDeChargeService = cahierDeChargeService;
    }

    @PostMapping
    public ResponseEntity<CahierDeChargeDto> createCahierDeCharge(@RequestBody CahierDeChargeDto cahierDeChargeDto) {
        CahierDeChargeDto createdCahierDeCharge = cahierDeChargeService.saveCahierDeCharge(cahierDeChargeDto);
        return new ResponseEntity<>(createdCahierDeCharge, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CahierDeChargeDto> getCahierDeChargeById(@PathVariable Long id) {
        CahierDeChargeDto cahierDeChargeDto = cahierDeChargeService.getCahierDeChargeById(id);
        return new ResponseEntity<>(cahierDeChargeDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CahierDeChargeDto> updateCahierDeCharge(@PathVariable Long id,
                                                                  @RequestBody CahierDeChargeDto cahierDeChargeDto) {
        CahierDeChargeDto updatedCahierDeCharge = cahierDeChargeService.updateCahierDeCharge(id, cahierDeChargeDto);
        return new ResponseEntity<>(updatedCahierDeCharge, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCahierDeCharge(@PathVariable Long id) {
        cahierDeChargeService.deleteCahierDeCharge(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<CahierDeChargeDto>> getAllCahiersDeCharge() {
        List<CahierDeChargeDto> cahiersDeCharge = cahierDeChargeService.getAllCahiersDeCharge();
        return new ResponseEntity<>(cahiersDeCharge, HttpStatus.OK);
    }

    @GetMapping("/professeur/{professeurId}")
    public ResponseEntity<List<CahierDeChargeDto>> getCahiersDeChargeByProfesseur(@PathVariable Long professeurId) {
        List<CahierDeChargeDto> cahiersDeCharge = cahierDeChargeService.getCahiersDeChargeByProfesseur(professeurId);
        return new ResponseEntity<>(cahiersDeCharge, HttpStatus.OK);
    }

    @GetMapping("/chef-classe/{chefClasseId}")
    public ResponseEntity<List<CahierDeChargeDto>> getCahiersDeChargeByChefClasse(@PathVariable Long chefClasseId) {
        List<CahierDeChargeDto> cahiersDeCharge = cahierDeChargeService.getCahiersDeChargeByChefClasse(chefClasseId);
        return new ResponseEntity<>(cahiersDeCharge, HttpStatus.OK);
    }
}
