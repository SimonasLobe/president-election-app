package com.nasdaq.presidentelectionapp.candidate;

import com.nasdaq.presidentelectionapp.candidate.representation.CandidateApiRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CandidateApiFacade {

   private final FindCandidateService findCandidateService;

   public List<CandidateApiRepresentation> findAll() {
      var candidateApiRepresentations = new ArrayList<CandidateApiRepresentation>();
      findCandidateService.findAll().forEach(findCandidateDto ->
            candidateApiRepresentations.add(CandidateApiRepresentation.builder()
                  .number(findCandidateDto.getNumber())
                  .name(findCandidateDto.getName())
                  .lastName(findCandidateDto.getLastName())
                  .agendaSummary(findCandidateDto.getAgendaSummary())
                  .build()));

      return candidateApiRepresentations;
   }
}
