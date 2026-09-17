package com.app.controller;

import com.app.dto.Entries;
import com.app.service.EntriesService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.slf4j.LoggerFactory.getLogger;

@RequestMapping("api/entries")
@RestController
public class EntriesController {
    private final EntriesService entriesService;

    private static final Logger log = getLogger(EntriesController.class);

    public EntriesController(EntriesService entriesService) {
        this.entriesService = entriesService;
    }

    @GetMapping("/{id}")
    public Entries getEntryById(@PathVariable Long id){
        log.info("Выполняется получение записи по айди");
        return entriesService.getEntryById(id);
    }

    @GetMapping("/all")
    public Page <Entries> getAllEntries(@PageableDefault(
            size = 3,
            page = 0,
            sort = "id",
            direction = Sort.Direction.ASC
    )Pageable pageable
    ) {
        log.info("Выполнился get запрос getEntries");
        return entriesService.getAllEntries(pageable);
    }

    @PostMapping
    public ResponseEntity<Entries> createEntries(@RequestBody @Valid Entries newEntry){
        log.info("Выполнилось создание записи");
        Entries created = entriesService.createEntries(newEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public Entries updateEntry(@PathVariable Long id, @RequestBody @Valid Entries editEntry){
        log.info("Обновление записи");
        return entriesService.updateEntry(id, editEntry);
    }

    @PatchMapping("/{id}")
    public Entries completeTaskById(@PathVariable Long id){
        log.info("Запись выполнена");
        return entriesService.completeTaskById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllEntries(){
        log.info("Удалены все записи");
        entriesService.deleteAllEntry();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntryById(@PathVariable Long id){
        log.info("Удалена запись по id");
        entriesService.deleteEntryById(id);
        return ResponseEntity.noContent().build();
    }

}
