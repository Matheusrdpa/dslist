package com.estudo.dslist.services;

import com.estudo.dslist.dto.GameListDTO;
import com.estudo.dslist.entities.GameList;
import com.estudo.dslist.projections.GameMinProjection;
import com.estudo.dslist.repositories.GameListRepository;
import com.estudo.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll(){
        List<GameList> result = gameListRepository.findAll();
        return result.stream().map(x -> new GameListDTO(x)).toList();
    }

    @Transactional
    public void move(Long listId, int sourceIndex, int destinationIndex){
        List<GameMinProjection> result =  gameRepository.searchByList(listId);
        GameMinProjection obj = result.remove(sourceIndex);
        result.add(destinationIndex,obj);

        int min = Math.min(sourceIndex, destinationIndex);
        int max = Math.max(sourceIndex,destinationIndex);

        for (int i = min; i<= max; i++){
            gameListRepository.updateBelongingPosition(listId, result.get(i).getId() ,i);
        }
    }
}
