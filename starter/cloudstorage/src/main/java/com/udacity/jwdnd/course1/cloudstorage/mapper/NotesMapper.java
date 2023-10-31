package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotesMapper {

    @Select("SELECT * FROM NOTES Where userId = #{userId}")
    List<NoteModel> getUserNoteByUserId(Integer userId);

    @Select("SELECT * FROM NOTES Where noteid = #{noteid}")
    NoteModel getNoteById(Integer noteid);

    @Select("SELECT COUNT(*) FROM NOTES WHERE noteid = #{noteid} AND userId = #{userId}")
    int isNoteExist(Integer noteid ,  Integer userId);


    @Update("UPDATE NOTES SET notetitle = #{notetitle}, notedescription = #{notedescription} WHERE noteid = #{noteid} AND userid = #{userid}")
    void update(NoteModel noteModel);


    @Delete("DELETE  FROM NOTES Where noteid = #{noteid}")
    void deleteNoteById(Integer noteid);

    @Insert(
            "INSERT INTO NOTES (notetitle , notedescription , userid)" +
                    "VALUES (#{notetitle} , #{notedescription} , #{userid} )"
    )
    @Options(useGeneratedKeys = true , keyProperty = "noteid")
    void insert(NoteModel noteModel);
}
