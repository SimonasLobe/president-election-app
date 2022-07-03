package com.nasdaq.presidentelectionapp.voter;

import com.nasdaq.presidentelectionapp.exceptions.DomainValidationException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class GetVoterServiceImpl implements GetVoterService {

   private final GetVoterRepository repository;

   @Override
   public Optional<VoterDto> getById(Long id) throws DomainValidationException {

      if (id == null) {
         throw new DomainValidationException("Voter id not provided", "VoterDto.id");
      }

      return repository.getById(id);
   }
}
