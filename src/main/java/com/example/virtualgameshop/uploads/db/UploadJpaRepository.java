package com.example.virtualgameshop.uploads.db;

import com.example.virtualgameshop.uploads.domain.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadJpaRepository extends JpaRepository<Upload,Long> {

}
