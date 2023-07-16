package com.blogone;

import com.blogone.slack.SlackWebhookClient;

public class Main {

    public static void main(String[] args) throws Exception {

         int x = 0;
         if (x < 1) {
             SlackWebhookClient.sendMessage(new Exception("Simulation error"));
         }


    }
}
