package com.nasdaq.presidentelectionapp.voter;

import com.nasdaq.presidentelectionapp.exceptions.DomainValidationException;
import com.nasdaq.presidentelectionapp.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SaveVoterServiceImplTest {

   @Mock
   private SaveVoterRepository saveVoterRepository;

   @InjectMocks
   private SaveVoterServiceImpl saveVoterService;

   @Test
   void whenVoterValid_shouldSaveWithNoExceptions() throws DomainValidationException, EntityNotFoundException {
      VoterDto voter = VoterDto.builder()
            .id(1L)
            .region("Massachusetts")
            .votedCandidateNumber(5).build();
      saveVoterService.save(voter);

      verify(saveVoterRepository, times(1)).save(voter);
   }

   @Test
   void whenIdNotProvided_shouldThrowException() {
      VoterDto voter = VoterDto.builder()
            .id(null)
            .region("Massachusetts")
            .votedCandidateNumber(5).build();
      DomainValidationException domainValidationException = assertThrows(DomainValidationException.class, () -> saveVoterService.save(voter));

      assertThat(domainValidationException.getPropertyName(), is("SaveVoterDto.id"));
      assertThat(domainValidationException.getMessage(), is("Voter id not provided!"));
   }

   @Test
   void whenCandidateNumberNotProvided_shouldThrowException() {
      VoterDto voter = VoterDto.builder()
            .id(1L)
            .region("Massachusetts")
            .votedCandidateNumber(null).build();
      DomainValidationException domainValidationException = assertThrows(DomainValidationException.class, () -> saveVoterService.save(voter));

      assertThat(domainValidationException.getPropertyName(), is("SaveVoterDto.votedCandidateNumber"));
      assertThat(domainValidationException.getMessage(), is("Voter candidate choice not provided!"));
   }

}
