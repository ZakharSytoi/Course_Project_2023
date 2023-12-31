package com.example.course_project_2023.repository.model.views;

import com.example.course_project_2023.repository.model.Teacher;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Immutable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name = "review_with_likes_dislikes_count")
@Data
@EntityListeners(AuditingEntityListener.class)
@Immutable
public class ReviewView {
    @Id
    @Column(name = "review_id")
    private Long id;
    @Column(name = "score")
    private Short score;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @Column(name = "subject_name")
    private String subjectName;
    @Column(name = "review_text")
    private String reviewText;
    @Column(name = "created_date")
    @CreatedDate
    private Date createdDate;
    @Column(name = "like_count")
    private Long likeCount;
    @Column(name = "dislike_count")
    private Long dislikeCount;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "university_name")
    private String universityName;
}
