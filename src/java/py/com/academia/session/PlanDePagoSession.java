
package py.com.academia.session;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import py.com.academia.beans.Alumno;
import py.com.academia.beans.CuotaPlanDePago;
import py.com.academia.beans.PlanDePago;
import py.com.academia.beans.PlanDePagoItem;
import py.com.academia.beans.Servicio;
import py.com.academia.beans.enums.EstadoCuota;
import py.com.academia.ejb.BasicSessionBean;
import py.com.academia.execao.ExceptionParametroNulo;
import py.com.academia.execao.ExceptionCriarNovaCuota;
import py.com.academia.execao.ExceptionEliminarObjeto;


@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PlanDePagoSession extends BasicSessionBean 
{
   
    public PlanDePago getPlanDePagoById(int idPlanDePago)
    {
        PlanDePago dePago = getPojo(PlanDePago.class, idPlanDePago);
        getEm().refresh(dePago);
        return dePago;
    }
    
    public List<PlanDePago> getAllPlanDePagos()
    {
        return getList(PlanDePago.class ,
                " select al from PlanDePago al ");
    }


    public List<PlanDePago> getPlanDePagosPorAlumno(Alumno alumno)
    {        
        
        
                return getList(PlanDePago.class, " "
                + " select planDePago from PlanDePago planDePago where  "
                + "  planDePago.alumno = ?1 " 
                , alumno);                                                
    }
    
    
    public PlanDePago savePlanDePago(PlanDePago us)
    {    
        getEm().persist(us);        
        return us ;        
    }
    
    
    public List<CuotaPlanDePago> getListaCuotaPlanDePagoPorEstadoYFecha( Calendar inicio 
                                            , Calendar fin, EstadoCuota estadoCuota)
    {        
        
        StringBuilder hql = new StringBuilder(" select cuotaPlanDePago from CuotaPlanDePago cuotaPlanDePago ");
        hql.append(" where cuotaPlanDePago.fechaPago >= ?1 ")
        .append(" and  cuotaPlanDePago.fechaPago <= ?2 ")
        .append(" and  cuotaPlanDePago.estadoCuota = ?3 ")
        .append(" order by cuotaPlanDePago.fechaPago desc ");                        
        
        return getList(CuotaPlanDePago.class,String.valueOf(hql)
                , inicio.getTime(), fin.getTime(), estadoCuota);
        
    } 
    
    public CuotaPlanDePago efetuarPagoCuotaPlanDePago(CuotaPlanDePago cuotaPlanDePago)
    {
        if(cuotaPlanDePago == null)
        {            
            return null;
        }
        else
        {
            cuotaPlanDePago.setEstadoCuota(EstadoCuota.CANCELADO);
            cuotaPlanDePago.setFechaPago(new Date());
            return getEm().merge(cuotaPlanDePago);            
        }            
    }
    
    private CuotaPlanDePago saveCuotaPlanDePago( PlanDePago planDePago) 
    {
        Integer idPlanDePago = planDePago.getId() ;
        
        PlanDePago dePago = getPlanDePagoById(idPlanDePago) ;
//        dePago.addCuota(cuotaPlanDePago);
                
        getEm().refresh(dePago);
        
        CuotaPlanDePago cp = new CuotaPlanDePago();
        cp.setEstadoCuota(EstadoCuota.PENDIENTE);
        cp.setFechaPago(null);
        cp.setFechaVencimiento(new Date());
        cp.setPlanDePago(dePago);
        cp.setValor(dePago.getValorFinal());
        
        try 
        {
            getEm().persist(cp);
        }
        catch (javax.validation.ConstraintViolationException e) 
        {
            e.printStackTrace();
            e.getConstraintViolations();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        return cp ;
        
    }
    
    
    public PlanDePago setPlanDePago( PlanDePago us )
    {
        getEm().merge(us);
        return us ;
    }
    
    public void removePlanDePago(PlanDePago us)
    {        
        us = getEm().merge(us);
        getEm().remove(us);
    }
    
    public boolean removePlanDePago(int idPlanDePago)
    {
        
        
        boolean toReturn =  execute(
                " DELETE FROM PlanDePago us WHERE us.id  = ?1 "
                , idPlanDePago) >= 0 ;
        
        return toReturn ;
    }
    
    
//-------------Plan De Pago Item -----------------------------------------------
    
    public PlanDePago gePlanDePagoItemById(int idPlanDePago)
    {
        return getPojo(PlanDePago.class, idPlanDePago);
    }
    
    public List<PlanDePagoItem> getAllPlanDePagoItem()
    {
        return getList(PlanDePagoItem.class ,
                " select al from PlanDePagoItem al ");
    }
    
    public PlanDePagoItem savePlanDePagoItem(PlanDePagoItem planDePagoItem)
    {    
        
        if(planDePagoItem.getPlanDePago().getId() == null)
        {
                
                planDePagoItem.getPlanDePago().setValorFinal(100.0);
                
                
                CuotaPlanDePago cuotaPlanDePago = new CuotaPlanDePago();
                cuotaPlanDePago.setFechaPago(new Date());
                cuotaPlanDePago.setFechaVencimiento(new Date());
                cuotaPlanDePago.setPlanDePago(planDePagoItem.getPlanDePago());
                cuotaPlanDePago.setValor(planDePagoItem.getPlanDePago().getValorFinal());
            
                List<CuotaPlanDePago> lista = new ArrayList<CuotaPlanDePago>();
                lista.add(cuotaPlanDePago);
                
                planDePagoItem.getPlanDePago().setCuotasPlanPago(lista);
                getEm().persist(planDePagoItem.getPlanDePago());             
        }
                        
        getEm().persist(planDePagoItem);        
        return planDePagoItem ;        
    }
    
    public PlanDePagoItem setPlanDePagoItem( PlanDePagoItem us )
    {
        getEm().merge(us);
        return us ;
    }
    
    public void removePlanDePagoItem(PlanDePagoItem planDePagoItem) 
            throws ExceptionEliminarObjeto
    {        
 
        System.out.println("Vamos la...  ");
        System.out.println("Plan de pago   " + planDePagoItem );
        System.out.println("Plan de pago   " + planDePagoItem.getPlanDePago());
        System.out.println("Plan de pago   " + planDePagoItem.getPlanDePago().getId()) ;
        
        
        Integer idPlanPago = planDePagoItem.getPlanDePago().getId();
        
        try 
        {
            boolean toReturn =  execute(
                    " DELETE FROM PlanDePagoItem us WHERE us.id  = ?1 "
                    , planDePagoItem.getId()) >= 0 ;


            setPlanDePago(getPlanDePagoById(idPlanPago));
            
        }
        catch (Exception e) 
        {
            throw new ExceptionEliminarObjeto(""+PlanDePagoSession.class+""
                    + "removePlanDePagoItem  \n" , e);
            
        }
        
        
        
        
        
    }
    
    public boolean removePPlanDePagoItem(int idPlanDePagoItem)
    {
        
        
        boolean toReturn =  execute(
                " DELETE FROM PlanDePagoItem us WHERE us.id  = ?1 "
                , idPlanDePagoItem) >= 0 ;
        
        return toReturn ;
    }

    public List<Servicio> getAllServicios() 
    {
       return getList(Servicio.class, 
                " select al from Servicio al "
               +" order by al.descripcion ");
    }

    public List<PlanDePagoItem> getListPlanDePagoItemPorPlanDePagoID(PlanDePago dePago)
    {
        
       return getList(PlanDePagoItem.class, 
                " select planDePagoItem from PlanDePagoItem planDePagoItem " +
                " where planDePagoItem.planDePago = ?1 "
                , dePago);        
    }

    public List<CuotaPlanDePago> getListCuotaPlanDePagoAlumnoID(Alumno alumno)
    {        
       return getList(CuotaPlanDePago.class, 
                " select cuotaPlanDePago from CuotaPlanDePago cuotaPlanDePago " +
                " where cuotaPlanDePago.planDePago.alumno = ?1 "
                , alumno);        
    }

    public List<CuotaPlanDePago> getListCuotaPlanDePagoPendientesPorAlumnoID(Alumno alumno)
    {        
       return getList(CuotaPlanDePago.class, 
                " select cuotaPlanDePago from CuotaPlanDePago cuotaPlanDePago " +
                " where cuotaPlanDePago.planDePago.alumno = ?1 "
                , alumno);        
    }
    
    
     public List<CuotaPlanDePago> getListCuotaPlanDePago()
    {        
       return getList(CuotaPlanDePago.class, 
                " select cuotaPlanDePago from CuotaPlanDePago cuotaPlanDePago " +
                " order by cuotaPlanDePago.fechaVencimiento  " 
               );        
    }
     
@Schedule(second = "*" ,hour="12", minute = "5" , dayOfWeek = "*" )        
public void verificarCuotas()
{    
    cada10();
}    


@Schedule(second = "*" ,hour="7", minute = "5", dayOfWeek = "*" )     
public void verificarCuotas7()
{    
    cada10();
}    


@Schedule(second = "*" ,hour="20", minute = "5" , dayOfWeek = "*" )     
public void verificarCuotas20()
{    
    cada10();
}    


    public void cada10() 
    {           
        
        try 
        {            
            procederCuotas(obtnerListaPlanDePagoFecha(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
        }
        catch (ExceptionParametroNulo ex) 
        {            
            ex.printStackTrace();
        }
            
    }

    
    public void procederCuotas(List<PlanDePago> listaPlanPago)throws ExceptionParametroNulo
    {

        if (listaPlanPago == null)         
            throw new ExceptionParametroNulo(" Lista de Plan de Pago esta Nula "
                    + "\n Classe - PlanDePagoSession  \n"
                    + " Metodo - procederCuotas  ");
        


        for (PlanDePago planDePago : listaPlanPago) 
        {
            CuotaPlanDePago cpdp = obtnerCuotaPlanDePagoPorPlanPagoYMes(planDePago, Calendar.getInstance());

            if (cpdp == null) 
            {
                System.out.println("Plan de Pago  " + planDePago.getId()
                        + "\n No se econtro Cuota !!");
                /* Criaremos uma nova Cuota */
                CuotaPlanDePago dePago = new CuotaPlanDePago();
                try 
                {
                    dePago = criarNovaCuota(planDePago);
                }
                catch (ExceptionCriarNovaCuota ex) 
                {
                    Logger.getLogger(PlanDePagoSession.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
                System.out.println("***CuotaCriada id = " + dePago);
                enviaEmailCobrandoPrimeraCuota(dePago);

            } 
            else 
            {
                System.out.println("Plan de Pago  " + planDePago.getId()
                        + "\n ENCONTRO CUOTA - " + cpdp.getId());
                if (EstadoCuota.PENDIENTE.equals(cpdp.getEstadoCuota())) 
                {
                    enviaEmailCobrando(cpdp);
                    //Verificamos para aque envie email de cobranca toda quarta feira
//                    if (Calendar.getInstance().equals(Calendar.getInstance().get(Calendar.WEDNESDAY))) 
//                    {
//                    }

                }

            }
            
        }

    }
    
    public CuotaPlanDePago obtnerCuotaPlanDePagoPorPlanPagoYMes(
            PlanDePago planDePago, Calendar fecha)throws ExceptionParametroNulo
    {
        if(planDePago == null || planDePago.getId() == null)
            throw new ExceptionParametroNulo("PlanDePagoNulo o ID \n" 
                    +PlanDePagoSession.class.getName() +" \nMetodo "
                    + "obtnerCuotaPlanDePagoPorPlanPagoYMes " );        
        try 
        {
            return getList(CuotaPlanDePago.class, 
                        " select cuotaPlanDePago  from  CuotaPlanDePago cuotaPlanDePago  "
                        + " where cuotaPlanDePago.fechaVencimiento <=  ?1 "
                        + " and cuotaPlanDePago.planDePago = ?2 "                
                    , fecha.getTime(), planDePago).get(0);                  
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e) 
        {
            return null ;
        }
        
    }        
        
private List<PlanDePago> obtnerListaPlanDePagoFecha(int diaDoMes ) 
    {
        
        List<PlanDePago> lista = null ;
        
            lista = getList(PlanDePago.class ,
                        " select al from PlanDePago al "
                      + " where al.diaDoMesVenc <= ?1 ", diaDoMes);
        
            if(lista != null)            
                System.out.println("Tamanho Lista PlanDeoPago " + lista.size() );
            
            return lista ;
            
        
    }        



    private CuotaPlanDePago criarNovaCuota(PlanDePago planDePago) throws ExceptionCriarNovaCuota
    {
        
        CuotaPlanDePago resultado =  saveCuotaPlanDePago(planDePago);
       
        if(resultado == null || resultado.getId() == null )
            throw new ExceptionCriarNovaCuota("Problemas al crear una nueva Cuota  Classe - PlanDePagoSession \n"
                    + " metodo - criarNovaCuota  \n Complemento"
                    + " Para la PlanDePago Id =  " + planDePago.getId());
              
        System.out.println("Criamos nNueva cuota "  + resultado.getId());
        
       return resultado ;
        
    }

    private void enviaEmailCobrando(CuotaPlanDePago cpdp) 
    {
        StringBuilder msj = new StringBuilder(" Senor ");
        msj.append(cpdp.getPlanDePago().getAlumno().getNombre());
        msj.append(" ")
        .append(cpdp.getPlanDePago().getAlumno().getApelido())
        .append(" \nEsta Devendo la Cuota del mes ")
        .append(cpdp.getFechaVencimiento().toString())
        .append("\nCodigo ").append(cpdp.getId())
        .append("\nEn caso que ya haya pagado desconsidere este email ");
        
        
        System.out.println("Email de Cobranca Enviado !!!\n" + String.valueOf(msj));
    }

    private void enviaEmailCobrandoPrimeraCuota(CuotaPlanDePago cpdp)
    {
        StringBuilder msj = new StringBuilder(" Senor ");
        msj.append(cpdp.getPlanDePago().getAlumno().getNombre());
        msj.append(" ")
        .append(cpdp.getPlanDePago().getAlumno().getApelido())
        .append(" \nLes Comunicamos que vencio la Factura del Mes... ")
        .append(cpdp.getFechaVencimiento().toString())
        .append("\nCodigo ").append(cpdp.getId())
        .append("\nEn caso que ya haya pagado desconsidere este email ");
        
        
        System.out.println("Email de Cobranca Enviado !!!\n" + String.valueOf(msj));    
    }

    public CuotaPlanDePago ReanudarPagoCuotaPlanDePago(CuotaPlanDePago cuotaPlanDePago) 
    {    
        if(cuotaPlanDePago == null)
        {            
            return null;
        }
        else
        {
            cuotaPlanDePago.setEstadoCuota(EstadoCuota.PENDIENTE);
            cuotaPlanDePago.setFechaPago(null);
            return getEm().merge(cuotaPlanDePago);            
        }            
        
    }

    public PlanDePagoItem getPlanDePagoItemByID(Integer idPlanDePagoItem) 
    {
        
       return getPojo(PlanDePagoItem.class, idPlanDePagoItem);
        
    }

   
    
}
