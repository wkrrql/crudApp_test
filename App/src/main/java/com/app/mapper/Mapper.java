package com.app.mapper;

import com.app.domain.EntriesDB;
import com.app.dto.Entries;
import org.springframework.stereotype.Component;


@Component
public class Mapper {
    public Entries mapping(EntriesDB entityDB){
        return new Entries(
                entityDB.getId(),
                entityDB.getEntry(),
                entityDB.getStatus()
        );
    }
}
