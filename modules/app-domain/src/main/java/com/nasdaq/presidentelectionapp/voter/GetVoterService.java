package com.nasdaq.presidentelectionapp.voter;

import com.nasdaq.presidentelectionapp.exceptions.DomainValidationException;

import java.util.Optional;
import java.util.Set;

public interface GetVoterService {

   Optional<VoterDto> getById(Long id) throws DomainValidationException;

   Set<VoterDto> getAll();

}
