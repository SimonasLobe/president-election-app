package com.nasdaq.presidentelectionapp.rest;

import com.nasdaq.presidentelectionapp.rest.representation.CandidateApiRepresentation;
import com.nasdaq.presidentelectionapp.rest.representation.ResultApiPerRegionRepresentation;
import com.nasdaq.presidentelectionapp.rest.representation.ResultApiRepresentation;
import com.nasdaq.presidentelectionapp.rest.representation.WinnerApiRepresentation;
import com.nasdaq.presidentelectionapp.exceptions.DomainValidationException;
import com.nasdaq.presidentelectionapp.exceptions.EntityNotFoundException;
import com.nasdaq.presidentelectionapp.voter.GetVoterService;
import com.nasdaq.presidentelectionapp.voter.SaveVoterService;
import com.nasdaq.presidentelectionapp.voter.VoterDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class ElectionRestController {

   private final CandidateApiFacade candidateApiFacade;
   private final GetVoterApiFacade getVoterFacade;
   private final SaveVoterService saveVoterService;
   private final GetVoterService getVoterService;

   @Operation(summary = "Get all candidates")
   @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "All candidates are found successfully", content = {
               @Content(mediaType = "application/json", schema = @Schema(implementation = CandidateApiRepresentation.class))})})
   @GetMapping("/candidates")
   public ResponseEntity<List<CandidateApiRepresentation>> getAllCandidates() {
      List<CandidateApiRepresentation> allCandidates = candidateApiFacade.findAll();
      return ResponseEntity.ok(allCandidates);
   }

   @Operation(summary = "Save vote for a candidate. Returns a message if vote was successful or not")
   @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "Voters vote successfully registered", content = {
               @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class))}),
         @ApiResponse(responseCode = "400", description = "Voters vote cannot be registered, parameters are bad", content = {
               @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class))}),
         @ApiResponse(responseCode = "404", description = "Voters vote cannot be registered", content = {
               @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class))})})
   @PostMapping(path = "/vote/{id}")
   public ResponseEntity<ResponseMessage> vote(@Parameter(description = "Unique id of Voter")
                                               @PathVariable Long id,
                                               @RequestBody VoteInput voteInput) {
//todo logging
      try {
         Optional<VoterDto> voterInDB = getVoterService.getById(id);

         if (voterInDB.isPresent()) {
            return ResponseEntity.badRequest().body(ResponseMessage.builder().message("Vote failed! Person already voted").build());
         }
      } catch (DomainValidationException e) {
         return ResponseEntity.badRequest().body(ResponseMessage.builder().message(e.getMessage()).build());
      }

      VoterDto voterDto = VoterDto.builder()
            .id(id)
            .region(voteInput.getVoterRegion())
            .votedCandidateNumber(voteInput.getCandidateNumber())
            .build();
      try {
         saveVoterService.save(voterDto);
      } catch (DomainValidationException e) {
         return ResponseEntity.badRequest().body(ResponseMessage.builder().message(e.getMessage()).build());
      } catch (EntityNotFoundException e) {
         return ResponseEntity.notFound().build();
      }

      return ResponseEntity.ok(ResponseMessage.builder().message("Vote successful!").build());
   }

   @Operation(summary = "Get Overall distribution of votes amongst candidates")
   @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "Voting results collected successfully", content = {
               @Content(mediaType = "application/json", schema = @Schema(implementation = ResultApiRepresentation.class))})})
   @GetMapping("/results")
   public ResponseEntity<List<ResultApiRepresentation>> getResults() {
      List<ResultApiRepresentation> results = getVoterFacade.getResults();
      return ResponseEntity.ok(results);
   }

   @Operation(summary = "Get voting result distribution amongst different regions")
   @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "Voting results per region collected successfully", content = {
               @Content(mediaType = "application/json", schema = @Schema(implementation = ResultApiPerRegionRepresentation.class))})})
   @GetMapping("/resultsPerRegion")
   public ResponseEntity<List<ResultApiPerRegionRepresentation>> getResultsPerRegion() {
      List<ResultApiPerRegionRepresentation> resultsPerRegion = getVoterFacade.getResultsPerRegion();
      return ResponseEntity.ok(resultsPerRegion);
   }

   @Operation(summary = "Returns a single candidate if he/she was voted " +
         "for by more than 50%. Otherwise two most voted candidates returned")
   @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "Winner resolved", content = {
               @Content(mediaType = "application/json", schema = @Schema(implementation = WinnerApiRepresentation.class))})})
   @GetMapping("/winner")
   public ResponseEntity<WinnerApiRepresentation> getWinner() {
      WinnerApiRepresentation resultsPerRegion = getVoterFacade.getWinner();
      return ResponseEntity.ok(resultsPerRegion);
   }

}
