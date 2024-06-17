package com.crush.match.adapter.out.persistence;

import com.crush.match.domain.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MatchPersistenceAdapter {

    @Autowired
    private MatchRepository matchRepository;

    public Match save(Match match) {
        MatchEntity entity = new MatchEntity();
        entity.setUserId1(match.getUserId1());
        entity.setUserId2(match.getUserId2());
        entity.setMatchedAt(match.getMatchedAt());
        entity = matchRepository.save(entity);
        match.setId(entity.getId());
        return match;
    }

    public List<Match> findAll() {
        return matchRepository.findAll().stream().map(entity -> {
            Match match = new Match();
            match.setId(entity.getId());
            match.setUserId1(entity.getUserId1());
            match.setUserId2(entity.getUserId2());
            match.setMatchedAt(entity.getMatchedAt());
            return match;
        }).collect(Collectors.toList());
    }
}
