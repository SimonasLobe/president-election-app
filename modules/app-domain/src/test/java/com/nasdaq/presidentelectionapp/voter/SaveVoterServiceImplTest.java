package com.nasdaq.presidentelectionapp.voter;

import com.nasdaq.presidentelectionapp.candidate.CandidateDto;
import com.nasdaq.presidentelectionapp.candidate.FindCandidateRepository;
import com.nasdaq.presidentelectionapp.exceptions.DomainValidationException;
import com.nasdaq.presidentelectionapp.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveVoterServiceImplTest {

   @Mock
   private SaveVoterRepository saveVoterRepository;

   @Mock
   private FindCandidateRepository findCandidateRepository;

   @InjectMocks
   private SaveVoterServiceImpl saveVoterService;

   @Test
   void whenVoterValid_shouldSaveWithNoExceptions() throws DomainValidationException, EntityNotFoundException {
      when(findCandidateRepository.findAll()).thenReturn(Set.of(CandidateDto.builder().number(5).build()));
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

   @Test
   void whenCandidateNotFound_shouldThrowException() {
      when(findCandidateRepository.findAll()).thenReturn(Set.of(CandidateDto.builder().number(5).build()));
      VoterDto voter = VoterDto.builder()
            .id(1L)
            .region("Massachusetts")
            .votedCandidateNumber(11).build();
      EntityNotFoundException entityNotFoundException = assertThrows(EntityNotFoundException.class, () -> saveVoterService.save(voter));

      assertThat(entityNotFoundException.getEntityClass().getSimpleName(), is("VoterDto"));
      assertThat(entityNotFoundException.getId(), is(11));
      assertThat(entityNotFoundException.getMessage(), is("Entity of type VoterDto with ID 11 not found."));
   }

}
