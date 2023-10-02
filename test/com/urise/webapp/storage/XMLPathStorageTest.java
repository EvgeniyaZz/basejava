package com.urise.webapp.storage;

import com.urise.webapp.serializer.XmlStreamSerializer;

public class XMLPathStorageTest extends AbstractStorageTest{

    public XMLPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlStreamSerializer()));
    }
}