package net.javaguides.springboot.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@Table(name="mt_programdetails")
public class mt_programdetails {

    @Id
    @GeneratedValue(generator = "programdetail_id")
    @GenericGenerator(name="programdetail_id", strategy= "net.javaguides.springboot.model.ProgramDetailIdGenerator")
    @Column(name="programdetailid")
    private String programDetailId ;

    @Column(name="categorytype")
    private String categoryType;
    @Column(name="programname")
    private String programName;

    @Column(name="programdescription")
    private String programDescription;

    @Column(name="startdate")
    private LocalDate startDate;

    @Column(name="enddate")
    private LocalDate endDate;

    @Column(name="lastdate")
    private LocalDate lastDate;

    @Column(name="approvaldate")
    private LocalDate approvalDate;

    @Column(name="approvedusercode")
    private String approvedUserCode;

    @Column(name="programtype")
    private String programType;

    @Column(name="programcategory")
    private String programCategory;

    @Column(name="officecode")
    private String officeCode;

    @Column(name="closed")
    private String closed;

    @Column(name="closingreport")
    private String closingReport;

    @Column(name="enteredby")
    private String enteredBy;

    @Column(name="hasphases")
    private String hasPhases;

    @Column(name="venues")
    private String venues;

    @Column(name="programstate")
    private String programState;






//    RELATIONSHIPS

    @ManyToOne
    @JoinColumn(name = "programcode", referencedColumnName = "programcode")
    private m_programs program;

    @ManyToOne
    @JoinColumn(name = "phaseid", referencedColumnName = "phaseid")
    private m_phases phase;





    // GETTER AND SETTER


    public m_phases getPhase() {
        return phase;
    }

    public String getVenues() {
        return venues;
    }

    public void setVenues(String venues) {
        this.venues = venues;
    }

    public void setPhase(m_phases phase) {
        this.phase = phase;
    }

    public String getProgramType() {
        return programType;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }

    public String getProgramDetailId() {
        return programDetailId;
    }

    public void setProgramDetailId(String programDetailId) {
        this.programDetailId = programDetailId;
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

    public LocalDate getLastDate() {
        return lastDate;
    }

    public void setLastDate(LocalDate lastDate) {
        this.lastDate = lastDate;
    }

    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApprovedUserCode() {
        return approvedUserCode;
    }

    public void setApprovedUserCode(String approvedUserCode) {
        this.approvedUserCode = approvedUserCode;
    }

    public m_programs getProgram() {
        return program;
    }

    public void setProgram(m_programs program) {
        this.program = program;
    }

    public String getProgramCategory() {
        return programCategory;
    }

    public void setProgramCategory(String programCategory) {
        this.programCategory = programCategory;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public String getClosingReport() {
        return closingReport;
    }

    public void setClosingReport(String closingReport) {
        this.closingReport = closingReport;
    }

    public String getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }

    public String getHasPhases() {
        return hasPhases;
    }

    public void setHasPhases(String hasPhases) {
        this.hasPhases = hasPhases;
    }

    public String getProgramState() {
        return programState;
    }

    public void setProgramState(String programState) {
        this.programState = programState;
    }

}
