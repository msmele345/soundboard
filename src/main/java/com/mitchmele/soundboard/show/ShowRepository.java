package com.mitchmele.soundboard.show;

import com.mitchmele.soundboard.show.model.ShowDO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends MongoRepository<ShowDO, String> {
}
