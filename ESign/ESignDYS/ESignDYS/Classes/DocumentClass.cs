using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ESignDYS.Classes
{
    public class DocumentClass
    {
        String documentID;

        public String DocumentID
        {
            get { return documentID; }
            set { documentID = value; }
        }
        String documentName;

        public String DocumentName
        {
            get { return documentName; }
            set { documentName = value; }
        }
        String fileName;

        public String FileName
        {
            get { return fileName; }
            set { fileName = value; }
        }
        String fileHash;

        public String FileHash
        {
            get { return fileHash; }
            set { fileHash = value; }
        }
        Boolean isSigned;

        public Boolean IsSigned
        {
            get { return isSigned; }
            set { isSigned = value; }
        }
        String signedFileName;

        public String SignedFileName
        {
            get { return signedFileName; }
            set { signedFileName = value; }
        }
        Boolean hashSigned;

        public Boolean HashSigned
        {
            get { return hashSigned; }
            set { hashSigned = value; }
        }
        String uploadDate;

        public String UploadDate
        {
            get { return uploadDate; }
            set { uploadDate = value; }
        }
        Boolean timeStamped;

        public Boolean TimeStamped
        {
            get { return timeStamped; }
            set { timeStamped = value; }
        }
        String timeStampingDate;

        public String TimeStampingDate
        {
            get { return timeStampingDate; }
            set { timeStampingDate = value; }
        }
        String comment;

        public String Comment
        {
            get { return comment; }
            set { comment = value; }
        }
        String uploader;

        public String Uploader
        {
            get { return uploader; }
            set { uploader = value; }
        }

    }
}