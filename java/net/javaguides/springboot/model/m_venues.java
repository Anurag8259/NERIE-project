package net.javaguides.springboot.model;

import jakarta.persistence.*;

@Entity
@Table(name="m_venues")
public class m_venues {

    // FK : officecode
    @ManyToOne
    @JoinColumn(name = "officecode", referencedColumnName = "officecode")
    private m_offices office;

    public m_offices getOffice() {
        return office;
    }

    public void setOffice(m_offices office) {
        this.office = office;
    }

    @Id
    @Column(name="venuecode")
    private String venueCode;
    @Column(name="venuename")
    private String venueName;
//    @ManyToOne
//    @JoinColumn(name = "statecode", referencedColumnName = "statecode")
//    private m_states state;

    @ManyToOne
    @JoinColumn(name = "districtcode", referencedColumnName = "districtcode")
    private m_districts district;

//    public m_states getState() {
//        return state;
//    }
//
//    public void setState(m_states state) {
//        this.state = state;
//    }

    public m_districts getDistrict() {
        return district;
    }

    public void setDistrict(m_districts district) {
        this.district = district;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getVenueCode() {
        return venueCode;
    }

    public void setVenueCode(String venueCode) {
        this.venueCode = venueCode;
    }
}
