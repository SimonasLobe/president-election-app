package com.nasdaq.presidentelectionapp.voter;

import com.nasdaq.presidentelectionapp.candidate.CandidateDto;
import com.nasdaq.presidentelectionapp.candidate.FindCandidateRepository;
import com.nasdaq.presidentelectionapp.exceptions.DomainValidationException;
import com.nasdaq.presidentelectionapp.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SaveVoterServiceImpl implements SaveVoterService {

   private final SaveVoterRepository saveVoterRepository;
   private final FindCandidateRepository findCandidateRepository;

   @Override
   public void save(VoterDto voterDto) throws DomainValidationException, EntityNotFoundException {

      if (voterDto.getId() == null) {
         throw new DomainValidationException("Voter id not provided!", "SaveVoterDto.id");
      }

      if (voterDto.getVotedCandidateNumber() == null) {
         throw new DomainValidationException("Voter candidate choice not provided!", "SaveVoterDto.votedCandidateNumber");
      }

      Set<Integer> candidates = findCandidateRepository.findAll().stream().map(CandidateDto::getNumber).collect(Collectors.toSet());
      if (!candidates.contains(voterDto.getVotedCandidateNumber())) {
         throw new EntityNotFoundException(VoterDto.class, voterDto.getVotedCandidateNumber());
      }

      saveVoterRepository.save(voterDto);
   }
}
