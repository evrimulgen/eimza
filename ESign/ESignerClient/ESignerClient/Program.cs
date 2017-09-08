using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;

namespace ESignerClient
{
    static class Program
    {
        /// <summary>
        ///  
        /// </summary>
        [STAThread]
        static void Main(string[] args)
        {
            //if (System.Diagnostics.Debugger.IsAttached)
            //{
            //    args = new string[] { @"C:\Documents and Settings\cihan\Belgelerim\İndirilenler\sample(33).tobesign" };
            //}
            //System.Threading.Thread.CurrentThread.CurrentCulture = new System.Globalization.CultureInfo("en-US");
            //System.Threading.Thread.CurrentThread.CurrentUICulture = new System.Globalization.CultureInfo("en-US");
 
            
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new frmMain(args));
        }
    }
}
