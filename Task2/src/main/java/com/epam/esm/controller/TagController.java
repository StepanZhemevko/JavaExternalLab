package com.epam.esm.controller;

import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
@CrossOrigin
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/{id}")
    public Tag tag(@PathVariable("id") long id) {
        return tagService.findById(id);
    }

    @GetMapping("/all/{page}")
    public List<Tag> getAllCertificates(@PathVariable("page") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        return tagService.findAll(page, size);
    }

    @PostMapping(path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Tag tag(@RequestBody Tag tag) {
        return tagService.create(tag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        tagService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
