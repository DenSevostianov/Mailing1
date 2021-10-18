package com.servingwebcontent.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.servingwebcontent.models.MailAd;
import com.servingwebcontent.repos.MailRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class WebControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;;
    @MockBean
    private MailRepository repository;

    @Test
    public void givenId_whenGetExistingPerson_thenStatus200andPersonReturned() throws Exception {
        MailAd mailAd = new MailAd("Michail", "Michail@mail.ru");
        Mockito.when(repository.findById(Mockito.any())).thenReturn(java.util.Optional.of(mailAd));

        //System.out.println(jsonPath());
    }

}