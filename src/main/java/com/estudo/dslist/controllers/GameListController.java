package com.estudo.dslist.controllers;

import com.estudo.dslist.dto.GameDTO;
import com.estudo.dslist.dto.GameListDTO;
import com.estudo.dslist.dto.GameMinDTO;
import com.estudo.dslist.dto.ReplacementDTO;
import com.estudo.dslist.entities.GameList;
import com.estudo.dslist.services.GameListService;
import com.estudo.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

    @Autowired
    private GameListService gameListService;

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameListDTO> findAll(){
        return gameListService.findAll();
    }

    @GetMapping(value = "/{listId}/games")
    public List<GameMinDTO> findByList(@PathVariable Long listId){
        List<GameMinDTO> result = gameService.findByList(listId);
        return result;
    }

    @PostMapping(value = "/{listId}/replacement")
    public void move(@PathVariable  Long listId,@RequestBody ReplacementDTO body){
        gameListService.move(listId,body.getSourceIndex(),body.getDestinationIndex());
    }


}
