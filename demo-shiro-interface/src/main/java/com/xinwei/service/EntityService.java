package com.xinwei.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface EntityService<T, PK extends Serializable> {
    T get(PK var1);
    Iterable<T> getAll();
    void save(T var1);
    void delete(PK var1);
    void delete(T var1);
    void delete(Iterable<T> var1);
    List<T> findAll(Map<String, Object> var1);
    List<T> findAll(Map<String, Object> var1, Sort var2);
    Page<T> search(Map<String, Object> var1, int var2, int var3, String var4);
}