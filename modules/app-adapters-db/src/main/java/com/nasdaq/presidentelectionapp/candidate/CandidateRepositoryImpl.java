package com.nasdaq.presidentelectionapp.candidate;

import com.nasdaq.presidentelectionapp.database.InMemoryDatabase;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class CandidateRepositoryImpl implements FindCandidateRepository {
   @Override
   public Set<CandidateDto> findAll() {
      InMemoryDatabase inMemoryDatabase = InMemoryDatabase.getInstance();
      return inMemoryDatabase.getCandidates();
   }
}
