package mx.uatx.siia.citas.modelo.citasBusiness;


import mx.uatx.siia.citas.modelo.dao.citasDAO;
import mx.uatx.siia.serviciosUniversitarios.dto.ResultadoTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;

@Service
@Configurable //para la inyección de dependencias no administradas por spring.
public class CitaBusiness implements Serializable {

    @Autowired
    private citasDAO citasDAO;

    private static final long serialVersionUID = 7209423068137481883L;
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public ResultadoTO agendarCita(Map<String, String> dataCita, String restService) {
        final ResultadoTO resultado  = new ResultadoTO(true);

        try {
            final int wasSavedCita = citasDAO.guardarCita(dataCita, restService);
            if (wasSavedCita==200){
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
}
