package nl.cwo_app.entity;
// Generated Feb 23, 2017 2:41:20 PM by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * CursistBehaaldEisen generated by hbm2java
 */
@Entity
public class CursistBehaaldEis implements java.io.Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @JsonIgnore
    @NotNull
    private Long cursistId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diploma_eis_id")
    private DiplomaEis diplomaEis;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datum = new Date();

    public CursistBehaaldEis() {
    }

    public CursistBehaaldEis(Long cursistId, DiplomaEis diplomaEis) {
        this.cursistId = cursistId;
        this.diplomaEis = diplomaEis;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCursistId() {
        return cursistId;
    }

    public void setCursistId(Long cursistId) {
        this.cursistId = cursistId;
    }

    public Date getDatum() {
        return this.datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public DiplomaEis getDiplomaEis() {
        return diplomaEis;
    }

    public void setDiplomaEis(DiplomaEis diplomaEis) {
        this.diplomaEis = diplomaEis;
    }

    public boolean checkTheorieEis() {
        return diplomaEis.isTheorie();
    }

}