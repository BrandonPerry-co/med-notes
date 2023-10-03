package com.mediscreen.Mednotes.repository;

import com.mediscreen.Mednotes.model.Notes;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotesRepository extends MongoRepository <Notes, Integer> {
    List<Notes> findByPatId(int patId);

}
