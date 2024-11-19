package com.spring.apol.services.impl;

import com.spring.apol.api.dto.BulletinDto;
import com.spring.apol.data.models.Bulletin;
import com.spring.apol.data.repositories.BulletinRepository;
import com.spring.apol.services.BulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BulletinSrvImpl implements BulletinService {

    private final BulletinRepository bulletinRepository;

    @Autowired
    public BulletinSrvImpl(BulletinRepository bulletinRepository) {
        this.bulletinRepository = bulletinRepository;
    }

    @Override
    public BulletinDto createBulletin(BulletinDto bulletinDto) {
        Bulletin bulletin = new Bulletin();
        // Convertir le DTO en entité
        bulletin.setNomEtablissement(bulletinDto.getNomEtablissement());
        bulletin.setAdresseEtablissement(bulletinDto.getAdresseEtablissement());
        bulletin.setEmailEtablissement(bulletinDto.getEmailEtablissement());
        bulletin.setTelephoneEtablissement(bulletinDto.getTelephoneEtablissement());
        bulletin.setAppreciation(bulletinDto.getAppreciation());
        // Ajouter d'autres conversions nécessaires
        bulletin = bulletinRepository.save(bulletin);
        return BulletinDto.fromEntity(bulletin);
    }

    @Override
    public BulletinDto getBulletinById(Long id) {
        Bulletin bulletin = bulletinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bulletin not found"));
        return BulletinDto.fromEntity(bulletin);
    }

    @Override
    public BulletinDto updateBulletin(Long id, BulletinDto bulletinDto) {
        Bulletin bulletin = bulletinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bulletin not found"));
        // Mettre à jour les champs
        bulletin.setNomEtablissement(bulletinDto.getNomEtablissement());
        bulletin.setAdresseEtablissement(bulletinDto.getAdresseEtablissement());
        bulletin.setEmailEtablissement(bulletinDto.getEmailEtablissement());
        bulletin.setTelephoneEtablissement(bulletinDto.getTelephoneEtablissement());
        bulletin.setAppreciation(bulletinDto.getAppreciation());
        // Enregistrer les changements
        bulletin = bulletinRepository.save(bulletin);
        return BulletinDto.fromEntity(bulletin);
    }

    @Override
    public boolean deleteBulletin(Long id) {
        bulletinRepository.deleteById(id);
        return false;
    }

    @Override
    public List<BulletinDto> getBulletinsByEtudiantId(Long etudiantId) {
        List<Bulletin> bulletins = bulletinRepository.findByEtudiantId(etudiantId);
        return bulletins.stream().map(BulletinDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<BulletinDto> getBulletinsByClasseId(Long classeId) {
        List<Bulletin> bulletins = bulletinRepository.findByClasseId(classeId);
        return bulletins.stream().map(BulletinDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<BulletinDto> getBulletinsByFiliereId(Long filiereId) {
        List<Bulletin> bulletins = bulletinRepository.findByFiliereId(filiereId);
        return bulletins.stream().map(BulletinDto::fromEntity).collect(Collectors.toList());
    }
}
