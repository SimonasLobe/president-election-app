package com.nasdaq.presidentelectionapp.voter;

import com.nasdaq.presidentelectionapp.database.InMemoryDatabase;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public class VoterRepositoryImpl implements GetVoterRepository, SaveVoterRepository {

   @Override
   public Optional<VoterDto> getById(long id) {
      InMemoryDatabase inMemoryDatabase = InMemoryDatabase.getInstance();
      return inMemoryDatabase.getVoters().stream()
            .filter(v -> id == v.getId())
            .findFirst();
   }

   @Override
   public Set<VoterDto> getAll() {
      InMemoryDatabase inMemoryDatabase = InMemoryDatabase.getInstance();
      return inMemoryDatabase.getVoters();
   }

   @Override
   public void save(VoterDto voterDto) {
      InMemoryDatabase inMemoryDatabase = InMemoryDatabase.getInstance();
      inMemoryDatabase.getVoters().add(voterDto);
   }
}
