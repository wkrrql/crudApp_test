package com.app.service;


import com.app.dto.Entries;
import com.app.domain.EntriesDB;
import com.app.dto.Status;
import com.app.mapper.Mapper;
import com.app.repository.EntriesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EntriesService {

    public final EntriesRepository repository;

    public final Mapper mapper;

    public EntriesService(EntriesRepository repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page <Entries> getAllEntries(Pageable pageable){
        Page <EntriesDB> entriesDB = repository.findAll(pageable);
        return entriesDB.map(mapper::mapping);
    }

    @Transactional(readOnly = true)
    public Entries getEntryById(Long id){
        EntriesDB findById = repository
                .findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Not found with this id"));

        return mapper.mapping(findById);
    }

    @Transactional
    public Entries createEntries(Entries newEntry){
        if(newEntry.id() != null)
            throw new IllegalArgumentException("Id should be empty");
        if(newEntry.status() != null)
            throw new IllegalArgumentException("Status should be empty");
       var entryToSave = new EntriesDB(
               null,
               newEntry.entry(),
               Status.IN_PROCESS
       );
       return mapper.mapping(repository.save(entryToSave));
    }

    @Transactional
    public Entries updateEntry(Long id, Entries newEntry) {
        EntriesDB entryFoundById = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found entry with id = " + id));

        if(entryFoundById.getStatus() != Status.IN_PROCESS)
            throw new IllegalStateException("Status of entry isn't IN_PROCESS");

        entryFoundById.setEntry(newEntry.entry());
        
        return mapper.mapping(repository.save(entryFoundById));
    }

    @Transactional
    public void deleteAllEntry(){
        if(repository.count() == 0)
            throw new EntityNotFoundException("Entries list is empty");

        repository.updateStatusToDelete(Status.REMOVED);
//        repository.deleteAll(); //это для жёсского удаления
    }

    @Transactional
    public void deleteEntryById(Long id){
        EntriesDB existingEntry = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entry not found"));

        existingEntry.setStatus(Status.REMOVED); //отмечаем на удаление
        repository.save(existingEntry);
//        repository.deleteById(id);  //всё ещё жёсское удаление
    }

    @Transactional
    public Entries completeTaskById(Long id){
        EntriesDB completeEntry = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entry not found"));

        completeEntry.setStatus(Status.COMPLETED);
        repository.save(completeEntry);
        return mapper.mapping(completeEntry);
    }
}
