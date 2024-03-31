package com.kpoy.housecams.bootstrap;

import com.kpoy.housecams.configuration.ApplicationConfiguration;
import com.kpoy.housecams.configuration.InitializerConfiguration;
import com.kpoy.housecams.configuration.VideoConfigProperties;
import com.kpoy.housecams.repositories.MediaDirectoryRepository;
import com.kpoy.housecams.repositories.MediaFileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.ContextRefreshedEvent;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
    Note the two imports of configuration classes:
    needed to initialize and populate the database schema
 */
@DataR2dbcTest
@Import({ApplicationConfiguration.class, InitializerConfiguration.class})
class StoredVideoLoaderTest {

    @Autowired
    MediaDirectoryRepository mediaDirectoryRepository;
    @Autowired
    MediaFileRepository mediaFileRepository;

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    private VideoConfigProperties videoConfigProperties;

    StoredVideoLoader storedVideoLoader;

    @Test
    void onApplicationEvent() {
        ContextRefreshedEvent contextRefreshedEvent = new ContextRefreshedEvent(appContext);
        storedVideoLoader.onApplicationEvent(contextRefreshedEvent);
        assertTrue(mediaDirectoryRepository.count().block() > 0, "Repository should have content");
    }

    @BeforeEach
    void setUp() {
        storedVideoLoader = new StoredVideoLoader(videoConfigProperties, mediaDirectoryRepository, mediaFileRepository);
    }
}