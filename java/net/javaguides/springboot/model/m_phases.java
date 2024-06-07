package net.javaguides.springboot.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="m_phases")
public class m_phases {
    @Id
    @Column(name="phaseid")
    private String phaseId;


    @ManyToOne
    @JoinColumn(name = "programcode") // Foreign key column name in m_phases table
    private m_programs program;

    public m_programs getProgram() {
        return program;
    }

    public void setProgram(m_programs program) {
        this.program = program;
    }
    // Same table as m_programs, so no need for separate table reference

    @Column(name="programtype")
    private String programType;

    @Column(name="programdescription")
    private String programDescription;

    @Column(name="categorytype")
    private String categoryType;

    @Column(name="phaseno")
    private String nextPhaseNo;

    @Column(name="phasedescription")
    private String phaseDescription;

    @Column(name="start_date")
    private LocalDate startDate;

    @Column(name="end_date")
    private LocalDate endDate;

    @Column(name="last_date_of_apply")
    private LocalDate lastDateOfApply;

    @Column(name="venues")
    private String venues;

    @Column(name="program_close_date")
    private LocalDate programCloseDate;


    @Column(name="programstate")
    private String programState;

    public m_phases() {
        // Set default values
        this.programState = "Pending";
    }
    @OneToMany(mappedBy = "phase", cascade = CascadeType.ALL)
    private List<mt_programdetails> programDetailsList;


    // Getters and setters...

    public String getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(String phaseId) {
        this.phaseId = phaseId;
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }

    public String getProgramDescription() {
        return programDescription;
    }

    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getNextPhaseNo() {
        return nextPhaseNo;
    }

    public void setNextPhaseNo(String nextPhaseNo) {
        this.nextPhaseNo = nextPhaseNo;
    }

    public String getPhaseDescription() {
        return phaseDescription;
    }

    public void setPhaseDescription(String phaseDescription) {
        this.phaseDescription = phaseDescription;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getLastDateOfApply() {
        return lastDateOfApply;
    }

    public void setLastDateOfApply(LocalDate lastDateOfApply) {
        this.lastDateOfApply = lastDateOfApply;
    }

    public String getVenues() {
        return venues;
    }

    public void setVenues(String venues) {
        this.venues = venues;
    }

    public LocalDate getProgramCloseDate() {
        return programCloseDate;
    }

    public void setProgramCloseDate(LocalDate programCloseDate) {
        this.programCloseDate = programCloseDate;
    }
}