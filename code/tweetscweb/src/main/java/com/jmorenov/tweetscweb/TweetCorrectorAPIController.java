package com.jmorenov.tweetscweb;

import com.jmorenov.tweetsccore.spellchecker.DictionaryMethod;
import com.jmorenov.tweetsccore.spellchecker.SpellChecker;
import com.jmorenov.tweetsccore.twitter.Tweet;
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
     * @param tweetCorrected {@link TweetCorrected} with the model of the call.
     * @return {@link Response} with the response of the call.
     */
    @PostMapping("/corrector")
    public Response simpleCorrectSubmit(@RequestBody TweetCorrected tweetCorrected) {
        Response response = new Response();

        try {
            SpellChecker spellChecker = new SpellChecker(new DictionaryMethod());

            tweetCorrected.setCorrectedContent(spellChecker.correctText(tweetCorrected.getText()));
            response.setData(tweetCorrected);
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

        try {
            SpellChecker spellChecker = new SpellChecker(new DictionaryMethod());

            for (TweetCorrected tweetCorrected : tweetListModel.getTweets()) {
                tweetCorrected.setCorrectedContent(spellChecker.correctText(tweetCorrected.getText()));
            }

            response.setData(tweetListModel);
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

        if (tweetSearchQueryModel.isValidQuery()) {
            tweetSearchQueryModel.loadTweets();

            response.setData(tweetSearchQueryModel);
            response.setStatus("Done");
        } else {
            response.setStatus("Invalid query");
        }

        return response;
    }
}