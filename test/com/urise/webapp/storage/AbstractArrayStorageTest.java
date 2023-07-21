package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final int STORAGE_LIMIT = 10000;
    private final Storage storage;
    private final Resume r1 = new Resume(UUID_1);
    private final Resume r2 = new Resume(UUID_2);
    private final Resume r3 = new Resume(UUID_3);
    private final Resume[] resumes = new Resume[]{r1, r2, r3};

    public AbstractArrayStorageTest(ArrayStorage arrayStorage) {
        storage = arrayStorage;
    }

    public AbstractArrayStorageTest(SortedArrayStorage sortedArrayStorage) {
        storage = sortedArrayStorage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test(expected = NotExistStorageException.class)
    public void clear() {
        storage.clear();
        storage.get(UUID_1);
    }

    @Test
    public void update() {
        storage.update(storage.get(UUID_1));
        Assert.assertEquals(r1, storage.get(UUID_1));
    }

    @Test
    public void save() {
        Resume r4 = new Resume("uuid4");
        storage.save(r4);
        Assert.assertEquals(r4, storage.get("uuid4"));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        storage.get(UUID_1);
    }

    @Test
    public void getAll() {
        Assert.assertArrayEquals(resumes, storage.getAll());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(r1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void getExist() {
        storage.save(r1);
    }

    @Test(expected = StorageException.class)
    public void getStorageOverflow() {
        int freeSpace = STORAGE_LIMIT - storage.size();
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