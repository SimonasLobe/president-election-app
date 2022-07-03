package com.nasdaq.presidentelectionapp.candidate.representation;

import lombok.Builder;
import lombok.Data;
import lombok.With;

@Data
@Builder
@With
public class ResultApiRepresentation {

   private Integer candidateNumber;
   private Long totalVotes;

}

