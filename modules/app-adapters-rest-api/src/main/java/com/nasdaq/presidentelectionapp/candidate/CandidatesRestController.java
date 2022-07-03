package com.nasdaq.presidentelectionapp.candidate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest")
public class CandidatesRestController {

   private final CandidateApiFacade candidateApiFacade;

   @Operation(summary = "Get all candidates")
   @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "All candidates are found successfully", content = {
               @Content(mediaType = "application/json", schema = @Schema(implementation = CandidateApiRepresentation.class))})})
   @GetMapping("/candidates")
   public ResponseEntity<List<CandidateApiRepresentation>> getAllCandidates() {
      List<CandidateApiRepresentation> allCandidates = candidateApiFacade.findAll();
      return ResponseEntity.ok(allCandidates);
   }

}
