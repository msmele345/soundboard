package com.mitchmele.soundboard.video;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "youtube")
public class YoutubeProperties {

    private String apiKey;
    private String type;
    private Long maxResults;
}
