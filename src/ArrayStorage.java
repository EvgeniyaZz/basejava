/**
 * Array based storage for Resumes
 */
import java.util.Arrays;

public class ArrayStorage {

    private int quantityResume;
    Resume[] storage = new Resume[10000];

    void clear() {
        for(int i = 0; i < quantityResume; i++) {
            storage[i] = null;
        }
        quantityResume = 0;
    }

    void save(Resume r) {
        storage[quantityResume++] = r;
    }

    Resume get(String uuid) {
        for(int i = 0; i < quantityResume; i++) {
            if(storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for(int i = 0; i < quantityResume; i++) {
            if(storage[i].uuid.equals(uuid)) {
                if(i != storage.length - 1) {
                    System.arraycopy(storage, i + 1, storage, i, quantityResume - 1 - i);
                    storage[quantityResume - 1] = null;
                } else {
                    storage[i] = null;
                }
                quantityResume--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, quantityResume);
    }

    int size() {
        return quantityResume;
    }
}