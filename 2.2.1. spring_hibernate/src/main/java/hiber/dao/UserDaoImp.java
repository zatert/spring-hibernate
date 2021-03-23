package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   public User extract(String model, int series){
      //String hql = "from Car c where c.model=:paramModel and c.series=:paramSeries";  // "+"'paramModel'"+" and c.series = "+"'paramSeries'"; //Car where model = :paramModel"; and series = 'paramSeries'";
      String hql = "from User u where u.car.model=:paramModel and u.car.series=:paramSeries";
      Query query = sessionFactory.getCurrentSession().createQuery(hql);
      query.setParameter("paramModel", model);
      query.setParameter("paramSeries", series);
      return (User)query.getSingleResult();
   }
}