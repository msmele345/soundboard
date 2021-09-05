package com.mitchmele.soundboard.video.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "YouTubeItem")
public class YouTubeItem {

    @Id
    private String _id;
    private String title;
    private String url;
    private String thumbnailUrl;
    private String description;
//    private String videoDuration;
}
