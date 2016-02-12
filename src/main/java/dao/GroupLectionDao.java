package dao;

import models.GroupLection;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import java.util.Collection;
import java.util.List;


public class GroupLectionDao extends BaseDao {

    public Collection<GroupLection> list() {
        beginTransaction();
        try{
            List<GroupLection> result = getSession().createQuery("FROM GroupLection").list();
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public Collection<GroupLection> list(int start, int size) {
        beginTransaction();
        try{
            List<GroupLection> result = getSession().createQuery("FROM GroupLection").setFirstResult(start).setMaxResults(size).list();
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
            Long result = (Long) getSession().createQuery("select count(*) from GroupLection").uniqueResult();
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public GroupLection findById(Long id) {
        beginTransaction();
        try{
            GroupLection result = (GroupLection) getSession().get(GroupLection.class, id);
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public GroupLection fullFindById(Long id) {
        beginTransaction();
        try{
            Query query = getSession().createQuery("FROM GroupLection gl left join fetch gl.group where gl.id=:id");
            query.setParameter("id", id);

            GroupLection result = (GroupLection) query.uniqueResult();

            commitTransaction();

            return result;

        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public GroupLection fullFind2ById(Long id) {
        beginTransaction();
        try{
            Query query = getSession().createQuery("FROM GroupLection gl left join fetch gl.lection left join fetch gl.group where gl.id=:id");
            query.setParameter("id", id);

            GroupLection result = (GroupLection) query.uniqueResult();

            commitTransaction();

            return result;

        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public void save(GroupLection groupLection) {
        beginTransaction();
        try{
            getSession().persist(groupLection);

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
            getSession().createQuery("delete from GroupLection where id=:id").setParameter("id", id).executeUpdate();

            commitTransaction();
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }
}
