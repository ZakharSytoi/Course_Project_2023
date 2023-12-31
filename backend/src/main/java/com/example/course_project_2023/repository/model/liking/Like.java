package com.example.course_project_2023.repository.model.liking;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Setter;

@Entity
@Table(name = "likes")
@Setter
public class Like {
    @EmbeddedId
    private ComposeId composeId;
}
