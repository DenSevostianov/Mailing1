package com.servingwebcontent.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
@RequestMapping("/send")
public class MailController {

    //@Autowired
    //private ScheduledTasks scheduledTasks;

    @GetMapping("/start")
    public String mailSend(){

        return "start";
    }
}
