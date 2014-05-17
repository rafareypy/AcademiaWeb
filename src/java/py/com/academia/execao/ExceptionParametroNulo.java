/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.academia.execao;

/**
 *
 * @author rafael
 */
public class ExceptionParametroNulo extends Exception
{

    private static final long serialVersionUID = 2936768031552325716L;

    private static final String msjpadrao = "Parametro Nulo Exception";
    
	public ExceptionParametroNulo() {
		super();
		msjConsole("");
	}

	public ExceptionParametroNulo(String arg0, Throwable arg1) {
		super(""+ msjpadrao + "\n" +arg0, arg1);
		
	}

	public ExceptionParametroNulo(String arg0) {
		super(""+ msjpadrao + "\n" +arg0);
		
	}

	public ExceptionParametroNulo(Throwable arg0) {
		super(""+ msjpadrao + "\n",arg0);
		
	}

    private void msjConsole(String msj) 
    {
        System.out.println("  \n "+ msj);
    }    
    
}
