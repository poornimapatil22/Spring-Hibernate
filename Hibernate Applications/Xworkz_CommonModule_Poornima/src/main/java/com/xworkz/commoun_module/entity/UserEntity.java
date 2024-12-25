package com.xworkz.commoun_module.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;
@Entity
@NamedQuery(name="getUserByEmail",query="select u FROM UserEntity u WHERE u.email=:email")
@NamedQuery(name="getNameByEmailAndPassword" ,query="select e.name from UserEntity e where e.email = :byemail and e.password = :bypassword ")
@NamedQuery(name="countName" , query ="select count(*) from UserEntity e where e.name =:SetName")
@NamedQuery(name = "countEmail", query = "select count(*) from UserEntity e where e.email = :SetEmail")
@NamedQuery(name = "countAltEmail", query = "select count(e) from UserEntity e where e.altEmail = :SetAltEmail")
@NamedQuery(name = "countByPhone", query = "select count(*) from UserEntity e where e.phone = :SetPhone")
@NamedQuery(name = "countAltPhone", query = "select count(*) from UserEntity e where e.altPhone = :SetAltPhone")
@NamedQuery(name = "countLocation", query = "select count(*) from UserEntity e where e.location = :SetLocation")
//@NamedQuery(name = "UserEntity.updatePasswordByCount", query = "UPDATE UserEntity u SET u.password = :newPassword WHERE u.count = -1")
@Table(name="xworkz_module_table")

@Data
@RequiredArgsConstructor

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String location;
    private long altPhone;
    private String email;
    private String password;
    private long phone;
    private String altEmail;
    private Integer count ;
}


