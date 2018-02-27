package com.wagawin.crazyfamilies.model.dto;

import com.wagawin.crazyfamilies.model.House;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HouseDto {

    private long id;

    private PersonDto person;

    private String address;

    private String zipCode;

    private House.HouseType houseType;

}
