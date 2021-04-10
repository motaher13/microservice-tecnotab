package com.tecnotab.limits.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("limits-service")  /*note: same as written in in properties file
                                                this will auto call setter for properties*/
public class LimitsConfiguration {

    private int maximum;
    private int minimum;

    public LimitsConfiguration() {
    }

    public LimitsConfiguration(int maximum, int minimum) {
        super();
        this.maximum = maximum;
        this.minimum = minimum;
    }


}
