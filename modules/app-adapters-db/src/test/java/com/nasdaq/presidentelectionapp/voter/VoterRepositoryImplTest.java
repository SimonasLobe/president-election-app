package com.nasdaq.presidentelectionapp.voter;

import com.nasdaq.presidentelectionapp.database.InMemoryDatabase;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class VoterRepositoryImplTest {

   @Test
   void whenSaveVote_voteIsSaved() {
      var voterRepositoryImpl = new VoterRepositoryImpl();
      var voter = VoterDto.builder()
            .id(1L)
            .region("Massachusetts")
            .votedCandidateNumber(5).build();
      voterRepositoryImpl.save(voter);

      var inMemoryDatabase = InMemoryDatabase.getInstance();
      assertThat(inMemoryDatabase.getVoters().size(), is(1));
      var voterDto = inMemoryDatabase.getVoters().iterator().next();
      assertThat(voterDto.getId(), is(1L));
      assertThat(voterDto.getRegion(), is("Massachusetts"));
      assertThat(voterDto.getVotedCandidateNumber(), is(5));
   }

   @Test
   void whenSaveSameUserVoteTwice_voteIsUpdated() {
      var voterRepositoryImpl = new VoterRepositoryImpl();
      var voter = VoterDto.builder()
            .id(1L)
            .region("Massachusetts")
            .votedCandidateNumber(5).build();
      voterRepositoryImpl.save(voter);
      var voter2 = VoterDto.builder()
            .id(1L)
            .region("Texas")
            .votedCandidateNumber(7).build();
      voterRepositoryImpl.save(voter2);

      var inMemoryDatabase = InMemoryDatabase.getInstance();
      assertThat(inMemoryDatabase.getVoters().size(), is(1));

   }


}
