package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.*;
import net.javaguides.springboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PhaseController {

    @Autowired
    private PhaseRepository phaseRepository;

    @Autowired
    private ProgramMembersRepository programMembersRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private Program2Repository program2Repository;
    @Autowired
    private VenueRepository venueRepository;



    @Autowired
    public PhaseController(ProgramRepository programRepository, VenueRepository venueRepository) {
        this.programRepository = programRepository;
        this.venueRepository = venueRepository;
    }

    @GetMapping("/add-new-phase")
    public String addNewPhase(Model model) {
        // Fetch programs with hasPhases = "true"
//        List<mt_programdetails> programsWithPhases = programRepository.findByHasPhases("true");
//        model.addAttribute("programs", programsWithPhases);
        List<m_programs> programsM = program2Repository.findByHasPhases("true");
        model.addAttribute("programsM", programsM);

        List<mt_userlogin> usernames = userRepository.findAll();
        model.addAttribute("usernames", usernames);
        List<mt_program_members> members = programMembersRepository.findAll();
        model.addAttribute("programMembers", members);


        // Fetch all venues
        List<m_venues> venues = venueRepository.findAll();
        model.addAttribute("venues", venues);

        int next=100;
        model.addAttribute("next",next);

        model.addAttribute("phase", new m_phases()); // Assuming phase is a new phase object
        return "add_new_phase_form";
    }

    @PostMapping("/add-new-phase")
    public String addPhase(
        @RequestParam("programCode") String programCode,
        @RequestParam("phaseDescription") String phaseDescription,
        @RequestParam("nextPhaseNo") long nextPhaseNo,
        @RequestParam("startDate") String startDate,
        @RequestParam("endDate") String endDate,
        @RequestParam("lastDateOfApply") String lastDateOfApply,
        @RequestParam("venues") String venueCode,
        @RequestParam("programCloseDate") String programCloseDate,
        @RequestParam("programDescription") String programDescription,
        @RequestParam("programMembers") String programMembers,
        RedirectAttributes redirectAttributes) {


        String[] userCodes = programMembers.split(",");
        System.out.println(programCode + "check");


        // Fetch venue information based on the venue code
        m_venues venue = venueRepository.findByVenueCode(venueCode);
        m_programs program = program2Repository.findByProgramCode(programCode);

        // Create m_phases entity
        m_phases phase = new m_phases();
        phase.setProgram(program);
        phase.setPhaseId(generatePhaseId(programCode, nextPhaseNo));
        phase.setProgramType("Old"); // Set program type to "Old"
        phase.setProgramDescription(phaseDescription);
        phase.setCategoryType("Old"); // Set category type to "Old"
        phase.setNextPhaseNo(String.valueOf(nextPhaseNo));
        phase.setPhaseDescription(phaseDescription);
        phase.setStartDate(LocalDate.parse(startDate)); // Convert to LocalDate
        phase.setEndDate(LocalDate.parse(endDate)); // Convert to LocalDate
        phase.setLastDateOfApply(LocalDate.parse(lastDateOfApply)); // Convert to LocalDate
        phase.setVenues(venue.getVenueName());
        phase.setProgramCloseDate(LocalDate.parse(programCloseDate)); // Convert to LocalDate

        // Save the m_phases entity
        phaseRepository.save(phase);

        // Create mt_programdetails entity
        mt_programdetails programDetails = new mt_programdetails();
        programDetails.setPhase(phase);
        programDetails.setProgramType("Old");
        programDetails.setProgramState("Pending");
        programDetails.setProgramDescription(programDescription);
        m_programs obj=program2Repository.findByProgramCode(programCode);
        programDetails.setProgramName(obj.getProgramName());
//        programDetails.setHasPhases("true");



        // Set the program entity in mt_programdetails
        programDetails.setProgram(program);
    //    programDetails.setProgramDetailId();
        programDetails.setStartDate(LocalDate.parse(startDate)); // Convert to LocalDate
        programDetails.setEndDate(LocalDate.parse(endDate)); // Convert to LocalDate
        programDetails.setLastDate(LocalDate.parse(lastDateOfApply)); // Convert to LocalDate

        // Save the mt_programdetails entity
        programRepository.save(programDetails);

        for (String userCode : userCodes) {
            mt_program_members programMember = new mt_program_members();
            programMember.setProgram(program);
            programMember.setPhase(phase);
//            programMember.setPhase();
            mt_userlogin user=userRepository.findByUserCode(userCode);
            programMember.setUser(user);
            // Set other fields as needed
            // Save the mt_program_members entity
            programMembersRepository.save(programMember);
        }

        return "redirect:/add-edit-program"; // Redirect to clear the form after submission
    }

    // Method to generate phase ID based on program code and next phase number
    private String generatePhaseId(String programCode, long nextPhaseNo) {
        return programCode + "-PH-" + nextPhaseNo;
    }

}
