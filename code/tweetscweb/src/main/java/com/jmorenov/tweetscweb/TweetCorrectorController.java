package com.jmorenov.tweetscweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class TweetCorrectorController {
    @GetMapping("/")
    public String homeForm(Model model) {
        model.addAttribute("tweetModel", new TweetModel());
        return "tweet-corrector";
    }
}