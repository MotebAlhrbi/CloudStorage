package com.udacity.jwdnd.course1.cloudstorage.mapper;


import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {


    @Select("SELECT * FROM CREDENTIALS Where userId = #{userId}")
    List<CredentialModel> getUserCredentialByUserId(Integer userId);

    @Select("SELECT COUNT(*) FROM CREDENTIALS WHERE credentialid = #{credentialid} AND userId = #{userId}")
    int credentialid(Integer credentialid ,  Integer userId);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username} , key = #{key}  , password = #{password} , encryptedPassword = #{encryptedPassword} WHERE credentialid = #{credentialid} AND userid = #{userid}")
    void update(CredentialModel credentialModel);

    @Delete("DELETE FROM CREDENTIALS Where credentialid = #{credentialid}")
    void deleteCredentialById(Integer credentialid);

    @Insert(
            "INSERT INTO CREDENTIALS (url , username  , key , password , encryptedPassword , userid)" +
                    "VALUES (#{url} , #{username} , #{key}  , #{password} ,#{encryptedPassword}, #{userid})"
    )
    @Options(useGeneratedKeys = true , keyProperty = "credentialid")
    void insert(CredentialModel credentialModel);

}

