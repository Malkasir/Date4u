package com.tutego.date4u.interfaces;

import com.tutego.date4u.Date4uProperties;
import com.tutego.date4u.core.FileSystem;
import com.tutego.date4u.core.Photo;
import jakarta.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.*;
import org.springframework.util.unit.DataSize;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;
import java.util.Set;

@ShellComponent
public class FsCommands {
    private final FileSystem fs;

    private final Date4uProperties date4uProperties;

    @Autowired
    Validator validator;



    @Autowired
    public FsCommands(FileSystem fs, Date4uProperties date4uProperties) {
        this.fs = fs;
        this.date4uProperties = date4uProperties;
    }

    @ShellMethod( "Display required free disk space" )
    public long minimumFreeDiskSpace() {

        return date4uProperties.getFilesystem().getMinimumFreeDiskSpace();
    }



    @ShellMethod( "Lowercase string" )
    public String toLowercase( String input ) { return input.toLowerCase(); }

    @ShellMethod( "Free Disk space" )
    public Long freeDiskSpace( ) {
        return DataSize.ofBytes(fs.getFreeDiskSpace()).toGigabytes();
    }




}