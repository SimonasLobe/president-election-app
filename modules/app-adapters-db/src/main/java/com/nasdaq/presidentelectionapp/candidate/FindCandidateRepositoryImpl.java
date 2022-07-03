package com.nasdaq.presidentelectionapp.candidate;

import com.nasdaq.presidentelectionapp.database.InMemoryDatabase;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FindCandidateRepositoryImpl implements FindCandidateRepository {
   @Override
   public List<FindCandidateDto> findAll() {
      InMemoryDatabase inMemoryDatabase = InMemoryDatabase.getInstance();
      return inMemoryDatabase.getCandidates();
   }
}
