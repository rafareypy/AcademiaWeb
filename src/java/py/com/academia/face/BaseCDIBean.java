/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.academia.face;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



public abstract class BaseCDIBean<T> implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private T selectedBean;
    @PersistenceContext
    protected EntityManager em;

    public T getSelectedBean() {
        return selectedBean;
    }

    public void setSelectedBean(T selectedBean) {
        this.selectedBean = selectedBean;
    }
    

   
    

}
