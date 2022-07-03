package com.nasdaq.presidentelectionapp.candidate;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FindCandidateServiceImpl implements FindCandidateService {

   private final FindCandidateRepository findCandidateRepository;

   @Override
   public List<FindCandidateDto> findAll() {
      return findCandidateRepository.findAll();
   }
}
