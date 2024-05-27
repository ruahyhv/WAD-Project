package com.example.mvcproducts.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path rootLocation = Paths.get("D:\\resurse facultate\\year3_sem2\\wad\\lab07_wad\\springSecurity-final\\src\\main\\resources\\static\\images");
    private static final Logger logger = Logger.getLogger(StorageServiceImpl.class.getName());

    @Override
    public void save(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file.");
            }
            Files.createDirectories(rootLocation); // Ensure the directory exists
            Path destinationFile = rootLocation.resolve(
                            Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            logger.info("Destination file path: " + destinationFile.toString());
            if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
                throw new RuntimeException("Cannot store file outside current directory.");
            }
            // Delete the file if it already exists
            Files.deleteIfExists(destinationFile);
            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
            logger.info("File successfully saved: " + destinationFile.toString());
        } catch (IOException e) {
            logger.severe("Failed to store file: " + e.getMessage());
            throw new RuntimeException("Failed to store file.", e);
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = rootLocation.resolve(filename).normalize();
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read file: " + filename);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read file: " + filename, e);
        }
    }
}
