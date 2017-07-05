import Models.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Burak Köken on 5.7.2017.
 */
public class HibernateTest {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        String hql = "SELECT P.name, SUM(P.price)" +
                "FROM Product AS P " +
                "GROUP BY P.name";
        Query query = session.createQuery(hql);

        List resultList = query.list();
        System.out.println("result list size : " + resultList.size());

        for (Object object : resultList) {
            Object[] fields = (Object[]) object;
            System.out.println("name : " + fields[0]
                    + ", total price :  " +  fields[1]);
        }

    }
}
