package com.mitchmele.soundboard.show.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Show")
public class ShowDO {

    @Id
    private String _id;

    private String url;
    private String bandName;
    private String date;
    private String location;
}
