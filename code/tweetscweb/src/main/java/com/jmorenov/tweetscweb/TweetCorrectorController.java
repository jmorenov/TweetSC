package com.jmorenov.tweetscweb;

import com.jmorenov.tweetsccore.spellchecker.SpellCheckerByDictionary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.jmorenov.tweetsccore.spellchecker.SpellChecker;

import java.io.IOException;

@Controller
public class TweetCorrectorController {
    @GetMapping("/")
    public String homeForm(Model model) {
        model.addAttribute("tweetModel", new TweetModel());
        return "tweet-corrector";
    }

    @PostMapping("/")
    public String homeSubmit(@ModelAttribute TweetModel tweetModel) {
        try {
            SpellCheckerByDictionary spellChecker = new SpellCheckerByDictionary();
            tweetModel.setCorrectedContent(spellChecker.correctText(tweetModel.getContent()));
        } catch (IOException ex) {
            tweetModel.setCorrectedContent("Error loading files!");
        }

        return "tweet-corrector-result";
    }
}