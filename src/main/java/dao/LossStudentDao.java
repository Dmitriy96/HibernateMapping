package dao;

import models.LossStudent;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;
import java.util.List;


public class LossStudentDao extends BaseDao {

    public Collection<LossStudent> list() {
        beginTransaction();
        try{
            List<LossStudent> result = getSession().createQuery("FROM LossStudent").list();
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public Collection<LossStudent> list(int start, int size) {
        beginTransaction();
        try{
            List<LossStudent> result = getSession().createQuery("FROM LossStudent").setFirstResult(start).setMaxResults(size).list();
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
            Long result = (Long) getSession().createQuery("select count(*) from LossStudent").uniqueResult();
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public LossStudent findById(Long id) {
        beginTransaction();
        try{
            LossStudent result = (LossStudent) getSession().get(LossStudent.class, id);
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public LossStudent fullFindById(Long id) {
        beginTransaction();
        try{
            Query query = getSession().createQuery("FROM LossStudent ls left join fetch ls.student where ls.id=:id");
            query.setParameter("id", id);

            LossStudent result = (LossStudent) query.uniqueResult();

            commitTransaction();

            return result;

        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public LossStudent fullFind2ById(Long id) {
        beginTransaction();
        try{
            Query query = getSession().createQuery("FROM LossStudent ls left join fetch ls.student left join fetch ls.lection left join fetch ls.group where ls.id=:id");
            query.setParameter("id", id);

            LossStudent result = (LossStudent) query.uniqueResult();

            commitTransaction();

            return result;

        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public void save(LossStudent lossStudent) {
        beginTransaction();
        try{
            getSession().persist(lossStudent);

            commitTransaction();
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public void delete(Long id) {
        beginTransaction();
        try{
            getSession().createQuery("delete from LossStudent where id=:id").setParameter("id", id).executeUpdate();

            commitTransaction();
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }
}
