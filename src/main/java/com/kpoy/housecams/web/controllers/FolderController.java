package com.kpoy.housecams.web.controllers;

import com.kpoy.housecams.repositories.MediaDirectoryRepository;
import com.kpoy.housecams.repositories.MediaFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring6.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;

@Slf4j
@RequiredArgsConstructor
@RequestMapping()
@Controller
public class FolderController {
    private final MediaDirectoryRepository mediaDirectoryRepository;
    private final MediaFileRepository mediaFileRepository;

    @GetMapping("/history")
    public String allFolders(final Model model) {
        IReactiveDataDriverContextVariable reactiveDataDriverContextVariable =
                new ReactiveDataDriverContextVariable(mediaDirectoryRepository.findAll(Sort.by(Sort.Direction.DESC, "path", "filename")));
        model.addAttribute("folders", reactiveDataDriverContextVariable);
        log.debug("allFolders");
        return "history/folderList";
    }

    @GetMapping("/history/{id}")
    public String oneFolder(final Model model, @PathVariable("id")Integer id) {
        IReactiveDataDriverContextVariable reactiveDataDriverContextVariable =
                new ReactiveDataDriverContextVariable(mediaFileRepository.findByMediaDirectoryId(id, Sort.by(Sort.Direction.DESC, "filename")));
        model.addAttribute("videos", reactiveDataDriverContextVariable);

        log.debug("oneFolder {}", id);
        return "history/folder";
    }
}
