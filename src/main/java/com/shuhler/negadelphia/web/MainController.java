package com.shuhler.negadelphia.web;


import com.shuhler.negadelphia.domain.TwitterManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.shuhler.negadelphia.domain.twitter.SearchConstants.EAGLES_NO_RETWEETS;

@Controller
public class MainController {

    private Logger logger = LoggerFactory.getLogger(MainController.class);

    private TwitterManager twitterManager;

    @Autowired
    public MainController(TwitterManager twitterManager) {
        this.twitterManager = twitterManager;
    }

    @Value("${profile.name}")
    private String profileName;

    @GetMapping("/")
    public String main(Model model) {
        logger.info("profname:" + profileName);
        model.addAttribute("profile_name", profileName);
        return "index";
    }

    @PostMapping("/start")
    public String start(Model model) {
        logger.info("Starting...");
        twitterManager.pollingTwitterSearch(EAGLES_NO_RETWEETS);
        return "index";
    }

    @PostMapping("/stop")
    public String stop(Model model) {
        logger.info("Stopping...");
        twitterManager.stopAll();
        return "index";
    }

}
