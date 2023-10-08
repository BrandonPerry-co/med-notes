package com.mediscreen.Mednotes.repository;

import com.mediscreen.Mednotes.model.Notes;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface NotesRepository extends MongoRepository <Notes, String> {
    List<Notes> findByPatId(int patId);

    Optional<Notes> findById(String id);
}
