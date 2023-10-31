package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CredentialService {

    private final UserService userService;
    private final CredentialsMapper credentialsMapper;
    private final EncryptionService encryptionService;


    public Boolean isCredentialExist(Integer credentialid , Integer userId){return credentialsMapper.credentialid(credentialid , userId) != 0;}


    public List<CredentialModel> getUserCredentia(Integer currentUserId){
        return credentialsMapper.getUserCredentialByUserId(currentUserId);
    }



    public Boolean createCredential(CredentialModel credentialModel){

        boolean  isCredentialExistInDatabase = isCredentialExist(credentialModel.getCredentialid(),userService.getUserId());
        if(isCredentialExistInDatabase){
            String encryptedPassword = encryptionService.encryptValue(credentialModel.getPassword() , credentialModel.getKey());
            credentialModel.setEncryptedPassword(encryptedPassword);
            credentialModel.setUserid(userService.getUserId());
            credentialsMapper.update(credentialModel);
            return false;
        }
        credentialModel.setUserid(userService.getUserId());
        credentialModel.setKey(getRandomKey());
        String encryptedPassword = encryptionService.encryptValue(credentialModel.getPassword() , credentialModel.getKey());
        credentialModel.setEncryptedPassword(encryptedPassword);
        credentialsMapper.insert(credentialModel);
        return true;

    }

    public void deleteCredentialById(Integer credentialid){
        credentialsMapper.deleteCredentialById(credentialid);
    }


    public String getRandomKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }
}
