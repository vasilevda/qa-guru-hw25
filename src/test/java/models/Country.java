package models;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    private String areaId;

    private String name;

    private String city;

    private String countryId;

    private String countryCode;

    private String country;
}
