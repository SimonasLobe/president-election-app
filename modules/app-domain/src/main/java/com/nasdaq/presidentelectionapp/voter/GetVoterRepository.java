package com.nasdaq.presidentelectionapp.voter;

import java.util.Optional;
import java.util.Set;

public interface GetVoterRepository {

   Optional<VoterDto> getById(long id);

   Set<VoterDto> getAll();
}
