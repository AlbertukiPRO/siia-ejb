package mx.uatx.siia.citas.modelo.dao;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import mx.uatx.siia.citas.modelo.MisCitas;
import mx.uatx.siia.citas.modelo.SiPaCitas;
import mx.uatx.siia.citas.modelo.citasBusiness.MethodsGenerics;
import mx.uatx.siia.citas.modelo.enums.URLs;
import mx.uatx.siia.serviciosUniversitarios.dto.CitasTO;
import org.eclipse.persistence.exceptions.EntityManagerSetupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;

import static mx.uatx.siia.citas.modelo.citasBusiness.MethodsGenerics.readUrl;

@Repository
    /*
La anotación se utiliza para indicar que la clase proporciona es el mecanismo para la operación de almacenamiento, recuperación, búsqueda, actualización y eliminación de objetos.
*/
//* DAO: DATA ACCESS OBJECT

public class citasDAO implements Serializable {
    @PersistenceContext(name = "SIIA")
    private EntityManager entityManager;

    protected final Logger logger = LoggerFactory.getLogger(getClass());



    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public boolean NuevaCita(final SiPaCitas cita){

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cita);
            entityManager.getTransaction().commit();
            return true;
        } catch (EntityManagerSetupException e){
            logger.error(e.getMessage());
        }

        return false;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public List<CitasTO> MisCitas(final String strUsuario) throws Exception {

        List<CitasTO> citasTOList = null;

        //TODO:  Obtener citas de la DB y crear una lista de citas.

        return citasTOList;
    }

    public String getNumCita(String apirest, String iduser, String idtramite){

        System.out.println("----- Finish get num Citas ----");

        return readUrl(apirest+"?user="+iduser+"&tramite="+idtramite);
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

        if (!strJson.isEmpty()){
            System.out.println("----- FINISH GET MIS CITAS ---");
            Type listType = new TypeToken<List<MisCitas>>(){}.getType();

            misCitas = new Gson().fromJson(strJson,listType);
        }else
            misCitas = new ArrayList<>();

        return misCitas;
    }

    /**
     * Metodo para almacenar la cita en la DB local.
     * @param dataCita [Map<String, String>] con los datos del formulario.
     * @param restService [String] con la url del servicio web
     * @return boolean con base en el exito de la transaccion.
     */
    public Map<String, Object> putDataRequest(Map<String, Object> dataCita, String restService){
        int codeResponde = 0;
        Map<String, Object> response = new HashMap<>();
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
                   codeResponde = Integer.parseInt(strCurrentLine);
                }
            } else {
                br = new BufferedReader(new InputStreamReader(http.getErrorStream()));
                String strCurrentLine;
                while ((strCurrentLine = br.readLine()) != null) {
                    System.out.println("Buffer: "+strCurrentLine);
                    codeResponde = Integer.parseInt(strCurrentLine);
                }
            }

            response.put("respoSMS", String.valueOf(http.getResponseCode()));
            response.put("responsecode",http.getResponseMessage());
            response.put("codefromservice", String.valueOf(codeResponde));

            http.disconnect();

        }catch (Exception e){
            System.out.println(e);
        }
        return response;
    }

    /**
     * @param strIdCita String value del IDCITA
     * @param strMotivo String con el texto del movito de la cancelacion de la cita.
     * @return nRows => String con el numero de filas afectadas segun la consulta TODO: Implementacion temporal
     */
    public String cancelarCita(String strIdCita, String strMotivo){
        String nRows;
        try{
            nRows = readUrl(URLs.CancelarMiCita.getValor()+"?idCita="+strIdCita+"&motivo="+strMotivo);
        }catch (Exception e){
            nRows = "0";
        }
        return nRows;
    }

    /**
     * @param idArea String con él, id del área o departamento
     * @param apiurl String con la direccion del servicio php
     * @return List<MisCitas> de los datos de la DB
     */
    public List<MisCitas> getAllCitasOnId(String idArea, String apiurl){
        List<MisCitas> misCitas;
        String strJson = MethodsGenerics.readUrl(apiurl+"idArea="+idArea);
        if (!strJson.isEmpty()){
            Type lisType = new TypeToken<List<MisCitas>>(){}.getType();
            misCitas = new Gson().fromJson(strJson, lisType);
            System.out.println("|--------------------------------------------------------");
            System.out.println("|----- Finished Service http.get() @return => " + misCitas);
            System.out.println("|--------------------------------------------------------");
        }else
            misCitas = new ArrayList<>();

        return misCitas;
    }

    /**
     * @param params String[] con los parametros de criterio para la consulta
     * @param apirurl String del servicio de la DB
     * @return List<MisCitas> de los datos de la DB
     */
    public List<MisCitas> getAllCitasOnDate(String[] params, String apirurl){
        List<MisCitas> misCitas;
        String strJson = MethodsGenerics.readUrl(apirurl+"idArea="+params[0]+"&fecha="+params[1]);
        if (!strJson.isEmpty()){
            Type lisType = new TypeToken<List<MisCitas>>(){}.getType();
            misCitas = new Gson().fromJson(strJson, lisType);
            System.out.println("|--------------------------------------------------------");
            System.out.println("|----- Finished Service http.get() @return => " + misCitas);
            System.out.println("|--------------------------------------------------------");
        }else
            misCitas = new ArrayList<>();

        return misCitas;
    }

    /**
     * @param params String[] con los parametros de criterio para la consulta
     * @param api String del servicio de la DB
     * @return List<MisCitas> de los datos de la DB
     */
    public List<MisCitas> getAllCitasOnTramite(String[] params, String api){
         List<MisCitas> misCitas;
         String strJson = MethodsGenerics.readUrl(api+"idArea="+params[0]+"&idTramite="+params[1]);
        if (!strJson.isEmpty()){
            Type lisType = new TypeToken<List<MisCitas>>(){}.getType();
            misCitas = new Gson().fromJson(strJson, lisType);
            System.out.println("|--------------------------------------------------------");
            System.out.println("|----- Finished Service http.get() @return => " + misCitas);
            System.out.println("|--------------------------------------------------------");
        }else
            misCitas = new ArrayList<>();

        return misCitas;
    }

    public List<String> getHoursDisablesCalendar(String idArea, String fecha, String api){
        List<String> hours;
        String json = MethodsGenerics.readUrl(api+"?coomon=hoursdisable&idarea="+idArea+"&fecha="+fecha);
        System.out.println("--------- FINISH GET ALL Hours Disables ---- [List] => "+json);
        if (!json.isEmpty()){
            hours = new ArrayList<>(Arrays.asList(json.split(",")));
        }else
            hours = new ArrayList<>();

        return hours;
    }
    public boolean reservarHora(String api) {
        return MethodsGenerics.readUrl(api).equals("1");
    }

}
