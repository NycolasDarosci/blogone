package com.blogone;

import com.blogone.observer.Editor;
import com.blogone.observer.JobListener;
import com.blogone.jobs.JobOne;
import com.blogone.jobs.JobThree;
import com.blogone.jobs.JobTwo;
import com.blogone.slack.SlackWebhookClient;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<JobListener> jobs = List.of(
        new JobOne(),
        new JobTwo(),
        new JobThree()
    );

    public static void main(String[] args) throws Exception {

        Editor editor = new Editor();

        for (JobListener job : jobs) {
            editor.events.subscribe("error", job);
        }

        try {
            editor.sendMessage(new SlackWebhookClient());
        } catch (Exception e) {
            throw new Exception("Something happened when published message to slack: " + e.getMessage());
        }
    }

}
