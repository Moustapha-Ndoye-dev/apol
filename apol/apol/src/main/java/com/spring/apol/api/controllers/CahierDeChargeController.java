package com.spring.apol.api.controllers;

import com.spring.apol.api.dto.CahierDeChargeDto;
import com.spring.apol.services.CahierDeChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cahiers-de-charge")
public class CahierDeChargeController {

    private final CahierDeChargeService cahierDeChargeService;

    @Autowired
    public CahierDeChargeController(CahierDeChargeService cahierDeChargeService) {
        this.cahierDeChargeService = cahierDeChargeService;
    }

    // Créer un nouveau cahier de charge
    @PostMapping
    public ResponseEntity<Map<String, Object>> createCahierDeCharge(@RequestBody CahierDeChargeDto cahierDeChargeDto) {
        try {
            CahierDeChargeDto createdCahierDeCharge = cahierDeChargeService.saveCahierDeCharge(cahierDeChargeDto);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Cahier de charge créé avec succès");
            response.put("cahierDeCharge", createdCahierDeCharge);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la création du cahier de charge");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Récupérer un cahier de charge par ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCahierDeChargeById(@PathVariable Long id) {
        try {
            CahierDeChargeDto cahierDeChargeDto = cahierDeChargeService.getCahierDeChargeById(id);
            Map<String, Object> response = new HashMap<>();
            if (cahierDeChargeDto != null) {
                response.put("cahierDeCharge", cahierDeChargeDto);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("error", "Cahier de charge non trouvé");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la récupération du cahier de charge");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Mettre à jour un cahier de charge
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateCahierDeCharge(@PathVariable Long id,
                                                                    @RequestBody CahierDeChargeDto cahierDeChargeDto) {
        try {
            CahierDeChargeDto updatedCahierDeCharge = cahierDeChargeService.updateCahierDeCharge(id, cahierDeChargeDto);
            Map<String, Object> response = new HashMap<>();
            if (updatedCahierDeCharge != null) {
                response.put("message", "Cahier de charge mis à jour avec succès");
                response.put("cahierDeCharge", updatedCahierDeCharge);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("error", "Cahier de charge non trouvé");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la mise à jour du cahier de charge");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Supprimer un cahier de charge
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteCahierDeCharge(@PathVariable Long id) {
        try {
            boolean deleted = cahierDeChargeService.deleteCahierDeCharge(id);
            Map<String, Object> response = new HashMap<>();
            if (deleted) {
                response.put("message", "Cahier de charge supprimé avec succès");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            } else {
                response.put("error", "Cahier de charge non trouvé");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la suppression du cahier de charge");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Récupérer tous les cahiers de charge
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCahiersDeCharge() {
        try {
            List<CahierDeChargeDto> cahiersDeCharge = cahierDeChargeService.getAllCahiersDeCharge();
            Map<String, Object> response = new HashMap<>();
            response.put("cahiersDeCharge", cahiersDeCharge);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la récupération des cahiers de charge");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Récupérer les cahiers de charge par professeur
    @GetMapping("/professeur/{professeurId}")
    public ResponseEntity<Map<String, Object>> getCahiersDeChargeByProfesseur(@PathVariable Long professeurId) {
        try {
            List<CahierDeChargeDto> cahiersDeCharge = cahierDeChargeService.getCahiersDeChargeByProfesseur(professeurId);
            Map<String, Object> response = new HashMap<>();
            response.put("cahiersDeCharge", cahiersDeCharge);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la récupération des cahiers de charge pour ce professeur");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Récupérer les cahiers de charge par chef de classe
    @GetMapping("/chef-classe/{chefClasseId}")
    public ResponseEntity<Map<String, Object>> getCahiersDeChargeByChefClasse(@PathVariable Long chefClasseId) {
        try {
            List<CahierDeChargeDto> cahiersDeCharge = cahierDeChargeService.getCahiersDeChargeByChefClasse(chefClasseId);
            Map<String, Object> response = new HashMap<>();
            response.put("cahiersDeCharge", cahiersDeCharge);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la récupération des cahiers de charge pour ce chef de classe");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
