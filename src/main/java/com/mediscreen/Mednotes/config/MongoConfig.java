//package com.mediscreen.Mednotes.config;
//
//
//import com.mediscreen.Mednotes.model.Notes;
//import com.mediscreen.Mednotes.repository.NotesRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
//@EnableMongoRepositories(basePackageClasses = NotesRepository.class)
//@Configuration
//public class MongoConfig {
//    @Bean
//    CommandLineRunner commandLineRunner(NotesRepository notesRepository) {
//        return strings -> {
//            notesRepository.save(new Notes(1, "These are the notes"));
//            notesRepository.save(new Notes(2, "These are the notes"));
//        };
//    }
//}

package com.mediscreen.Mednotes.config;

import com.mediscreen.Mednotes.model.Notes;
import com.mediscreen.Mednotes.repository.NotesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = NotesRepository.class)
@Configuration
public class MongoConfig {

    @Bean
    CommandLineRunner commandLineRunner(NotesRepository notesRepository) {
        return strings -> {
            notesRepository.save(new Notes(null, 1, "These are the notes"));
            notesRepository.save(new Notes(null, 2, "These are the notes"));
        };
    }
}

