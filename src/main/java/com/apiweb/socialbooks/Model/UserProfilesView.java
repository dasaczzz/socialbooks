package com.apiweb.socialbooks.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("UserProfiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfilesView {
    private String fullName;
    private String profilePicture;
    private String description;
    private List<UserProfilesView> friends;
}
