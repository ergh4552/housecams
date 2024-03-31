package com.kpoy.housecams.bootstrap;

import com.kpoy.housecams.configuration.VideoConfigProperties;
import com.kpoy.housecams.domain.MediaDirectory;
import com.kpoy.housecams.domain.MediaFile;
import com.kpoy.housecams.repositories.MediaDirectoryRepository;
import com.kpoy.housecams.repositories.MediaFileRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
@Setter
@Getter
public class StoredVideoLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final VideoConfigProperties videoConfigProperties;

    private final MediaDirectoryRepository mediaDirectoryRepository;
    private final MediaFileRepository mediaFileRepository;
    private String fileLocation = null;

    private void loadStoredVideos() {
        if (fileLocation == null) {
            setFileLocation(videoConfigProperties.getDirectoriespath());
        }
        File videoFolders = new File(fileLocation);
        List<File> subDirectories = Arrays.asList(Objects.requireNonNull(videoFolders.listFiles(f -> {
            if (f.isDirectory() && f.getName().startsWith("20")) {
                return true;
            }
            return false;
        })));
        if (!subDirectories.isEmpty()) {
            for (File sub : subDirectories) {
                MediaDirectory md = new MediaDirectory();
                md.setPath(sub.getPath());
                md.setFilename(sub.getName());
                // md = mediaDirectoryRepository.save(md).block();
                md = mediaDirectoryRepository.save(md).block();
                loadVideoFiles(sub, md);
            }
        }
    }

    private void loadVideoFiles(File subDirectory, MediaDirectory md) {
        List<File> videoFiles = Arrays.asList(Objects.requireNonNull(subDirectory.listFiles(f -> {
            // Motion is configured to store the videos in .mkv
            if (!f.isDirectory() && f.getName().endsWith(videoConfigProperties.getExtension())) {
                return true;
            }
            return false;
        })));
        if (!videoFiles.isEmpty()) {
            log.debug("Video files found ({})", videoFiles.size());
            for (File video : videoFiles) {
                MediaFile mf = MediaFile.builder().filename(video.getAbsolutePath()).mediaDirectoryId(md.getId()).build();
                mediaFileRepository.save(mf).block();
            }
        }

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        log.debug("onApplicationEvent: {}", event.toString());
        try {
            loadStoredVideos();
        } catch (Exception ex) {
            log.debug("Exception trying to initialize database");
            loadStoredVideos();
            log.debug("Did it work this way?");
        }
    }
}
