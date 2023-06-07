package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user, Car car) {
      user.setCarId(car);
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @Transactional
   public User usersOwners(String model, String series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession()
              .createQuery("FROM User user LEFT OUTER JOIN FETCH user.carId WHERE user.carId.model = :mod AND user.carId.series = :ser");
      query.setParameter("mod", model);
      query.setParameter("ser", series);
      return query.getSingleResult();
   }
}
