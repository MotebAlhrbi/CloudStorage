package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeControler {



    @Controller
    @RequiredArgsConstructor
    @RequestMapping("/home")
    public class HomeController {

        private final FilesService fileService;
        private final NoteService noteService;
        private final UserService userService;
        private final CredentialService credentialService;


        @GetMapping
        public String signupView(Model model) {
            Integer currentUserId = userService.getUserId();

            List<CredentialModel> userCredentials =  credentialService.getUserCredentia(currentUserId);
            List<FileModel> userFiles = fileService.getUserFile(currentUserId);
            List<NoteModel> userNotes = noteService.getUserNote(currentUserId);

            model.addAttribute("files" ,userFiles);
            model.addAttribute("notes" ,userNotes);
            model.addAttribute("credentials" ,userCredentials);

            return "home";
        }












    }

}
