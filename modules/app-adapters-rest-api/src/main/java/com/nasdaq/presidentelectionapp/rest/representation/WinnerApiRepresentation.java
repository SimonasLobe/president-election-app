package com.nasdaq.presidentelectionapp.rest.representation;

import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.util.List;

@Data
@Builder
@With
public class WinnerApiRepresentation {

   private List<Integer> candidateNumbers;

}

