package nl.cwo_app.entity;
// Generated Feb 23, 2017 2:41:20 PM by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
/**
 * CwoEisen generated by hbm2java
 */
@Entity
@Table(name = "cwo_eisen")
public class CwoEisen implements java.io.Serializable {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long id;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cwo_discipline_id")
  @JsonIgnore
  private Diploma diploma;
  
  private String titel;
  
  private String omschrijving;
/*  
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cwoEisen")
  @JsonIgnore
  private Set<CursistBehaaldEisen> cursistBehaaldEisen = new HashSet(0);
*/
  
  
  public CwoEisen() {
  }

  public CwoEisen(Diploma cwoDiscipline, String titel) {
    this.diploma = cwoDiscipline;
    this.titel = titel;
  }

  public CwoEisen(Diploma diploma, String titel, String omschrijving) {
    this.diploma = diploma;
    this.titel = titel;
    this.omschrijving = omschrijving;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Diploma getDiploma() {
    return this.diploma;
  }

  public void setDiploma(Diploma diploma) {
    this.diploma = diploma;
  }

  public String getTitel() {
    return this.titel;
  }

  public void setTitel(String titel) {
    this.titel = titel;
  }

  public String getOmschrijving() {
    return this.omschrijving;
  }

  public void setOmschrijving(String omschrijving) {
    this.omschrijving = omschrijving;
  }

}