package com.tutego.date4u.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.mockito.Mock;

import java.util.Base64;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

// @SpringBootTest
class PhotoServiceTest {                               // tinyurl.com/2p9y49n
    private static final byte[] MINIMAL_JPG = Base64.getDecoder().decode(
            "/9j/4AAQSkZJRgABAQEASABIAAD/2wBDAP////////////////////////////////////"
                    +"//////////////////////////////////////////////////wgALCAABAAEBAREA/8QA"
                    +"FBABAAAAAAAAAAAAAAAAAAAAAP/aAAgBAQABPxA=" );

    private final Logger log = LoggerFactory.getLogger( getClass() );

    @Mock FileSystem fileSystem = mock( FileSystem.class );
    // @Mock(strictness = Mock.Strictness.LENIENT) FileSystem fileSystem;



    @BeforeEach
    void setupFileSystem() {
        given( fileSystem.getFreeDiskSpace()  ).willReturn( 1L );
        given( fileSystem.load( anyString() ) ).willReturn( MINIMAL_JPG );
    }

    @Test
    void successful_photo_upload() {
        // given

        PhotoService photoService=new PhotoService(fileSystem,new AwtBicubicThumbnail());

        // when
        String imageName = photoService.upload( MINIMAL_JPG );

        // then
        Assertions.assertThat( imageName ).isNotEmpty();
    }
}
