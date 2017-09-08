using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using tr.gov.tubitak.uekae.esya.api.asn.x509;

namespace ESignerClient.SmartCardForms
{
    public partial class frmSmartCardSelector : Form
    {
        ESign_Util.ESignUtil esignUtil;
        List<ECertificate> listOfCertificates;
        private Classes.SelectedESignProperties selectedESignProperties = new Classes.SelectedESignProperties();
        System.Resources.ResourceManager resMan = new System.Resources.ResourceManager("ESignerClient.Resources", typeof(frmMain).Assembly);

        public Classes.SelectedESignProperties SelectedESignProperties
        {
            get { return selectedESignProperties; }
            set { selectedESignProperties = value; }
        }

        public frmSmartCardSelector(ESign_Util.ESignUtil esignUtil)
        {
            InitializeComponent();
            this.esignUtil = esignUtil;
            cmbTerminals.Items.Clear();
            cmbTerminals.Items.AddRange(esignUtil.getTerminals());
            localize(this);
        }

        private void localize(Control parentt)
        {
            foreach (Control ctrl in parentt.Controls)
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
                if (ctrl.Controls.Count > 0)
                {
                    localize(ctrl);
                }
            }
        }

        private void btnOk_Click(object sender, EventArgs e)
        {
            if (cmbTerminals.SelectedIndex == -1)
            {
                MessageBox.Show(resMan.GetString("msgSelectSmartCard"));
                return;
            }
            if (cmbCertificates.SelectedIndex == -1)
            {
                MessageBox.Show(resMan.GetString("msgSelectCertificate"));
                return;
            }

            if (String.IsNullOrWhiteSpace(txtPinCode.Text))
            {
                MessageBox.Show(resMan.GetString("msgEnterPin"));
                return;
            }
            selectedESignProperties.SelectedTerminal = cmbTerminals.Text;
            selectedESignProperties.SelectedCertificate = listOfCertificates[cmbCertificates.SelectedIndex];
            selectedESignProperties.EnteredPinCode = txtPinCode.Text.Trim();
            Close();
            DialogResult = System.Windows.Forms.DialogResult.OK;
        }

        private void cmbTerminals_SelectedIndexChanged(object sender, EventArgs e)
        {
            cmbCertificates.Items.Clear();
            if (cmbTerminals.SelectedIndex > -1)
            {
                listOfCertificates = esignUtil.getSignatureCertificates(cmbTerminals.Text);
                foreach (ECertificate item in listOfCertificates)
                {
                    cmbCertificates.Items.Add(item.asX509Certificate2().GetNameInfo(System.Security.Cryptography.X509Certificates.X509NameType.SimpleName, false));
                }
            }
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            Close();
            DialogResult = System.Windows.Forms.DialogResult.Cancel;
        }


       
    }
}
