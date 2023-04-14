package Design;

public class MyHashMap {
    private int[] storage = new int[(int) Math.pow(10,6)+1];
    private boolean[] keyStorage = new boolean[(int) Math.pow(10,6)+1];
    public MyHashMap() {
    }

    public void put(int key, int value) {
        if(get(key)==-1){
            keyStorage[key] = true;
        }
        storage[key] = value;
    }

    public int get(int key) {
        if(keyStorage[key]==false){
            return -1;
        }
        return storage[key];
    }

    public void remove(int key) {
        keyStorage[key] = false;
    }
}
