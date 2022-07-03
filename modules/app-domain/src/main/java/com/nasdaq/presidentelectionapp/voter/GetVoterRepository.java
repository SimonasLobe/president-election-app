package com.nasdaq.presidentelectionapp.voter;

import java.util.Optional;

public interface GetVoterRepository {

   Optional<VoterDto> getById(long id);

}
