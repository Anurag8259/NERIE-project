package net.javaguides.springboot.model;

import jakarta.persistence.*;

@Entity
public class m_districts {


    @Id
    @Column(name="districtcode")
    private String districtCode;

    @Column(name="districtname")
    private String districtName;

    @ManyToOne
    @JoinColumn(name="statecode") // Name of the foreign key column in m_districts
    private m_states state;

    public m_states getState() {
        return state;
    }

    public void setState(m_states state) {
        this.state = state;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
