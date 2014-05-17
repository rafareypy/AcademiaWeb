
package py.com.academia.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import py.com.academia.execao.ExceptionFormatoFechaString;


public class Util 
{
    
    private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    
    public static Date passaFechaStringToDate(String fecha) throws ExceptionFormatoFechaString
    {
        Date fechaDate = null ;
        
        try 
        {
            fechaDate = format.parse(fecha);
        }
        catch (Exception e) 
        {
            throw new ExceptionFormatoFechaString( "Erro.."+ fecha + "..Formato invalido !!!" );
        }
        
        
        return fechaDate ;
        
    }
    
    public static  Calendar passaFechaStringToCalendar(String data) throws ExceptionFormatoFechaString
    {
        
        Calendar c = null ;
        try {

            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

            c = Calendar.getInstance();

            c.setTime(formatoData.parse(data));

        }
        catch (Exception e) 
        {            
            c = null;
            throw new ExceptionFormatoFechaString( "Erro.."+ data + "..Formato invalido !!!" );
        }        
        return c ;
    }
    
}
