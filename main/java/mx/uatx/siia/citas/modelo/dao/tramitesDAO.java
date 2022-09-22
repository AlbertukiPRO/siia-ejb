package mx.uatx.siia.citas.modelo.dao;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import mx.uatx.siia.citas.modelo.Tramites.SiPaTramites;
import mx.uatx.siia.serviciosUniversitarios.dto.TramitesTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import static mx.uatx.siia.citas.modelo.citasBusiness.MethodsGenerics.readUrl;

@Repository

public class tramitesDAO implements Serializable {

    @PersistenceContext(name = "SIIA")
    private EntityManager em;


    @Transactional
    public List<SiPaTramites> getListTramites(int idArea){
        String query = "SELECT * FROM SIIUAT.SICTTRAMITES WHERE IDAREACAMPUS ="+idArea;
        return (List<SiPaTramites>) em.createNativeQuery(query, SiPaTramites.class).getResultList();
    }


    /**
     *
     * @param url String, de la dirección web del servicio REST.
     * @param idArea String, con el ID del área seleccionada por el usuario
     * @return List => TramitesTO.
     */
    public List<TramitesTO> getTramitesDAO(String url, String idArea){
        List<TramitesTO> listTramites;
        /**
         * @param idTramite variable encode url obligatoria para el servicio.
         */
        String strJson = readUrl(url+"?idTramite="+idArea);

        System.out.println("----  Response from [ " +url+"?idTramite="+idArea+" ]");

        Type listType = new TypeToken<List<TramitesTO>>(){}.getType();

        listTramites = new Gson().fromJson(strJson,listType);

        return listTramites;
    }

}
