package com.nasdaq.presidentelectionapp.candidate;

import lombok.Builder;
import lombok.Data;
import lombok.With;

@Data
@Builder
@With
public class FindCandidateDto {

   private Integer number;
   private String name;
   private String lastName;
   private String agendaSummary;

}
