
package py.com.academia.envio.email;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EnvioEmail 
{

    public EnvioEmail() 
    {
        super();
    }

    
    public void enviarEmail()
    {
    
        Properties p = new Properties();
        p.put("mail.host", "smtp2.locaweb.com.br");
        
        
        Session session = Session.getInstance(p, null);
        MimeMessage msg = new MimeMessage(session);
        
        try {
                // "de" e "para"!!
                msg.setFrom(new InternetAddress("servermail.hg-corporation.com"));
                msg.setRecipient(Message.RecipientType.TO, 
                        new InternetAddress("rreynoud@gmail.com"));

                // nao esqueca da data!
                // ou ira 31/12/1969 !!!
                msg.setSentDate(new Date());

                msg.setSubject("assunto da mensagem");

                msg.setText("corpo da mensagem");

                // evniando mensagem (tentando)
                Transport.send(msg);
        }
        catch (AddressException e) {
                // nunca deixe catches vazios!
        }
        catch (MessagingException e) {
                // nunca deixe catches vazios!
        }
        
        
        
    }
    
    
}
