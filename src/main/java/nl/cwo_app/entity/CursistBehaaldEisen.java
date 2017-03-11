package nl.cwo_app.entity;
// Generated Feb 23, 2017 2:41:20 PM by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import javax.persistence.*;

/**
 * CursistBehaaldEisen generated by hbm2java
 */
@Entity
public class CursistBehaaldEisen implements java.io.Serializable {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  @JoinColumn(name="cursist_id")
  private Cursist cursist;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="cwo_eisen_id")
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  private CwoEisen cwoEisen;

  @Temporal(javax.persistence.TemporalType.DATE)
  private Date datum = new Date();

  public CursistBehaaldEisen() {
  }

  public CursistBehaaldEisen(Cursist cursist, CwoEisen cwoEisen) {
    this.cursist = cursist;
    this.cwoEisen = cwoEisen;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Cursist getCursist() {
    return this.cursist;
  }

  public void setCursist(Cursist cursist) {
    this.cursist = cursist;
  }

  public CwoEisen getCwoEisen() {
    return this.cwoEisen;
  }

  public void setCwoEisen(CwoEisen cwoEisen) {
    this.cwoEisen = cwoEisen;
  }

  public Date getDatum() {
    return this.datum;
  }

  public void setDatum(Date datum) {
    this.datum = datum;
  }

}
