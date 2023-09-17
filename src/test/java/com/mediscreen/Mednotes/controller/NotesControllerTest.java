package com.mediscreen.Mednotes.controller;

import com.mediscreen.Mednotes.model.Notes;
import com.mediscreen.Mednotes.repository.NotesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotesController.class)
public class NotesControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private NotesRepository notesRepository;

    @Test
    public void testGetAllNotes() throws Exception {
        Notes mockNote = Notes.builder().patId(1).note("Test Note").build();
        given(notesRepository.findAll()).willReturn(Collections.singletonList(mockNote));

        mvc.perform(get("/patHistory/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].patId").value(1))
                .andExpect(jsonPath("$[0].note").value("Test Note"));
        System.out.println(mockNote);
    }

    @Test
    public void testInsertNote() throws Exception {
        mvc.perform(post("/patHistory/add")
                        .param("patId", "1")
                        .param("note", "Test Note")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patId").value(1))
                .andExpect(jsonPath("$.note").value("Test Note"));
//        System.out.println()
    }
}