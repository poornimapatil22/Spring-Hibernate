package com.xworkz.commoun_module.Repository;

import com.xworkz.commoun_module.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

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
            em.persist(userEntity);
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
}
