package com.nasdaq.presidentelectionapp.voter;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VoterDto {

   @EqualsAndHashCode.Include
   Long id;
   String region;
   Integer votedCandidateNumber;

}
