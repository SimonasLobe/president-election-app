package com.nasdaq.presidentelectionapp.rest.representation;

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

