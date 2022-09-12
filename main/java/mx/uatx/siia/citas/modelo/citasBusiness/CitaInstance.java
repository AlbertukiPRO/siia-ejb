package mx.uatx.siia.citas.modelo.citasBusiness;

import mx.uatx.siia.citas.modelo.areas.business.AreasBusiness;
import mx.uatx.siia.citas.modelo.areas.business.SiPaAreasConfiguraciones;
import mx.uatx.siia.serviciosUniversitarios.dto.ResultadoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public class CitaInstance {
    public List<SiPaAreasConfiguraciones> configuraciones;
    private static CitaInstance citaInstance;

    private CitaInstance(){

    }

    public static CitaInstance getInstance(){
        if(citaInstance==null){
            citaInstance = new CitaInstance();
        }

        return citaInstance;
    }

    public void asignar(List<SiPaAreasConfiguraciones> lista){
        configuraciones = lista;
    }

}
