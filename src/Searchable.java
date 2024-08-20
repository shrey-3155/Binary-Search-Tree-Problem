interface Searchable{
    boolean add(String key);
    boolean find(String key);
    boolean remove(String key);
    int size();
    int levelOf(String key);
    String[] serialize();
    boolean rebuild(String[] keys);
}
