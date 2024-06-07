package net.javaguides.springboot.controller;


import net.javaguides.springboot.model.*;
import net.javaguides.springboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ProgramController {


    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private Program2Repository program2Repository;
    @Autowired
    private VenueRepository venueRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProgramMembersRepository programMembersRepository;
    @Autowired
    private OfficeRepository officeRepository;


    @GetMapping("/add-program")
    public String addProgramPage(Model model) {
        List<m_venues> venues = venueRepository.findAll();
        model.addAttribute("venues", venues);
        List<m_coursecategories> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        List<mt_userlogin> usernames = userRepository.findAll();
        model.addAttribute("usernames", usernames);
        List<mt_program_members> members = programMembersRepository.findAll();
        model.addAttribute("programMembers", members);
        return "add_program_form";
    }

    @PostMapping("/add-program")
    public String addProgram(
                             @RequestParam("programName") String programName,
                             @RequestParam("programType") String programType,
                             @RequestParam("programCode") String programCode,
                             @RequestParam("programDescription") String programDescription,
                             @RequestParam("startDate") String startDate,
                             @RequestParam("endDate") String endDate,
                             @RequestParam("programCategory") String programCategory,
                             @RequestParam("venueCode") String venueCode,
                             @RequestParam("hasPhases") String hasPhases,
                             @RequestParam("lastDate") String lastDate,
                             @RequestParam("programMembers") String programMembers,
                             RedirectAttributes redirectAttributes) {


//        System.out.println(programMembers);

        String[] userCodes = programMembers.split(",");

        m_coursecategories category=categoryRepository.findByCourseCategoryCode(programCategory);
        m_venues venue=venueRepository.findByVenueCode(venueCode);
        // Create m_programs entity
        m_programs program = new m_programs();
        program.setHasPhases(hasPhases);
        program.setProgramCode(programCode);
        program.setCourseCategory(category);

        program.setOfficeCode(venue.getOffice().getOfficeCode());
        program.setProgramName(programName);
        program.setProgramDescription(programDescription);

//        m_offices office= officeRepository.findByOfficeCode()
        // Save the m_programs entity
        System.out.println(program);
        program2Repository.save(program);

        // Create mt_programdetails entity
        mt_programdetails programDetails = new mt_programdetails();
        programDetails.setProgram(program); // Set the m_programs entity
        programDetails.setOfficeCode(venue.getOffice().getOfficeCode());
        programDetails.setLastDate(LocalDate.parse(lastDate));
        programDetails.setHasPhases(hasPhases);
        programDetails.setCategoryType(category.getCourseType());
        programDetails.setProgramCategory(category.getCourseCategoryName());
        programDetails.setProgramType(programType);
        programDetails.setVenues(venue.getVenueName());
        programDetails.setProgramName(programName);
        programDetails.setProgramDescription(programDescription);
        programDetails.setStartDate(LocalDate.parse(startDate)); // Convert to LocalDate
        programDetails.setEndDate(LocalDate.parse(endDate)); // Convert to LocalDate
        programDetails.setProgramState("Pending");
        // Set other fields as needed

        // Save the mt_programdetails entity
        System.out.println(programDetails);
        programRepository.save(programDetails);

        for (String userCode : userCodes) {
            mt_program_members programMember = new mt_program_members();
            programMember.setProgram(program);
//            programMember.setPhase();
            mt_userlogin user=userRepository.findByUserCode(userCode);
            programMember.setUser(user);
            // Set other fields as needed
            // Save the mt_program_members entity
            programMembersRepository.save(programMember);
        }

        return "redirect:/add-edit-program";
    }
    @GetMapping("/pending-programs")
    public String pendingPrograms(Model model) {
        List<mt_programdetails> pendingPrograms = programRepository.findByProgramState("Pending");
        model.addAttribute("pendingPrograms", pendingPrograms);
        return "pending_programs";
    }
    @PostMapping("/accept-program")
    public String acceptProgram(@RequestParam("programId") String programId) {
        mt_programdetails program = programRepository.findById(programId).orElse(null);
        if (program != null) {
            program.setProgramState("Accepted");
            programRepository.save(program);
        }
        return "redirect:/pending-programs";
    }

    @PostMapping("/reject-program")
    public String rejectProgram(@RequestParam("programId") String programId) {
        mt_programdetails program = programRepository.findById(programId).orElse(null);
        if (program != null) {
            program.setProgramState("Rejected");
            programRepository.save(program);
        }
        return "redirect:/pending-programs";
    }

    @GetMapping("/accepted-programs")
    public String acceptedPrograms(Model model) {
        List<mt_programdetails> acceptedPrograms = programRepository.findByProgramState("Accepted");
        model.addAttribute("acceptedPrograms", acceptedPrograms);
        return "accepted_programs";
    }

    @GetMapping("/rejected-programs")
    public String rejectedPrograms(Model model) {
        List<mt_programdetails> rejectedPrograms = programRepository.findByProgramState("Rejected");
        model.addAttribute("rejectedPrograms", rejectedPrograms);
        return "rejected_programs";
    }
}
