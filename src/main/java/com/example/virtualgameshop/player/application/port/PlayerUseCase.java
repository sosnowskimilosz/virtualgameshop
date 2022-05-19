package com.example.virtualgameshop.player.application.port;

import com.example.virtualgameshop.player.domain.Player;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PlayerUseCase {

    List<Player> getAll();

    Optional<Player> getById(Long id);

    void removeById(Long id);

    Player addNewPlayer(CreatePlayerCommand command);

    UpdatePlayerResponse updatePlayer(UpdatePlayerCommand command);

    @Value
    class CreatePlayerCommand {
        String email;
        String password;
        BigDecimal credits;

        public Player toPlayer(){
            return new Player(email,password,credits);
        }
    }

    @Value
    class UpdatePlayerCommand {
        Long id;
        String email;
        String password;
        BigDecimal credits;

        public Player updateFields(Player player){
            if(email != null){
                player.setEmail(email);
            }
            if(password != null){
                player.setPassword(password);
            }
            if(credits != null){
                player.setCredits(credits);
            }
            return player;
        }
    }

    @Value
    class UpdatePlayerResponse {
        boolean success;
        List<String> errors;

        public static UpdatePlayerResponse SUCCESS = new UpdatePlayerResponse(true, List.of());
    }
}
