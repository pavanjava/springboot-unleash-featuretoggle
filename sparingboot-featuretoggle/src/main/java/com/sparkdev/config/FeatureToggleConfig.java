package com.sparkdev.config;

import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;
import io.getunleash.util.UnleashConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeatureToggleConfig {

    @Value("${APP_NAME}")
    private String APP_NAME;
    @Value("${API_KEY}")
    private String API_KEY;
    @Value("${METRICS_DELAY}")
    private int METRICS_DELAY;
    @Value("${UNLEASH_API_ENDPOINT}")
    private String UNLEASH_API_ENDPOINT;
    @Value("${FEATURE_TOGGLE_INTERVAL}")
    private int FEATURE_TOGGLE_INTERVAL;
    @Value("${INSTANCE_ID}")
    private String INSTANCE_ID;

    @Bean
    public UnleashConfig unleashConfig() {
        return UnleashConfig.builder()
                .appName(APP_NAME)
                .instanceId(INSTANCE_ID)
                .apiKey(API_KEY)
                .unleashAPI(UNLEASH_API_ENDPOINT)
                .fetchTogglesInterval(FEATURE_TOGGLE_INTERVAL)
                .synchronousFetchOnInitialisation(true)
                .build();
    }

    @Bean
    public Unleash unleash(UnleashConfig unleashConfig){
        return new DefaultUnleash(unleashConfig);
    }
}
