package com.istad.fileupload.service.fileServiceIMPL;

import com.istad.fileupload.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    Path fileLocationStorage;
    public FileServiceImpl(){
        fileLocationStorage= Path.of("src/main/resources/images");
    }


    @Override
    public String uploadFile(MultipartFile file) throws IOException {

        String filename = file.getOriginalFilename();
        // dog.png

        if (filename != null) {
            // condition for filename
            if (filename.contains("..")) {
                System.out.println("Filename is incorrect !! ");
                return null;
            }
            String[] fileParts = filename.split("\\.");
            filename = UUID.randomUUID() +"." + fileParts[1];
            // asdlfkasfd-asdfkasdjflasd-sdkfj.png
            //resolved path

            Path resolvedPath = fileLocationStorage.resolve(filename);
            Files.copy(file.getInputStream(), resolvedPath, StandardCopyOption.REPLACE_EXISTING);
            return filename;

        }else return null ;
    }


}
