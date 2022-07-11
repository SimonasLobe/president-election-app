package com.nasdaq.presidentelectionapp.rest.representation;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WinnerApiRepresentation {

   private List<Integer> candidateNumbers;

}

