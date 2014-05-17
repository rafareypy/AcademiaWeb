
package py.com.academia.util;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.MessagingException;
import javax.naming.NamingException;

@Named(value = "acompanhaMB")
@ConversationScoped
public class AcompanhaMB implements Serializable 
{


    private String mensagem = "";
 
    public AcompanhaMB() {  }
 
 
    /**
     *  Envia o mensagem ao email do usuário para acompanhamento da cotação da moeda
     */
    public String adiciona() {
        
        
        try {
            EnviaEmail.enviaMensagem("rreynoud@gmail.com",
                    "Confirmação de acompanhamento de cotação",
                    "Caro " + "RAfael"
                    + ", você acaba de assinar o serviço para recebimento "
                    + "automático da cotação diária da moeda ");
        } catch (NamingException ex) {
            Logger.getLogger(AcompanhaMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(AcompanhaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setMensagem("Mensagem enviada para " + "rreynoud@gmail.com");
        return "acompanhar"; //listamoedas
    }

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
 




}
