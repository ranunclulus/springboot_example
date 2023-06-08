package com.example.mvc;

import org.springframework.stereotype.Service;

@Service
public class LottoService {
    private int hits = 0;

    public int addHit() {
        hits++;
        return hits;
    }
}
