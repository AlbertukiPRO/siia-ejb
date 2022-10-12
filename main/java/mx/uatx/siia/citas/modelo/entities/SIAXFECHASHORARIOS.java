package mx.uatx.siia.citas.modelo.entities;

import javax.persistence.*;

@Entity
@Table(name = "SIIUAT.SIAXFECHASHORARIOS")
public class SIAXFECHASHORARIOS {
    @Id
    @SequenceGenerator(name = "IDFECHAHORA", sequenceName = "IDFECHAHORA", allocationSize = 1)
    @Column(name = "IDFECHAHORA")
    private Integer id;

    @Column(name = "IDAREACAMPUS")
    private Integer idarea;
    @Column(name = "IDEXCEPCIONES")
    private Integer idexcepcion;
    @Column(name = "USERAUDIT")
    private Integer useraudit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdarea() {
        return idarea;
    }

    public void setIdarea(Integer idarea) {
        this.idarea = idarea;
    }

    public Integer getIdexcepcion() {
        return idexcepcion;
    }

    public void setIdexcepcion(Integer idexcepcion) {
        this.idexcepcion = idexcepcion;
    }

    public Integer getUseraudit() {
        return useraudit;
    }

    public void setUseraudit(Integer useraudit) {
        this.useraudit = useraudit;
    }

    @Override
    public String toString() {
        return "SIAXFECHASHORARIOS{" +
                "id=" + id +
                ", idarea=" + idarea +
                ", idexcepcion=" + idexcepcion +
                ", useraudit=" + useraudit +
                '}';
    }
}
