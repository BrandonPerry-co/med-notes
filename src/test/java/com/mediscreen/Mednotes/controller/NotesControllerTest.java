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

//    @Test
//    public void testInsertNote() throws Exception {
//        mvc.perform(post("/patHistory/add")
//                        .param("patId", "1")
//                        .param("note", "Test Note")
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.patId").value(1))
//                .andExpect(jsonPath("$.note").value("Test Note"));
////        System.out.println()
//    }



    // the new test starts here
//    @Test
//    public void testGetNote() throws Exception {
//        when(notesRepository.findById(1)).thenReturn(java.util.Optional.of(testNote));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/notes/1"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(containsString("Test Note")));
//    }
//
//    @Test
//    public void testPostNote() throws Exception {
//        when(notesRepository.save(any(Notes.class))).thenReturn(testNote);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/notes/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"patId\":1, \"note\":\"Test Note\"}"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    public void testPutNote() throws Exception {
//        when(notesRepository.findById(1)).thenReturn(java.util.Optional.of(testNote));
//        when(notesRepository.save(any(Notes.class))).thenReturn(testNote);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/notes/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"patId\":1, \"note\":\"Updated Note\"}"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(containsString("Updated Note")));
//    }
//
//    @Test
//    public void testDeleteNote() throws Exception {
//        when(notesRepository.findById(1)).thenReturn(java.util.Optional.of(testNote));
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/notes/1"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//        verify(notesRepository, times(1)).deleteById(1);
//    }
}