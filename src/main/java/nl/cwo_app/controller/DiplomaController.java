/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.cwo_app.controller;

import java.util.List;
import nl.cwo_app.entity.Diploma;
import nl.cwo_app.entity.DiplomaEis;
import nl.cwo_app.repository.CursistBehaaldEisenRepository;
import nl.cwo_app.repository.CursistHeeftDiplomaRepository;
import nl.cwo_app.repository.CursistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import nl.cwo_app.repository.DiplomaRepository;
import nl.cwo_app.repository.DiplomaEisRepository;

/**
 *
 * @author Sonja
 */
@RestController
public class DiplomaController {
    // TODO bekijken welke hiervan nodig zijn.

    private final CursistRepository cursistRepository;
    private final CursistHeeftDiplomaRepository cursistHeeftDiplomaRepository;
    private final DiplomaRepository diplomaRepository;
    private final DiplomaEisRepository cwoEisenRepository;
    private final CursistBehaaldEisenRepository cursistBehaaldEisenRepository;

    @Autowired
    public DiplomaController(CursistRepository cursistRepository,
            CursistHeeftDiplomaRepository cursistHeeftDiplomaRepository,
            DiplomaRepository diplomaRepository,
            DiplomaEisRepository cwoEisenRepository,
            CursistBehaaldEisenRepository cursistBehaaldEisenRepository) {
        this.cursistRepository = cursistRepository;
        this.cursistHeeftDiplomaRepository = cursistHeeftDiplomaRepository;
        this.diplomaRepository = diplomaRepository;
        this.cwoEisenRepository = cwoEisenRepository;
        this.cursistBehaaldEisenRepository = cursistBehaaldEisenRepository;
    }

    @RequestMapping(value = "/diplomas", method = RequestMethod.GET)
    public List<Diploma> cursisten() {
        List<Diploma> diplomas = (List) diplomaRepository.findAll();
        diplomas.forEach((diploma) -> {
            // Because of lazy loading we need to access all the diploma eisen so they get loaded.
            List<DiplomaEis> deLijst = diploma.getDiplomaEisen();
            // because of a circular reference JSON would have an infinate response, hence setting diploma in diplomaEis to null.
            deLijst.forEach((diplomaEis) -> {

                diplomaEis.setDiploma(null);
            });
        });
        return diplomas;
    }
}
