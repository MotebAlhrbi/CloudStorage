package com.udacity.jwdnd.course1.cloudstorage.mapper;


import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FilesMapper {

    @Select("SELECT * FROM FILES Where userId = #{userId}")
    List<FileModel> getUserFileByUserId(Integer userId);

    @Select("SELECT * FROM FILES Where fileId = #{fileId}")
    FileModel getFileById(Integer fileId);

    @Select("SELECT COUNT(*) FROM FILES WHERE filename = #{filename} AND userId = #{userId}")
    int isDuplicateFileName(String filename ,  Integer userId);


    @Delete("DELETE  FROM FILES Where fileId = #{fileId}")
    void deleteFileById(Integer fileId);

    @Insert(
            "INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) " +
                    "VALUES (#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})"
    )
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    void insert(FileModel file);

}
