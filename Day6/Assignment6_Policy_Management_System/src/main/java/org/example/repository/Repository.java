package org.example.repository;

import java.util.*;

public class Repository<ID,T> {  // this is called genric type definition
    private Map<ID,T> storage = new HashMap<>();

    public Map<ID, T> getStorage() {
        return storage;
    }

    public void setStorage(Map<ID, T> storage) {
        this.storage = storage;
    }

    public Repository(Map<ID, T> storage) {
        this.storage = storage;
    }

    //add
    public void add(ID id, T obj ){
        storage.put(id,obj);
    }

    public void remove(ID id){
        storage.remove(id);
    }

    public List<T> listAll() {
        return new ArrayList<>(storage.values());
    }


}
