package com.apiweb.socialbooks.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Document("Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersModel {
    @Id
    private ObjectId id;
    private String fullName;
    private UserAddress address;
    private String profilePicture;
    private String description;
    private UserCredentials credentials;
    private List<ObjectId> friends = new ArrayList<>();

    @JsonProperty("id")
    public String getIdAsString() {
        return id != null ? id.toHexString() : null;
    }

    @JsonProperty("friends")
    public List<String> getAmigosAsString() {
        return friends != null ? friends.stream().map(ObjectId::toHexString).collect(Collectors.toList()) : null;
    }
}
