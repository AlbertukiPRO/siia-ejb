package mx.uatx.siia.citas.modelo.dao;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import mx.uatx.siia.citas.modelo.MisCitas;
import mx.uatx.siia.citas.modelo.areas.SiPaAreas;
import mx.uatx.siia.citas.modelo.areas.business.SiPaAreasConfiguraciones;
import mx.uatx.siia.serviciosUniversitarios.dto.AreasTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @PersistenceContext(name="SIIA")
    private EntityManager em;

    @Transactional
    public List<SiPaAreas> obtenerAreas(){
        String query = "SELECT * FROM SIIUAT.SICTAREAS";
        return em.createNativeQuery(query, SiPaAreas.class).getResultList();
    }

    @Transactional
    public List<String> obtenerFechasArea(String idArea){
        List<String> lista = null;
        try {
            String query = "SELECT s.fechaexcepcion as fechaexepcion FROM siexcepciones as s INNER JOIN sictareas as c ON c.idareacampus = s.idareareservada where s.idareareservada = $value and s.horaexepcion = 'all'";
            lista = em.createNativeQuery(query, String.class).getResultList();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return lista;
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

    /**
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

    public List<MisCitas> getEventos(String idCita, String url) {
        List<MisCitas> list = null;
        try {
            String json = readUrl(url+"?idarea="+idCita);
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

}
