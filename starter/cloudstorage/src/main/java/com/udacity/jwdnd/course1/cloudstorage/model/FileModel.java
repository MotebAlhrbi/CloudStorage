package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Data;

@Data


public class FileModel {


    private Integer fileId;

    private String filename;

    private String contenttype;

    private long filesize;

    private int userid;

    private byte[] filedata;


    public FileModel(Integer fileId, String filename, String contenttype, long filesize, int userid, byte[] filedata) {
        this.fileId = fileId;
        this.filename = filename;
        this.contenttype = contenttype;
        this.filesize = filesize;
        this.userid = userid;
        this.filedata = filedata;
    }
}
