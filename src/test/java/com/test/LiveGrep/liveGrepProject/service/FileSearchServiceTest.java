package com.test.LiveGrep.liveGrepProject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileSearchServiceTest {

    @Autowired
    private FileSearchService fileSearchService;

    @BeforeEach
    void setUp() {
        fileSearchService = new FileSearchService();
    }

}
