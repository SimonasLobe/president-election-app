package com.nasdaq.presidentelectionapp.candidate;

import java.util.Set;

public interface FindCandidateRepository {

   Set<CandidateDto> findAll();

}
