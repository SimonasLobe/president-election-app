package com.nasdaq.presidentelectionapp.candidate;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class CandidateRepositoryImplTest {

   @Test
   void whenLoadCandidates_candidatesAreLoaded() {
      var candidatesRepositoryImpl = new CandidateRepositoryImpl();
      var allCandidates = candidatesRepositoryImpl.findAll();

      assertThat(allCandidates.size(), is(10));
      var candidateDto = allCandidates.stream().filter(candidate -> 1 == candidate.getNumber()).findFirst();
      assertThat(candidateDto.get().getNumber(), is(1));
      assertThat(candidateDto.get().getName(), is("Eric"));
      assertThat(candidateDto.get().getLastName(), is("Adams"));
      assertThat(candidateDto.get().getAgendaSummary(), is("You The Voter Have Integrity, Honesty, And Experience When You Vote For me"));
   }


}
