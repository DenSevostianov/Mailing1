package com.servingwebcontent.controllers;

import com.servingwebcontent.models.MailAd;
import com.servingwebcontent.repos.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/posts")
public class WebController {


    //@Autowired
    //private EmailServiceImpl emailService;

    @Autowired
    private MailRepository mailRepository;


    @GetMapping("/n")
    public String index(Model model){
        model.addAttribute("mailPerson",mailRepository.findAll());
      return "index";
    }
    @GetMapping("/new")
    public String newAddress(){//@ModelAttribute("addres") MailAd mailAd
        return "newMail";
    }

    @PostMapping("/add")
    public String creat(@RequestParam("name") String name, @RequestParam("email") String email){
       MailAd mailAd = new MailAd();
       mailAd.setName(name);
       mailAd.setEmail(email);
       mailRepository.save(mailAd);
        return "redirect:/n";
    }

    @GetMapping("/{id}/show")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("mailAd", mailRepository.findById(id).get());
        return "show";
    }

    @PostMapping("/{id}/edit")
    public String edit(@ModelAttribute("mailAd") MailAd mailAd, @PathVariable("id") int id){
        mailRepository.save(mailAd);
        return "redirect:/n";

    }

    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable("id") int id, Model model){
        model.addAttribute("mailAd", mailRepository.findById(id).get());
        return "edit";
    }

    @PostMapping("/{id}/delete")
    public String delete(@ModelAttribute("mailAd") MailAd mailAd){
        mailRepository.delete(mailAd);
        return "redirect:/n";
    }

    /*@GetMapping("/mail") //отправка письма
    public String mail(){
        emailService.sendSimpleMessage("mahard@rambler.ru", "Проверка связи", "Привет. Первое письмо отправленное с JAVA");
        return "mail";
    }*/



    /*@GetMapping(path="/all")
    public @ResponseBody Iterable<MailAd> getAllUsers() {
        // This returns a JSON or XML with the users
        return mailRepository.findAll();
    }*/
    /*@GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World")
                                       String name, Model model){
        model.addAttribute("name", name);
        return "greeting";
    }*/
}
