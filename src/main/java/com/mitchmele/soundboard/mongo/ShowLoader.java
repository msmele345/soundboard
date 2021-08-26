package com.mitchmele.soundboard.mongo;

import com.mitchmele.soundboard.show.ShowRepository;
import com.mitchmele.soundboard.util.ShowFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShowLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final ShowRepository showRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        log.info("Loading DB");

        ShowFactory factory = ShowFactory.getInstance();

        showRepository.saveAll(factory.produceShows(1));

        log.info("Finished Loading DB. Total Records Loaded: {}", showRepository.count());
    }
}
