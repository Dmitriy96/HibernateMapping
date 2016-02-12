package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.LoggerFactory;

public class BaseDao {
    protected org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private static final ThreadLocal session = new ThreadLocal();

    private static final Configuration configuration = new Configuration().configure();
    private static final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
    private static final SessionFactory sessionFactory =  configuration.buildSessionFactory(builder.build());

    protected BaseDao() {
    }

    public static Session getSession() {
        Session session = (Session) BaseDao.session.get();
        if (session == null) {
            session = sessionFactory.openSession();
            BaseDao.session.set(session);
        }
        return session;
    }

    protected void beginTransaction() {
        getSession().beginTransaction();
    }

    protected void commitTransaction() {
        getSession().getTransaction().commit();
    }

    protected void rollbackTransaction() {
        try {
            getSession().getTransaction().rollback();
        } catch (HibernateException e) {
            log.warn("Cannot rollback", e);
        }
        try {
            getSession().close();
        } catch (HibernateException e) {
            log.warn("Cannot close", e);
        }
        BaseDao.session.set(null);
    }

    public static void closeSession() {
        getSession().close();
        BaseDao.session.set(null);
    }
}
