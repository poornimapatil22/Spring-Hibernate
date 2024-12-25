package com.xworkz.commoun_module.Repository;

import com.xworkz.commoun_module.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.mail.*;
import javax.mail.internet.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Properties;

@Repository
public class UserRepoImpl  implements UserRepo{
    @Autowired
    EntityManagerFactory emf;

    @Override
    public boolean save(UserEntity userEntity) {
        System.out.println("Running on a save in a repoimpl");
        System.out.println("data in repoImpl:"+userEntity.toString());
        EntityManager em=emf.createEntityManager();
        EntityTransaction et=em.getTransaction();
        try{
            et.begin();
            em.merge(userEntity);
            et.commit();
        }
        catch(Exception e)
        {
            if(et.isActive())
            {
                et.rollback();
            }
        }
        finally {
            em.close();
            //` emf.close();
        }
        return false;
    }

    @Override
    public Long countName(String name) {
        EntityManager em=emf.createEntityManager();
        EntityTransaction et=em.getTransaction();
        Long count= (Long)em.createNamedQuery("countName").setParameter("SetName",name).getSingleResult();
        try{
            et.begin();
            et.commit();
        }
        catch(Exception e)
        {
            if(et.isActive())
            {
                et.rollback();
            }
        }
        finally {
            em.close();
            //` emf.close();
        }
        return count;
    }



    @Override
    public Long countByLocation(String location) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et=em.getTransaction();
        Long count = 0L;
        try {
            et.begin();

            count = (Long) em.createNamedQuery("countLocation")
                    .setParameter("SetLocation", location)
                    .getSingleResult();
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return count;
    }

    @Override
    public Long countByAltPhone(long altPhone) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et=em.getTransaction();
        Long count = 0L;
        try {
            et.begin();
            count = (Long) em.createNamedQuery("countAltPhone")
                    .setParameter("SetAltPhone", altPhone)
                    .getSingleResult();
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return count;
    }

    @Override
    public Long countByPhone(long phone) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et=em.getTransaction();
        Long count = 0L;
        try {
            et.begin();
            count = (Long) em.createNamedQuery("countByPhone")
                    .setParameter("SetPhone", phone)
                    .getSingleResult();
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return count;
    }
    @Override
    public Long countAltEmail(String altEmail) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();
        Long count = 0L;
        try {
            entityTransaction.begin();

            count = (Long) em.createNamedQuery("countAltEmail")
                    .setParameter("SetAltEmail", altEmail)
                    .getSingleResult();

            entityTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        } finally {
            em.close();
        }
        return count;
    }

    @Override
    public String getNameByEmailAndPassword(String email, String password) {
        EntityManager entityManager=emf.createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        String name=null;
        try{
            entityTransaction.begin();
            Query query=entityManager.createNamedQuery("getNameByEmailAndPassword");
            query.setParameter("byemail",email).setParameter("bypassword",password);
            name=(String)query.getSingleResult();
            entityTransaction.commit();
        } catch (Exception e) {
            if(entityTransaction.isActive()){
                entityTransaction.rollback();
            }
        }
        finally {
            entityManager.close();
        }
        return name;
    }

    @Override
    public Long countByEmail(String email) {
        EntityManager em =emf.createEntityManager();
        EntityTransaction entityTransaction=em.getTransaction();
        Long count = 0L;
        try {
            entityTransaction.begin();
            count = (Long) em.createNamedQuery("countEmail")
                    .setParameter("SetEmail", email)
                    .getSingleResult();
            entityTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return count;
    }


    public boolean saveEmail(String email, String password) {
        final String username = "lendeshreya@gmail.com";
        final String userPassword = "bnehxnddhtkaghbj";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, userPassword);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Your password");
            message.setText("Your password is: " + password);

            Transport.send(message);
            System.out.println("Email sent successfully to: " + email);
            return true;
        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }




    @Override
    public UserEntity getUserByEmail(String email) {
        EntityManager em =emf.createEntityManager();
        EntityTransaction entityTransaction=em.getTransaction();

        UserEntity name=null;
        try {

            entityTransaction.begin();
            Query query = em.createNamedQuery("getUserByEmail");
            query.setParameter("email", email);
             name = (UserEntity)query.getSingleResult();

            entityTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return name;
    }

}
