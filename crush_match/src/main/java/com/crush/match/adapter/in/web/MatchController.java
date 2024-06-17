package com.crush.match.adapter.in.web;

import com.crush.match.application.service.MatchService;
import com.crush.match.domain.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping("/create")
    public Match createMatch(@RequestBody RequestCreateMatch command) {
        return matchService.createMatch(command);
    }

    @GetMapping
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }
}
