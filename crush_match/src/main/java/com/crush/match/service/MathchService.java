package com.crush.match.service;

import com.crush.match.model.entity.Match;
import com.crush.match.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Match createMatch(Match match) {
        return matchRepository.save(match);
    }
}
