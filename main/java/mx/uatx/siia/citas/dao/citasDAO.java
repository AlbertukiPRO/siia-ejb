package mx.uatx.siia.citas.dao;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import mx.uatx.siia.citas.entities.MisCitas;
import mx.uatx.siia.citas.entities.SIMSCITAS;
import mx.uatx.siia.citas.citasBusiness.MethodsGenerics;
import mx.uatx.siia.citas.enums.URLs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static mx.uatx.siia.citas.citasBusiness.MethodsGenerics.readUrl;

@Repository
    /*
La anotación se utiliza para indicar que la clase proporciona es el mecanismo para la operación de almacenamiento, recuperación, búsqueda, actualización y eliminación de objetos.
*/
//* DAO: DATA ACCESS OBJECT

public class citasDAO implements Serializable {
    @PersistenceContext(name = "SIIA")
    private EntityManager entityManager;
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Transactional()
    public boolean nuevaCita(final SIMSCITAS cita){
        try {
            Query query = entityManager.createNativeQuery("INSERT INTO SIIUAT.SIMSCITAS (IDCITA, IDHISTORIALACADEMICO, IDAREACAMPUS, IDTRAMITE, DESCRIPCIONCITA, ESTATUSCITAS, FECHARESERVADACITA, RETROALIMENTACIONCITA, FECHARESERVADACITA_1, FCAUDIT, USERAUDIT) VALUES (SIIUAT.IDCITA.nextval, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?)");
            query.setParameter(1, cita.getLongHistorialAcademico());
            query.setParameter(2,cita.getIntIdArea());
            query.setParameter(3, cita.getIntTramite());
            query.setParameter(4, cita.getStrDescripcionCita());
            query.setParameter(5,cita.getStrEstatus());
            query.setParameter(6, cita.getStrFechaReservada());
            query.setParameter(7, cita.getStrRetroalimentacion());
            query.setParameter(8, cita.getStrHoraReservada());
            query.setParameter(9, cita.getStrUSERAUDIT());
            query.executeUpdate();

            logger.info("--> RES OF QUERY [Save Cita] => "+entityManager.getTransaction().getRollbackOnly());

        } catch (Exception e){
            logger.error(e.getMessage()+ "\n"+e.getCause());
        }
        return true;
    }

    @Transactional()
    public boolean validarTramite(Long longIdhistorical, int intIdTramite) {
        int flag = 0;
        try {
              Query query = entityManager.createNativeQuery("SELECT COUNT(sm.IDTRAMITE) num FROM SIIUAT.SIMSCITAS sm WHERE IDHISTORIALACADEMICO = ? and IDTRAMITE = ?");
              query.setParameter(1, longIdhistorical);
              query.setParameter(2, intIdTramite);
              logger.info("--> STEP 1 Results => "+query.getSingleResult());
              flag = Integer.parseInt(query.getSingleResult().toString());
          }catch (Exception e){
              logger.error(e.getMessage());
          }
          return flag == 0;
    }

    @Transactional()
    public boolean validarHorario(String strFecha, String strHora){
        int flag = 0;
        try{
            Query query = entityManager.createNativeQuery("SELECT COUNT(HORAEXEPCION) FROM SIIUAT.SIEXCEPCIONES ex where FECHAEXCEPCION = ? and HORAEXEPCION = ?");
            query.setParameter(1, strFecha);
            query.setParameter(2, strHora);

            logger.info("--> STEP 2 Results => "+query.getSingleResult());

            flag = Integer.parseInt(query.getSingleResult().toString());
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return flag == 0;
    }

    @Transactional()
    public List<MisCitas> MisCitas(final Long longidHistorico) throws Exception {

        List<MisCitas> citasTOList = null;

        Query query = entityManager.createNativeQuery("SELECT SIMSCITAS.*, S.NOMBRETRAMITE, A.NBAREA FROM SIIUAT.SIMSCITAS INNER JOIN SIIUAT.SICTTRAMITES S on S.IDTRAMITE = SIMSCITAS.IDTRAMITE INNER JOIN SIIUAT.SICTAREAS A on A.CLAREA = SIMSCITAS.IDAREACAMPUS WHERE IDHISTORIALACADEMICO = ?", MisCitas.class);
        query.setParameter(1, longidHistorico);
        citasTOList = query.getResultList();

        return citasTOList;
    }

    @Transactional
    public boolean reservarHorario(String[] params1){

        Query queryExcepciones = entityManager.createNativeQuery("" +
                "INSERT INTO SIIUAT.SIEXCEPCIONES (IDEXCEPCION, FECHAEXCEPCION, HORAEXEPCION, FCAUDIT, USERAUDIT) VALUES " +
                "(SIIUAT.IDEXCEPCION.nextval, ?, ?, sysdate, ?)");
        queryExcepciones.setParameter(1, params1[0]);
        queryExcepciones.setParameter(2, params1[1]);
        queryExcepciones.setParameter(3, params1[2]);
        queryExcepciones.executeUpdate();

        return true;
    }

    @Transactional
    public Integer getIdEx(){
        Query getIdEx = entityManager.createNativeQuery("SELECT * FROM ( SELECT SIEXCEPCIONES.IDEXCEPCION FROM SIIUAT.SIEXCEPCIONES order by IDEXCEPCION DESC ) where ROWNUM <= 1");
        logger.info("RESULTADOS => "+getIdEx.getSingleResult());
        return Integer.parseInt(getIdEx.getSingleResult().toString());
    }

    @Transactional
    public void insertFechas(String[] params2, Integer index){
        Query queryFechasYHorarios = entityManager.createNativeQuery("INSERT INTO SIIUAT.SIAXFECHASHORARIOS (IDFECHAHORA, IDAREACAMPUS, IDEXCEPCIONES, FCAUDIT, USERAUDIT) VALUES " +
                "(SIIUAT.IDFECHAHORA.nextval, ?, ?, sysdate, ?)");
        queryFechasYHorarios.setParameter(1, params2[0]);
        queryFechasYHorarios.setParameter(2, index);
        queryFechasYHorarios.setParameter(3, params2[1]);
        queryFechasYHorarios.executeUpdate();
    }

    @Transactional
    public List<MisCitas> obtenerCitasPorTramite(long idtramite, long idarea, String fechaA, String fechaB){
        Query query = entityManager.createNativeQuery("SELECT SIMSCITAS.IDHISTORIALACADEMICO, 'Yair Ivan Valencia Perez' NOMBREUSER, 'Ingeniería en computación' PROGRAMA, 20082306 MATRICULA, T.NOMBRETRAMITE, SIMSCITAS.FECHARESERVADACITA, SIMSCITAS.FECHARESERVADACITA_1, SIMSCITAS.DESCRIPCIONCITA, SIMSCITAS.IDCITA" +
                " FROM SIIUAT.SIMSCITAS " +
                "INNER JOIN SIIUAT.SICTTRAMITES T on T.IDTRAMITE = SIMSCITAS.IDTRAMITE " +
                "where SIMSCITAS.IDTRAMITE = ? and SIMSCITAS.IDAREACAMPUS = ? and " +
                "                      to_date(SIMSCITAS.FCAUDIT, 'dd-mm-yyyy') BETWEEN to_date(?, 'dd-mm-yyyy') and to_date(?, 'dd-mm-yyyy') ", MisCitas.class);
        query.setParameter(1, idtramite);
        query.setParameter(2, idarea);
        query.setParameter(3, fechaA);
        query.setParameter(4, fechaB);

        return (List<MisCitas>) query.getResultList();
    }
    @Transactional
    public List<MisCitas> obtenerCitasGlobales(long idArea, String fechaA, String fechaB){
        Query query = entityManager.createNativeQuery("SELECT SIMSCITAS.IDHISTORIALACADEMICO, 'Yair Ivan Valencia Perez' NOMBREUSER, 'Ingeniería en computación' PROGRAMA, 20082306 MATRICULA, T.NOMBRETRAMITE, SIMSCITAS.FECHARESERVADACITA, SIMSCITAS.FECHARESERVADACITA_1, SIMSCITAS.DESCRIPCIONCITA, SIMSCITAS.IDCITA" +
                        " FROM SIIUAT.SIMSCITAS " +
                        "INNER JOIN SIIUAT.SICTTRAMITES T on T.IDTRAMITE = SIMSCITAS.IDTRAMITE " +
                        "where SIMSCITAS.IDAREACAMPUS = ? and " +
                        "                      to_date(SIMSCITAS.FCAUDIT, 'dd-mm-yyyy') BETWEEN to_date(?, 'dd-mm-yyyy') and to_date(?, 'dd-mm-yyyy') ", MisCitas.class);
        query.setParameter(1, idArea);
        query.setParameter(2, fechaA);
        query.setParameter(3, fechaB);

        return (List<MisCitas>) query.getResultList();
    }

    @Transactional
    public List<MisCitas> obtenerCitasEstatus(long idArea, String strEstatus, String fechaA, String fechaB){
        Query query = entityManager.createNativeQuery("SELECT SIMSCITAS.IDHISTORIALACADEMICO, 'Yair Ivan Valencia Perez' NOMBREUSER, 'Ingeniería en computación' PROGRAMA, 20082306 MATRICULA, T.NOMBRETRAMITE, SIMSCITAS.FECHARESERVADACITA, SIMSCITAS.FECHARESERVADACITA_1, SIMSCITAS.DESCRIPCIONCITA, SIMSCITAS.IDCITA" +
                " FROM SIIUAT.SIMSCITAS " +
                "INNER JOIN SIIUAT.SICTTRAMITES T on T.IDTRAMITE = SIMSCITAS.IDTRAMITE " +
                "where SIMSCITAS.IDAREACAMPUS = ? and SIMSCITAS.ESTATUSCITAS = ? and " +
                "                      to_date(SIMSCITAS.FCAUDIT, 'dd-mm-yyyy') BETWEEN to_date(?, 'dd-mm-yyyy') and to_date(?, 'dd-mm-yyyy') ", MisCitas.class);
        query.setParameter(1, idArea);
        query.setParameter(2, strEstatus);
        query.setParameter(3, fechaA);
        query.setParameter(4, fechaB);

        return (List<MisCitas>) query.getResultList();
    }

    @Transactional
    public List<MisCitas> obtenerCitasMes(long idArea, String mes){
        Query query = entityManager.createNativeQuery("SELECT SIMSCITAS.IDHISTORIALACADEMICO, 'Yair Ivan Valencia Perez' NOMBREUSER, 'Ingeniería en computación' PROGRAMA, 20082306 MATRICULA, T.NOMBRETRAMITE, SIMSCITAS.FECHARESERVADACITA, SIMSCITAS.FECHARESERVADACITA_1, SIMSCITAS.DESCRIPCIONCITA, SIMSCITAS.IDCITA " +
                "                 FROM SIIUAT.SIMSCITAS " +
                "                INNER JOIN SIIUAT.SICTTRAMITES T on T.IDTRAMITE = SIMSCITAS.IDTRAMITE " +
                "                where SIMSCITAS.IDAREACAMPUS = ? and  to_date(SIMSCITAS.FECHARESERVADACITA, 'mm-dd-yyyy') BETWEEN to_date(?, 'mm-dd-yyyy') and to_date(?, 'mm-dd-yyyy')", MisCitas.class);
        query.setParameter(1, idArea);
        query.setParameter(2, mes.split(",")[0]);
        query.setParameter(3, mes.split(",")[1]);

        return (List<MisCitas>) query.getResultList();
    }



    @Transactional
    public List<String> obtenerHorasDesactivadasFromCalendar(long longidarea, String strfecha){
        Query query = entityManager.createNativeQuery("SELECT SIEXCEPCIONES.HORAEXEPCION FROM SIIUAT.SIEXCEPCIONES\n" +
                "INNER JOIN SIIUAT.SIAXFECHASHORARIOS S on SIEXCEPCIONES.IDEXCEPCION = S.IDEXCEPCIONES\n" +
                "WHERE SIEXCEPCIONES.FECHAEXCEPCION = ? and S.IDAREACAMPUS  = ?");
        query.setParameter(1, strfecha);
        query.setParameter(2, longidarea);
        return (List<String>) query.getResultList();
    }

    @Transactional
    public boolean guardarRetroalimentacion(Integer idcita, String strRetro){
        Query query = entityManager.createNativeQuery("UPDATE SIIUAT.SIMSCITAS SET RETROALIMENTACIONCITA = ? WHERE IDCITA  = ?");
        query.setParameter(1, strRetro);
        query.setParameter(2, idcita);

        return query.executeUpdate() != 0;
    }
    @Transactional
    public boolean citaEstatus(Integer idCita, String estatus){
        Query query = entityManager.createNativeQuery("UPDATE SIIUAT.SIMSCITAS SET ESTATUSCITAS = ? WHERE IDCITA = ?");
        query.setParameter(1, estatus);
        query.setParameter(2, idCita);
        return query.executeUpdate() != 0;
    }

    public MisCitas obtenerCita(Integer idcita){
        Query query  = entityManager.createNativeQuery("SELECT SIMSCITAS.IDHISTORIALACADEMICO, 'Yair Ivan Valencia Perez' NOMBREUSER, 'Ingeniería en computación' PROGRAMA, 20082306 MATRICULA, T.NOMBRETRAMITE, SIMSCITAS.FECHARESERVADACITA, SIMSCITAS.FECHARESERVADACITA_1, SIMSCITAS.DESCRIPCIONCITA, SIMSCITAS.IDCITA " +
                "                        FROM SIIUAT.SIMSCITAS " +
                "                        INNER JOIN SIIUAT.SICTTRAMITES T on T.IDTRAMITE = SIMSCITAS.IDTRAMITE " +
                "                        where SIMSCITAS.IDCITA = ? ", MisCitas.class);
        query.setParameter(1, idcita);
        return (MisCitas) query.getSingleResult();
    }

    @Transactional
    public Integer obtenerIdExecepcion(String fecha, String hora){
        Query query = entityManager.createNativeQuery("SELECT SIEXCEPCIONES.IDEXCEPCION FROM SIIUAT.SIEXCEPCIONES WHERE SIEXCEPCIONES.FECHAEXCEPCION = ? AND SIEXCEPCIONES.HORAEXEPCION = ?");
        query.setParameter(1, fecha);
        query.setParameter(2, hora);
        return Integer.parseInt(query.getSingleResult().toString());
    }

    @Transactional
    public boolean liberarHorarios(Integer idexcepcion){

        Query deleteFecha = entityManager.createNativeQuery("DELETE FROM SIIUAT.SIAXFECHASHORARIOS WHERE IDEXCEPCIONES = ? ");
        deleteFecha.setParameter(1, idexcepcion);

        Query deleteExcep = entityManager.createNativeQuery("DELETE FROM SIIUAT.SIEXCEPCIONES WHERE IDEXCEPCION = ? ");
        deleteExcep.setParameter(1, idexcepcion);

        return deleteFecha.executeUpdate() != 0 && deleteExcep.executeUpdate() != 0;
    }

    @Transactional
    public Integer obtenerIdCita(long idhistorico){
        Query query = entityManager.createNativeQuery("SELECT * FROM (SELECT SIMSCITAS.IDCITA FROM SIIUAT.SIMSCITAS WHERE IDHISTORIALACADEMICO = ? ORDER BY IDCITA DESC) WHERE ROWNUM <= 1");
        query.setParameter(1, idhistorico);
        return Integer.parseInt(query.getSingleResult().toString());
    }


    /**
     * @param strIdCita String value del IDCITA
     * @param strMotivo String con el texto del movito de la cancelacion de la cita.
     * @return nRows => String con el numero de filas afectadas segun la consulta TODO: Implementacion temporal
     */
    @Transactional
    public boolean cancelarCita(Integer strIdCita, Integer idhistorical, String strMotivo){
        Query query = entityManager.createNativeQuery("UPDATE SIIUAT.SIMSCITAS set ESTATUSCITAS = 'Cancelado', DESCRIPCIONCITA = ? WHERE IDHISTORIALACADEMICO = ? and IDCITA = ? ");
        query.setParameter(1, strMotivo);
        query.setParameter(2, idhistorical);
        query.setParameter(3, strIdCita);

        return query.executeUpdate() != 0;
    }

    /*------------------------------------------------------------------------*/

    /**
     * Metodo para obtener las citas agendadas por el usuario
     * @deprecated no usar en la implementacion del servidor del SIIA
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

}
