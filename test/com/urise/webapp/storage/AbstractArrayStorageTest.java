package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest{

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        int freeSpace = AbstractArrayStorage.STORAGE_LIMIT - storage.size();
        try {
            for(int i = 0; i < freeSpace; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("Overflow occurred ahead of time");
        }
        storage.save(new Resume());
    }
}