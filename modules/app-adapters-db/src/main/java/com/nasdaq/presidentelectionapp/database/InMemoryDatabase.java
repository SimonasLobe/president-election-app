package com.nasdaq.presidentelectionapp.database;

import com.nasdaq.presidentelectionapp.candidate.FindCandidateDto;
import lombok.Data;

import java.util.List;

@Data
public class InMemoryDatabase {

   List<FindCandidateDto> candidates;

   private static InMemoryDatabase instance = null;

   private InMemoryDatabase() {
   }

   public static InMemoryDatabase getInstance() {
      if (instance == null) {
         instance = new InMemoryDatabase();
         instance.setCandidates(predifinedCandidates());
      }

      return instance;
   }

   private static List<FindCandidateDto> predifinedCandidates() {
      return List.of(FindCandidateDto.builder().number(1).name("Eric").lastName("Adams")
                  .agendaSummary("You The Voter Have Integrity, Honesty, And Experience When You Vote For me").build(),
            FindCandidateDto.builder().number(2).name("Pete").lastName("Buttigieg")
                  .agendaSummary("For great results, not empty promises.").build(),
            FindCandidateDto.builder().number(3).name("Eric").lastName("Adams")
                  .agendaSummary("Take action, make country a better place to live.").build(),
            FindCandidateDto.builder().number(4).name("Roy").lastName("Cooper")
                  .agendaSummary("Think of the Future, Vote to make a difference!").build(),
            FindCandidateDto.builder().number(5).name("Andrew").lastName("Cuomo")
                  .agendaSummary("Making the World a Better Place!").build(),
            FindCandidateDto.builder().number(6).name("Kamala").lastName("Harris")
                  .agendaSummary("A Voice for the People.").build(),
            FindCandidateDto.builder().number(7).name("Jay").lastName("Inslee")
                  .agendaSummary("Make America Great Again").build(),
            FindCandidateDto.builder().number(8).name("Amy").lastName("Klobuchar")
                  .agendaSummary("For great results, not empty promises.").build(),
            FindCandidateDto.builder().number(9).name("Chris").lastName("Murphy")
                  .agendaSummary("Be the Change, Vote!").build(),
            FindCandidateDto.builder().number(10).name("Alexandria").lastName("Ocasio-Cortez")
                  .agendaSummary("Decisions made from integrity, honesty and experience.").build());
   }
}
