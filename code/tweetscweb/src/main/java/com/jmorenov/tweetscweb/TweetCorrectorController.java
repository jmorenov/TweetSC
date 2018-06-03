package com.jmorenov.tweetscweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * TweetCorrectorController class with the controller of the frontend.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
@Controller
public class TweetCorrectorController {
    /**
     * Method to control the frontend calls.
     * @param model {@link Model} with the model of the call.
     * @return String with the template to show.
     */
    @GetMapping("/")
    public String homeForm(Model model) {
        model.addAttribute("tweetModel", new TweetModel());
        return "tweet-corrector";
    }
}