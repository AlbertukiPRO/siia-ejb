package mx.uatx.siia.citas.areas.business;

import mx.uatx.siia.citas.SIMSCITAS;
import mx.uatx.siia.citas.areas.SiPaAreas;
import mx.uatx.siia.serviciosUniversitarios.dto.ResultadoTO;
import mx.uatx.siia.serviciosUniversitarios.enums.SeveridadMensajeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Alberto Noche Rosas
 */

@Service
@Configurable
public class AreasBusiness implements Serializable {

    /**
     * @serial
     */
    private static final long serialVersionUID = -1695304585195206108L;
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private mx.uatx.siia.citas.dao.areasDAO areasDAO;

    public ResultadoTO obtenerAreas(){

        final ResultadoTO resultado = new ResultadoTO(true);

        try{
            final List<SiPaAreas> areasList = areasDAO.obtenerAreas();
            List<SelectItem> selectItems = new ArrayList<>();

            for (SiPaAreas item : areasList){
                selectItems.add(new SelectItem(item.getIdarea(), item.getStrNombreArea()));
            }
            resultado.agregarMensaje(SeveridadMensajeEnum.INFO, "comun.msg.citas.areas.found");
            resultado.setObjeto(selectItems);
        }catch (Exception e){
            logger.error(e.getMessage());
            resultado.setBlnValido(false);
        }
        return resultado;
    }

    public ResultadoTO obtenerDiasInhabiles(String strIdArea){
        final ResultadoTO resultado = new ResultadoTO(true);

        try {
            List<String> horarios = areasDAO.getFechas(strIdArea);
            if (horarios == null){
                resultado.setBlnValido(false);
                resultado.setObjeto(Collections.singletonList("Sin datos"));
            }else resultado.setObjeto(horarios);
        }catch (Exception e){
            logger.error(e.getMessage()+e.getCause());
            resultado.setBlnValido(false);
        }
        return resultado;
    }
    public ResultadoTO obtenerHorarios(String[] params){
        ResultadoTO resultado = new ResultadoTO(true);
        try {
            final List<String> horarios = areasDAO.getHorarios(params);
            if (horarios == null){
                resultado.setBlnValido(false);
                resultado.setObjeto(Collections.singletonList("Sin horarios"));
            }else resultado.setObjeto(horarios);
        }catch (Exception e){
            logger.error(e.getMessage()+"\n"+e.getCause());
            resultado.setBlnValido(false);
        }
        return resultado;
    }

    /**
     * @param idArea string con el id del area
     * @return ResultadoTO con la lista de la configuración
     */
    public ResultadoTO obtenerConfiguracionArea(String idArea){
        ResultadoTO resultado = new ResultadoTO(true);
        try {
            final SiPaAreasConfiguraciones configuraciones = areasDAO.getConfig(Integer.parseInt(idArea));
            if (configuraciones==null) {
                resultado.setBlnValido(false);
                resultado.agregarMensaje(SeveridadMensajeEnum.ALERTA, "comun.msg.citas.areas.config.none");
            } else {
                resultado.setObjeto(configuraciones);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            resultado.setBlnValido(false);
        }
        return resultado;
    }


    public ResultadoTO obtenerEventos(String idarea){
        ResultadoTO resultado = new ResultadoTO(true);
        try{
            final List<SIMSCITAS> listEventos =areasDAO.obtenerCitasToCalendar(idarea);
            logger.info("--- LISTA EVENTOS => "+listEventos);
            if (listEventos == null) resultado.setBlnValido(false);
            else resultado.setObjeto(listEventos);
        }catch (Exception e){
            logger.info(e.getMessage());
            resultado.setBlnValido(false);
        }
        return resultado;
    }

    public ResultadoTO obtenerCita(Long longIdUser, Long longIdCita){
        ResultadoTO resultado = new ResultadoTO(true);
        try {
            final SIMSCITAS cita = areasDAO.obtenerCita(longIdUser, longIdCita);
            logger.info(cita.toString());
            resultado.setObjeto(cita);
        }catch (Exception e){
            resultado.setBlnValido(false);
            logger.error(e.getMessage()+"\n"+e.getCause());
        }
        return resultado;
    }

    public ResultadoTO guardarConfiguracionesArea(long longIdArea, String[] params, int type){

        ResultadoTO resultado = new ResultadoTO(true);
        try {
            final boolean flag = areasDAO.guardarConfiguracion(longIdArea, params, type);
            resultado.setBlnValido(flag);
        }catch (Exception e){
            logger.error(e.getMessage()+"\n"+e.getCause());
            resultado.setBlnValido(false);
        }
        return  resultado;
    }

    public ResultadoTO desactivarDia(String[] params){
        ResultadoTO resultado = new ResultadoTO(true);
        try{
            final boolean flagExcepcion = areasDAO.desactivarDiaToExcepciones(params[1], params[2]);
            Integer getIDEX = areasDAO.getIdExcepcion();
            final boolean flagFechas = areasDAO.insertFechas(params[0],params[2], getIDEX);
            if (flagExcepcion && flagFechas) {
                resultado.setObjeto(true);
            } else {
                resultado.setBlnValido(false);
                resultado.setObjeto(false);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            resultado.setObjeto(false);
        }
        return resultado;
    }

    public ResultadoTO guardarTramite(long idArea, String strName, String strDescrip, String strRequi){
        ResultadoTO resultado = new ResultadoTO(true);

        try {
            final boolean flag = areasDAO.guardarTramite(idArea, strName, strDescrip, strRequi);
            if (!flag)
                resultado.setBlnValido(false);
            else
                resultado.setObjeto(true);
        }catch (Exception e){
            logger.error(e.getMessage()+"\n"+e.getCause());
            resultado.setBlnValidoExtra(false);
        }

        return resultado;
    }

    public ResultadoTO deleteTramite(long idTramite){
        ResultadoTO resultado = new ResultadoTO(true);

        try {
            final boolean flag = areasDAO.borrarTramite(idTramite);
            if (flag)
                resultado.setObjeto(true);
            else resultado.setBlnValido(false);
        }catch (Exception e){
            logger.error(e.getMessage()+"\n"+e.getCause());
            resultado.setBlnValido(false);
        }

        return  resultado;
    }
}
