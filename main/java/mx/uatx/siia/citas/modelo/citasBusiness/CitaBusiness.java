package mx.uatx.siia.citas.modelo.citasBusiness;


import mx.uatx.siia.citas.modelo.MisCitas;
import mx.uatx.siia.citas.modelo.SIMSCITAS;
import mx.uatx.siia.citas.modelo.dao.citasDAO;
import mx.uatx.siia.citas.modelo.enums.URLs;
import mx.uatx.siia.serviciosUniversitarios.dto.ResultadoTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import javax.persistence.SequenceGenerator;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
@Configurable //para la inyección de dependencias no administradas por spring.
public class CitaBusiness implements Serializable {

    @Autowired
    private citasDAO citasDAO;

    private static final long serialVersionUID = 7209423068137481883L;
    protected final Logger logger = LoggerFactory.getLogger(getClass());


    public ResultadoTO guardarCita(Map<String, Object> formData){

        final ResultadoTO resultado = new ResultadoTO(true);
        try {

            SIMSCITAS citas = new SIMSCITAS();
            citas.setIntIdAlumno((Long) formData.get("idhistorical"));
            citas.setIntTramite(Integer.parseInt(formData.get("idtramite").toString()));
            citas.setIntIdArea(Integer.parseInt(formData.get("idarea").toString()));
            citas.setStrDescripcionCita((String) formData.get("descripcion"));
            citas.setStrEstatus("Agendada");
            citas.setStrRetroalimentacion("vacio");
            citas.setStrFechaReservada((String) formData.get("fecha"));
            citas.setStrHoraReservada((String) formData.get("hora"));
            citas.setStrUSERAUDIT("20181837");

            boolean flag = citasDAO.nuevaCita(citas);
            if (flag){
                resultado.setObjeto(flag);
                resultado.setBlnValido(true);
            }else
                resultado.setBlnValido(false);
        } catch (Exception e){
            logger.info(e.getMessage()+"\n"+e.getCause());
        }
        return resultado;
    }

    public ResultadoTO validarTramite(Long longIdUser, Integer intIdTramite){
        final ResultadoTO resultado = new ResultadoTO(true);

        try {
            final boolean flag1 = citasDAO.validarTramite(longIdUser, intIdTramite);
            if (flag1) {
                resultado.setObjeto(flag1);
                resultado.setBlnValido(true);
            }else resultado.setBlnValido(false);
            logger.info("[main/java/mx/uatx/siia/citas/modelo/citasBusiness/CitaBusiness.java] => validarTramite -> ["+flag1+"]");
        }catch (Exception e){
            resultado.setBlnValido(false);
            logger.error(e.getMessage()+"\n"+e.getCause());
        }
        return resultado;
    }
    public ResultadoTO validarHorario(String strHora, String strFecha){
        final ResultadoTO resultado = new ResultadoTO(true);

        try {
            final boolean flag2 = citasDAO.validarHorario(strFecha, strHora);
            if (flag2) {
                resultado.setObjeto(flag2);
                resultado.setBlnValido(true);
            }else resultado.setBlnValido(false);
            logger.info("[main/java/mx/uatx/siia/citas/modelo/citasBusiness/CitaBusiness.java] => validarHorario -> ["+flag2+"]");
        }catch (Exception e){
            resultado.setBlnValido(false);
            logger.error(e.getMessage()+"\n"+e.getCause());
        }
        return resultado;
    }

    public ResultadoTO obtenerMisCitas(Long longidHistorico){
        ResultadoTO resultado = new ResultadoTO(true);
        try {
            List<MisCitas> list = citasDAO.MisCitas(longidHistorico);
            if (list!=null){
                resultado.setObjeto(list);
            }else resultado.setBlnValido(false);
        }catch (Exception e){
            resultado.setBlnValido(false);
            logger.error(e.getMessage()+"\n"+e.getCause());
        }
        return resultado;
    }

    public ResultadoTO saveDataDB(Map<String, Object> dataCita, String restService) {
        final ResultadoTO resultado  = new ResultadoTO(true);

        try {
            final Map<String, Object> wasSavedCita = citasDAO.putDataRequest(dataCita, restService);
            if (!(wasSavedCita.get("responsecode").equals("OK"))){
                resultado.setBlnValido(false);
            }else{
                resultado.setObjeto(wasSavedCita);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            resultado.setBlnValido(false);
        }
        return resultado;
    }

    public ResultadoTO miCita(String restApi, String idUser){
        final ResultadoTO resultado = new ResultadoTO(true);

        try {
            final List<MisCitas> lista = citasDAO.obtenerCita(restApi,idUser);
            if (lista==null){
                resultado.setBlnValido(false);
            }else{
                resultado.setObjeto(lista);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            resultado.setBlnValido(false);
        }
        return  resultado;
    }

    public ResultadoTO numeroCitas(String restApi, String idUser, String tramite){
        final ResultadoTO resultado = new ResultadoTO(true);

        try {
            final String num = citasDAO.getNumCita(restApi, idUser, tramite);
            if (num==null)
                resultado.setBlnValido(false);
            else
                resultado.setObjeto(num);
        } catch (Exception e){
            logger.error(e.getMessage());
            resultado.setBlnValido(false);
        }

        return resultado;
    }

    public ResultadoTO cancelarCita(String strIdCita, String strMotivo){
        final ResultadoTO resultado = new ResultadoTO(true);

        try {
            final int nRows = Integer.parseInt(citasDAO.cancelarCita(strIdCita, strMotivo));
            if (nRows!=0)
                resultado.setObjeto(nRows);
            else
                resultado.setBlnValido(false);
        }catch (Exception e){
            logger.info(e.getMessage());
            resultado.setBlnValido(false);
        }

        return resultado;
    }

    /**
     * @param params string array con los parametros para la peticion
     * @param service int con el numero del servicio segun la api
     * @return List<MisCitas> en un generico ResultadoTO
     */
    public ResultadoTO getMisCitasOfService(String[] params, int service){
        final ResultadoTO resultado = new ResultadoTO(true);

        List<MisCitas> misCitas = null;
        try {
            switch (service){
                case 1:
                    misCitas = citasDAO.getAllCitasOnTramite(params, URLs.ReportesGeneric.getValor()+service+"&");
                     break;
                     case 2:
                         misCitas = citasDAO.getAllCitasOnId(params[0], URLs.ReportesGeneric.getValor()+service+"&");
                         break;
                case 3:
                    misCitas = citasDAO.getAllCitasOnDate(params, URLs.ReportesGeneric.getValor()+service+"&");
                    break;
            }
            if (misCitas.isEmpty())
                resultado.setBlnValido(false);
            else
                resultado.setObjeto(misCitas);
        }catch (Exception e){
            logger.info(e.getMessage());
            resultado.setBlnValido(false);
        }
        return resultado;
    }

    public ResultadoTO getHoursOfCalendarDisable(String idArea, String fecha){
        ResultadoTO resultado = new ResultadoTO(true);
        List<String> hours;

        try {
            hours = citasDAO.getHoursDisablesCalendar(idArea, fecha, URLs.Commun.getValor());
            if (hours == null) resultado.setBlnValido(false);
            else resultado.setObjeto(hours);
        }catch (Exception e){
            logger.info(e.getMessage());
            resultado.setBlnValido(false);
        }

        return resultado;
    }

    public ResultadoTO reservarHorario(Map<String, Object> datacollect){
        ResultadoTO resultado = new ResultadoTO(true);
        try {
            final Map<String, Object> resMap = citasDAO.putDataRequest(datacollect, URLs.InsertData.getValor()+"?reservar=true");
            if (resMap.get("codefromservice").equals("1")){
                resultado.setObjeto(true);
            }else resultado.setObjeto(false);
        }catch (Exception e){
            resultado.setObjeto(false);
            logger.info(e.getMessage());
        }
        return resultado;
    }
}
