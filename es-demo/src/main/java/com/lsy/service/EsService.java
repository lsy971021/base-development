package com.lsy.service;

public interface EsService {
    void createIndex();

    void deleteDocV1();

    void exitDocV1();

    void getSourceV1();

    void termVectorsV1();

    void bulkV1();
}
