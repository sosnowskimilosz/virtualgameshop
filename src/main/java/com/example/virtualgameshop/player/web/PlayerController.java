package com.example.virtualgameshop.player.web;

import com.example.virtualgameshop.CreatedURI;
import com.example.virtualgameshop.player.application.PlayerService;
import com.example.virtualgameshop.player.application.port.PlayerUseCase;
import com.example.virtualgameshop.player.application.port.PlayerUseCase.CreatePlayerCommand;
import com.example.virtualgameshop.player.application.port.PlayerUseCase.UpdatePlayerCommand;
import com.example.virtualgameshop.player.application.port.PlayerUseCase.UpdatePlayerResponse;
import com.example.virtualgameshop.player.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/player")
public class PlayerController {

    PlayerService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Player> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getById(@PathVariable Long id){
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        service.removeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createPlayer(@Valid @RequestBody PlayerRestCommand command){
        Player newPlayer = service.addNewPlayer(command.toCreatePlayerCommand());
        URI uri = createPlayerUri(newPlayer);
        return ResponseEntity.created(uri).build();
    }

    private URI createPlayerUri(Player player) {
        return new CreatedURI("/" + player.getId().toString()).uri();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createPlayer(@PathVariable Long id, @RequestBody PlayerRestCommand command){
        UpdatePlayerResponse response = service.updatePlayer(command.toUpdatePlayerCommand(id));
        if(!response.isSuccess()){
            String message = String.join(", ",response.getErrors());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,message);
        }
    }

    @Data
    private static class PlayerRestCommand {
        @NotBlank(message = "Email can not be empty")
        String email;
        @NotBlank(message = "PAssword can not be empty")
        String password;
        @PositiveOrZero(message = "Credits can not be negative")
        BigDecimal credits;

        CreatePlayerCommand toCreatePlayerCommand(){
            return new CreatePlayerCommand(email,password,credits);
        }

        UpdatePlayerCommand toUpdatePlayerCommand(Long id){
            return new UpdatePlayerCommand(id, email, password, credits);
        }
    }
}
