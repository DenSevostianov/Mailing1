package com.servingwebcontent;

import com.servingwebcontent.controllers.WebController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ServingWebContentApplicationTests {

	@Autowired
	private WebController webController;

	@Test
	public void contextLoads() {
		assertThat(webController).isNotNull();
	}

}
