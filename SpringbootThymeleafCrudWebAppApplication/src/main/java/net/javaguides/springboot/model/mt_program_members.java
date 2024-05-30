package net.javaguides.springboot.model;

import jakarta.persistence.*;

@Entity
public class mt_program_members {


    @Id
    @Column(name="program_memberid")
    private int programMemberId;

    @ManyToOne
    @JoinColumn(name = "programcode", referencedColumnName = "programcode")
    private m_programs program;

    // Establishing relationship with m_phases entity
    @ManyToOne
    @JoinColumn(name = "phaseid", referencedColumnName = "phaseid")
    private m_phases phase;

    // Establishing relationship with mt_userlogin entity
    @ManyToOne
    @JoinColumn(name = "usercode", referencedColumnName = "usercode")
    private mt_userlogin user;

    public int getProgramMemberId() {
        return programMemberId;
    }

    public void setProgramMemberId(int programMemberId) {
        this.programMemberId = programMemberId;
    }

    public m_programs getProgram() {
        return program;
    }

    public void setProgram(m_programs program) {
        this.program = program;
    }

    public m_phases getPhase() {
        return phase;
    }

    public void setPhase(m_phases phase) {
        this.phase = phase;
    }

    public mt_userlogin getUser() {
        return user;
    }

    public void setUser(mt_userlogin user) {
        this.user = user;
    }
}
