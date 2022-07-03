package com.nasdaq.presidentelectionapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class})
@SpringBootTest
class PresidentElectionApplicationTests {

   private MockMvc mockMvc;

   @BeforeEach
   public void setup(WebApplicationContext webApplicationContext) {
      this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .build();
   }

   @Test
   void contextLoads() {
   }

   @Test
   void whenGetCandidates_thenSuccessful() throws Exception {
      MvcResult mvcResult = this.mockMvc.perform(get("/rest/candidates"))
            .andExpect(status().isOk())
            .andDo(print()).andExpect(status().isOk())
            .andReturn();

      assertThat(mvcResult.getResponse().getContentType(), is("application/json"));
   }

}
