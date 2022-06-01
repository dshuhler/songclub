package com.shuhler.negadelphia.web;


import com.shuhler.negadelphia.infra.JobRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    JobRunner jobRunner;

    Logger logger = LoggerFactory.getLogger(MainController.class);

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
        logger.warn("Starting...");
        jobRunner.doTestRun();
        return "index";
    }

    @PostMapping("/stop")
    public String stop(Model model) {
        logger.warn("Stopping...");
        jobRunner.stopAll();
        return "index";
    }

}
