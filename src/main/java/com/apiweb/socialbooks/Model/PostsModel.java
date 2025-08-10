package com.apiweb.socialbooks.Model;

import com.apiweb.socialbooks.Model.Enum.BookState;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsModel extends BaseModel {
    private ObjectId userId;
    private String title;
    private String author;
    private String genre;
    private BookState condition;
    private String coverImage;

    @JsonProperty("userId")
    public String getUserIdAsString() { return userId != null ? userId.toHexString() : null; }
}
