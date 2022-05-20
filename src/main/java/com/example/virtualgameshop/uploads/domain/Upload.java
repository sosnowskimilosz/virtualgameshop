package com.example.virtualgameshop.uploads.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Upload {

    @Id
    @GeneratedValue
    Long id;
    byte[] file;
    String fileName;
    String contentType;
    LocalDateTime createdTime;

    public Upload(byte[] file, String fileName, String contentType) {
        this.file = file;
        this.fileName = fileName;
        this.contentType = contentType;
    }
}
