package mx.uatx.siia.citas.modelo.citasBusiness;


import mx.uatx.siia.citas.modelo.dao.citasDAO;
import mx.uatx.siia.serviciosUniversitarios.dto.CitasTO;
import mx.uatx.siia.serviciosUniversitarios.dto.ResultadoTO;
import mx.uatx.siia.serviciosUniversitarios.enums.SeveridadMensajeEnum;
import net.sf.jasperreports.engine.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Service
@Configurable //para la inyección de dependencias no administradas por spring.
public class citaBusiness implements Serializable {

    @Autowired
    private citasDAO citasDAO;

    private static final long serialVersionUID = 3296618443018487900L;
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public ResultadoTO NuevaCita(final String strUsuario)  {


        final ResultadoTO res = new ResultadoTO(true); //Mostrar resultado

        try {
            final CitasTO loadcita = citasDAO.NuevaCita(strUsuario);

            if (loadcita == null){
                res.agregarMensaje(SeveridadMensajeEnum.ERROR, "comun.msj.citas.nuevacita.error");
                res.setBlnValido(false);
            } else {

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public void CambiarEstadoCita(int isActivo ){

    }
}
