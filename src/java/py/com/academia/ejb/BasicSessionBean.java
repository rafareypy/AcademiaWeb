/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.academia.ejb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrador
 */
public abstract class BasicSessionBean implements java.io.Serializable {

    public final static boolean debug = false;
    private static final long serialVersionUID = 2L;

    @PersistenceContext
    private EntityManager em;

    public BasicSessionBean() {
    }

    public EntityManager getEm() {
        return em;
    }

    public <T> List<T> getList(Class<T> classToCast,String query,Object... values) {
        Query qr = createQuery(query, values);
        return qr.getResultList();
    }

    
    public <T> List<T> getListPorFecha(Class<T> classToCast,String query, Date inicio, Date fin ) 
    {
        
        String hql = " select cuotaPlanDePago  from  CuotaPlanDePago cuotaPlanDePago  "
                 + " where cuotaPlanDePago.fechaVencimiento between  ?1 and ?2 ";
        
        Query qr = em.createQuery(hql);
        
        qr.setParameter("data1 ", new Date() );   
        qr.setParameter("data2", new Date());   
        
        return qr.getResultList();

//     Query query = em.createQuery("from Classe as o where data between :data1 and :data2");
//     query.setParameter("data1 ", data1 );   
//     query.setParameter("data2", data2);   
//     return (List<Classe>)query.getResultList();        
        
        
    }
    
    
    public <T> List<T> getLimitedList(Class<T> classToCast,String query,int limit,Object... values) {
        Query qr = createQuery(query, values);
        qr.setMaxResults(limit);
        return qr.getResultList();
    }

    public <T> List<T> getNamedList(Class<T> classToCast,String namedQuery,Object... values) {
        Query qr = em.createNamedQuery(namedQuery);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                Object object = values[i];
                qr.setParameter(i + 1, object);
            }
        }
        return qr.getResultList();
    }

    public <T> T getPojo(Class<T> classToCast,String query,Object... values) {
        Query qr = createQuery(query, values);
        return (T) qr.getSingleResult();
    }

    public <T> T getPojo(Class<T> classToCast,Serializable primaryKey) {
        T objeto = em.find(classToCast, primaryKey);
        em.refresh(objeto);
        return objeto ;
    }


    public int execute(String query,Object... values) {
        Query qr = createQuery(query, values);
        return qr.executeUpdate();
    }


    private Query createQuery(String query, Object[] values) {
        Query qr = em.createQuery(query);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                Object object = values[i];
                qr.setParameter(i + 1, object);
            }
        }
        return qr;
    }


}
