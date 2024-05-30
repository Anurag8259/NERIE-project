package net.javaguides.springboot.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="m_coursecategories")
public class m_coursecategories {


    @Id
    @Column(name="coursecategorycode")
    private String courseCategoryCode;

    @Column(name="coursecategoryname")
    private String courseCategoryName;

    @Column(name="coursetype")
    private String courseType;

    @OneToMany(mappedBy = "courseCategory")
    private List<m_programs> programs;

    public String getCourseCategoryName() {
        return courseCategoryName;
    }

    public void setCourseCategoryName(String courseCategoryName) {
        this.courseCategoryName = courseCategoryName;
    }

    public String getCourseCategoryCode() {
        return courseCategoryCode;
    }

    public void setCourseCategoryCode(String courseCategoryCode) {
        this.courseCategoryCode = courseCategoryCode;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }
}
