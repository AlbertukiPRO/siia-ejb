package mx.uatx.siia.citas.citasBusiness;

import mx.uatx.siia.citas.areas.business.SiPaAreasConfiguraciones;

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
