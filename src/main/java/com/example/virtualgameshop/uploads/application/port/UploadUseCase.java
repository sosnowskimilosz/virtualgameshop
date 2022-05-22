package com.example.virtualgameshop.uploads.application.port;


import com.example.virtualgameshop.uploads.domain.Upload;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;
import java.util.Optional;

public interface UploadUseCase {

    Upload save(SaveUploadCommand command);

    Optional<Upload> getById(Long id);

    void removeById(Long id);

    List<Upload> getAll();

    @Value
    @AllArgsConstructor
    class SaveUploadCommand{
        byte[] file;
        String fileName;
        String contentType;

     public Upload toUpload(){
         return new Upload(file,fileName,contentType);
     }
    }
}
