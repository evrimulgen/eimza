using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Data.SqlClient;
using TimeStamper.Classes;
using ESign_Util;
using System.IO;

namespace TimeStamper
{
    public partial class frmMain : Form
    {
        DataTable dtDocuments = new DataTable();
        ESignUtil esignUtil = new ESignUtil();

        public frmMain()
        {
            InitializeComponent();
            dtDocuments = DbManager.getDataTable("select * from Documents");
            dataGridView1.DataSource = dtDocuments;
            esignUtil.setLicenseXml(new FileStream(Application.StartupPath + "\\lisans\\lisans.xml", FileMode.Open));
            esignUtil.policyFile = Application.StartupPath + "\\config\\certval-policy-test.xml";
            esignUtil.dataFileContentType = "text/plain";
            esignUtil.dataTextFile = "data.txt";
            esignUtil.configFile = Application.StartupPath + "\\config\\esya-signature-config.xml";
        }

        private void zamanDamgasıEkleToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (dataGridView1.SelectedRows.Count > 0)
            {
                foreach (DataGridViewRow item in dataGridView1.SelectedRows)
                {
                    DataRowView rowView = item.DataBoundItem as DataRowView;
                    string fileName = rowView.Row["SignedFileName"].ToString();
                    byte[] signedFileBytes = FileManager.getFileBytes(fileName);
                    if (signedFileBytes != null)
                    {
                         
                    }
                }
            }
        }
    }
}
