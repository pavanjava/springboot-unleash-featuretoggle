package com.sparkdev;

import io.getunleash.Unleash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class GreetingsController {

    @Autowired
    private Unleash unleash;

    @RequestMapping("/feature-toggles")
    public ResponseEntity<Map<String, Boolean>> getListOfFeatureFlags(){
        Map<String, Boolean> response = new HashMap<>();
        List<String> featureStrings = unleash.getFeatureToggleNames();
        featureStrings.forEach(s -> response.put(s, unleash.isEnabled(s)));
        return ResponseEntity.ok().body(response);
    }
}
