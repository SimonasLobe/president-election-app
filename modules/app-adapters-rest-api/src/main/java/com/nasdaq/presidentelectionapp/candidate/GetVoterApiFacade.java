package com.nasdaq.presidentelectionapp.candidate;

import com.nasdaq.presidentelectionapp.voter.GetVoterService;
import com.nasdaq.presidentelectionapp.voter.VoterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GetVoterApiFacade {

   private final GetVoterService getVoterService;

   List<ResultApiRepresentation> getResults() {
      var result = new ArrayList<ResultApiRepresentation>();

      Map<Integer, Long> resultsByCandidateNumber = getVoterService.getAll().stream()
            .collect(Collectors.groupingBy(VoterDto::getVotedCandidateNumber, Collectors.counting()));

      resultsByCandidateNumber.forEach((candidate, votes) -> result.add(ResultApiRepresentation.builder()
            .candidateNumber(candidate)
            .totalVotes(votes)
            .build()));

      return result;
   }

}
