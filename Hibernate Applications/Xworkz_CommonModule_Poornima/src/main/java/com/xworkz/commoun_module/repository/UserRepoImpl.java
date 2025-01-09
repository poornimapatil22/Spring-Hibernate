package com.xworkz.commoun_module.repository;

import com.xworkz.commoun_module.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.mail.*;
import javax.mail.internet.*;
import javax.persistence.*;
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
        final String username = "";
        final String userPassword = "xcogkgdpdyvrcicy";

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
    public UserEntity updateUserEntity(String email, String name, String location, Long altPhone, Long phone, String altEmail) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();
        UserEntity userEntity = null;

        try {
            entityTransaction.begin();

            // Execute the update query
            Query query = em.createNamedQuery("updateUserEntity");
            query.setParameter("email", email);
            query.setParameter("name", name);
            query.setParameter("location", location);
            query.setParameter("altPhone", altPhone);
            query.setParameter("phone", phone);
            query.setParameter("altEmail", altEmail);


            // Execute the update and get the number of updated rows
            int rowsUpdated = query.executeUpdate();

            // If update is successful, retrieve the updated user using email
            if (rowsUpdated > 0) {
                // Use a query to fetch the updated user based on email (instead of find)
                TypedQuery<UserEntity> userQuery = em.createQuery(
                        "SELECT u FROM UserEntity u WHERE u.email = :email", UserEntity.class);
                userQuery.setParameter("email", email);
                userEntity = userQuery.getSingleResult();
               // userEntity = em.find(UserEntity.class, email);
            }

            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }

        return userEntity;
    }

    @Override
    public String forgotPassword(String email, String password) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            // Using a named query to update password based on email
            Query query = entityManager.createNamedQuery("forgotPassword");
            query.setParameter("email", email);
            query.setParameter("password", password);

            int rowsUpdated = query.executeUpdate();  // This executes the update query

            if (rowsUpdated > 0) {
                entityTransaction.commit();  // Commit transaction if rows are updated
                return email;  // Return email to indicate success
            } else {
                entityTransaction.rollback();  // Rollback if no rows were updated
                return null;  // Indicate failure
            }

        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();  // Rollback on exception
            }
            e.printStackTrace();
            return null;  // Indicate failure
        } finally {
            entityManager.close();  // Close the entity manager
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
