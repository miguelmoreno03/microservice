package com.dh.serieservice.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document("Series")
public class Serie {
    @Id
    private Long id;
    private String name;
    private String genre;
    private Integer seasons;

}
