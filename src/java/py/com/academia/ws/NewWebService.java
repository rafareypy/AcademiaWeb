/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.academia.ws;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rafael
 */
@WebService(serviceName = "NewWebService")
public class NewWebService {

    
    
    
    @PersistenceContext        
    private EntityManager em ;
    
        
    
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
}
