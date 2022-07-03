package com.nasdaq.presidentelectionapp.candidate;

import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class FindCandidateServiceImpl implements FindCandidateService {

   private final FindCandidateRepository findCandidateRepository;

   @Override
   public Set<CandidateDto> findAll() {
      return findCandidateRepository.findAll();
   }

}
