package com.estudo.dslist.services;


import com.estudo.dslist.dto.GameDTO;
import com.estudo.dslist.dto.GameMinDTO;
import com.estudo.dslist.entities.Game;
import com.estudo.dslist.projections.GameMinProjection;
import com.estudo.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public GameDTO findById(Long id){
         Game result = gameRepository.findById(id).get();
         return new GameDTO(result);

    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll(){
      List<Game> result =  gameRepository.findAll();
      return result.stream().map(x -> new GameMinDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findByList(Long listId){
        List<GameMinProjection> result =  gameRepository.searchByList(listId);
        return result.stream().map(x -> new GameMinDTO(x)).toList();
    }

}
