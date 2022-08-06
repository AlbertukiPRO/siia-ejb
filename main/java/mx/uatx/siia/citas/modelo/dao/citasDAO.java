package mx.uatx.siia.citas.modelo.dao;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import mx.uatx.siia.citas.modelo.MisCitas;
import mx.uatx.siia.serviciosUniversitarios.dto.CitasTO;
import mx.uatx.siia.serviciosUniversitarios.modelo.SiPaPermisos;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static mx.uatx.siia.citas.modelo.citasBusiness.MethodsGenerics.readUrl;

@Repository
    /*
La anotación se utiliza para indicar que la clase proporciona es el mecanismo para la operación de almacenamiento, recuperación, búsqueda, actualización y eliminación de objetos.
*/
//* DAO: DATA ACCESS OBJECT

public class citasDAO implements Serializable {

    @PersistenceContext(name = "SIIA")
    private EntityManager em;
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public CitasTO NuevaCita(final String strUsuario) throws Exception {
        CitasTO citasTO = null; //clase donde se almacena el resultado del acceso a los datos.

        //TODO: storedProcedure [ ❗ consulta SQL ]

        //crear instancia de la clase y enviar los datos al constructor
        //TODO: citasTO  = new CitasTo(values constructor 2 o "n");

        return citasTO;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public List<CitasTO> MisCitas(final String strUsuario) throws Exception {

        List<CitasTO> citasTOList = null;

        //TODO:  Obtener citas de la DB y crear una lista de citas.

        return citasTOList;
    }

    public int getNumCita(String apirest, String iduser){

        System.out.println("----- Finish get num Citas ----");

        return Integer.parseInt(readUrl(apirest+"?user="+iduser));
    }


    /**
     * Metodo para obtener las citas agendadas por el usuario
     * @param api URL string del servicio
     * @param user string con el la matricula del usuario
     * @return List<MisCitas>
     */
    public List<MisCitas> obtenerCita(String api, String user){
        List<MisCitas> misCitas;

        String strJson = readUrl(api+"?id="+user);

        System.out.println("----- FINISH GET MIS CITAS ---");
        Type listType = new TypeToken<List<MisCitas>>(){}.getType();

        misCitas = new Gson().fromJson(strJson,listType);

        return misCitas;
    }

    /**
     * Metodo para almacenar la cita en la DB local.
     * @param dataCita [Map<String, String>] con los datos del formulario.
     * @param restService [String] con la url del servicio web
     * @return boolean con base en el exito de la transaccion.
     */
    public int guardarCita(Map<String, String> dataCita, String restService){
        int codeResponde = 0;
        System.out.println("---- SEND VALUES TO api save [ Run ]");
        try {
            URL url = new URL(restService);
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("PUT");
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json");

            String strJson = new Gson().toJson(dataCita);

            byte[] out = strJson.getBytes(StandardCharsets.UTF_8);

            OutputStream stream = http.getOutputStream();
            stream.write(out);

            BufferedReader br = null;
            if (http.getResponseCode() == 200) {
                br = new BufferedReader(new InputStreamReader(http.getInputStream()));
                String strCurrentLine;
                while ((strCurrentLine = br.readLine()) != null) {
                    System.out.println(strCurrentLine);
                }
            } else {
                br = new BufferedReader(new InputStreamReader(http.getErrorStream()));
                String strCurrentLine;
                while ((strCurrentLine = br.readLine()) != null) {
                    System.out.println("Buffer: "+strCurrentLine);
                    codeResponde = Integer.parseInt(strCurrentLine);
                }
            }

            //HEADERS.
//           Map<String, List<String>> header = http.getHeaderFields();
//
//           for (Map.Entry<String,List<String>> entry : header.entrySet())
//               System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

            http.disconnect();

        }catch (Exception e){
            System.out.println(e);
        }
        return codeResponde;
    }

}
