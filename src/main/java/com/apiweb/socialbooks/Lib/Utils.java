package com.apiweb.socialbooks.Lib;

import com.apiweb.socialbooks.Exception.ResourceNotFound;
import com.apiweb.socialbooks.Model.BaseModel;
import com.apiweb.socialbooks.Service.BaseService;
import org.bson.types.ObjectId;

public class Utils {
    public static <T extends BaseModel> T validateRecord(String id, BaseService<T> service) throws ResourceNotFound {
        if(!ObjectId.isValid(id)) {
            throw new ResourceNotFound(String.format("The %s with id %s has not been found", service.getRecordType(), id));
        }
        T record = service.getRecordById(new ObjectId(id));
        if (record == null) {
            throw new ResourceNotFound(String.format("The %s with id %s has not been found", service.getRecordType(), id));
        }
        return record;
    }
}
