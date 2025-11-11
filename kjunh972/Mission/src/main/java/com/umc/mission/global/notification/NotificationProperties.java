package com.umc.mission.global.notification;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "notification")
public class NotificationProperties {
    private Discord discord;
    private Environments environments;

    @Getter
    @Setter
    public static class Discord {
        private String webhookUrl;
        private boolean enabled;
    }

    @Getter
    @Setter
    public static class Environments {
        private List<String> alertEnabled;
    }
}