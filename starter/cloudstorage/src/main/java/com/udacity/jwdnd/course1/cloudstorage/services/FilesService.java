package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilesService {

    private final FilesMapper filesMapper;
    private final UserService userService;



    public boolean isDuplicateFileName(String fileName , Integer userId){return filesMapper.isDuplicateFileName(fileName , userId) == 0;}

    public Boolean uploadFile(@RequestParam("fileUpload") MultipartFile file  , Integer currentUserId) throws IOException {
        boolean isFileNotEmpty  = !file.isEmpty();
        boolean isDuplicateFileName  = isDuplicateFileName(String.valueOf(file.getOriginalFilename()) , userService.getUserId());
        if (isFileNotEmpty && isDuplicateFileName){
            FileModel newFile = new FileModel(null , file.getOriginalFilename() , file.getContentType() , file.getSize() , currentUserId , file.getBytes() );
            filesMapper.insert(newFile);
            return true;
        }

        return false;

    }

    public FileModel getFileById(Integer fileId){
        return filesMapper.getFileById(fileId);
    }
    public void deleteFileById(Integer fileId){
        filesMapper.deleteFileById(fileId);
    }

    public List<FileModel> getUserFile(Integer currentUserId){
        return filesMapper.getUserFileByUserId(currentUserId);
    }




}