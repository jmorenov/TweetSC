package com.jmorenov.tweetscweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.jmorenov.tweetsccore.spellchecker.SpellChecker;

@Controller
public class TweetCorrectorController {
    @GetMapping("/")
    public String homeForm(Model model) {
        model.addAttribute("tweetModel", new TweetModel());
        return "tweet-corrector";
    }

    @PostMapping("/")
    public String homeSubmit(@ModelAttribute TweetModel tweetModel) {
        SpellChecker spellChecker = new SpellChecker(tweetModel.getContent());
        tweetModel.setCorrectedContent(spellChecker.correct());

        return "tweet-corrector-result";
    }
}