package org.lamisplus.base.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonContactResponse {
    private Long id;
    private String mobilePhoneNumber;
    private String alternatePhoneNumber;
    private String email;
    private String zipCode;
    private String city;
    private String street;
    private String landmark;
    private String countryId;
    private String stateId;
    private String provinceId;
    private String personId;
}
