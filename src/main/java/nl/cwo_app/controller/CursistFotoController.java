/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.cwo_app.controller;

import java.io.IOException;
import java.util.Base64;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import nl.cwo_app.entity.CursistFoto;
import nl.cwo_app.repository.CursistFotoRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sonja
 */
@Controller
public class CursistFotoController {

    private CursistFotoRepository cursistFotoRepository;

    public CursistFotoController(CursistFotoRepository cursistFotoRepository) {
        this.cursistFotoRepository = cursistFotoRepository;
    }

    @RequestMapping(value = "/foto/{cursustFotoId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void photo(@PathVariable long cursustFotoId, HttpServletResponse response) throws Exception {
    //response.setContentType("image/jpeg");

        CursistFoto cursistFoto = cursistFotoRepository.findOne(cursustFotoId);
        if(cursistFoto == null)
            throw new Exception();
        
        byte[] data = Base64.getDecoder().decode(cursistFoto.getImage());
        
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        try (ServletOutputStream responseOutputStream = response.getOutputStream()) {
            responseOutputStream.write(data);
            responseOutputStream.flush();
        }

    }
}
