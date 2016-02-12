package dao;

import models.Group;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;
import java.util.List;


public class GroupDao extends BaseDao {


    public Collection<Group> list() {
        beginTransaction();
        try{
            List<Group> result = getSession().createQuery("FROM Group").list();
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public Collection<Group> list(int start, int size) {
        beginTransaction();
        try{
            List<Group> result = getSession().createQuery("FROM Group").setFirstResult(start).setMaxResults(size).list();
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public Collection<Group> list(String firstName) {
        beginTransaction();
        try{
            List<Group> result = getSession().createCriteria(Group.class).add(Restrictions.eq("name", firstName)).list();
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
            Long result = (Long) getSession().createQuery("select count(*) from Group").uniqueResult();
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public Group findById(Integer id) {
        beginTransaction();
        try{
            Group result = (Group) getSession().get(Group.class, id);
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public Group fullFindById(Integer id) {
        beginTransaction();
        try{
            Query query = getSession().createQuery("FROM Group g left join fetch g.studentList where g.id=:id");
            query.setParameter("id", id);

            Group result = (Group) query.uniqueResult();

            commitTransaction();

            return result;

        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public Group fullFind2ById(Integer id) {
        beginTransaction();
        try{
            Query query = getSession().createQuery("FROM Group g left join fetch g.studentList left join fetch g.groupLections where g.id=:id");
            query.setParameter("id", id);

            Group result = (Group) query.uniqueResult();

            commitTransaction();

            return result;

        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public void save(Group group) {
        beginTransaction();
        try{
            getSession().save(group);

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
            getSession().createQuery("delete from Group where id=:id").setParameter("id", id).executeUpdate();

            commitTransaction();
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }
}
