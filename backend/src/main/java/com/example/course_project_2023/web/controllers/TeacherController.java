package com.example.course_project_2023.web.controllers;

import com.example.course_project_2023.service.ReviewService;
import com.example.course_project_2023.service.TeacherService;
import com.example.course_project_2023.service.TeacherWithMostPopularReviewService;
import com.example.course_project_2023.service.dto.ReviewDto;
import com.example.course_project_2023.service.dto.ShortReviewDto;
import com.example.course_project_2023.service.dto.TeacherCardDto;
import com.example.course_project_2023.service.dto.TeacherPreviewDto;
import com.example.course_project_2023.service.exception.ReviewNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/knowyourteacher-api/v1/teachers")
public class TeacherController {
    private final TeacherWithMostPopularReviewService teacherWithMostPopularReviewService;
    private final TeacherService teacherService;
    private final ReviewService reviewService;

    @GetMapping("/topten")
    public ResponseEntity<List<TeacherPreviewDto>> getTopTenTeachers(
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return ResponseEntity.ok().body(teacherWithMostPopularReviewService.getNTeachersWithMostPopularReviews(pageSize));
    }

    @GetMapping(value = "/pictures/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImg(@PathVariable String id) {
        return teacherService.getTeacherPictureByPictureId(id);
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<TeacherCardDto> getTeacherById(@PathVariable Long id) {
        //TimeUnit.SECONDS.sleep(5);
        return ResponseEntity.ok().body(teacherService.getTeacherById(id));
    }

    @GetMapping("/{teacherId:\\d+}/reviews")
    public ResponseEntity<Page<ReviewDto>> getTeacherReviews(
            @PathVariable Long teacherId,
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return ResponseEntity.ok().body(reviewService.findAllByTeachersId(teacherId, pageNumber, pageSize));
    }

    @GetMapping("/{teacherId:\\d+}/review")
    public ResponseEntity<ShortReviewDto> getUserReviewByTeacherId(@PathVariable Long teacherId) {
        return ResponseEntity.ok().body(reviewService.getUserReviewByTeacherId(teacherId));
    }

    @PostMapping("/{teacherId:\\d+}/review")
    public ResponseEntity<ShortReviewDto> postUserReviewByTeacherId(@PathVariable Long teacherId,
                                                                    @RequestBody ShortReviewDto reviewDto) {
        return ResponseEntity.created(reviewService.createUserReviewByTeacherId(teacherId, reviewDto)).build();
    }

    @DeleteMapping("/{teacherId:\\d+}/review")
    public ResponseEntity<?> deleteUserReviewByTeacherId(@PathVariable Long teacherId) {
        reviewService.deleteUserReviewByTeacherId(teacherId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    ResponseEntity<?> handleReviewNotFoundException(ReviewNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
