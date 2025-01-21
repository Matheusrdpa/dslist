package com.estudo.dslist.repositories;


import com.estudo.dslist.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GameRepository extends JpaRepository<Game,Long>{

}
