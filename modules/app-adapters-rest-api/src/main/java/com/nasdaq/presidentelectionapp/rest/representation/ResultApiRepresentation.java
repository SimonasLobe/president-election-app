package com.nasdaq.presidentelectionapp.rest.representation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultApiRepresentation {

   private Integer candidateNumber;
   private Long totalVotes;

}

