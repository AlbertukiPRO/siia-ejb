package mx.uatx.siia.citas.areas.business;

import mx.uatx.siia.citas.MisCitas;
import mx.uatx.siia.citas.SIMSCITAS;
import mx.uatx.siia.citas.areas.SiPaAreas;
import mx.uatx.siia.citas.enums.URLs;
import mx.uatx.siia.serviciosUniversitarios.dto.ResultadoTO;
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
            if (configuraciones==null) resultado.setBlnValido(false);
            else resultado.setObjeto(configuraciones);
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
            if (cita==null) {
                resultado.setBlnValido(false);
            } else resultado.setObjeto(cita);
        }catch (Exception e){
            resultado.setBlnValido(false);
            logger.error(e.getMessage()+"\n"+e.getCause());
        }
        return resultado;
    }

    public ResultadoTO guardarConfiguracionesArea(long longIdArea, String[] params){

        ResultadoTO resultado = new ResultadoTO(true);
        try {
            final boolean flag = areasDAO.guardarConfiguracion(longIdArea, params);
        }catch (Exception e){
            logger.error(e.getMessage()+"\n"+e.getCause());
            resultado.setBlnValido(false);
        }
        return  resultado;
    }

    /*----------------------------------------------------------------------------------------*/

    /*
     * @deprecated metodo local para uso con php y msql
     * @param url del api rest para los horarios reservados en la base de datos.
     * @return List de Strings de Horarios que no se mostraran.
     */
//    public ResultadoTO obtenerFechasFromDB(String url, String idArea){
//
//        final ResultadoTO resultado = new ResultadoTO(true);
//
//        try {
//            final List<String> horarios = areasDAO.getFechasDB(url, idArea);
//            if (horarios == null){
//                resultado.setBlnValido(false);
//            }else{
//                resultado.setObjeto(horarios);
//            }
//        }catch (Exception e){
//            logger.error(e.getMessage());
//            resultado.setBlnValido(false);
//        }
//        return resultado;
//    }


    public ResultadoTO obtenerEventosMariaDB(String idarea){
        ResultadoTO resultado = new ResultadoTO(true);
        try{
            final List<MisCitas> listEventos = areasDAO.getEventos(idarea, URLs.Eventos.getValor());
            logger.info("--- LISTA EVENTOS => "+listEventos);
            if (listEventos == null) resultado.setBlnValido(false);
            else resultado.setObjeto(listEventos);
        }catch (Exception e){
            logger.info(e.getMessage());
            resultado.setBlnValido(false);
        }
        return resultado;
    }

    public ResultadoTO obtenerCita(int type, String url, String[] params){
        ResultadoTO resultado = new ResultadoTO(true);
        try {

            switch (type){
                case 1:
                    List<MisCitas> list = null;
                    list = areasDAO.getCitas(url);
                    if (list.isEmpty()) resultado.setBlnValido(false);
                    else resultado.setObjeto(list);
                    break;
                case 2:
                    MisCitas cita = areasDAO.getCita(params[0].toString(), url+"?idarea="+params[0]+"&idcita="+params[2]+"&iduser="+params[1]);
                    if (cita == null) resultado.setBlnValido(false);
                    else resultado.setObjeto(cita);
                    break;
                default:
                    List<MisCitas> list1 = new ArrayList<>();
                    resultado.setBlnValido(false);
                    resultado.setObjeto(list1);
                    break;
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return resultado;
    }

    public ResultadoTO desactivarDia(String[] params){
        ResultadoTO resultado = new ResultadoTO(true);
        try{
            final int flag = areasDAO.desactivarDia(params[0], params[1], params[2]);
            if (flag==0) {
                resultado.setBlnValido(false);
                resultado.setObjeto(false);
                logger.info("Was day delete ? =>"+ false);
            } else {
                resultado.setObjeto(flag);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return resultado;
    }

}
