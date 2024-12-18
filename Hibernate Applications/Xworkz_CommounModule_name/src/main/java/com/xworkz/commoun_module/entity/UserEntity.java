package com.xworkz.commoun_module.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;
@Entity
@Table(name="xworkz_module_table")
@Data
@RequiredArgsConstructor
//@NamedQuery(name="getNameByEmailAndPassword" ,query="select ce.name from CourseRegisterEntity ce where ce.email =:byEmail and ce.password =:byPassword")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String location;
    private long altPhone;
    private String email;
    private Integer password;
    private long phone;
    private String alterEmail;
}


