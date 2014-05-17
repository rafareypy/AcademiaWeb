/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.academia.util;

import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class TesteEnviarMsj 
{

    public TesteEnviarMsj() 
    {
        super();
    }

    
    public void enviaEmail()
    {
        AcompanhaMB acompanhaMB = new AcompanhaMB();
        acompanhaMB.adiciona();
    }
    
    
    public static void main(String args[])
    {
    
        JOptionPane.showMessageDialog(null, "vamos enviar MSJ");
//        TesteEnviarMsj enviarMsj = new TesteEnviarMsj();
//        enviarMsj.enviaEmail();
        
        SendMail sendMail = new SendMail();

        sendMail.sendMail("rreynoud@gmail.com", "rreynoud@gmail.com", "corpo", "teste envio msj");
        
        JOptionPane.showMessageDialog(null, "Ok");
        
    }
    
}
