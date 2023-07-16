package com.blogone.jobs;

import com.blogone.observer.JobListener;
import com.blogone.slack.SlackWebhookClient;

import java.util.logging.Logger;

public class JobTwo implements JobListener {

    private static final Logger log = Logger.getLogger(JobTwo.class.getName());

    @Override
    public void slack(SlackWebhookClient client) {
        var x = 0;
        if (x < 1) {
            client.sendMessage(new Exception("Simulation error"), this);
            log.info("TestExceptionTwo() -> Sent message");
        }
    }
}