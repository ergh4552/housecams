package com.kpoy.housecams.web.controllers;

import com.kpoy.housecams.repositories.MediaFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
@RequiredArgsConstructor
public class VideoController {
    @Autowired
    private final MediaFileRepository mediaFileRepository;

    @GetMapping("/video/{videoId}")
    public Mono<String> streamVideo(Model model, @PathVariable("videoId") Integer videoId) {
        return mediaFileRepository.findById(videoId).flatMap(mediaFile -> {
            model.addAttribute("baseUrl", mediaFile.getFilename());
            return Mono.empty();
        }).thenReturn("video");
    }
}
/*
        return mediaFileRepository.findById(videoId).flatMap(mediaFile -> {
            try {
                final File initialFile = new File(mediaFile.getFilename());
                final InputStream is =
                        new DataInputStream(new FileInputStream(initialFile));

                byte[] videoResource = is.readAllBytes();

                return ServerResponse.ok()
                        .contentType(MediaType.parseMediaType("video/mp4"))
                        .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=video_%s.%s", 1, "mp4"))
                        .body(BodyInserters.fromValue(videoResource));
            } catch (IOException ex) {
                log.error("Failed to read file: {}", ex.getMessage());
                return ServerResponse.noContent().header(HttpHeaders.WARNING).build();
            }
        });


        IReactiveDataDriverContextVariable reactiveDataDriverContextVariable =
                new ReactiveDataDriverContextVariable(mediaFileRepository.findById(videoId));
        model.addAttribute("video", reactiveDataDriverContextVariable);
        return "video/" + videoId.toString();

 */