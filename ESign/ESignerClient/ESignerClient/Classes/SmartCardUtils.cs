using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using tr.gov.tubitak.uekae.esya.api.asn.x509;

namespace ESignerClient.Classes
{
    public class SelectedESignProperties
    {
        public SelectedESignProperties()
        {
        }

        private string selectedTerminal = "";
        private ECertificate selectedCertificate = null;
        private string enteredPinCode = "";

        public string SelectedTerminal
        {
            get { return selectedTerminal; }
            set { selectedTerminal = value; }
        }

        public ECertificate SelectedCertificate
        {
            get { return selectedCertificate; }
            set { selectedCertificate = value; }
        }

        public string EnteredPinCode
        {
            get { return enteredPinCode; }
            set { enteredPinCode = value; }
        }

    }
    public static class SmartCardUtils
    {
        public static SelectedESignProperties selectESignProperties(ESign_Util.ESignUtil esignUtil)
        {
            SelectedESignProperties selectedESignProperties = null;
            using (SmartCardForms.frmSmartCardSelector frm = new SmartCardForms.frmSmartCardSelector(esignUtil))
            {
                if (frm.ShowDialog() == System.Windows.Forms.DialogResult.OK)
                {
                    selectedESignProperties = frm.SelectedESignProperties;
                }
            }
            return selectedESignProperties;
        }


    }
}
