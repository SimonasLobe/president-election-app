package com.nasdaq.presidentelectionapp.candidate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindCandidateServiceImplTest {

   @Mock
   private FindCandidateRepository findCandidateRepository;

   @InjectMocks
   private FindCandidateServiceImpl findCandidateService;

   @Test
   void shouldReturnAllCandidates() {
      when(findCandidateRepository.findAll()).thenReturn(Set.of(CandidateDto.builder().number(1).name("Eric").lastName("Adams")
                  .agendaSummary("You The Voter Have Integrity, Honesty, And Experience When You Vote For me").build(),
            CandidateDto.builder().number(2).name("Pete").lastName("Buttigieg")
                  .agendaSummary("For great results, not empty promises.").build()));

      var allCandidates = findCandidateService.findAll();

      verify(findCandidateRepository, times(1)).findAll();

      assertThat(allCandidates.size(), is(2));
      var findCandidateDto = allCandidates.iterator().next();

      assertThat(findCandidateDto.getNumber(), is(1));
      assertThat(findCandidateDto.getName(), is("Eric"));
      assertThat(findCandidateDto.getLastName(), is("Adams"));
      assertThat(findCandidateDto.getAgendaSummary(), is("You The Voter Have Integrity, Honesty, And Experience When You Vote For me"));
   }

}
