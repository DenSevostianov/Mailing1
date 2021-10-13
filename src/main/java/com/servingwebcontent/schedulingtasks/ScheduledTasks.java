package com.servingwebcontent.schedulingtasks;

import com.servingwebcontent.models.MailAd;
import com.servingwebcontent.repos.MailRepository;
import com.servingwebcontent.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@EnableScheduling
//@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
public class ScheduledTasks {


    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private MailRepository mailRepository;


    @Scheduled(cron = "${interval-in-cron}")
    public void sendingMail(){
        List<MailAd> mailAdList = (List<MailAd>) mailRepository.findAll();
        for(MailAd mailAddress:mailAdList) {
        System.out.println(mailAddress.getEmail());
        //emailService.sendSimpleMessage(mailAddress.getEmail(), "Проверка связи 10", "Пора на работу");
    }}
}
