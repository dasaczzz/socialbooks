package com.apiweb.socialbooks.Service;

import com.apiweb.socialbooks.Lib.BaseResponse;
import com.apiweb.socialbooks.Model.BaseModel;
import org.bson.types.ObjectId;

import java.util.List;

public interface BaseService<T extends BaseModel> {
    String getRecordType();
    BaseResponse createRecord(T record);
    List<T> getRecords();
    T getRecordById(ObjectId id);
    BaseResponse deleteRecord(ObjectId id);
}
