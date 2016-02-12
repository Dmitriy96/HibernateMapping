package dao;

import models.Teacher;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;
import java.util.List;


public class TeacherDao extends BaseDao {

    public Collection<Teacher> list() {
        beginTransaction();
        try{
            List<Teacher> result = getSession().createQuery("FROM Teacher").list();
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public Collection<Teacher> list(int start, int size) {
        beginTransaction();
        try{
            List<Teacher> result = getSession().createQuery("FROM Teacher").setFirstResult(start).setMaxResults(size).list();
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public Collection<Teacher> list(String firstName) {
        beginTransaction();
        try{
            List<Teacher> result = getSession().createCriteria(Teacher.class).add(Restrictions.eq("firstName", firstName)).list();
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
            Long result = (Long) getSession().createQuery("select count(*) from Teacher").uniqueResult();
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public Teacher findById(Long id) {
        beginTransaction();
        try{
            Teacher result = (Teacher) getSession().get(Teacher.class, id);
            commitTransaction();

            return result;
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public Teacher fullFindById(Long id) {
        beginTransaction();
        try{
            Query query = getSession().createQuery("FROM Teacher t left join fetch t.groupList where t.id=:id");
            query.setParameter("id", id);

            Teacher result = (Teacher) query.uniqueResult();

            commitTransaction();

            return result;

        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public Teacher fullFind2ById(Long id) {
        beginTransaction();
        try{
            Query query = getSession().createQuery("FROM Teacher t left join fetch t.groupList left join fetch t.groupLectionList where t.id=:id");
            query.setParameter("id", id);

            Teacher result = (Teacher) query.uniqueResult();

            commitTransaction();

            return result;

        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }

    public void save(Teacher teacher) {
        beginTransaction();
        try{
            getSession().persist(teacher);

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
            getSession().createQuery("delete from Teacher where id=:id").setParameter("id", id).executeUpdate();

            commitTransaction();
        }catch (HibernateException e) {
            rollbackTransaction();

            throw new HibernateException(e);
        }finally {
            closeSession();
        }
    }
}
