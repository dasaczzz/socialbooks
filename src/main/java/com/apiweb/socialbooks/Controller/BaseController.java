package com.apiweb.socialbooks.Controller;

import com.apiweb.socialbooks.Lib.BaseResponse;
import com.apiweb.socialbooks.Model.BaseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BaseController<T extends BaseModel> {
    ResponseEntity<BaseResponse> createRecord(T record);
    ResponseEntity<List<T>> getRecords();
    ResponseEntity<T> getRecordById(String id);
    ResponseEntity<BaseResponse> deleteRecord(String id);
}
