package com.blogone.observer;

import com.blogone.slack.SlackWebhookClient;

public class Editor {
    public EventManager events;

    public Editor() {
        this.events = new EventManager("error");
    }

    public void sendMessage(SlackWebhookClient e) throws Exception {
        events.notify("error", e);
    }


}
