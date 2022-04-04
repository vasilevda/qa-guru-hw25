package models;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    @JsonProperty("areaId")
    private String areaId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("city")
    private String city;

    @JsonProperty("countryId")
    private String countryId;

    @JsonProperty("countryCode")
    private String countryCode;

    @JsonProperty("country")
    private String country;
}
