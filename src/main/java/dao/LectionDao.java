package dao;

import models.Lection;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;
import java.util.List;


public class LectionDao extends BaseDao {


    public Collection<Lection> list() {
        beginTransaction();
        try{
            List<Lection> result = getSession().createQuery("FROM Lection").list();
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public Collection<Lection> list(int start, int size) {
        beginTransaction();
        try{
            List<Lection> result = getSession().createQuery("FROM Lection").setFirstResult(start).setMaxResults(size).list();
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public Collection<Lection> list(String firstName) {
        beginTransaction();
        try{
            List<Lection> result = getSession().createCriteria(Lection.class).add(Restrictions.eq("name", firstName)).list();
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public Long getCount() {
        beginTransaction();
        try{
            Long result = (Long) getSession().createQuery("select count(*) from Lection").uniqueResult();
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public Lection findById(Integer id) {
        beginTransaction();
        try{
            Lection result = (Lection) getSession().get(Lection.class, id);
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public Lection fullFindById(Integer id) {
        beginTransaction();
        try{
            Query query = getSession().createQuery("FROM Lection l left join fetch l.groupLections where l.id=:id");
            query.setParameter("id", id);

            Lection result = (Lection) query.uniqueResult();

            commitTransaction();

            return result;

        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public void save(Lection lection) {
        beginTransaction();
        try{
            getSession().save(lection);

            commitTransaction();
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public void delete(Integer id) {
        beginTransaction();
        try{
            getSession().createQuery("delete from Lection where id=:id").setParameter("id", id).executeUpdate();

            commitTransaction();
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }
}
