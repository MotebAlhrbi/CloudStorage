package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/credential")

public class CredentialController {

    private final CredentialService credentialService;
    @PostMapping
    public String createCredentials(CredentialModel credentialModel, RedirectAttributes redirectAttributes){
        boolean credentialCreated = credentialService.createCredential(credentialModel);
        redirectAttributes.addFlashAttribute("showAlertMassageCredential" , true);
        if(credentialCreated){
            redirectAttributes.addFlashAttribute("alertMassageCredential" , " Credential Was Created Successfully");
        }else {
            redirectAttributes.addFlashAttribute("alertMassageCredential" , " Credential Was Updated Successfully");
        }

        return "redirect:/home";
    }

    @GetMapping("delete/{credentialid}")
    public String deleteCredentials(@PathVariable Integer credentialid , RedirectAttributes redirectAttributes){
        credentialService.deleteCredentialById(credentialid);
        redirectAttributes.addFlashAttribute("showAlertMassageCredential" , true);
        redirectAttributes.addFlashAttribute("alertMassageCredential" , " Credential Was Deleted Successfully");
        return "redirect:/home";
    }


}