package com.barberia.app.files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("files")
@CrossOrigin
public class FileController {
    @Autowired
    private FileStorageService fileStorageService;
//    @PostMapping
//    public ResponseEntity<FileResponse> uploadFile(@RequestParam("file") String fileInput){
//        MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipart(fileInput);
//        String fileName = fileStorageService.storeFile(file);
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(fileName).toUriString();
//        System.out.println(fileDownloadUri);
//        FileResponse fileResponse = new FileResponse(fileName, fileDownloadUri,file.getContentType(), file.getSize());
//        return new ResponseEntity<>(fileResponse, HttpStatus.OK);
//    }
    @PostMapping
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("file") MultipartFile file){
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(fileName).toUriString();
        FileResponse fileResponse = new FileResponse(fileName, fileDownloadUri,file.getContentType(), file.getSize());
        return new ResponseEntity<>(fileResponse, HttpStatus.OK);
    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        String contentType = null;

        try{
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (IOException ex){
            System.out.println("Could not determine fileType");
        }

        if(contentType == null){
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}
