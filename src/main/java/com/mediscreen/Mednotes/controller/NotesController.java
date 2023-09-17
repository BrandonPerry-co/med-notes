package com.mediscreen.Mednotes.controller;

import com.mediscreen.Mednotes.model.Notes;
import com.mediscreen.Mednotes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/add")
    public Notes insert(@RequestParam Integer patId, @RequestParam String note,  RedirectAttributes redirectAttributes) {
       Notes notes = Notes.builder().patId(patId).note(note).build();
        redirectAttributes.addFlashAttribute("message", "Patient notes are successfully added!");
        return notes;
    }

//    @PutMapping("/update")
//    public Notes insert(@RequestParam Integer patId, @RequestParam String note,  RedirectAttributes redirectAttributes) {
//        Notes notes = Notes.builder().patId(patId).note(note).build();
//        redirectAttributes.addFlashAttribute("message", "Patient notes are successfully added!");
//        return notes;
//    }
}
