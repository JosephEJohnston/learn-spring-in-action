package com.noob.commons.service.impl;

import com.noob.commons.service.TestDependencyService;
import org.springframework.stereotype.Service;

@Service
public class TestDependencyServiceImpl implements TestDependencyService {

    @Override
    public void doTest() {
        System.out.println("Test success!!!");
    }
}
