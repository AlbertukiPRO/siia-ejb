package mx.uatx.siia.citas.modelo.Areas.areasBusiness;

import mx.uatx.siia.citas.modelo.dao.areasDAO;
import mx.uatx.siia.serviciosUniversitarios.dto.AreasTO;
import mx.uatx.siia.serviciosUniversitarios.dto.ResultadoTO;
import mx.uatx.siia.serviciosUniversitarios.enums.SeveridadMensajeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;


@Service
@Configurable
public class areaBusiness implements Serializable {

    @Autowired
    private areasDAO areasDAO;

    public List<AreasTO> ObtenerAreas(){
        final ResultadoTO res = new ResultadoTO(true);

        List<AreasTO> listAreas = null;

       try {
           final AreasTO loadAreas = areasDAO.obtenerAreas();

           //TODO: Agregar listAreas.add(new AreasTO("",""))
           listAreas.add(loadAreas);

           if (loadAreas == null){
               res.agregarMensaje(SeveridadMensajeEnum.ERROR, "comun.msj.citas.nuevacita.error");
               res.setBlnValido(false);
           }
       }catch (Exception exception){

       }
        return listAreas;
    }

}
