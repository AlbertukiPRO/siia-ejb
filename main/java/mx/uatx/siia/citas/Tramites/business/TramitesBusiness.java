package mx.uatx.siia.citas.Tramites.business;

import mx.uatx.siia.citas.Tramites.SiPaTramites;
import mx.uatx.siia.citas.dao.tramitesDAO;
import mx.uatx.siia.serviciosUniversitarios.dto.ResultadoTO;
import mx.uatx.siia.serviciosUniversitarios.dto.TramitesTO;
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

    /**
     * @param idArea typeOf=>Integer , Example: int idarea = 1;
     * @return ResultadoTO con List<SelectItem>.
     */
    public ResultadoTO obtenerTramites(int idArea){
        final ResultadoTO resultado = new ResultadoTO(true);
        logger.info("--- Executing Query [select TRAMITES where idarea = "+idArea+"]");
        try {
            final List<SiPaTramites> tramites = tramitesDAO.getListTramites(idArea);
            List<SelectItem> selectOneMenu = new ArrayList<>();
            if (tramites == null){
                resultado.setBlnValido(false);
            }else{
                for (SiPaTramites item : tramites){
                    selectOneMenu.add(new SelectItem(item.getIdTramites(), item.getStrNombreTramite()));
                }
                resultado.setObjeto(selectOneMenu);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            resultado.setBlnValido(false);
        }
        return resultado;
    }

    /**
     * @deprecated este metodo solo funciona en local.
     */
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

}
