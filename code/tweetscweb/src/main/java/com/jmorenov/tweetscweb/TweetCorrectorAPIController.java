package com.jmorenov.tweetscweb;

import com.jmorenov.tweetsccore.spellchecker.SpellCheckerByDictionary;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/tweet")
public class TweetCorrectorAPIController {
    @PostMapping("/corrector")
    public Response homeSubmit(@RequestBody TweetModel tweetModel) {
        Response response = new Response();

        try {
            SpellCheckerByDictionary spellChecker = new SpellCheckerByDictionary();

            tweetModel.setCorrectedContent(spellChecker.correctText(tweetModel.getContent()));
            response.setData(tweetModel);
            response.setStatus("Done");
        } catch (IOException ex) {
            response.setStatus("Error");
        }

        return response;
    }
}
