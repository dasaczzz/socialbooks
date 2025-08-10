package com.apiweb.socialbooks.Lib;

import com.apiweb.socialbooks.Exception.ResourceNotFound;
import com.apiweb.socialbooks.Model.UsersModel;
import org.bson.types.ObjectId;

public class Utils {
    public static String validateRecord(String id) throws ResourceNotFound {
        if(!ObjectId.isValid(id)) {
            throw new ResourceNotFound(String.format("The user with id %s has not been found", id));
        }
//        T user = usersService.getUserById(new ObjectId(id));
//        if (user == null) {
//            throw new ResourceNotFound(String.format("The user with id %s has not been found", id));
//        }
        return id;
    }
}
