package com.example.Project_Spring.services;

import com.example.Project_Spring.mappers.ProposalMapper;
import com.example.Project_Spring.models.Proposal;
import com.example.Project_Spring.models.ProposalDto;
import com.example.Project_Spring.repositories.ProposalRepository;
import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ProposalService {

    ProposalRepository proposalRepository;
    ProposalMapper proposalMapper;

  public void saveProposal(String topic, String textMessage, @Nullable Long senderId, @Nullable String senderEmail, @Nullable String nameAndSurname){

      ProposalDto proposalDto = ProposalDto.builder()
              .topic(topic)
              .textMessage(textMessage)
              .senderId(senderId)
              .senderEmail(senderEmail)
              .nameAndSurname(nameAndSurname)
              .creationDate(LocalDateTime.now())
              .readed(false)
              .build();

      System.out.println("zapisywanie");
      proposalRepository.save(proposalMapper.reverseMap(proposalDto));
  }

  public List<Proposal> findAllProposals(){
      return proposalRepository.findAll();
  }


  public Optional<Proposal> findProposalById(Long id){
      return proposalRepository.findById(id);
  }

  public void updateProposalReadedParameter(Long id){

      Proposal proposal = proposalRepository.findById(id).get();
      proposal.setReaded(true);
      proposalRepository.save(proposal);

  }

}
