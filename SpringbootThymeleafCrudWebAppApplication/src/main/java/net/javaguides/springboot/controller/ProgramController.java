package net.javaguides.springboot.controller;


import net.javaguides.springboot.model.m_venues;
import net.javaguides.springboot.model.mt_programdetails;
import net.javaguides.springboot.repository.CategoryRepository;
import net.javaguides.springboot.repository.Program2Repository;
import net.javaguides.springboot.model.m_coursecategories;
import net.javaguides.springboot.model.m_programs;
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
public class ProgramController {


    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private Program2Repository program2Repository;
    @Autowired
    private VenueRepository venueRepository;
    @Autowired
    private CategoryRepository categoryRepository;



    @GetMapping("/add-program")
    public String addProgramPage(Model model) {
        List<m_venues> venues = venueRepository.findAll();
        model.addAttribute("venues", venues);
        List<m_coursecategories> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
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
                             RedirectAttributes redirectAttributes) {

        m_coursecategories category=categoryRepository.findByCourseCategoryCode(programCategory);
        m_venues venue=venueRepository.findByVenueCode(venueCode);
        // Create m_programs entity
        m_programs program = new m_programs();
        program.setProgramCode(programCode);
        program.setCourseCategory(category);

        program.setOfficeCode(venue.getOffice().getOfficeCode());
        program.setProgramName(programName);
        program.setProgramDescription(programDescription);

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

        return "redirect:/";
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
