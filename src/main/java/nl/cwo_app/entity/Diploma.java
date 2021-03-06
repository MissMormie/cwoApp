package nl.cwo_app.entity;
// Generated Feb 23, 2017 2:41:20 PM by Hibernate Tools 4.3.1

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Diploma generated by hbm2java
 */
@Entity
public class Diploma implements java.io.Serializable {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long id;
  
  private String titel;
  
  private int nivo;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "diploma") 
  private List<DiplomaEis> diplomaEisen = new ArrayList();
  


  public Diploma() {
  }

  public Diploma(String titel) {
    this.titel = titel;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitel() {
    return this.titel;
  }

  public void setTitel(String titel) {
    this.titel = titel;
  }

    public int getNivo() {
        return nivo;
    }

    public void setNivo(int nivo) {
        this.nivo = nivo;
    }

    public List<DiplomaEis> getDiplomaEisen() {
        return diplomaEisen;
    }

    public void setDiplomaEisen(List<DiplomaEis> diplomaEisen) {
        this.diplomaEisen = diplomaEisen;
    }


}
