package com.dh.catalogservice.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document("Movies")
public class Movie {
    @Id
    private Long id;

    private String name;

    private String genre;

    private String urlStream;
}
