package com.urise.webapp.storage;

import com.urise.webapp.serializer.ObjectStreamSerializer;

public class ObjectStreamStorageTest extends AbstractStorageTest{

    public ObjectStreamStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializer()));
    }
}