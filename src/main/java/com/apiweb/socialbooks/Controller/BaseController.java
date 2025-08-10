package com.apiweb.socialbooks.Controller;

import com.apiweb.socialbooks.Model.BaseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BaseController<T extends BaseModel> {
    ResponseEntity<String> createRecord(T record);
    ResponseEntity<List<T>> getRecords();
    ResponseEntity<?> getRecordById(String id);
    ResponseEntity<?> deleteRecord(String id);
}
