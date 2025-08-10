package com.apiweb.socialbooks.Service;

import com.apiweb.socialbooks.Model.BaseModel;
import org.bson.types.ObjectId;

import java.util.List;

public interface BaseService<T extends BaseModel> {
    String createRecord(T record);
    List<T> getRecords();
    T getRecordById(ObjectId id);
    String deleteRecord(ObjectId id);
}
