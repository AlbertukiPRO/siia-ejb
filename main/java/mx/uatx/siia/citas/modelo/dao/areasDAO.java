package mx.uatx.siia.citas.modelo.dao;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import mx.uatx.siia.citas.modelo.MisCitas;
import mx.uatx.siia.citas.modelo.areas.SiPaAreas;
import mx.uatx.siia.citas.modelo.areas.business.SiPaAreasConfiguraciones;
import mx.uatx.siia.serviciosUniversitarios.dto.AreasTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.Executable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static mx.uatx.siia.citas.modelo.citasBusiness.MethodsGenerics.readUrl;


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
    public List<SiPaAreas> obtenerAreas(){
                                                                                    // %Control% => parmatro de prueba;
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
            query.setParameter(1, ""+params[0]);
            query.setParameter(2, ""+params[1]);
            lista = query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage()+"\n"+e.getCause());
        }
        return lista;
    }


    /**
     * @deprecated no usar
     * @param link => url del servicio [String] => http:
     * @param fecha => fecha [String] => 19/05/2022
     * @param idArea => idArea [String] => 1
     * @return List => [String] con los horarios => 08:00, 08:25
     */
    public List<String> getHorarioFromDB(String link, String fecha, String idArea){

        List<String> lista;
        try {
            String resultado = readUrl(link+"?fecha="+fecha+"&idarea="+idArea);

            if (!resultado.equals("0")){
                lista = Arrays.asList(resultado.split(","));
            }else{
                lista = Collections.emptyList();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<SiPaAreasConfiguraciones> getSettings(String idArea, String api){
        List<SiPaAreasConfiguraciones> list = null;
        try {
            String json = readUrl(api+"?coomon=settings&idarea="+idArea);
            if (!json.isEmpty()){
                Type listType = new TypeToken<List<SiPaAreasConfiguraciones>>(){}.getType();

                list = new Gson().fromJson(json,listType);
            }else list = new ArrayList<>();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<SiPaAreasConfiguraciones> getConfig(String idArea){
        List<SiPaAreasConfiguraciones> list = null;
        try {
            String query = "SELECT * FROM SIIUAT.SIAXCONFIGURACIONES WHERE IDAREA = "+idArea;
            list = em.createNativeQuery(query, SiPaAreasConfiguraciones.class).getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<MisCitas> getEventos(String idCita, String url) {
        List<MisCitas> list = null;
        try {
            String json = readUrl(url+"?idarea="+idCita);
            if(!json.equals("vacio")){
                if (!json.isEmpty()){
                    Type listype = new TypeToken<List<MisCitas>>(){}.getType();
                    list = new Gson().fromJson(json, listype);
                }else
                    list = new ArrayList<>();
            }else list = new ArrayList<>();
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
