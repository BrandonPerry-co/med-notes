package com.mediscreen.Mednotes.model;

import lombok.*;
import org.springframework.data.annotation.Id;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Notes {
    @Id
    private int patId;
    private String note;

}
