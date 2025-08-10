package com.apiweb.socialbooks.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Document("Users")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UsersModel extends BaseModel {
    private String fullName;
    private UserAddress address;
    private String profilePicture;
    private String description;
    private UserCredentials credentials;
    private List<ObjectId> friends = new ArrayList<>();

    @JsonProperty("friends")
    public List<String> getAmigosAsString() {
        return friends != null ? friends.stream().map(ObjectId::toHexString).collect(Collectors.toList()) : null;
    }
}
