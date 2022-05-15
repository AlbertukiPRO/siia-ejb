package mx.uatx.siia.citas.modelo.dao;

import mx.uatx.siia.serviciosUniversitarios.dto.CitasTO;
import mx.uatx.siia.serviciosUniversitarios.modelo.SiPaPermisos;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.io.Serializable;
import java.util.List;

@Repository
    /*
La anotación se utiliza para indicar que la clase proporciona es el mecanismo para la operación de almacenamiento, recuperación, búsqueda, actualización y eliminación de objetos.
*/
//* DAO: DATA ACCESS OBJECT

public class citasDAO implements Serializable {

    @PersistenceContext(name = "SIIA")
    private EntityManager em;
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public CitasTO NuevaCita(final String strUsuario) throws Exception {
        CitasTO citasTO = null; //clase donde se almacena el resultado del acceso a los datos.

        //TODO: storedProcedure [ ❗ consulta SQL ]

        //crear instancia de la clase y enviar los datos al constructor
        //TODO: citasTO  = new CitasTo(values constructor 2 o "n");

        return citasTO;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public List<CitasTO> MisCitas(final String strUsuario) throws Exception {

        List<CitasTO> citasTOList = null;

        //TODO:  Obtener citas de la DB y crear una lista de citas.

        return citasTOList;
    }

}
