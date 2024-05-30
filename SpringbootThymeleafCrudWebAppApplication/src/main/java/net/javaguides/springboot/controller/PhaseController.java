package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.m_phases;
import net.javaguides.springboot.model.m_programs;
import net.javaguides.springboot.model.mt_programdetails;
import net.javaguides.springboot.model.m_venues;
import net.javaguides.springboot.repository.PhaseRepository;
import net.javaguides.springboot.repository.Program2Repository;
import net.javaguides.springboot.repository.ProgramRepository;
import net.javaguides.springboot.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class PhaseController {

    @Autowired
    private PhaseRepository phaseRepository;

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
        List<mt_programdetails> programsWithPhases = programRepository.findByHasPhases("true");
        model.addAttribute("programs", programsWithPhases);

        // Fetch all venues
        List<m_venues> venues = venueRepository.findAll();
        model.addAttribute("venues", venues);

        model.addAttribute("phase", new m_phases()); // Assuming phase is a new phase object
        return "add_new_phase_form";
    }
//    @GetMapping("/add-new-phase")
//    public String addNewPhase(Model model) {
//        // Fetch programs with hasPhases = "true"
//        List<m_programs> programsWithPhases = programRepository.findByHasPhases("true");
//        model.addAttribute("programs", programsWithPhases);
//
//        model.addAttribute("phase", new m_phases()); // Assuming phase is a new phase object
//        return "add_new_phase_form";
//    }
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
        RedirectAttributes redirectAttributes) {


        System.out.println(programCode + "check");
    // Fetch venue information based on the venue code
    m_venues venue = venueRepository.findByVenueCode(venueCode);
    m_programs program = program2Repository.findByProgramCode(programCode);

    // Create m_phases entity
    m_phases phase = new m_phases();
    phase.setProgram(program);
    phase.setPhaseId(generatePhaseId(programCode, nextPhaseNo)); // Generate phase ID
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
    programDetails.setPhase(phase); // Set the phase
    // Initialize the program entity

//    // Set the program code
    // Set the program entity in mt_programdetails
    programDetails.setProgram(program);
//    programDetails.setProgramDetailId();
    programDetails.setStartDate(LocalDate.parse(startDate)); // Convert to LocalDate
    programDetails.setEndDate(LocalDate.parse(endDate)); // Convert to LocalDate
    programDetails.setLastDate(LocalDate.parse(lastDateOfApply)); // Convert to LocalDate

    // Save the mt_programdetails entity
    programRepository.save(programDetails);

    return "redirect:/add-new-phase"; // Redirect to clear the form after submission
}

    // Method to generate phase ID based on program code and next phase number
    private String generatePhaseId(String programCode, long nextPhaseNo) {
        return programCode + "-PH-" + nextPhaseNo;
    }



//    @PostMapping("/add-new-phase")
//    public String addNewPhase(@ModelAttribute("phase") m_phases phase){
////                              @RequestParam("selectedProgramCode") String selectedProgramCode,
////                              @RequestParam("selectedProgramDescription") String selectedProgramDescription) {
//        // Set the selected program code and description in the phase object
//
////        phase.setProgramId(selectedProgramCode);
////        phase.setProgramDescription(selectedProgramDescription);
//
//        // Save the phase to the database
//        phaseRepository.save(phase);
//
//        return "redirect:/add-new-phase"; // Redirect to clear the form after submission
//    }
//@PostMapping("/add-new-phase")
//public String addNewPhase(@ModelAttribute("phase") m_phases phase, @RequestParam("programCode") String programCode) {
//    // Fetch the program detail based on the program code
//    m_programs programDetail = programRepository.findByProgramDetailId(programCode);
//
//    // Check if the program detail exists
//    if (programDetail != null) {
//        // Set the program detail in the phase
//        phase.setProgramDetail(programDetail);
//
//        // Save the phase to the database
//        phaseRepository.save(phase);
//
//        return "redirect:/add-new-phase"; // Redirect to clear the form after submission
//    } else {
//        // Handle case where program detail is not found
//        // You can redirect to an error page or display an error message
//        return "error"; // Change to appropriate error handling mechanism
//    }
//}


//    @PostMapping("/add-new-phase")
//    public String addNewPhase(@ModelAttribute("phase") m_phases phase) {
//        phaseRepository.save(phase);
//        return "redirect:/add-new-phase"; // Redirect to clear the form after submission
//    }
}
