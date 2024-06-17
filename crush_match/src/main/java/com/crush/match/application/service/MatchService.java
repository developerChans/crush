package com.crush.match.application.service;

import com.crush.match.adapter.in.web.RequestCreateMatch;
import com.crush.match.adapter.out.persistence.MatchPersistenceAdapter;
import com.crush.match.domain.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchPersistenceAdapter matchPersistenceAdapter;

    public Match createMatch(RequestCreateMatch command) {
        Match match = new Match();
        match.setUserId1(command.getUserId1());
        match.setUserId2(command.getUserId2());
        match.setMatchedAt(new Timestamp(System.currentTimeMillis()));
        return matchPersistenceAdapter.save(match);
    }

    public List<Match> getAllMatches() {
        return matchPersistenceAdapter.findAll();
    }
}
