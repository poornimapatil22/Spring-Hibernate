package com.xworkz.commoun_module.entity;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@MappedSuperclass
public class AbstractAuditEntity implements Serializable {
    private String createdBy;
    private LocalDate createdOn=LocalDate.now();
    private String updateBy;
    private LocalDate updatedOn=LocalDate.now();
}
