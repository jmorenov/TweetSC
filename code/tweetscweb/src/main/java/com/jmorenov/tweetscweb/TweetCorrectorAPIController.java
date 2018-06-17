package com.jmorenov.tweetscweb;

import com.jmorenov.tweetsccore.method.DictionaryMethod;
import com.jmorenov.tweetsccore.spellchecker.SpellChecker;
import com.jmorenov.tweetsccore.twitter.TweetCorrected;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * TweetCorrectorAPIController class with the controller of the API.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
@RestController
@RequestMapping("/api/tweet")
public class TweetCorrectorAPIController {
    /**
     * Method to control the api calls of simple corrector.
     * @param tweetModel {@link TweetModel} with the model of the call.
     * @return {@link Response} with the response of the call.
     */
    @PostMapping("/corrector")
    public Response simpleCorrectSubmit(@RequestBody TweetModel tweetModel) {
        Response response = new Response();

        try {
            SpellChecker spellChecker = new SpellChecker(new DictionaryMethod());
            TweetCorrected tweetCorrected = spellChecker.correctTweet(tweetModel.toTweet());
            TweetCorrectedModel tweetCorrectedModel = new TweetCorrectedModel(tweetCorrected);

            response.setData(tweetCorrectedModel);
            response.setStatus("Done");
        } catch (IOException ex) {
            response.setStatus("Error");
        }

        return response;
    }

    /**
     * Method to control the api calls of advanced corrector.
     * @param tweetListModel {@link TweetListModel} with the model of the call.
     * @return {@link Response} with the response of the call.
     */
    @PostMapping("/advancedcorrector")
    public Response advancedCorrectSubmit(@RequestBody TweetListModel tweetListModel) {
        Response response = new Response();
        TweetCorrectedListModel tweetCorrectedListModel = new TweetCorrectedListModel();

        try {
            SpellChecker spellChecker = new SpellChecker(new DictionaryMethod());

            for (TweetModel tweetModel : tweetListModel.tweets) {
                TweetCorrected tweetCorrected = spellChecker.correctTweet(tweetModel.toTweet());
                TweetCorrectedModel tweetCorrectedModel = new TweetCorrectedModel(tweetCorrected);

                tweetCorrectedListModel.tweets.add(tweetCorrectedModel);
            }

            response.setData(tweetCorrectedListModel);
            response.setStatus("Done");
        } catch (IOException ex) {
            response.setStatus("Error");
        }

        return response;
    }

    /**
     * Method to control the api calls.
     * @param tweetSearchQueryModel {@link TweetSearchQueryModel} with the model of the call.
     * @return {@link Response} with the response of the call.
     */
    @PostMapping("/get")
    public Response getTweets(@RequestBody TweetSearchQueryModel tweetSearchQueryModel) {
        Response response = new Response();
        TweetSearchQuery tweetSearchQuery = new TweetSearchQuery(tweetSearchQueryModel);

        if (tweetSearchQuery.isValidQuery()) {
            tweetSearchQueryModel.setTweets(tweetSearchQuery.loadTweets());

            response.setData(tweetSearchQueryModel);
            response.setStatus("Done");
        } else {
            response.setStatus("Invalid query");
        }

        return response;
    }
}