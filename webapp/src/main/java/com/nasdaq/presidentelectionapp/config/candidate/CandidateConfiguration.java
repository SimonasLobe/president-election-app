package com.nasdaq.presidentelectionapp.config.candidate;

import com.nasdaq.presidentelectionapp.candidate.FindCandidateRepository;
import com.nasdaq.presidentelectionapp.candidate.FindCandidateService;
import com.nasdaq.presidentelectionapp.candidate.FindCandidateServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CandidateConfiguration {

   @Bean
   public FindCandidateService findCandidateService(FindCandidateRepository findCandidateRepository) {
      return new FindCandidateServiceImpl(findCandidateRepository);
   }

}
