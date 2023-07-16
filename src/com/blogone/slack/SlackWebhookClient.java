package com.blogone.slack;

import com.blogone.observer.JobListener;
import okhttp3.*;

import java.io.IOException;
import java.util.logging.Logger;

public class SlackWebhookClient {

    private static final Logger log = Logger.getLogger(SlackWebhookClient.class.getName());

    private static final String CONTENT_TYPE = "application/json";

    public void sendMessage(Exception e, JobListener jobListener) {
        try {
            log.info("SlackWebhookClient() -> Sending error message to Slack... " + "Sent by Listener: " + jobListener.getClass().getName());
            OkHttpClient client = new OkHttpClient().newBuilder().build();

            RequestBody body = createRequestBodyWith(e);

            Request request = buildRequest(body);

            client.newCall(request).execute();
            log.info("SlackWebhookClient() -> Error message sent to Slack.");
        } catch (IOException ex) {
            log.warning("SlackWebhookClient() -> Something happened when tried to make a call: " + ex.getMessage());
        } catch (Exception ex) {
            log.warning("SlackWebhookClient() -> " + ex.getMessage());
            throw ex;
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
