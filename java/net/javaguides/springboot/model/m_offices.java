package net.javaguides.springboot.model;

import jakarta.persistence.*;

@Entity
public class m_offices {

    @Id
    @Column(name="officecode")
    public String officeCode;
    @Column(name="officename")
    public String officeName;


    @ManyToOne
    @JoinColumn(name="statecode")
    private m_states state;

    @ManyToOne
    @JoinColumn(name="officedistrictcode")
    private m_districts district;

    public m_states getState() {
        return state;
    }

    public void setState(m_states state) {
        this.state = state;
    }

    public m_districts getDistrict() {
        return district;
    }

    public void setDistrict(m_districts district) {
        this.district = district;
    }
// FK - statecode , officedistrictcode(m_districts)

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }
}
