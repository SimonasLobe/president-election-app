package com.nasdaq.presidentelectionapp.rest.representation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandidateApiRepresentation {

   private Integer number;
   private String name;
   private String lastName;
   private String agendaSummary;

}

