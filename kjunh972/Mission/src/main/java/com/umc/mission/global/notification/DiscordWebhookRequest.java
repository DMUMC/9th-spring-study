package com.umc.mission.global.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscordWebhookRequest {
    private String content;
    private List<Embed> embeds;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Embed {
        private String title;
        private String description;
        private Integer color;
        private List<Field> fields;
        private String timestamp;

        @Getter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Field {
            private String name;
            private String value;
            private Boolean inline;
        }
    }
}