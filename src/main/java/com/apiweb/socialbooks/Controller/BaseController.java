package com.apiweb.socialbooks.Controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BaseController<T> {
    ResponseEntity<String> createRecord(T record);
    ResponseEntity<List<T>> getRecords();
    ResponseEntity<?> getRecordById(String id);
    ResponseEntity<?> deleteRecord(String id);
}
