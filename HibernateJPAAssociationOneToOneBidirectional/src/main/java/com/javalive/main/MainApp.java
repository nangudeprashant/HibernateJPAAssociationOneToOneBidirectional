package com.javalive.main;

import java.time.LocalDate;
import java.time.Month;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.javalive.entity.User;
import com.javalive.entity.UserDetail;
import com.javalive.util.HibernateUtil;

/**
 * @author javalive.com
 */
public class MainApp {
   public static void main(String[] args) {
      Session session = null;
      Transaction transaction = null;
      try {
         session = HibernateUtil.getSessionFactory().openSession();
         transaction = session.getTransaction();
         transaction.begin();

         User user = new User();
         user.setUsername("USR003");
         user.setPassword("mno@xyz.com");

         UserDetail userDetail = new UserDetail();
         userDetail.setFirstName("Manish");
         userDetail.setLastName("Singh");
         userDetail.setEmail("manish.singh@javalive.com");
         userDetail.setDob(LocalDate.of(1986, Month.MAY, 1));
         userDetail.setUser(user);

         user.setUserDetail(userDetail);

         session.persist(user);
         transaction.commit();

         System.out.println("User saved successfully");

      } catch (Exception e) {
         if (transaction != null) {
            System.out.println("Transaction is being rolled back.");
            transaction.rollback();
         }
         e.printStackTrace();
      } finally {
         if (session != null) {
            session.close();
         }
      }
      HibernateUtil.shutdown();
   }
}
