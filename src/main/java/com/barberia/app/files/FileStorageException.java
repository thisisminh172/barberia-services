package com.barberia.app.files;

public class FileStorageException extends RuntimeException {
    public FileStorageException(String message){
        super(message);
    }

    public FileStorageException(String message, Throwable cause){
        super(message, cause);
    }
}
