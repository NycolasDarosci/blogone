package com.blogone.slack;

import okhttp3.*;

import java.io.IOException;
import java.util.logging.Logger;

public class SlackWebhookClient {

    private static final Logger log = Logger.getLogger(SlackWebhookClient.class.getName());

    private static final String CONTENT_TYPE = "application/json";

    public static void sendMessage(Exception ex) throws IOException {
        try {
            log.info("Sending error message to Slack...");
            OkHttpClient client = new OkHttpClient().newBuilder().build();

            RequestBody body = createRequestBodyWith(ex);

            Request request = buildRequest(body);

            client.newCall(request).execute();
            log.info("Error message sent to Slack.");

        } catch (IOException e) {
            log.warning("Something happened when tried to make a call: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            log.warning(e.getMessage());
            throw e;
        }
    }

    private static RequestBody createRequestBodyWith(Exception ex) {
        String message = "{ \"text\" : \"" + ex.getMessage() + "\"}";
        return RequestBody.create(message, MediaType.parse(CONTENT_TYPE));
    }

    public static Request buildRequest(RequestBody body) {
        return new Request.Builder()
                .url("https://hooks.slack.com/services/" + System.getenv("SLACK_WEBHOOK_URL"))
                .method("POST", body)
                .addHeader("Content-Type", CONTENT_TYPE)
                .build();
    }
}
