package lk.ijse.taskservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document
@Data
public class Task {
    @Id
    private String id;
    private String title;
    private String description;
    private String email; // assign user

}