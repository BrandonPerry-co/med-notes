package com.mediscreen.Mednotes.repository;

import com.mediscreen.Mednotes.model.Notes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotesRepository extends MongoRepository <Notes, Integer> {
}
