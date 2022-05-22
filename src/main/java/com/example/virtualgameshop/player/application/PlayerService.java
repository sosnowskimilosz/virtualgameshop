package com.example.virtualgameshop.player.application;

import com.example.virtualgameshop.player.application.port.PlayerUseCase;
import com.example.virtualgameshop.player.db.PlayerRepository;
import com.example.virtualgameshop.player.domain.Player;
import com.example.virtualgameshop.uploads.application.port.UploadUseCase;
import com.example.virtualgameshop.uploads.application.port.UploadUseCase.SaveUploadCommand;
import com.example.virtualgameshop.uploads.domain.Upload;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlayerService implements PlayerUseCase {

    private final PlayerRepository playerRepository;
    private final UploadUseCase uploadService;

    @Override
    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    @Override
    public Optional<Player> getById(Long id) {
        return playerRepository.findById(id);
    }

    @Override
    public void removeById(Long id) {
        playerRepository.deleteById(id);
    }

    @Override
    public Player addNewPlayer(CreatePlayerCommand command) {
        Player newPlayer = command.toPlayer();
        return playerRepository.save(newPlayer);
    }

    @Override
    public UpdatePlayerResponse updatePlayer(UpdatePlayerCommand command) {
        return playerRepository.findById(command.getId())
                .map(player -> {
                    Player updatedPlayer = command.updateFields(player);
                    playerRepository.save(updatedPlayer);
                    return UpdatePlayerResponse.SUCCESS;
                }).orElse(new UpdatePlayerResponse(false, Collections.singletonList("Player not found with following ID: " + command.getId())));
    }

    @Override
    public void updateUserAvatar(UpdateUserAvatarCommand command) {
        playerRepository.findById(command.getId())
                .ifPresent(player -> {
                    Upload upload = uploadService.save(new SaveUploadCommand(command.getFile(), command.getFileName(), command.getContentType()));
                    player.setAvatarId(upload.getId());
                    playerRepository.save(player);
                });
    }

    @Override
    public void removeUserAvatar(Long id) {
        playerRepository.findById(id)
                .ifPresent(player -> {
                    if (player.getAvatarId() != null) {
                        uploadService.removeById(player.getAvatarId());
                        player.setAvatarId(null);
                        playerRepository.save(player);
                    }
                });
    }
}
