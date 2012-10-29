/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moa.mymoney.hmapping;

import java.util.List;
import moa.tools.data.hybernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author IronMan
 */
public class MyMoneyHelper {

    public Banque getBanque(String node) {
        Banque bk = null;
        try {
            Session session=HibernateUtil.openSession();
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Banque as banque where banque.name = '"+node + "'");
            List<Banque> bankList =  q.list();
            if (bankList.isEmpty()){
                throw new Exception ("Banque " + node + " not found."); 
            }else{
                if (bankList.size()>1){
                    throw new Exception ("Please refine your search criteria. " + node +" return " + bankList.size() +" values");
                }else{
                    bk=bankList.get(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bk;
    }

    //Session session = null;

    public MyMoneyHelper() {
        //this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    

    public List getAllName(String table) {
        List<Banque> banqueList = null;
        try {
            Session session=HibernateUtil.openSession();
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from "+table);
            banqueList =  q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return banqueList;
    }

    public List<Compte> getCompteByBank(int id) {
        List<Compte> compteList = null;
        try {
            Session session=HibernateUtil.openSession();
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from from Compte as compte where compte.banque = "+id );
            compteList =  q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return compteList;
    }

    public void updateBank(Banque bk) {
          Session session=HibernateUtil.openSession(); 
          session.beginTransaction();
          session.update(bk);
          session.getTransaction().commit();
          session.close();    
    }
}
