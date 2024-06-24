package com.tutego.date4u.interfaces;

import com.tutego.date4u.core.AwtBicubicThumbnail;
import com.tutego.date4u.core.FileSystem;
import com.tutego.date4u.core.Photo;
import com.tutego.date4u.core.PhotoService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;


import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Set;

import static org.springframework.shell.command.invocation.InvocableShellMethod.log;

@ShellComponent
public class PhotoCommands {

    @Autowired
    private FileSystem fs;

    @Autowired
    private final PhotoService photoService;

    private final AwtBicubicThumbnail awtBicubicThumbnail;

    @Autowired
    Validator validator;

    public PhotoCommands(FileSystem fs, PhotoService photoService, AwtBicubicThumbnail awtBicubicThumbnail) {
        this.fs = fs;
        this.photoService = photoService;
        this.awtBicubicThumbnail = awtBicubicThumbnail;
    }

    @ShellMethod( "Show photo" )
    String showPhoto( String name ) {  // show-photo
        return photoService.download( name )
                .map( bytes -> {
                    try {
                        var image = ImageIO.read( new ByteArrayInputStream( bytes ) );
                        return "Width: " + image.getWidth() + ", Height: " + image.getHeight();
                    }
                    catch ( IOException e ) {
                        return "Unable to read image dimensions";
                    }
                } )
                .orElse( "Image not found" );
    }

    @ShellMethod( "Upload photo" )
    String uploadPhoto( String filename ) throws IOException {// show-photo

        byte[] bytes = Files.readAllBytes( Paths.get( filename ) );
        return photoService.upload(bytes);
    }

    @ShellMethod( "Insert a new photo" )
    String insertPhoto( Long id, long profile, String name,
                        boolean isProfilePhoto, String created ) {

        Photo photo = new Photo( id, profile, name, isProfilePhoto,
                LocalDateTime.parse( created ) );

//        Photo photo = new Photo();
//        try {
//            photoService.download( photo );
//        }
//        catch ( ConstraintViolationException e ) {
//            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
//            log.info( violations.toString() );
//        }

        Set<ConstraintViolation<Photo>> violationSet = validator.validate( photo );



        // Insert photo into database if violationSet.isEmpty()

        return violationSet.isEmpty() ? "Photo inserted"
                : "Photo not inserted\n" + violationSet;
    }


}
