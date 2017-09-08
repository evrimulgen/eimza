using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using ESignDYS.Classes;
using System.Data.SqlClient;
using System.IO;
using System.Data;

namespace ESignDYS
{
    /// <summary>
    /// File Manager Web Service
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    public class WSFileManager : System.Web.Services.WebService
    {
        [WebMethod]
        public string helloWorld()
        {
            return "Hello World";
        }

        [WebMethod]
        public byte[] getFileBytes(String documentID, String sessionID)
        {
            try
            {
                return FileManager.getFileBytesFromDocumentId(documentID);
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine(ex.Message + ex.StackTrace);
                return null;
            }
        }

        [WebMethod]
        public byte[] getSignedFileBytes(String documentID, String sessionID)
        {
            try
            {
                return FileManager.getSignedFileBytesFromDocumentId(documentID);
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine(ex.Message + ex.StackTrace);
                return null;
            }
        }

        [WebMethod]
        public bool uploadSignedFile(String documentID, byte[] signedFileBytes)
        {
            try
            {
                int result = 0;
                DataTable dtFile = DbManager.getDataTable("select * from Documents Where Id = " + documentID);
                if (dtFile.Rows.Count > 0)
                {
                    System.Data.DataRow drwFile = dtFile.Rows[0];
                    string newFileName = drwFile["FileHash"].ToString() + "_signed_" + drwFile["FileName"].ToString();
                    FileManager.saveFileToFileServer(newFileName, signedFileBytes);
                    SqlConnection con = DbManager.getConnection();
                    if (con.State != ConnectionState.Open)
                        con.Open();

                    SqlCommand cmd = con.CreateCommand();
                    cmd.CommandText = "update Documents Set Signed = 1, SignedFileName = @SignedFileName Where Id = @Id";
                    cmd.Parameters.AddWithValue("@SignedFileName", newFileName);
                    cmd.Parameters.AddWithValue("@Id", documentID);
                    result = cmd.ExecuteNonQuery();
                    con.Close();
                }


                return result > 0;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        [WebMethod]
        public DocumentClass[] getNotTimeStampedDocuments(bool justNotTimeStamped)
        {            
            List<DocumentClass> lstResult = new List<DocumentClass>();
            try
            {
                string query = "select * from Documents Where 1 = 1 ";
                if (justNotTimeStamped)                
                    query += " and isnull(TimeStamped,0) = 0";
                else
                    query += " and isnull(TimeStamped,0) = 1  And isnull(TimeStampingDate,getdate()) <= getdate()";
                System.Data.DataTable dtList = DbManager.getDataTable(query);
                foreach (DataRow drw in dtList.Rows)
                {
                    DocumentClass documentClass = new DocumentClass();
                    documentClass.DocumentID = drw["Id"].ToString();
                    documentClass.DocumentName = drw["Name"].ToString();
                    documentClass.FileName = drw["FileName"].ToString();
                    documentClass.FileHash = drw["FileHash"].ToString();
                    documentClass.IsSigned = !drw.IsNull("Signed") && drw.Field<bool>("Signed");
                    documentClass.SignedFileName = !drw.IsNull("SignedFileName") ? drw["SignedFileName"].ToString() : "";
                    documentClass.HashSigned = !drw.IsNull("HashSigned") && drw.Field<bool>("HashSigned");
                    documentClass.UploadDate = !drw.IsNull("UploadDate") ? drw.Field<DateTime>("UploadDate").ToString("dd.MM.yyyy HH:mm:ss") : "";
                    documentClass.TimeStamped = !drw.IsNull("TimeStamped") && drw.Field<bool>("TimeStamped");
                    documentClass.TimeStampingDate = !drw.IsNull("TimeStampingDate") ? drw.Field<DateTime>("TimeStampingDate").ToString("dd.MM.yyyy HH:mm:ss") : "";
                    documentClass.Comment = !drw.IsNull("Comment") ? drw["Comment"].ToString() : "";
                    documentClass.Uploader = drw["Uploader"].ToString();
                    lstResult.Add(documentClass);
                }
            }
            catch (Exception ex)
            {
                throw ex;
            }
            return lstResult.ToArray();
        }

        [WebMethod]
        public bool uploadTimeStampedFile(String documentID, byte[] signedFileBytes)
        {
            try
            {
                int result = 0;
                DataTable dtFile = DbManager.getDataTable("select * from Documents Where Id = " + documentID);
                if (dtFile.Rows.Count > 0)
                {
                    System.Data.DataRow drwFile = dtFile.Rows[0];
                    string newFileName = drwFile["FileHash"].ToString() + "_signed_timestamped_" + drwFile["FileName"].ToString();
                    FileManager.saveFileToFileServer(newFileName, signedFileBytes);
                    SqlConnection con = DbManager.getConnection();
                    if (con.State != ConnectionState.Open)
                        con.Open();

                    SqlCommand cmd = con.CreateCommand();
                    cmd.CommandText = "update Documents Set TimeStamped = 1, TimeStampedFileName = @SignedFileName, TimeStampingDate = getdate() Where Id = @Id";
                    cmd.Parameters.AddWithValue("@SignedFileName", newFileName);
                    cmd.Parameters.AddWithValue("@Id", documentID);
                    result = cmd.ExecuteNonQuery();
                    con.Close();
                }


                return result > 0;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
    }
}
