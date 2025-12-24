package com.umc.mission.global.notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiscordNotificationService {

    private final NotificationProperties notificationProperties;
    private final Environment environment;
    private final ObjectMapper objectMapper;

    public void sendErrorNotification(Exception exception, HttpServletRequest request) {
        if (!shouldSendNotification()) {
            log.info("Notification skipped - Current environment: {}", getCurrentEnvironment());
            return;
        }

        if (!notificationProperties.getDiscord().isEnabled()) {
            log.info("Discord notification is disabled");
            return;
        }

        try {
            DiscordWebhookRequest webhookRequest = buildErrorMessage(exception, request);
            sendWebhook(webhookRequest);
            log.info("Discord notification sent successfully");
        } catch (Exception e) {
            log.error("Failed to send Discord notification", e);
        }
    }

    private boolean shouldSendNotification() {
        String currentEnv = getCurrentEnvironment();
        List<String> alertEnabledEnvs = notificationProperties.getEnvironments().getAlertEnabled();
        return alertEnabledEnvs.contains(currentEnv);
    }

    private String getCurrentEnvironment() {
        String[] activeProfiles = environment.getActiveProfiles();
        return activeProfiles.length > 0 ? activeProfiles[0] : "local";
    }

    private DiscordWebhookRequest buildErrorMessage(Exception exception, HttpServletRequest request) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String requestUrl = request != null ? request.getRequestURL().toString() : "N/A";
        String method = request != null ? request.getMethod() : "N/A";
        String errorMessage = exception.getMessage() != null ? exception.getMessage() : "No message";
        String stackTrace = getStackTrace(exception);

        List<DiscordWebhookRequest.Embed.Field> fields = Arrays.asList(
                DiscordWebhookRequest.Embed.Field.builder()
                        .name("Environment")
                        .value(getCurrentEnvironment())
                        .inline(true)
                        .build(),
                DiscordWebhookRequest.Embed.Field.builder()
                        .name("HTTP Method")
                        .value(method)
                        .inline(true)
                        .build(),
                DiscordWebhookRequest.Embed.Field.builder()
                        .name("Request URL")
                        .value(requestUrl)
                        .inline(false)
                        .build(),
                DiscordWebhookRequest.Embed.Field.builder()
                        .name("Error Message")
                        .value(errorMessage)
                        .inline(false)
                        .build(),
                DiscordWebhookRequest.Embed.Field.builder()
                        .name("Stack Trace")
                        .value("```" + stackTrace + "```")
                        .inline(false)
                        .build()
        );

        DiscordWebhookRequest.Embed embed = DiscordWebhookRequest.Embed.builder()
                .title("500 Internal Server Error")
                .description("An internal server error occurred")
                .color(15158332) // Red color
                .fields(fields)
                .timestamp(timestamp)
                .build();

        return DiscordWebhookRequest.builder()
                .content("@here **500 Error Alert!**")
                .embeds(List.of(embed))
                .build();
    }

    private String getStackTrace(Exception exception) {
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = exception.getStackTrace();
        int limit = Math.min(5, stackTrace.length);

        for (int i = 0; i < limit; i++) {
            sb.append(stackTrace[i].toString()).append("\n");
        }

        if (stackTrace.length > limit) {
            sb.append("... (").append(stackTrace.length - limit).append(" more)");
        }

        return sb.toString();
    }

    private void sendWebhook(DiscordWebhookRequest webhookRequest) throws IOException {
        String webhookUrl = notificationProperties.getDiscord().getWebhookUrl();

        if (webhookUrl == null || webhookUrl.isEmpty() || webhookUrl.equals("your-discord-webhook-url-here")) {
            log.warn("Discord webhook URL is not configured");
            return;
        }

        URL url = new URL(webhookUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String jsonPayload = objectMapper.writeValueAsString(webhookRequest);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonPayload.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode != 200 && responseCode != 204) {
            log.error("Discord webhook failed with response code: {}", responseCode);
        }
    }
}