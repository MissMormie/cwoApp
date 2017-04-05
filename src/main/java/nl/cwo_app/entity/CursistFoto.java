/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.cwo_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import sun.awt.image.ToolkitImage;

/**
 *
 * @author sonja
 */
@Entity
public class CursistFoto {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String thumbnail; // Base64 representation of thumbnail
    @JsonIgnore
    private String image; // Base64 representation of thumbnail
    
    @OneToOne
    @JoinColumn(name = "cursist_id")
    @JsonBackReference
    private Cursist cursist;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        createThumbnail();
    }

    public Cursist getCursist() {
        return cursist;
    }

    public void setCursist(Cursist cursist) {
        this.cursist = cursist;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    private void createThumbnail() {
        try {
            // Create Image from Base 64 string.
            byte[] imageByteArray = Base64.getDecoder().decode(image);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageByteArray);
            Image tmpImage = ImageIO.read(byteArrayInputStream);
            
            // Scale image
            ToolkitImage scaled = (ToolkitImage) tmpImage.getScaledInstance(75, 100, Image.SCALE_SMOOTH);
            scaled.getWidth(); // Not sure why, but without this scaled is null. 
            
            // Write image to byte array
            BufferedImage buffered = scaled.getBufferedImage();
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                ImageIO.write(buffered, "jpg", baos);
                baos.flush();
                imageByteArray = baos.toByteArray();
            }
            
            
            // Save byte[] to base64 string 
            thumbnail = Base64.getEncoder().encodeToString(imageByteArray);
        } catch (Exception e) {
            thumbnail = "";
        }
        
        
        
        // TODO create Thumbnail function.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
