package mx.uatx.siia.citas.modelo.dao;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import mx.uatx.siia.serviciosUniversitarios.dto.AreasTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
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

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public AreasTO obtenerAreas(){
        AreasTO areasTO = new AreasTO("1","Servicios Escolares",1,1,1);

        //TODO ;


        return areasTO;
    }

    /**
     *
     * @param url => 'http:localhost/siiaservices/getareas.php'
     * @return List => AreasTO
     */
    public List<AreasTO> getAreasDAO(String url){
        List<AreasTO> listaAreas;
        String strJson = readUrl(url);
        if (!strJson.isEmpty()){
            System.out.println("----- Response from ["+url+"] => "+strJson);
            Type listType = new TypeToken<List<AreasTO>>(){}.getType();

            listaAreas = new Gson().fromJson(strJson,listType);

        }else{
            listaAreas = new ArrayList<>();
            listaAreas.add(new AreasTO("1", "No se logro obtener los datos"));
        }
        return listaAreas;
    }

    /**
     *
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
                lista = Arrays.asList(resultado.split(","));
            }else{
                lista = new ArrayList<>();
            }
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println("Finish Horarios Reservados => [value] = "+lista.toString());
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

        System.out.println("|----- Finish Horarios Reservados => [value] = "+ lista);
        return lista;
    }

}
