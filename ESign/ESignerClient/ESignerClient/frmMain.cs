using System;
using System.Data;
using System.Windows.Forms;
using ESign_Util;
using System.IO;
using tr.gov.tubitak.uekae.esya.api.asn.x509;

namespace ESignerClient
{

    /*
      private void hashcheck(object sender, EventArgs e)
        {
            using (OpenFileDialog od = new OpenFileDialog())
            {
                if (od.ShowDialog() == System.Windows.Forms.DialogResult.OK)
                {
                    using (var md5 = MD5.Create())
                    {
                        StringBuilder sb = new StringBuilder();
                        var stream = File.OpenRead(od.FileName);
                        byte[] hashBytes = md5.ComputeHash(stream);
                        foreach (byte bt in hashBytes)
                        {
                            sb.Append(bt.ToString("x2"));
                        }
                        stream.Close();
                        MessageBox.Show(sb.ToString());
                    }
                }
            }
        }
     
     */
    public partial class frmMain : Form
    {
        String documentId = "";
        DataTable dtData;
        ESignUtil esignUtil = new ESignUtil();
        FileServices.WSFileManagerSoapClient client;
        System.Resources.ResourceManager resMan = new System.Resources.ResourceManager("ESignerClient.Resources", typeof(frmMain).Assembly);

        public frmMain(string[] args)
        {
            InitializeComponent();
            localize();
            client = new FileServices.WSFileManagerSoapClient();
            if (args != null && args.Length > 0)
            {
                if (File.Exists(args[0]))
                {
                    try
                    {
                        dtData = new DataTable("DocumentData");
                        dtData.ReadXml(args[0]);
                        if (dtData != null && dtData.Rows.Count > 0)
                        {
                            documentId = dtData.Rows[0]["DocumentId"].ToString();
                            txtDocumentID.Text = dtData.Rows[0]["DocumentId"].ToString();
                            txtFileName.Text = dtData.Rows[0]["FileName"].ToString();
                            txtHashID.Text = dtData.Rows[0]["FileHash"].ToString();
                            txtSessionID.Text = dtData.Rows[0]["SessionId"].ToString();
                            txtUploader.Text = dtData.Rows[0]["UserName"].ToString();
                        }
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show(ex.Message);
                    }

                }
            }


            esignUtil.setLicenseXml(new FileStream(Application.StartupPath + "\\lisans\\lisans.xml", FileMode.Open));
            esignUtil.policyFile = Application.StartupPath + "\\config\\certval-policy.xml";
            esignUtil.dataFileContentType = "text/plain";
            esignUtil.dataTextFile = "data.txt";
            esignUtil.configFile = Application.StartupPath + "\\config\\esya-signature-config.xml";
        }

        private void localize()
        {
            foreach (Control ctrl in this.Controls)
            {
                try
                {
                    if (resMan.GetString(ctrl.Name) != null)
                    {
                        ctrl.Text = resMan.GetString(ctrl.Name);
                    }
                }
                catch (Exception)
                {
                }
                
            }
        }

        private void btnShowFile_Click(object sender, EventArgs e)
        {
            byte[] fileBytes = client.getFileBytes(documentId, dtData.Rows[0]["SessionId"].ToString());
            if (fileBytes != null)
            {
                using (SaveFileDialog sd = new SaveFileDialog())
                {
                    sd.FileName = dtData.Rows[0]["FileName"].ToString();
                    if (sd.ShowDialog() == System.Windows.Forms.DialogResult.OK)
                    {
                        FileStream fs = new FileStream(sd.FileName, FileMode.OpenOrCreate);
                        fs.Write(fileBytes, 0, fileBytes.Length);
                        fs.Close();
                    }
                }
            }
        }

        private void btnSignFile_Click(object sender, EventArgs e)
        {
            string fileName = dtData.Rows[0]["FileName"].ToString();
            string fileExtension = new FileInfo(fileName).Extension.ToLower();
            string tempFile = Application.StartupPath + "\\tmpFile.pdf";
            if (".pdf".Equals(fileExtension))
            {
                if (File.Exists(tempFile))
                {
                    File.Delete(tempFile);
                }

                FileServices.WSFileManagerSoapClient client = new FileServices.WSFileManagerSoapClient();
                byte[] fileBytes = client.getFileBytes(documentId, dtData.Rows[0]["SessionId"].ToString());

                if (fileBytes != null)
                {
                    FileStream fs = new FileStream(tempFile, FileMode.OpenOrCreate);
                    fs.Write(fileBytes, 0, fileBytes.Length);
                    fs.Flush();
                    fs.Close();


                    Classes.SelectedESignProperties selectedESignProperties = Classes.SmartCardUtils.selectESignProperties(esignUtil);
                    if (selectedESignProperties == null)
                    {
                        return;
                    }
                    string terminal = selectedESignProperties.SelectedTerminal;
                    ECertificate cert = selectedESignProperties.SelectedCertificate;
                    string pinCode = selectedESignProperties.EnteredPinCode;
                    try
                    {
                        byte[] signedFileBytes = esignUtil.signPdfWithSmartCard(terminal, cert, pinCode, tempFile);
                        if (client.uploadSignedFile(documentId, signedFileBytes))
                        {
                            MessageBox.Show(resMan.GetString("msgSignCompleted"));
                        }
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show(ex.Message);
                    }

                }
            }
            else
            {
                MessageBox.Show(resMan.GetString("msgJustPdfFileCanSign"));
            }

        }
    }
}
