package com.akif.twitter4j;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class App2 {

    static final String CONSUMER_KEY = "7ADenFxk5aBmRyHYbwGahIQLX";
    static final String CONSUMER_SECRET = "9dbbdK5qXdJ5GSul4HSxgDjZGujRMbVYqgzEKbjzrzIt0YJfxe";
    String aT;
    String aTC;

    public void start() throws TwitterException, IOException {


        //Access ve AccesToken Key öğrenme
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        RequestToken requestToken = twitter.getOAuthRequestToken();
        System.out.println("Authorization URL: "
                + requestToken.getAuthorizationURL());

        AccessToken accessToken = null;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (null == accessToken) {
            try {
                System.out.print("Input PIN here: ");
                String pin = br.readLine();

                accessToken = twitter.getOAuthAccessToken(requestToken, pin);

            } catch (TwitterException te) {

                System.out.println("Failed to get access token, caused by: "
                        + te.getMessage());

                System.out.println("Retry input PIN");

            }
        }

        System.out.println("Access Token: " + accessToken.getToken());
        System.out.println("Access Token Secret: " + accessToken.getTokenSecret());

        //twitter.updateStatus("hi.. im updating this using Namex Tweet for Demo");

        // here's the difference
        aT = getSavedAccessToken(accessToken.getToken());
        aTC = getSavedAccessTokenSecret(accessToken.getTokenSecret());
        AccessToken oathAccessToken = new AccessToken(aT,aTC);

        twitter.setOAuthAccessToken(oathAccessToken);
        // end of difference

        //twitter.updateStatus("Hi, im updating status again from Namex Tweet for Demo");

        System.out.println("\nMy Timeline:");

        // I'm reading your timeline
        List<Status> list = twitter.getHomeTimeline();
        for (Status each : list) {

            System.out.println("Sent by: @" + each.getUser().getScreenName()
                    + " - " + each.getUser().getName() + "\n" + each.getText()
                    + "\n");
        }

    }

    private String getSavedAccessTokenSecret(String accessTokenSecret) {
        // consider this is method to get your previously saved Access Token
        // Secret
        return accessTokenSecret;
    }

    private String getSavedAccessToken(String accesToken) {
        // consider this is method to get your previously saved Access Token
        return accesToken;
    }

    public static void main(String[] args) throws Exception {
        new App2().start();// run the Twitter client
    }
}
