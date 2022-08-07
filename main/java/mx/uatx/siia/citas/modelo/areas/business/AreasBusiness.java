package mx.uatx.siia.citas.modelo.areas.business;

import mx.uatx.siia.citas.modelo.dao.areasDAO;
import mx.uatx.siia.serviciosUniversitarios.dto.AreasTO;
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
import java.util.List;

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


    /**
     *
     * @author Alberto Noche Rosas
     */

    public ResultadoTO obtenerAreas(String url){

        final ResultadoTO resultado = new ResultadoTO(true);

        try {
            final List<AreasTO> areas = areasDAO.getAreasDAO(url);
            List<SelectItem> listAreas = new ArrayList<>(); //lista local para el SelectOneMenu
            if (areas==null){
                resultado.setBlnValido(false);
            }else{
                for (AreasTO item : areas){
                    listAreas.add(new SelectItem(item.getIntIdAreas(), item.getStrNombreAreas()));
                }
                resultado.setObjeto(listAreas);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            resultado.setBlnValido(false);
        }
        return resultado;
    }


    /**
     *
     * @param url del api rest para los horarios reservados en la base de datos.
     * @return List de Strings de Horarios que no se mostrar.
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
