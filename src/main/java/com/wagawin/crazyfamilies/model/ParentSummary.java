package com.wagawin.crazyfamilies.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ParentSummary {

    @Id
    private long childCount;

    @Column
    private long personCount;

}
