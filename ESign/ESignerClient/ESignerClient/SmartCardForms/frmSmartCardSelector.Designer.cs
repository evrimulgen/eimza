namespace ESignerClient.SmartCardForms
{
    partial class frmSmartCardSelector
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.panel1 = new System.Windows.Forms.Panel();
            this.btnCancel = new System.Windows.Forms.Button();
            this.btnOk = new System.Windows.Forms.Button();
            this.txtPinCode = new System.Windows.Forms.TextBox();
            this.lblEnterPin = new System.Windows.Forms.Label();
            this.lblSelectCertificate = new System.Windows.Forms.Label();
            this.cmbCertificates = new System.Windows.Forms.ComboBox();
            this.lblSelectSmartCard = new System.Windows.Forms.Label();
            this.cmbTerminals = new System.Windows.Forms.ComboBox();
            this.tableLayoutPanel1.SuspendLayout();
            this.panel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.CellBorderStyle = System.Windows.Forms.TableLayoutPanelCellBorderStyle.Single;
            this.tableLayoutPanel1.ColumnCount = 1;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel1.Controls.Add(this.panel1, 0, 6);
            this.tableLayoutPanel1.Controls.Add(this.txtPinCode, 0, 5);
            this.tableLayoutPanel1.Controls.Add(this.lblEnterPin, 0, 4);
            this.tableLayoutPanel1.Controls.Add(this.lblSelectCertificate, 0, 2);
            this.tableLayoutPanel1.Controls.Add(this.cmbCertificates, 0, 3);
            this.tableLayoutPanel1.Controls.Add(this.lblSelectSmartCard, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.cmbTerminals, 0, 1);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 7;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 14.28571F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 14.28571F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 14.28571F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 14.28571F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 14.28571F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 14.28571F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 14.28571F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(432, 273);
            this.tableLayoutPanel1.TabIndex = 1;
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.btnCancel);
            this.panel1.Controls.Add(this.btnOk);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel1.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(162)));
            this.panel1.Location = new System.Drawing.Point(4, 232);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(424, 37);
            this.panel1.TabIndex = 7;
            // 
            // btnCancel
            // 
            this.btnCancel.Dock = System.Windows.Forms.DockStyle.Left;
            this.btnCancel.Location = new System.Drawing.Point(75, 0);
            this.btnCancel.Name = "btnCancel";
            this.btnCancel.Size = new System.Drawing.Size(75, 37);
            this.btnCancel.TabIndex = 1;
            this.btnCancel.Text = "Vazgeç";
            this.btnCancel.UseVisualStyleBackColor = true;
            this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
            // 
            // btnOk
            // 
            this.btnOk.Dock = System.Windows.Forms.DockStyle.Left;
            this.btnOk.Location = new System.Drawing.Point(0, 0);
            this.btnOk.Name = "btnOk";
            this.btnOk.Size = new System.Drawing.Size(75, 37);
            this.btnOk.TabIndex = 0;
            this.btnOk.Text = "Tamam";
            this.btnOk.UseVisualStyleBackColor = true;
            this.btnOk.Click += new System.EventHandler(this.btnOk_Click);
            // 
            // txtPinCode
            // 
            this.txtPinCode.Dock = System.Windows.Forms.DockStyle.Fill;
            this.txtPinCode.Location = new System.Drawing.Point(4, 194);
            this.txtPinCode.Name = "txtPinCode";
            this.txtPinCode.PasswordChar = '*';
            this.txtPinCode.Size = new System.Drawing.Size(424, 20);
            this.txtPinCode.TabIndex = 6;
            this.txtPinCode.UseSystemPasswordChar = true;
            // 
            // lblEnterPin
            // 
            this.lblEnterPin.AutoSize = true;
            this.lblEnterPin.Dock = System.Windows.Forms.DockStyle.Top;
            this.lblEnterPin.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(162)));
            this.lblEnterPin.Location = new System.Drawing.Point(4, 153);
            this.lblEnterPin.Name = "lblEnterPin";
            this.lblEnterPin.Padding = new System.Windows.Forms.Padding(10);
            this.lblEnterPin.Size = new System.Drawing.Size(424, 33);
            this.lblEnterPin.TabIndex = 5;
            this.lblEnterPin.Text = "Pin Kodu :";
            // 
            // lblSelectCertificate
            // 
            this.lblSelectCertificate.AutoSize = true;
            this.lblSelectCertificate.Dock = System.Windows.Forms.DockStyle.Top;
            this.lblSelectCertificate.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(162)));
            this.lblSelectCertificate.Location = new System.Drawing.Point(4, 77);
            this.lblSelectCertificate.Name = "lblSelectCertificate";
            this.lblSelectCertificate.Padding = new System.Windows.Forms.Padding(10);
            this.lblSelectCertificate.Size = new System.Drawing.Size(424, 33);
            this.lblSelectCertificate.TabIndex = 4;
            this.lblSelectCertificate.Text = "Seçilen Sertifika :";
            // 
            // cmbCertificates
            // 
            this.cmbCertificates.Dock = System.Windows.Forms.DockStyle.Top;
            this.cmbCertificates.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbCertificates.FormattingEnabled = true;
            this.cmbCertificates.Location = new System.Drawing.Point(6, 120);
            this.cmbCertificates.Margin = new System.Windows.Forms.Padding(5);
            this.cmbCertificates.Name = "cmbCertificates";
            this.cmbCertificates.Size = new System.Drawing.Size(420, 21);
            this.cmbCertificates.TabIndex = 3;
            // 
            // lblSelectSmartCard
            // 
            this.lblSelectSmartCard.AutoSize = true;
            this.lblSelectSmartCard.Dock = System.Windows.Forms.DockStyle.Top;
            this.lblSelectSmartCard.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(162)));
            this.lblSelectSmartCard.Location = new System.Drawing.Point(4, 1);
            this.lblSelectSmartCard.Name = "lblSelectSmartCard";
            this.lblSelectSmartCard.Padding = new System.Windows.Forms.Padding(10);
            this.lblSelectSmartCard.Size = new System.Drawing.Size(424, 33);
            this.lblSelectSmartCard.TabIndex = 0;
            this.lblSelectSmartCard.Text = "Seçilen Smart Card :";
            // 
            // cmbTerminals
            // 
            this.cmbTerminals.Dock = System.Windows.Forms.DockStyle.Top;
            this.cmbTerminals.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbTerminals.FormattingEnabled = true;
            this.cmbTerminals.Location = new System.Drawing.Point(6, 44);
            this.cmbTerminals.Margin = new System.Windows.Forms.Padding(5);
            this.cmbTerminals.Name = "cmbTerminals";
            this.cmbTerminals.Size = new System.Drawing.Size(420, 21);
            this.cmbTerminals.TabIndex = 1;
            this.cmbTerminals.SelectedIndexChanged += new System.EventHandler(this.cmbTerminals_SelectedIndexChanged);
            // 
            // frmSmartCardSelector
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(432, 273);
            this.Controls.Add(this.tableLayoutPanel1);
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "frmSmartCardSelector";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "E-İmza Bilgileri";
            this.tableLayoutPanel1.ResumeLayout(false);
            this.tableLayoutPanel1.PerformLayout();
            this.panel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.Label lblSelectSmartCard;
        public System.Windows.Forms.ComboBox cmbTerminals;
        public System.Windows.Forms.ComboBox cmbCertificates;
        private System.Windows.Forms.Label lblSelectCertificate;
        private System.Windows.Forms.Label lblEnterPin;
        public System.Windows.Forms.TextBox txtPinCode;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Button btnCancel;
        private System.Windows.Forms.Button btnOk;
    }
}