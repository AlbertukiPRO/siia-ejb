package mx.uatx.siia.citas;

import javax.persistence.*;

@Entity
public class SiPaExcepciones {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Excepciones_idExcepcion_TRG")
    @SequenceGenerator(name = "Excepciones_idExcepcion_TRG ", sequenceName = "Excepciones_idExcepcion_SEQ ", allocationSize = 1)

    @Column(name = "idexcepcion", nullable = false)
    private Integer intIdExcepcion;

    @Column(name = "idfechahora")
    private Integer intidFechaHora;


    /**/

    public Integer getIdexcepcion() {
        return intIdExcepcion;
    }

    public void setIdexcepcion(Integer idexcepcion) {
        this.intIdExcepcion = idexcepcion;
    }
}
