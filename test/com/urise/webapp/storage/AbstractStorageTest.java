package com.urise.webapp.storage;

import com.urise.webapp.ResumeTestData;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("D:\\Java\\basejava\\storage");
    private static final String FULL_NAME_1 = "Захарова Евгения";
    private static final String FULL_NAME_2 = "Макаров Александр";
    private static final String FULL_NAME_3 = "Губина Вероника";
    private static final String FULL_NAME_4 = "Громов Леонид";
    private static final String UUID_NOT_EXIST = "dummy";

    protected final Storage storage;
    protected Resume r1;
    protected Resume r2;
    protected Resume r3;

    ResumeTestData resumeTest = new ResumeTestData();

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        r1 = resumeTest.createResume(FULL_NAME_1);
        r2 = resumeTest.createResume(FULL_NAME_2);
        r3 = resumeTest.createResume(FULL_NAME_3);
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        Assert.assertArrayEquals(new Resume[0], storage.getAllSorted().toArray());
    }

    @Test
    public void update() {
        r1.setFullName("Алиева Олеся");
        storage.update(r1);
        Assert.assertEquals(r1, storage.get(r1.getUuid()));
    }

    @Test
    public void save() {
        Resume r4 = resumeTest.createResume(FULL_NAME_4);
        storage.save(r4);
        assertGet(r4);
        assertSize(4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(r1.getUuid());
        assertSize(2);
        storage.get(r1.getUuid());
    }

    @Test
    public void getAllSorted() {
        for(Resume r : storage.getAllSorted()) {
            System.out.println(r.getFullName());
        }
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void get() {
        assertGet(r1);
        assertGet(r2);
        assertGet(r3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_NOT_EXIST);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_NOT_EXIST);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(storage.get(UUID_NOT_EXIST));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(r1);
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }
}