using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using ESignDYS.Classes;
using System.Data;

namespace ESignDYS
{
    public partial class Login : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnLogin_Click(object sender, EventArgs e)
        {
            Session["loginerrors"] = "";
            Session["User"] = null;
            if (String.IsNullOrEmpty(txtUserName.Text))
            {
                Session["loginerrors"] = "Kullanıcı adı zorunlu";
                return;
            }
            if (String.IsNullOrEmpty(txtPassword.Text))
            {
                Session["loginerrors"] = "Parola zorunlu";
                return;
            }

            if (txtPassword.Text.Contains("'") || txtUserName.Text.Contains("'"))
            {
                Session["loginerrors"] = "Hata... Don't try this.";
                return;
            }

            DataTable dt = DbManager.getDataTable(string.Format("select UserName from Users Where UserName = '{0}' And Password = '{1}'", txtUserName.Text.Trim(), txtPassword.Text.Trim()));
            if (dt.Rows.Count > 0)
            {
                Session["User"] = dt.Rows[0][0].ToString();
                Response.Redirect("default.aspx");
            }
            else
            {
                Session["loginerrors"] = "Kullanıcı adı veya parola hatalı.";
                return;
            }

        }
    }
}