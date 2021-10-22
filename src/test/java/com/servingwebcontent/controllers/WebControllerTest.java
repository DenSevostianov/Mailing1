package com.servingwebcontent.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.servingwebcontent.models.MailAd;
import com.servingwebcontent.repos.MailRepository;
import com.servingwebcontent.service.EmailServiceImpl;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WebController.class)
public class WebControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;;
    @MockBean
    private MailRepository repository;
    //@MockBean
    //private EmailServiceImpl emailService;


    @Test
    public void show() throws Exception {
        MailAd mailAd = new MailAd("Michail", "Michail@mail.ru");
        Mockito.when(repository.findById(Mockito.any())).thenReturn(java.util.Optional.of(mailAd));

        mockMvc.perform( 
                get("/{id}/show", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("mailAd"))
                .andExpect(view().name("show"));

    }

    @Test
    public void index() throws Exception {
        MailAd mailAd1 = new MailAd("Michail", "Michail@mail.ru");
        MailAd mailAd2 = new MailAd("Sam", "Saml@mail.ru");

        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(mailAd1, mailAd2));
        mockMvc.perform(
                get("/n"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("mailPerson"))
                .andExpect(view().name("index"));

    }

    @Test
    public  void newAddress() throws Exception {
        //MailAd mailAd = new MailAd("Mac","mac@mac.ru");
        //Mockito.when(repository.save(mailAd)).thenReturn(mailAd);

        mockMvc.perform(
                post("/add")
                        //.content(objectMapper.writeValueAsString(mailAd))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"duke\", \"email\":\"duke@spring.io\"}")


                )
                .andExpect(status().isCreated())
                //.andExpect(header().exists("Location"))
                //.andExpect(header().string("Location", Matchers.containsString("duke")))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/n"));

        //verify(userService).storeNewUser(any(User.class));

    }

    @Test
    public void edit() throws Exception {
        MailAd mailAd = new MailAd("Mac","mac@mac.ru");
        mailAd.setId(1);
        mockMvc.perform( //MockMvcRequestBuilders.
                post("/{id}/edit", 2)
                .content(asJsonString(mailAd))

                //.contentType(MediaType.APPLICATION_JSON)
                //.accept(MediaType.APPLICATION_JSON))
                //.andExpect(status().isOk()
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("name"))
                //.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email2@mail.com"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/n"));
    }

    @Test
    public  void delete() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/{id}/delete", 1) )
                .andExpect(status().isAccepted());
    }



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}