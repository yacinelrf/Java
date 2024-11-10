import java.util.Arrays;

public class SortedStringIntMap {
    private int size;
    private StringIntCouple[] pairs;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int CAP_MULTIPLIER = 2;

    // Crée une carte vide avec une capacité par défaut
    public SortedStringIntMap() {
        pairs = new StringIntCouple[DEFAULT_CAPACITY];
        size = 0;
    }

    // Crée une copie d'une autre carte
    public SortedStringIntMap(SortedStringIntMap other) {
        this.size = other.size;
        this.pairs = new StringIntCouple[other.pairs.length];
        System.arraycopy(other.pairs, 0, this.pairs, 0, other.size);
    }

    // Renvoie la taille de la carte
    public int size() {
        return size;
    }

    // Redimensionne le tableau si la capacité est atteinte
    private void resize() {
        StringIntCouple[] newPairs = new StringIntCouple[pairs.length * CAP_MULTIPLIER];
        System.arraycopy(pairs, 0, newPairs, 0, size);
        pairs = newPairs;
    }

    // Recherche binaire pour trouver l'index de la clé ou le point d'insertion
    private int binarySearch(String key) {
        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = pairs[mid].fst().compareTo(key);

            if (cmp == 0) {
                return mid; // Clé trouvée
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -(low + 1); // Clé non trouvée, retourne le point d'insertion
    }

    // Renvoie la valeur associée à la clé, ou defaultValue si la clé n'est pas trouvée
    public int getOrDefault(String key, int defaultValue) {
        int index = binarySearch(key);
        if (index >= 0) {
            return pairs[index].snd();
        }
        return defaultValue;
    }

    // Vérifie si la clé est présente dans la carte
    public boolean containsKey(String key) {
        return binarySearch(key) >= 0;
    }

    // Insère ou met à jour une paire (clé, valeur)
    public int put(String key, int value) {
        int index = binarySearch(key);

        if (index >= 0) {
            // Clé existante, mise à jour de la valeur
            int oldValue = pairs[index].snd();
            pairs[index].setSnd(value);
            return oldValue;
        }

        // Clé non trouvée, insère dans l'ordre trié
        index = -(index + 1); // Point d'insertion

        if (size == pairs.length) {
            resize(); // Redimensionne le tableau si nécessaire
        }

        // Décale les éléments vers la droite et insère la nouvelle paire clé-valeur
        System.arraycopy(pairs, index, pairs, index + 1, size - index);
        pairs[index] = new StringIntCouple(key, value);
        size++;

        return -1; // Clé absente auparavant
    }

    // Incrémente la valeur associée à la clé ou ajoute une nouvelle paire (clé, 1)
    public void addOne(String key) {
        int index = binarySearch(key);

        if (index >= 0) {
            // Clé existante, incrémente la valeur
            pairs[index].incrSnd();
        } else {
            // Clé non trouvée, l'insère avec la valeur 1
            put(key, 1);
        }
    }

    // Supprime une paire (clé, valeur) si présente
    public StringIntCouple remove(String key) {
        int index = binarySearch(key);

        if (index >= 0) {
            StringIntCouple removedPair = pairs[index];
            // Décale les éléments vers la gauche pour combler l'espace
            System.arraycopy(pairs, index + 1, pairs, index, size - index - 1);
            pairs[--size] = null; // Efface le dernier élément
            return removedPair;
        }
        return null; // Clé non trouvée
    }

    // Renvoie un tableau contenant toutes les clés dans la carte
    public String[] keys() {
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = pairs[i].fst();
        }
        return result;
    }

    // Renvoie une représentation en chaîne de caractères de la carte
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < size; i++) {
            sb.append(pairs[i].fst()).append("=").append(pairs[i].snd());
            if (i < size - 1) sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }

    // Renvoie le code de hachage de la carte
    @Override
    public int hashCode() {
        int result = 0;
        for (int i = 0; i < size; i++) {
            result += pairs[i].fst().hashCode() ^ pairs[i].snd();
        }
        return result;
    }

    // Compare deux cartes pour l'égalité
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof SortedStringIntMap)) return false;

        SortedStringIntMap other = (SortedStringIntMap) obj;
        if (this.size != other.size) return false;

        for (int i = 0; i < size; i++) {
            if (!pairs[i].fst().equals(other.pairs[i].fst()) ||
                pairs[i].snd() != other.pairs[i].snd()) {
                return false;
            }
        }
        return true;
    }

    // Classe de test interne
    public static class TrySortedStringIntMap {
        public static void main(String[] args) {
            SortedStringIntMap map = new SortedStringIntMap();

            // Test de l'insertion et du tri
            map.put("banana", 2);
            map.put("apple", 3);
            map.put("pear", 5);
            System.out.println("Carte après insertion : " + map);

            // Test de getOrDefault
            System.out.println("Valeur pour 'banana' : " + map.getOrDefault("banana", -1));
            System.out.println("Valeur pour 'orange' (par défaut) : " + map.getOrDefault("orange", -1));

            // Test de containsKey
            System.out.println("Contient 'apple' ? " + map.containsKey("apple"));
            System.out.println("Contient 'orange' ? " + map.containsKey("orange"));

            // Test de addOne
            map.addOne("apple");
            map.addOne("orange");
            System.out.println("Carte après addOne : " + map);

            // Test de remove
            map.remove("banana");
            System.out.println("Carte après avoir supprimé 'banana' : " + map);

            // Test des clés
            System.out.println("Clés dans la carte : " + Arrays.toString(map.keys()));
        }
    }
}
