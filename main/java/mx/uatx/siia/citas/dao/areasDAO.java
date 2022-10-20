package mx.uatx.siia.citas.dao;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import mx.uatx.siia.citas.MisCitas;
import mx.uatx.siia.citas.SIMSCITAS;
import mx.uatx.siia.citas.areas.SiPaAreas;
import mx.uatx.siia.citas.areas.business.SiPaAreasConfiguraciones;
import mx.uatx.siia.serviciosUniversitarios.dto.AreasTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.*;

import static mx.uatx.siia.citas.citasBusiness.MethodsGenerics.readUrl;


/**
 * @author Alberto Noche Rosas
 * @version 1.1
 */

@Repository
public class areasDAO implements Serializable {

    private static final long serialVersionUID = -8768391488732671886L;
    @PersistenceContext(name="SIIA")
    private EntityManager em;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Transactional
    public List<SiPaAreas> obtenerAreas(){// %Control% => parmatro de prueba;
        String query = "SELECT * FROM SIIUAT.SICTAREAS where IDTIPOAREA = 10 and NBAREA LIKE '%Control%'";
        return em.createNativeQuery(query, SiPaAreas.class).getResultList();
    }

    /**
     * @deprecated
     * @param url => 'http:localhost/siiaservices/getareas.php'
     * @return List => AreasTO
     */
    public List<AreasTO> getAreasDAO(String url){
        List<AreasTO> listaAreas;
        String strJson = readUrl(url);
        if (!strJson.isEmpty()){
            Type listType = new TypeToken<List<AreasTO>>(){}.getType();

            listaAreas = new Gson().fromJson(strJson,listType);

        }else{
            listaAreas = new ArrayList<>();
            listaAreas.add(new AreasTO("1", "No se logro obtener los datos"));
        }
        return listaAreas;
    }

    /**
     * @deprecated uso unico de local con backend php y mysql
     * @param url  => 'http:localhost/siiaservices/getFechasReservadas.php'
     * @param area => id Area seleccionada
     * @return List => Strings, con los horarios reservados de las citas.
     */
    public List<String> getFechasDB(String url,String area){
        List<String> lista = null;
        try {
            String resultado = readUrl(url+"?idarea="+area);
            if (!resultado.equals("0")){
                System.out.println("----- Response from ["+url+"?idarea="+area+"] => resultado");
                lista = new ArrayList<>(Arrays.asList(resultado.split(",")));
            }else{
                lista = new ArrayList<>();
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return lista;
    }

    @Transactional
    public List<String> getFechas(String strIdArea){
        List<String> lista = null;
        try {
            Query query1 = em.createNativeQuery("SELECT ex.FECHAEXCEPCION FROM SIIUAT.SIEXCEPCIONES ex " +
                    "INNER JOIN SIIUAT.SIAXFECHASHORARIOS S on ex.IDEXCEPCION = S.IDEXCEPCIONES " +
                    "WHERE S.IDAREACAMPUS = ? and ex.HORAEXEPCION = 'all' ");
            query1.setParameter(1, ""+strIdArea);
            lista = query1.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage()+"\n"+e.getCause());
        }
        return lista;
    }

    @Transactional
    public List<String> getHorarios(String[] params){
        List<String> lista = null;
        try {
            Query query = em.createNativeQuery("SELECT ex.HORAEXEPCION FROM SIIUAT.SIEXCEPCIONES ex " +
                    "inner join SIIUAT.SIAXFECHASHORARIOS S on ex.IDEXCEPCION = S.IDEXCEPCIONES " +
                    "where S.IDAREACAMPUS = ? and ex.FECHAEXCEPCION = ?");
            query.setParameter(1, params[0]);
            query.setParameter(2, params[1]);
            lista = query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage()+"\n"+e.getCause());
        }
        return lista;
    }

    @Transactional
    public SiPaAreasConfiguraciones getConfig(Integer idArea){
        SiPaAreasConfiguraciones list = null;
        try {
            Query query = em.createNativeQuery("SELECT * FROM SIIUAT.SIAXCONFIGURACIONES WHERE IDAREA = ?", SiPaAreasConfiguraciones.class);
            query.setParameter(1, idArea);
            list = (SiPaAreasConfiguraciones) query.getSingleResult();
        }catch (Exception e){
            logger.error(e.getMessage()+"\n"+e.getCause());
        }
        return list;
    }

    public int desactivarDia(String strIdArea, String strFecha, String strUserAudit){
        int flag = 0;
        try {
            Query query  = em.createNativeQuery(" INSERT INTO SIIUAT.SIEXCEPCIONES (IDEXCEPCION, FECHAEXCEPCION, HORAEXEPCION, FCAUDIT, USERAUDIT) VALUES " +
                    "(SIIUAT.IDEXCEPCION.nextval, ?, 'all', sysdate, ? ");
            query.setParameter(1, strFecha);
            query.setParameter(2, strUserAudit);
            flag = (int) query.executeUpdate();

            Query query1 = em.createNativeQuery("SELECT * FROM ( SELECT SIIUAT.SIEXCEPCIONES.IDEXCEPCION FROM SIEXCEPCIONES order by IDEXCEPCION DESC ) where ROWNUM <= 1");
            int idException = (int) query1.getSingleResult();

            Query query2 = em.createNativeQuery("INSERT INTO SIIUAT.SIAXFECHASHORARIOS (IDFECHAHORA, IDAREACAMPUS, IDEXCEPCIONES, FCAUDIT, USERAUDIT) VALUES " +
                    "(SIIUAT.IDFECHAHORA.nextval, ?, ?, sysdate, ?)  ");
            query2.setParameter(1, strIdArea);
            query2.setParameter(2, idException);
            query.setParameter(3, strUserAudit);


        }catch (Exception e){
            logger.error(e.getMessage()+"\n"+e.getCause());
        }
        return flag;
    }

    @Transactional
    public List<SIMSCITAS> obtenerCitasToCalendar(String strIdArea){
        List<SIMSCITAS> list = null;
        try {
            Query query = em.createNativeQuery("SELECT * FROM SIIUAT.SIMSCITAS WHERE IDAREACAMPUS = ?", SIMSCITAS.class);
            query.setParameter(1, strIdArea);

            list = query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage()+"\n"+e.getCause());
        }
        return list;
    }

    @Transactional
    public SIMSCITAS obtenerCita(Long longIdUser, Long longIdCita){
        Query query = em.createNativeQuery("SELECT 'Yair Ivan Valencia Perez' NOMBRE, 'Ingeniería en computación' PROGRAMA, 'Facultad de Ciencias Básicas Ingeniería y Tecnología' FACULTAD, 9 GRADO, 'B' GRUPO, '200181837@uat.mx.com' CORREO, SIMSCITAS.* FROM SIIUAT.SIMSCITAS WHERE IDHISTORIALACADEMICO = ? and IDCITA = ?", SIMSCITAS.class);
        query.setParameter(1, longIdUser);
        query.setParameter(2, longIdCita);
        return (SIMSCITAS) query.getSingleResult();
    }

    /*-------------------------  METODOS LOCALES PARA USO DE PHP Y MYSQL  -----------------------------*/

    public List<MisCitas> getEventos(String idCita, String url) {
        List<MisCitas> list = null;
        try {
            String json = readUrl(url+"?idarea="+idCita);
            if(!json.equals("vacio")){
                Type listype = new TypeToken<List<MisCitas>>(){}.getType();
                list = new Gson().fromJson(json, listype);
            }else {
                list = new ArrayList<>();
                list.add(0, new MisCitas("Vacio"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public MisCitas getCita(String idCita, String url) {
        List<MisCitas> misCitas = null;
        try {
            String json = readUrl(url);
            if (!json.isEmpty()){
                Type listype = new TypeToken<List<MisCitas>>(){}.getType();
                misCitas = new Gson().fromJson(json, listype);
            }else
                misCitas = new ArrayList<>();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return misCitas.get(0);
    }

    public List<MisCitas> getCitas(String url) {
        List<MisCitas> list = null;
        try {
            String json = readUrl(url);
            if (!json.isEmpty()){
                Type listype = new TypeToken<List<MisCitas>>(){}.getType();
                list = new Gson().fromJson(json, listype);
            }else
                list = new ArrayList<>();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public boolean disableDay(String day, String area, String user, String url){
        //disableday=06/10/2022&idareas=1&user=20082306&param=disable
        String json = readUrl(url+"?disableday="+day+"&idareas="+area+"&user="+user+"&param=disable");
        return Boolean.parseBoolean(json);
    }

}
