package com.nasdaq.presidentelectionapp.candidate;

import com.nasdaq.presidentelectionapp.voter.GetVoterService;
import com.nasdaq.presidentelectionapp.voter.VoterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GetVoterApiFacade {

   private final GetVoterService getVoterService;

   List<ResultApiRepresentation> getResults() {
      Set<VoterDto> allVoters = getVoterService.getAll();
      return getResultsByCadidateNumber(allVoters);
   }

   private List<ResultApiRepresentation> getResultsByCadidateNumber(Set<VoterDto> allVoters) {
      var result = new ArrayList<ResultApiRepresentation>();

      Map<Integer, Long> resultsByCandidateNumber = allVoters.stream()
            .collect(Collectors.groupingBy(VoterDto::getVotedCandidateNumber, Collectors.counting()));

      resultsByCandidateNumber.forEach((candidate, votes) -> result.add(ResultApiRepresentation.builder()
            .candidateNumber(candidate)
            .totalVotes(votes)
            .build()));
      return result;
   }

   public List<ResultApiPerRegionRepresentation> getResultsPerRegion() {
      var resultByRegion = new ArrayList<ResultApiPerRegionRepresentation>();

      Map<String, List<VoterDto>> resultsByRegion = getVoterService.getAll().stream()
            .collect(Collectors.groupingBy(VoterDto::getRegion));

      resultsByRegion.forEach((region, voters) -> resultByRegion.add(ResultApiPerRegionRepresentation.builder()
            .region(region)
            .result(getResultsByCadidateNumber(new HashSet<>(voters)))
            .build()));

      return resultByRegion;
   }
}
