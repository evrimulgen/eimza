using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;
using ESignDYS.Classes;

namespace ESignDYS
{
    public partial class DocumentList : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                Session["signerrors"] = null;
            }
        }

        protected void btnImzala_Click(object sender, EventArgs e)
        {
            Session["signerrors"] = null;
            string docId = (sender as Button).CommandArgument;
            if (!String.IsNullOrEmpty(docId))
            {
                try
                {
                    DataTable dt = DbManager.getDataTable("select * from Documents Where Id = " + docId);
                    if (dt.Rows.Count > 0)
                    {
                        Response.ClearContent();
                        Response.Clear();
                        string fileName = dt.Rows[0]["FileName"].ToString();
                        string fileType = fileName.Substring(fileName.IndexOf("."));
                        string newFileName = fileName.Replace(fileType, ".tobesign");
                        string contentType = "application/tobesign";//my content type
                        Response.ContentType = contentType;
                        DataTable dtData = new DataTable("DocumentData");
                        dtData.Columns.Add("DocumentId", typeof(int));
                        dtData.Columns.Add("FileName", typeof(string));
                        dtData.Columns.Add("FileHash", typeof(string));
                        dtData.Columns.Add("SessionId", typeof(string));
                        dtData.Columns.Add("UserName", typeof(string));
                        dtData.Rows.Add(dtData.NewRow());
                        dtData.Rows[0]["DocumentId"] = docId;
                        dtData.Rows[0]["FileName"] = fileName;
                        dtData.Rows[0]["FileHash"] = dt.Rows[0]["FileHash"].ToString();
                        dtData.Rows[0]["SessionId"] = Session.SessionID;
                        dtData.Rows[0]["UserName"] = Session["User"];
                        Response.AppendHeader("Content-Disposition", "attachment; filename=" + newFileName);
                        dtData.WriteXml(Response.OutputStream, XmlWriteMode.WriteSchema);
                        Response.End();
                    }
                }
                catch (Exception ex)
                {
                    Session["signerrors"] = ex.Message;
                }
            }
        }
    }
}