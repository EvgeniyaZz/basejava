package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class AbstractMapStorageTest extends AbstractStorageTest{

    public AbstractMapStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    public void getAll() {
        Resume[] expectedResumes = {r1, r2, r3};
        Resume[] allResumes = storage.getAll();
        assertEquals(3, allResumes.length);
        int same = 0;
        for(Resume r : allResumes) {
            for (int j = 0; j < allResumes.length; j++) {
                if (r.equals(expectedResumes[j])) {
                    expectedResumes[j] = null;
                    same++;
                    break;
                }
            }
        }
        assertEquals(3, same);
    }
}