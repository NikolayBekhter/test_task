package com.example.test.servises;

import com.example.test.entities.Counter;
import com.example.test.repositories.CounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CounterService {
    private int currentValue = 0;
    private final CounterRepository repository;

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
        Counter counter = new Counter();
        counter.setMyValue(currentValue);
        repository.save(counter);
    }

    public void incrementValue() {
        currentValue++;
        Counter counter = new Counter();
        counter.setMyValue(currentValue);
    }

    public int getCurrentValue() {
        return currentValue;
    }
}
