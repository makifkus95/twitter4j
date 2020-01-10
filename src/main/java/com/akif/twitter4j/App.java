package com.akif.twitter4j;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class App
{
    static final String CONSUMER_KEY = "7ADenFxk5aBmRyHYbwGahIQLX";
    static final String CONSUMER_SECRET = "9dbbdK5qXdJ5GSul4HSxgDjZGujRMbVYqgzEKbjzrzIt0YJfxe";
    static final String ACCESS_TOKEN = "2150598456-2XGZSv47EbF4rDeiBbdj2hjdgRfUktGKICh6aEe";
    static final String ACCESS_TOKEN_SECRET = "ylBwYb3Zk1PFXwvsT6MtBW8fELKl8H74BVF0hfGu3vbhg";

    public static void main( String[] args ) throws TwitterException {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);

        TwitterFactory TwitterFactory = new TwitterFactory(cb.build());
        Twitter twitter = TwitterFactory.getInstance();

        //twitter.updateStatus("Hello #twitter4j"); //tweet atıyoruz

  //      anasayfamızda ki tweetleri görüntülüyoruz 18 tane
        List<Status> statuses = twitter.getHomeTimeline();
        System.out.println("Showing home timeline.");
        int i=0;
        for (Status status : statuses) {
            System.out.println(i+" "+status.getUser().getName() + " : " + status.getText());
            i++;
        }

        Query query = new Query("");
        QueryResult tweets = twitter.search(query);

        for (Status tweet : tweets.getTweets()) {
            System.out.println("Atılan tweet : "+tweet.getText());   //Atılan tweet
            System.out.println("Tweet atan userin ismi : "+ tweet.getUser().getName());  //Tweet atan userin ismi
            System.out.println("Tweet atan userin kullanıcı ismi : "+tweet.getUser().getScreenName()); //Tweet atan userin kullanıcı ismi
            System.out.println("Tweet favorilere eklimi ? "+tweet.isFavorited());  //Tweet favorilere eklimi ?
            //twitter.createFavorite(tweet.getId()); //Tweeti favorilere ekle
        }

    }

}
