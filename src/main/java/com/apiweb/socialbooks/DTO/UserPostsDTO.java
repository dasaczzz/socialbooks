package com.apiweb.socialbooks.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPostsDTO {
    private String user;
    private String profilePic;
    private String name;
    private String description;
}
