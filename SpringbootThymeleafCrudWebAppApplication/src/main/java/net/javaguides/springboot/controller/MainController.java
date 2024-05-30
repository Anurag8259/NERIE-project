package net.javaguides.springboot.controller;


import net.javaguides.springboot.repository.DistrictRepository;
import net.javaguides.springboot.repository.OfficeRepository;
import net.javaguides.springboot.repository.StateRepository;
import net.javaguides.springboot.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/add-edit-program")
    public String viewProgramPage() {
        return "add_edit_program";
    }

    @GetMapping("/initialization")
    public String initialize() {
        return "initialization";
    }

}
