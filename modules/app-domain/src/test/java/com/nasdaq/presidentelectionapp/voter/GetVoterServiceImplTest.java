package com.nasdaq.presidentelectionapp.voter;

import com.nasdaq.presidentelectionapp.exceptions.DomainValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetVoterServiceImplTest {

   @Mock
   private GetVoterRepository getVoterRepository;

   @InjectMocks
   private GetVoterServiceImpl getVoterService;

   @Test
   void whenIdProvided_shouldReturnVoter() throws DomainValidationException {
      when(getVoterRepository.getById(1L)).thenReturn(Optional.of(VoterDto.builder()
            .id(1L)
            .region("Massachusetts")
            .votedCandidateNumber(5)
            .build()));

      var voterDto = getVoterService.getById(1L);

      verify(getVoterRepository, times(1)).getById(1L);

      assertThat(voterDto.isPresent(), is(true));

      assertThat(voterDto.get().getId(), is(1L));
      assertThat(voterDto.get().getRegion(), is("Massachusetts"));
      assertThat(voterDto.get().getVotedCandidateNumber(), is(5));
   }

   @Test
   void whenIdNotProvided_shouldThrowException() {
      DomainValidationException domainValidationException = assertThrows(DomainValidationException.class, () -> getVoterService.getById(null));

      assertThat(domainValidationException.getPropertyName(), is("VoterDto.id"));
      assertThat(domainValidationException.getMessage(), is("Voter id not provided"));
   }

}
