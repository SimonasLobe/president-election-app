package com.nasdaq.presidentelectionapp.rest.representation;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResultApiPerRegionRepresentation {

   private String region;
   private List<ResultApiRepresentation> result;

}

