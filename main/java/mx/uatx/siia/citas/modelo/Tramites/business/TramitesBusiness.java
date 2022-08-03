package mx.uatx.siia.citas.modelo.Tramites.business;

import mx.uatx.siia.citas.modelo.dao.tramitesDAO;
import mx.uatx.siia.serviciosUniversitarios.dto.ResultadoTO;
import mx.uatx.siia.serviciosUniversitarios.dto.TramitesTO;
import mx.uatx.siia.serviciosUniversitarios.enums.SeveridadMensajeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
@Configurable

public class TramitesBusiness implements Serializable {
    /**
     * Serial
     */
    private static final long serialVersionUID = -6693832594627870558L;


    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private tramitesDAO tramitesDAO;

    public ResultadoTO obtenerTramites(String url, String idArea){

        final ResultadoTO resultado = new ResultadoTO(true);

        try {
            final List<TramitesTO> tramites = tramitesDAO.getTramitesDAO(url, idArea);
            List<SelectItem> selectOneMenu = new ArrayList<>();
            if (tramites == null){
                resultado.setBlnValido(false);
            }else{
                for (TramitesTO item : tramites){
                    selectOneMenu.add(new SelectItem(item.getIntIdTramite(), item.getStrNombreTramite()));
                }
                resultado.setObjeto(selectOneMenu);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            resultado.setBlnValido(false);
        }

        return resultado;
    }


//    public List<TramitesTO> ObtenerTramites() {
//        final ResultadoTO res = new ResultadoTO(true);
//
//        List<TramitesTO> listTramites = null;
//
//       try {
//           final TramitesTO loadTramites =  tramitesDAO.tramites();
//
//           if (loadTramites == null){
//               res.agregarMensaje(SeveridadMensajeEnum.ERROR, "comun.msj.citas.tramites.obtener");
//               res.setBlnValido(false);
//           } else {
//               //TODO:
//               listTramites.add(new TramitesTO());
//           }
//       } catch (Exception e) {
//       }
//        return listTramites;
//
//    }

}
