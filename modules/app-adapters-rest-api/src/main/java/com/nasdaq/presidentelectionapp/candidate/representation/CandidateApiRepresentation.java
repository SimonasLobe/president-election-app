package com.nasdaq.presidentelectionapp.candidate.representation;

import lombok.Builder;
import lombok.Data;
import lombok.With;

@Data
@Builder
@With
public class CandidateApiRepresentation {

   private Integer number;
   private String name;
   private String lastName;
   private String agendaSummary;

}

