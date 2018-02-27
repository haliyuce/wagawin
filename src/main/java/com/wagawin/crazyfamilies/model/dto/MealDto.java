package com.wagawin.crazyfamilies.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class MealDto {

    private long id;

    private String name;

    private Date invented;

}
