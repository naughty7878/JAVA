package com.test.es.service;

import java.io.IOException;

public interface IndexService {
    void add() throws IOException;

    void get() throws IOException;

    void delete() throws IOException;

    void close() throws IOException;
}
