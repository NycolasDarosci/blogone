package com.blogone.observer;

import com.blogone.slack.SlackWebhookClient;

public interface JobListener {
    void slack(SlackWebhookClient client) throws Exception;
}