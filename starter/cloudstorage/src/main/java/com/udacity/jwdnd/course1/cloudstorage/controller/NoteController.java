package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteController {



    private final UserService userService;
    private final NoteService noteService;

    @PostMapping
    public String createNote(NoteModel noteModel , RedirectAttributes redirectAttributes){
        boolean noteCreated =  noteService.createNote(noteModel);
        redirectAttributes.addFlashAttribute("showAlertMassageNote" , true);
        System.out.println(noteCreated);
        if(noteCreated){
            redirectAttributes.addFlashAttribute("alertMassageNote" , " Note Was Created Successfully");
        }else{
            redirectAttributes.addFlashAttribute("alertMassageNote" , " Note Was Updated Successfully");
        }

        return "redirect:/home";
    }

    @GetMapping("/delete/{noteid}")
    public String deleteNoteById(@PathVariable Integer noteid , RedirectAttributes redirectAttributes){
        noteService.deleteNoteById(noteid);
        redirectAttributes.addFlashAttribute("showAlertMassageNote" , true);
        redirectAttributes.addFlashAttribute("alertMassageNote" , " Note Was Deleted Successfully");
        return "redirect:/home";
    }

    @GetMapping("/edit/{noteid}")
    public String editNote(@PathVariable Integer noteid){
        System.out.println(noteid);
        return "redirect:/home";
    }

}
