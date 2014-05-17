
package py.com.academia.util;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class EnviaEmail 
{

    
    
  public static void enviaMensagem(String emailEndereco, String assunto, String mensagem)
    throws NamingException, MessagingException {
        EnviaEmail email = new EnviaEmail();
        email.sendMail(emailEndereco, assunto, mensagem);
    }
 
    private Session getMailgMailSessao() throws NamingException {
        Context c = new InitialContext();
        return (Session) c.lookup("mail/gMailSessao"); //corrija retirando "java:comp/env/"
    }
 
    private void sendMail(String email, String subject, String body)
        throws NamingException, MessagingException {
        Session mailgMailSessao = getMailgMailSessao();
        MimeMessage message = new MimeMessage(mailgMailSessao);
        message.setSubject(subject);
        message.setRecipients(RecipientType.TO, InternetAddress.parse(email, false));
        message.setText(body);
        Transport.send(message);
    }



    
}
