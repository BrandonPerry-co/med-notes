package com.mediscreen.Mednotes.model;

//import lombok.*;
//import org.springframework.data.annotation.Id;
//
//
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Generated
//public class Notes {
//    @Id
//    private int patId;
//    private String note;
//
//}
import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notes")
public class Notes {

    @Id
    private String id;

    private int patId;

    private String note;

}

