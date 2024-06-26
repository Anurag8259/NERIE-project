package net.javaguides.springboot.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="m_programs")
public class m_programs {

    @Id
    @Column(name="programcode")
    private String programCode;
    @Column(name="programname")
    private String programName;
    @Column(name="hasphases")
    private String hasPhases;
    @Column(name = "programdescription")
    private String programDescription;
    @Column(name="officecode")
    private String officeCode;

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getHasPhases() {
        return hasPhases;
    }

    public void setHasPhases(String hasPhases) {
        this.hasPhases = hasPhases;
    }

    @ManyToOne
    @JoinColumn(name = "coursecodecategory", referencedColumnName = "coursecategorycode")
    private m_coursecategories courseCategory;
    @OneToMany(mappedBy = "program")
    private List<mt_programdetails> programDetailsList;

    public m_coursecategories getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(m_coursecategories courseCategory) {
        this.courseCategory = courseCategory;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramDescription() {
        return programDescription;
    }

    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }
}
