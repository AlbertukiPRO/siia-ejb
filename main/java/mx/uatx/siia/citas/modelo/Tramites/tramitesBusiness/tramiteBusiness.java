package mx.uatx.siia.citas.modelo.Tramites.tramitesBusiness;

import mx.uatx.siia.citas.modelo.dao.tramitesDAO;
import mx.uatx.siia.serviciosUniversitarios.dto.ResultadoTO;
import mx.uatx.siia.serviciosUniversitarios.dto.TramitesTO;
import mx.uatx.siia.serviciosUniversitarios.enums.SeveridadMensajeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
@Configurable

public class tramiteBusiness implements Serializable {

    @Autowired
    private tramitesDAO tramitesDAO;


    public List<TramitesTO> ObtenerTramites() {
        final ResultadoTO res = new ResultadoTO(true);

        List<TramitesTO> listTramites = null;

       try {
           final TramitesTO loadTramites =  tramitesDAO.tramites();

           if (loadTramites == null){
               res.agregarMensaje(SeveridadMensajeEnum.ERROR, "comun.msj.citas.tramites.obtener");
               res.setBlnValido(false);
           } else {
               //TODO:
               listTramites.add(new TramitesTO());
           }
       } catch (Exception e) {
//           logger.error(e.getMessage());
//           res.agregarMensaje(SeveridadMensajeEnum.ERROR, "comun.msj.iniciar.sesion.credenciales.incorrectas");
//           res.setBlnValido(false);
       }
        return listTramites;

    }




}
