package com.nasdaq.presidentelectionapp.rest;

import com.nasdaq.presidentelectionapp.rest.representation.ResultApiPerRegionRepresentation;
import com.nasdaq.presidentelectionapp.rest.representation.ResultApiRepresentation;
import com.nasdaq.presidentelectionapp.rest.representation.WinnerApiRepresentation;
import com.nasdaq.presidentelectionapp.voter.GetVoterService;
import com.nasdaq.presidentelectionapp.voter.VoterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

   public WinnerApiRepresentation getWinner() {
      List<ResultApiRepresentation> results = getResults();

      Optional<ResultApiRepresentation> maxVotes = results.stream()
            .max((o1, o2) -> {
               if (o1.getTotalVotes().longValue() == o2.getTotalVotes().longValue()) {
                  return 0;
               } else {
                  return o1.getTotalVotes().compareTo(o2.getTotalVotes());
               }
            });

      List<ResultApiRepresentation> maxResults = new ArrayList<>();

      if (maxVotes.isPresent()) {
         maxResults = results.stream()
               .filter(result -> maxVotes.get().getTotalVotes().equals(result.getTotalVotes()))
               .toList();
      }

      return WinnerApiRepresentation.builder().candidateNumbers(maxResults.stream()
            .map(ResultApiRepresentation::getCandidateNumber)
            .toList()).build();


   }
}
