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

    Session session = null;

    public MyMoneyHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List getAllName(String table) {
        List<Banque> banqueList = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from "+table);
            banqueList =  q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return banqueList;
    }
}
