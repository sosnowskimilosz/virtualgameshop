package com.example.virtualgameshop.uploads.application;

import com.example.virtualgameshop.uploads.application.port.UploadUseCase;
import com.example.virtualgameshop.uploads.db.UploadJpaRepository;
import com.example.virtualgameshop.uploads.domain.Upload;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UploadService implements UploadUseCase {

    UploadJpaRepository repository;

    @Override
    public Upload save(SaveUploadCommand command) {
        Upload newUpload = command.toUpload();
        repository.save(newUpload);
        return newUpload;
    }

    @Override
    public Optional<Upload> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void removeById(Long id) {
        repository.findById(id);
    }
}
