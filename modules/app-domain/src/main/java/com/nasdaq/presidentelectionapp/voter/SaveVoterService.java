package com.nasdaq.presidentelectionapp.voter;

import com.nasdaq.presidentelectionapp.exceptions.DomainValidationException;
import com.nasdaq.presidentelectionapp.exceptions.EntityNotFoundException;

public interface SaveVoterService {

   void save(VoterDto voterDto) throws DomainValidationException, EntityNotFoundException;

}
