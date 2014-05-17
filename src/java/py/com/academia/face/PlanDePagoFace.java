package py.com.academia.face;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import py.com.academia.beans.PlanDePago;
import py.com.academia.session.PlanDePagoSession;




@Named
@SessionScoped  //@RequestScoped verificar
public class PlanDePagoFace extends BaseCDIBean<PlanDePago> {

    private static final long serialVersionUID = 7L;
    @Inject
    private PlanDePagoSession bean;
    private List<PlanDePago> list;

    public PlanDePagoFace() 
    {
        setSelectedBean(new PlanDePago());
    }

    public String doEditPlanDePago()
    {
        return "/admin/plan_de_pago/edit.faces";
    }

    public String doFinishEditPlanDePago() 
    {
        bean.setPlanDePago(getSelectedBean());
        return doListPlanDePago();
    }
    
    public String doGeneraCuotas()
    {
        bean.cada10();
        return doIndice();
    }
    
    public String doRemovePlanDePago() 
    {
        bean.removePlanDePago(getSelectedBean());
        return doListPlanDePago();
    }

    public String doCreatePlanDePago() {
        setSelectedBean(new PlanDePago());
        
        return "/admin/plan_de_pago/add.faces";
    }

    public String doFinishCreatePlanDePago() {
        bean.savePlanDePago(getSelectedBean());
        return doListPlanDePago();
    }

    public String doListPlanDePago() {
        list = bean.getAllPlanDePagos();
        return "/admin/plan_de_pago/list.faces";
    }

    public List<PlanDePago> getListPlanDePago() 
    {
        return bean.getAllPlanDePagos();
    }
    
    
    public List<PlanDePago> getList() {
        return list;
    }

    public void setList(List<PlanDePago> list) {
        this.list = list;
    }

    private String doIndice() 
    {
        return "main.faces";    
    }
    

    
}
