package com.nasdaq.presidentelectionapp.rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoteInput {

   String voterRegion;
   Integer candidateNumber;

}
