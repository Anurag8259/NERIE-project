package net.javaguides.springboot.dto;
import java.time.LocalDate;

public class ProgramDTO {
    private String programCode;
    private String programName;
    private String programDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate lastDate;
    private String phaseId;
    private LocalDate approvalDate;
    private String approvedUserCode;
    private String programCategory;
    private String officeCode;
    private String closed;
    private String closingReport;
    private String enteredBy;
    private String hasPhases;
    private String programState;

    // Constructors, getters, and setters...

    public ProgramDTO() {
    }

    // Getters and Setters for m_programs fields
    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
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

    // Getters and Setters for mt_programdetails fields
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

    public String getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(String phaseId) {
        this.phaseId = phaseId;
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
