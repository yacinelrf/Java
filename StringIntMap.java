public class StringIntMap {
    private int size;
    private StringIntCouple[] pairs;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int CAP_MULTIPLIER = 2;

    /* Create an empty map with a default capacity equal to DEFAULT_CAPACITY. */
    public StringIntMap() {
        pairs = new StringIntCouple[DEFAULT_CAPACITY];
        size = 0;
    }

    /* Create a copy of the map other */
    public StringIntMap(StringIntMap other) {
        this.size = other.size;
        this.pairs = new StringIntCouple[other.pairs.length];
        for (int i = 0; i < other.size; i++) {
            this.pairs[i] = new StringIntCouple(other.pairs[i].fst(), other.pairs[i].snd());
        }
    }

    /* Return the size of the current map */
    public int size() {
        return size;
    }

    /* Return the value of the associated key, if present or defaultValue if not */
    public int getOrDefault(String key, int defaultValue) {
        for (int i = 0; i < size; i++) {
            if (pairs[i].fst().equals(key)) {
                return pairs[i].snd();
            }
        }
        return defaultValue;
    }

    /* Return true if and only if key is present in the map */
    public boolean containsKey(String key) {
        for (int i = 0; i < size; i++) {
            if (pairs[i].fst().equals(key)) {
                return true;
            }
        }
        return false;
    }

    
    public int put(String key, int value) {
        for (int i = 0; i < size; i++) {
            if (pairs[i].fst().equals(key)) {
                int oldValue = pairs[i].snd();
                pairs[i].setSnd(value);
                return oldValue;
            }
        }

        // Check if we need to resize the array
        if (size == pairs.length) {
            resize();
        }

        pairs[size++] = new StringIntCouple(key, value);
        return -1;
    }

    /* Resize the array when necessary */
    private void resize() {
        StringIntCouple[] newPairs = new StringIntCouple[pairs.length * CAP_MULTIPLIER];
        System.arraycopy(pairs, 0, newPairs, 0, size);
        pairs = newPairs;
    }

    /* If key is not present, add the new (key, 1) association, otherwise increment
       * the value associated to key */
    public void addOne(String key) {
        for (int i = 0; i < size; i++) {
            if (pairs[i].fst().equals(key)) {
                pairs[i].incrSnd();
                return;
            }
        }

        // Check if we need to resize the array
        if (size == pairs.length) {
            resize();
        }

        pairs[size++] = new StringIntCouple(key, 1);
    }

    /* removes the (key, value) association associated to key if it is present,
       * otherwise do not change the map; returns the (key, value) couple which was
       * removed or null if key was not present */
    public StringIntCouple remove(String key) {
        for (int i = 0; i < size; i++) {
            if (pairs[i].fst().equals(key)) {
                StringIntCouple removedPair = pairs[i];
                pairs[i] = pairs[size - 1]; // Replace with the last element
                pairs[size - 1] = null; // Free the space
                size--;
                return removedPair;
            }
        }
        return null; // Return null if the key wasn't found
    }

    /* returns an array containing the keys of map */
    public String[] keys() {
        String[] keysArray = new String[size];
        for (int i = 0; i < size; i++) {
            keysArray[i] = pairs[i].fst();
        }
        return keysArray;
    }

    /* returns a String description of the map */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < size; i++) {
            sb.append(pairs[i].fst()).append("=").append(pairs[i].snd());
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /* returns a hashCode, conforming to the hashCode contract */
    @Override
    public int hashCode() {
        int result = 0;
        for (int i = 0; i < size; i++) {
            result += pairs[i].fst().hashCode() ^ pairs[i].snd();
        }
        return result;
    }

    /* returns true if the current map is equal to obj */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof StringIntMap)) return false;
        StringIntMap other = (StringIntMap) obj;
        if (this.size != other.size) return false;

        for (int i = 0; i < size; i++) {
            if (!other.containsKey(pairs[i].fst()) || other.getOrDefault(pairs[i].fst(), -1) != pairs[i].snd()) {
                return false;
            }
        }
        return true;
    }

    public static class TryStringIntMap {
        public static void main(String[] args) {
            // Test the default constructor
            StringIntMap map = new StringIntMap();
            System.out.println("Empty map: " + map);

            // Test the put method
            map.put("apple", 3);
            map.put("banana", 5);
            map.put("orange", 2);
            System.out.println("Map after adding 3 elements: " + map);

            // Test the getOrDefault method
            System.out.println("Value associated with 'banana': " + map.getOrDefault("banana", -1));
            System.out.println("Value associated with 'pear' (default): " + map.getOrDefault("pear", -1));

            // Test the containsKey method
            System.out.println("Does the map contain 'apple'? " + map.containsKey("apple"));
            System.out.println("Does the map contain 'pear'? " + map.containsKey("pear"));

            // Test the addOne method
            map.addOne("apple");
            map.addOne("pear");
            System.out.println("Map after incrementing: " + map);

            // Test the remove method
            map.remove("banana");
            System.out.println("Map after removing 'banana': " + map);

            // Test the copy constructor
            StringIntMap copiedMap = new StringIntMap(map);
            System.out.println("Copied map: " + copiedMap);

            // Test the equals and hashCode methods
            System.out.println("Are the maps equal? " + map.equals(copiedMap));
            System.out.println("Original map hashCode: " + map.hashCode());
            System.out.println("Copied map hashCode: " + copiedMap.hashCode());

            // Test the keys method
            String[] keys = map.keys();
            System.out.print("Keys in the map: ");
            for (String key : keys) {
                System.out.print(key + " ");
            }
            System.out.println();
        }
    }
}
