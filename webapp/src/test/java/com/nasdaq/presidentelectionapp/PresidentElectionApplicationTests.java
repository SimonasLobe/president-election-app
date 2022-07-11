package com.nasdaq.presidentelectionapp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasdaq.presidentelectionapp.rest.VoteInput;
import com.nasdaq.presidentelectionapp.voter.GetVoterService;
import com.nasdaq.presidentelectionapp.voter.VoterDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class})
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PresidentElectionApplicationTests {

   private MockMvc mockMvc;

   @Autowired
   private GetVoterService getVoterService;

   @BeforeEach
   public void setup(WebApplicationContext webApplicationContext) {
      this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .build();
   }

   @Test
   void contextLoads() {
   }

   @Test
   @Order(1)
   void whenGetCandidates_thenSuccessful() throws Exception {
      this.mockMvc.perform(get("/rest/candidates"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$", hasSize(equalTo(10))));
   }

   @Test
   @Order(2)
   void whenValidInput_thenSaveVote() throws Exception {

      VoteInput voteInput = VoteInput.builder()
            .voterRegion("Texas")
            .candidateNumber(1).build();

      mockMvc.perform(post("/rest/vote/1")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(toJson(voteInput)))
            .andExpect(status().isOk());

      Optional<VoterDto> voter = getVoterService.getById(1L);

      assertThat(voter.get().getId(), is(1L));
      assertThat(voter.get().getRegion(), is("Texas"));
      assertThat(voter.get().getVotedCandidateNumber(), is(1));
   }

   @Test
   @Order(3)
   void whenGetResult_thenSuccessful() throws Exception {
      this.mockMvc.perform(get("/rest/results"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$", hasSize(equalTo(1))))
            .andExpect(jsonPath("$[0].candidateNumber", is(1)))
            .andExpect(jsonPath("$[0].totalVotes", is(1)));
   }

   @Test
   @Order(4)
   void whenGetResultsPerRegion_thenSuccessful() throws Exception {
      this.mockMvc.perform(get("/rest/resultsPerRegion"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$", hasSize(equalTo(1))))
            .andExpect(jsonPath("$[0].region", is("Texas")))
            .andExpect(jsonPath("$[0].result", hasSize(equalTo(1))));
   }

   @Test
   @Order(5)
   void whenGetWinner_thenSuccessful() throws Exception {
      this.mockMvc.perform(get("/rest/winner"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("candidateNumbers", hasSize(equalTo(1))));
   }

   static byte[] toJson(Object object) throws IOException {
      ObjectMapper mapper = new ObjectMapper();
      mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
      return mapper.writeValueAsBytes(object);
   }

}
