package com.example.mvc;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class LottoService {
    private List<Integer> lottoNum = new ArrayList<>();
    private final List<List<Integer>> history = new ArrayList<>();
    public List<Integer> randomLotto() {
        lottoNum.clear();
        for (int i = 0; i < 6; i++) {
            int num = (int)(Math.random() * 45) + 1;
            lottoNum.add(num);
        }
        history.add(lottoNum);
        return lottoNum;
    }

    public List<List<Integer>> getHistory() {
        return history;
    }
}
