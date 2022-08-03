package mx.uatx.siia.citas.modelo.citasBusiness;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MethodsGenerics {

    public static String readUrl(String urlString) {
        System.out.println("*******************************************************");
        System.out.println("----- Calling client http.get() => [" + urlString + "]");
        System.out.println("*******************************************************");
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            System.out.println("|----- Finished http.get() @return => " + buffer);

            return buffer.toString();
        } catch (IOException e) {
            System.out.println("----------------- THROW EXCEPTION ------------- " + e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return "";
    }

    public static String removeLastChar(String s) {
        return (s == null || s.length() == 0)
                ? null
                : (s.substring(0, s.length() - 1));
    }

    public static List<String> generarHorarios(int horaInicio, int HoraFin, int DuracionCitas, List<String> horariosReservados){

        List<String> listHorarios = new ArrayList<String>();

        int hora = horaInicio;
        int minuto = 0;
        int residuo = 0;
        while (hora<HoraFin){
            if ( minuto < 59 && hora != HoraFin){
                String item = formatHora(Integer.toString(hora))+":"+formatHora(Integer.toString(minuto));
                listHorarios.add(item);
            }
            if(minuto<=60){
                minuto+=DuracionCitas;
            }else{
                residuo=minuto-60;
                minuto=residuo;
                hora+=1;
            }
        }

        if (!(horariosReservados.size() == 0)){
            horariosReservados.stream().forEach((item)->{
                for (int i = 0; i < listHorarios.size(); i++) {
                    if (item.equals(listHorarios.get(i))){
                        listHorarios.remove(i);
                    }
                }
            });
        }

        return listHorarios;
    }

    public static String formatHora(String number){
        return number.length() == 1 ? "0"+number : number;
    }

    public static String formattingStringFechasCalendar(List<String> horarios){
        StringBuilder cadena = new StringBuilder();

        for (String item: horarios){
            cadena.append("'").append(item).append("',");
        }
        return removeLastChar(String.valueOf(cadena));
    }

    public static String getCurrentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String formatDate(String fecha){
        java.util.Date date = new Date(fecha);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(date);
    }
}
