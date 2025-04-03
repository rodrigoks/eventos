package br.com.jbusiness.eventos.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;

import br.com.jbusiness.eventos.domain.event.Event;
import br.com.jbusiness.eventos.domain.event.EventRequestDTO;

@Service
public class EventService {

    @Autowired
    private AmazonS3 s3Client;

    @Value("${aws.bucketname}")
    private String bucketName;

    public Event createEvent(EventRequestDTO data) {

        String imageUrl = null;

        if(data.image() != null) {
            imageUrl = this.uploadImg(data.image());
        }

        Event newEvent = new Event(data);
        newEvent.setImageUrl(imageUrl);

        return newEvent;
        
    }

    private String uploadImg(MultipartFile multipartFile) {
        
        String filename = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try {
            File file = this.convertMultiparToFile(multipartFile);
            this.s3Client.putObject(bucketName, filename, file);
            file.delete();
            return this.s3Client.getUrl(bucketName, filename).toString();
        } catch (Exception e) {
            System.out.println("Erro ao subir o arquivo");
            return null;
        }

    }

    private File convertMultiparToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }

}
