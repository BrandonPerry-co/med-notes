package com.mediscreen.Mednotes.controller;

import com.mediscreen.Mednotes.model.Notes;
import com.mediscreen.Mednotes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/patHistory")
public class NotesController {
    @Autowired
    NotesRepository notesRepository;

    @GetMapping("/getAll")
    public List<Notes> list() {
        return notesRepository.findAll();
    }

// adding notes once for each patient, no list of notes
//    @PostMapping("/add")
//    @ResponseBody
//    public ResponseEntity<Notes> insert(@RequestBody Notes notes) {
//        Notes savedNote = notesRepository.save(notes);
//        if (savedNote != null) {
//            return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
//        } else {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    // Adding mutiple notes for same patient
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Notes> insert(@RequestBody Notes notes) {
        Notes savedNote = notesRepository.save(notes);
        if (savedNote != null) {
            return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Updating notes by patId
//    @PutMapping("/{patId}")
//    public ResponseEntity<Notes> update(@PathVariable int patId, @RequestBody Notes updatedNoteData, RedirectAttributes redirectAttributes) {
//
//        Notes existingNote = notesRepository.findById(patId).orElse(null);
//
//        if (existingNote == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        existingNote.setNote(updatedNoteData.getNote());
//        Notes savedNote = notesRepository.save(existingNote);
//        redirectAttributes.addFlashAttribute("message", "Patient notes are successfully updated!");
//
//        return new ResponseEntity<>(savedNote, HttpStatus.OK);
//    }
// update by unique ID
//    @PutMapping("/{id}")
//    public ResponseEntity<Notes> update(@PathVariable String id, @RequestBody Notes updatedNoteData, RedirectAttributes redirectAttributes) {
//
//        Notes existingNote = (Notes) notesRepository.findById(id).orElse(null);
//
//        if (existingNote == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        existingNote.setNote(updatedNoteData.getNote());
//        Notes savedNote = notesRepository.save(existingNote);
//        redirectAttributes.addFlashAttribute("message", "Patient notes are successfully updated!");
//
//        return new ResponseEntity<>(savedNote, HttpStatus.OK);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Notes> update(@PathVariable String id, @RequestBody Notes updatedNoteData) {
        Notes existingNote = notesRepository.findById(id).orElse(null);

        if (existingNote == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingNote.setNote(updatedNoteData.getNote());
        Notes savedNote = notesRepository.save(existingNote);

        return new ResponseEntity<>(savedNote, HttpStatus.OK);
    }


    //Deleting patient notes
    @DeleteMapping("/{patId}")
    public ResponseEntity<String> delete(@PathVariable String id, RedirectAttributes redirectAttributes) {

        Notes existingNote = notesRepository.findById(id).orElse(null);

        if (existingNote == null) {
            return new ResponseEntity<>("Note not found!", HttpStatus.NOT_FOUND);
        }

        notesRepository.deleteById(id);

        redirectAttributes.addFlashAttribute("message", "Patient notes are successfully deleted!");

        return new ResponseEntity<>("Note deleted successfully!", HttpStatus.OK);
    }

    //Delete by ID
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
//
//        Notes existingNote = notesRepository.findById(Integer.valueOf(id)).orElse(null);
//
//        if (existingNote == null) {
//            return new ResponseEntity<>("Note not found!", HttpStatus.NOT_FOUND);
//        }
//
//        notesRepository.deleteById(Integer.valueOf(id));
//
//        redirectAttributes.addFlashAttribute("message", "Patient notes are successfully deleted!");
//
//        return new ResponseEntity<>("Note deleted successfully!", HttpStatus.OK);
//    }


    //Getting all patients notes by ID
    @GetMapping("/{patId}")
    public ResponseEntity<Object> getPatientNotesByPatId(@PathVariable int patId) {

        List<Notes> notesForPatient = notesRepository.findByPatId(patId);

        if (notesForPatient.isEmpty()) {
            return new ResponseEntity<>("No notes found for the provided patient ID!", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(notesForPatient, HttpStatus.OK);
    }
}
