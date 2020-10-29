package com.udemy.multiinstance.service;

import com.udemy.multiinstance.model.DoctorUser;
import com.udemy.multiinstance.utils.HospitalNotFoundException;
import com.udemy.multiinstance.utils.UserExistsException;
import com.udemy.multiinstance.vo.DoctorUserRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.logging.Logger;


@EnableSwagger2
@Controller
public class DoctorUserController {

    public Logger logger = Logger.getLogger("DoctorUserController");
    @Autowired
    DoctorUserControllerService doctorUserControllerService;


    @GetMapping(value = "/doctor")
    public String listDoctors(Model model) {

        List<DoctorUser> doctorUsers = null;
        String user = "";
        try {
            doctorUsers = doctorUserControllerService.listDoctors();
            user = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        } catch (Exception e) {
            model.addAttribute("message", "Hospital not found" + e.getMessage());
            return "failureMessage";
        }

        model.addAttribute("doctorUsers", doctorUsers);
        model.addAttribute("user", user);
        return "listDoctorUsers";
    }

    @GetMapping("/doctor/addUser")
    public String showRegisterForm(Model model) {
        String user = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        model.addAttribute("userRequestVO", new DoctorUserRequestVO());

        model.addAttribute("user", user);
        return "addUser";
    }


    @PostMapping(value = "/doctor", produces = "application/json")
    public String createDoctor(@ModelAttribute DoctorUserRequestVO doctorUserRequestVO,
                               BindingResult bindingResult, Model model) {

        DoctorUser doctorUser = null;

        try {
            doctorUser = doctorUserControllerService.createDoctorUserDetails(doctorUserRequestVO);

        } catch (HospitalNotFoundException | UserExistsException e) {
            model.addAttribute("message", "Exception in Add Doctors" + e.getMessage());
            return "failureMessage";
        }

        model.addAttribute("doctorUser", doctorUser);
        return "redirect:/doctor";
    }


    @DeleteMapping(value = "/doctor/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable String id) {

        doctorUserControllerService.deleteDoctorUser(id);

        ResponseEntity<String> resp = new ResponseEntity<>("Successfully Deleted", HttpStatus.CREATED);
        return resp;
    }


}
