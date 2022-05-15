package mx.uatx.siia.citas.modelo.dao;

import mx.uatx.siia.serviciosUniversitarios.dto.TramitesTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Repository

public class tramitesDAO implements Serializable {

    @PersistenceContext(name = "SIIA")
    private EntityManager em;

    //@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public TramitesTO tramites() throws Exception {
        TramitesTO tramitesTO = new TramitesTO(1,1,"Baja Temporal","el estudiante solicita una baja","texto requisitos");

        return tramitesTO;
    }

}
