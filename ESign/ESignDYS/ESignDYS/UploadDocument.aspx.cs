using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;
using ESignDYS.Classes;
using System.IO;

namespace ESignDYS
{
    public partial class UploadDocument : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                Session["saveerrors"] = null;
                Session["savemessages"] = null;
            }
        }

        protected void btnSave_Click(object sender, EventArgs e)
        {
            Session["saveerrors"] = null;
            Session["savemessages"] = null;
            if (String.IsNullOrEmpty(txtName.Text))
            {
                Session["saveerrors"] = "Doküman adı boş.";
                return;
            }
            if (String.IsNullOrEmpty(fileName.FileName))
            {
                Session["saveerrors"] = "Doküman seçiniz.";
                return;
            }
            try
            {
                FileManager.saveFileToFileServer(fileName.FileName, fileName.FileBytes);

                SqlConnection con = DbManager.getConnection();
                SqlCommand cmdInsert = con.CreateCommand();
                cmdInsert.CommandText = "insert into Documents (Name, FileName,UploadDate,Comment,Uploader,FileHash ) values (@Name, @FileName, getdate(), @Comment, @Uploader, @FileHash)";
                cmdInsert.Parameters.AddWithValue("@Name", txtName.Text.Trim().Replace("'", ""));
                cmdInsert.Parameters.AddWithValue("@FileName", fileName.FileName.Replace("'", ""));
                cmdInsert.Parameters.AddWithValue("@Comment", txtComment.Text.Trim().Replace("'", ""));
                cmdInsert.Parameters.AddWithValue("@Uploader", Session["User"].ToString());
                cmdInsert.Parameters.AddWithValue("@FileHash", FileManager.getFileHash(fileName.FileName));
                if (con.State != System.Data.ConnectionState.Open)
                {
                    con.Open();
                }
                if (cmdInsert.ExecuteNonQuery() > 0)
                {
                    Session["savemessages"] = "Kayıt başarıyla tamamlandı.";
                }
                con.Close();
            }
            catch (Exception ex)
            {
                Session["saveerrors"] = ex.Message;
            }
        }
    }
}