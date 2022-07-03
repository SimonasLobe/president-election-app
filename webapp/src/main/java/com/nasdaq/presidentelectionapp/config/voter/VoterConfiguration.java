package com.nasdaq.presidentelectionapp.config.voter;

import com.nasdaq.presidentelectionapp.candidate.FindCandidateRepository;
import com.nasdaq.presidentelectionapp.voter.GetVoterRepository;
import com.nasdaq.presidentelectionapp.voter.GetVoterService;
import com.nasdaq.presidentelectionapp.voter.GetVoterServiceImpl;
import com.nasdaq.presidentelectionapp.voter.SaveVoterRepository;
import com.nasdaq.presidentelectionapp.voter.SaveVoterService;
import com.nasdaq.presidentelectionapp.voter.SaveVoterServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VoterConfiguration {

   @Bean
   public GetVoterService getVoterService(GetVoterRepository getVoterRepository) {
      return new GetVoterServiceImpl(getVoterRepository);
   }

   @Bean
   public SaveVoterService saveVoterService(SaveVoterRepository saveVoterRepository,
                                            FindCandidateRepository findCandidateRepository) {
      return new SaveVoterServiceImpl(saveVoterRepository, findCandidateRepository);
   }

}
