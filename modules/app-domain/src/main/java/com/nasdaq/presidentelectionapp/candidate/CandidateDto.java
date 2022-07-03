package com.nasdaq.presidentelectionapp.candidate;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.With;

@Data
@Builder
@With
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CandidateDto {

   @EqualsAndHashCode.Include
   private Integer number;
   private String name;
   private String lastName;
   private String agendaSummary;

}
