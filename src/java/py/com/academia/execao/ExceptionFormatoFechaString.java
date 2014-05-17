/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.academia.execao;

/**
 *
 * @author rafael
 */
public class ExceptionFormatoFechaString extends Exception
{

    private static final long serialVersionUID = 2936768031552325716L;

    private static final String msjpadrao = "Formato de Fecha passado no es Valido dd/MM/yyyy";
    
	public ExceptionFormatoFechaString() {
		super();
		msjConsole("");
	}

	public ExceptionFormatoFechaString(String arg0, Throwable arg1) {
		super(""+ msjpadrao + "\n" +arg0, arg1);
		
	}

	public ExceptionFormatoFechaString(String arg0) {
		super(""+ msjpadrao + "\n" +arg0);
		
	}

	public ExceptionFormatoFechaString(Throwable arg0) {
		super(""+ msjpadrao + "\n",arg0);
		
	}

    private void msjConsole(String msj) 
    {
        System.out.println("  \n "+ msj);
    }    
    
}
