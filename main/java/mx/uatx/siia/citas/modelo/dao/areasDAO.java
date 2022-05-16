package mx.uatx.siia.citas.modelo.dao;

import mx.uatx.siia.serviciosUniversitarios.dto.AreasTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@Repository
public class areasDAO implements Serializable {

    @PersistenceContext(name="SIIA")
    private EntityManager em;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public AreasTO obtenerAreas(){
        AreasTO areasTO = new AreasTO("1","Servicios Escolares",1,1,1);

        //TODO ;


        return areasTO;
    }

}
