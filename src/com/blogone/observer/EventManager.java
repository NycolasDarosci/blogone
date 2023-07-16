package com.blogone.observer;

import com.blogone.slack.SlackWebhookClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {

    Map<String, List<JobListener>> listeners = new HashMap<>();

    public EventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, JobListener jobListener) {
        List<JobListener> users = listeners.get(eventType);
        users.add(jobListener);
    }

    public void notify(String eventType, SlackWebhookClient e) throws Exception {
        List<JobListener> users = listeners.get(eventType);
        for (JobListener jobListener : users) {
            jobListener.slack(e);
        }
    }

}