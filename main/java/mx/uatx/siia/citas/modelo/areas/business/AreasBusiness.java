package mx.uatx.siia.citas.modelo.areas.business;

import mx.uatx.siia.citas.modelo.MisCitas;
import mx.uatx.siia.citas.modelo.areas.SiPaAreas;
import mx.uatx.siia.citas.modelo.dao.areasDAO;
import mx.uatx.siia.citas.modelo.enums.URLs;
import mx.uatx.siia.serviciosUniversitarios.dto.AreasTO;
import mx.uatx.siia.serviciosUniversitarios.dto.ResultadoTO;
import mx.uatx.siia.serviciosUniversitarios.enums.SeveridadMensajeEnum;
import org.bouncycastle.crypto.tls.TlsRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
    private areasDAO areasDAO;

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

    public ResultadoTO obtenerFechasArea(){
        final ResultadoTO resultado = new ResultadoTO(true);
        try {
            final List<String> horarios;
        }catch (Exception e){
            logger.error(e.getMessage());
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
     * @deprecated metodo local para uso con php y msql
     * @param url del api rest para los horarios reservados en la base de datos.
     * @return List de Strings de Horarios que no se mostraran.
     */
    public ResultadoTO obtenerFechasFromDB(String url, String idArea){

        final ResultadoTO resultado = new ResultadoTO(true);

        try {
            final List<String> horarios = areasDAO.getFechasDB(url, idArea);
            if (horarios == null){
                resultado.setBlnValido(false);
            }else{
                resultado.setObjeto(horarios);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            resultado.setBlnValido(false);
        }
        return resultado;
    }

    public ResultadoTO obtenerHorariosFromDB(String url, String fecha, String idArea){
        ResultadoTO resultado = new ResultadoTO(true);

        try {
            final List<String> horarios = areasDAO.getHorarioFromDB(url, fecha, idArea);
            if (horarios == null){
                resultado.setBlnValido(false);
            }else{
                resultado.setObjeto(horarios);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            resultado.setBlnValido(false);
        }
        return resultado;
    }


    public ResultadoTO obtenerConfiguracionArea(String idArea){
        ResultadoTO resultado = new ResultadoTO(true);
        try {
            final List<SiPaAreasConfiguraciones> configuraciones = areasDAO.getConfig(idArea);
            if (configuraciones==null) resultado.setBlnValido(false);
            else resultado.setObjeto(configuraciones);
        }catch (Exception e){
            logger.error(e.getMessage());
            resultado.setBlnValido(false);
        }
        return resultado;
    }

    public ResultadoTO obtenerEventos(String idcita){
        ResultadoTO resultado = new ResultadoTO(true);
        try{
            final List<MisCitas> listEventos = areasDAO.getEventos(idcita, URLs.Eventos.getValor());
            logger.info("--- LISTA EVENTOS => "+listEventos);
            if (listEventos.isEmpty()) resultado.setBlnValido(false);
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

    public ResultadoTO desactivarDia(String day, String[] params){
        ResultadoTO resultado = new ResultadoTO(true);
        try{
            final boolean flag = areasDAO.disableDay(day, params[0], params[1], URLs.FechasReservadas.getValor());
            if (!flag) {
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


//    public List<AreasTO> ObtenerAreas(){
//        final ResultadoTO res = new ResultadoTO(true);
//
//        List<AreasTO> listAreas = null;
//
//       try {
//           final AreasTO loadAreas = areasDAO.obtenerAreas();
//
//           //TODO: Agregar listAreas.add(new AreasTO("",""))
//           listAreas.add(loadAreas);
//
//           if (loadAreas == null){
//               res.agregarMensaje(SeveridadMensajeEnum.ERROR, "comun.msj.citas.nuevacita.error");
//               res.setBlnValido(false);
//           }
//       }catch (Exception exception){
//
//       }
//        return listAreas;
//    }

}
