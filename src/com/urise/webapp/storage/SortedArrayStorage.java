package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage{

    /*
    private static class ResumeComparator implements Comparator<Resume> {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    }
    */

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    protected void insertElement(Resume resume) {
        int index = (Arrays.binarySearch(storage, 0, quantityResume, resume, RESUME_COMPARATOR) * -1) - 1;
        System.arraycopy(storage, index, storage, index + 1, quantityResume - index);
        storage[index] = resume;
    }

    @Override
    public void fillDeletedElement(int index) {
        if(index < quantityResume - 1) {
            System.arraycopy(storage, index + 1, storage, index, quantityResume - 1 - index);
        }
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, quantityResume, searchKey, RESUME_COMPARATOR);
    }
}