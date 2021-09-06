package com.lsy.service;

public interface EsService {
    void createIndex();

    void deleteDocV1();

    void exitDocV1();

    void getSourceV1();

    void updateV1();

    void bulkV1();

    void deleteByQueryV1();

    void termVectorsV1();

    void multiGetDocV1();

    void updateByQueryV1();

    void searchDocV1();
}
