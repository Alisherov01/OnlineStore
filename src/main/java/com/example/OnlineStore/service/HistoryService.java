package com.example.OnlineStore.service;

import com.example.OnlineStore.repository.HistoryRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HistoryService {
    HistoryRepo historyRepo;
}
