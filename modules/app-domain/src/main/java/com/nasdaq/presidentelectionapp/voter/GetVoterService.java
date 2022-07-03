package com.nasdaq.presidentelectionapp.voter;

import com.nasdaq.presidentelectionapp.exceptions.DomainValidationException;

import java.util.Optional;

public interface GetVoterService {

   Optional<VoterDto> getById(Long id) throws DomainValidationException;

}
