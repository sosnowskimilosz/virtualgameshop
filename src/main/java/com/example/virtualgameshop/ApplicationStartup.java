package com.example.virtualgameshop;

import com.example.virtualgameshop.game.application.GameService;
import com.example.virtualgameshop.game.application.port.GameUseCase;
import com.example.virtualgameshop.game.domain.GameType;
import com.example.virtualgameshop.player.application.PlayerService;
import com.example.virtualgameshop.player.application.port.PlayerUseCase;
import com.example.virtualgameshop.player.application.port.PlayerUseCase.CreatePlayerCommand;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import static com.example.virtualgameshop.game.application.port.GameUseCase.*;

@Component
@AllArgsConstructor
public class ApplicationStartup implements CommandLineRunner {

    private final GameService gameService;
    private final PlayerService playerService;

    @Override
    public void run(String... args) throws Exception {
        initData();
        System.out.println(gameService.findAll());
        System.out.println(playerService.getAll());

    }

    private void initData() {
        playerService.addNewPlayer(new CreatePlayerCommand("milosz","abc123",BigDecimal.valueOf(50)));
        playerService.addNewPlayer(new CreatePlayerCommand("adam","lalala",BigDecimal.valueOf(30)));
        playerService.addNewPlayer(new CreatePlayerCommand("pawel","blabla",BigDecimal.valueOf(20)));

        gameService.addGame(new CreateGameCommand(
                "Diablo 2",
                BigDecimal.valueOf(30),
                Duration.of(10, ChronoUnit.MINUTES),
                GameType.RPG));
        gameService.addGame(new CreateGameCommand(
                "Baldur's Gate",
                BigDecimal.valueOf(50),
                Duration.of(50, ChronoUnit.MINUTES),
                GameType.RPG));
        gameService.addGame(new CreateGameCommand(
                "Civilization",
                BigDecimal.valueOf(10),
                Duration.of(90, ChronoUnit.MINUTES),
                GameType.STRATEGY));
    }
}
