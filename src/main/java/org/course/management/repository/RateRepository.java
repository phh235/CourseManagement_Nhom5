package org.course.management.repository;

import org.course.management.entity.Rate;
import org.course.management.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository <Rate, Integer>{
    @Query(value = "SELECT r FROM Rate r WHERE r.course.courseId = :courseId")
    List<Rate> findByCourseId(@Param("courseId") int courseId);

    @Query(value = "SELECT ROUND(AVG(CAST(stars AS DECIMAL(10, 1))), 1) FROM cm_rate WHERE course_id = :courseId", nativeQuery = true)
    Double getAverageStar(@Param("courseId") int courseId);
}
