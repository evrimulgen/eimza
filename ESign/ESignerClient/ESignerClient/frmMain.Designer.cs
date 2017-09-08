namespace ESignerClient
{
    partial class frmMain
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
            this.lblDocumentID = new System.Windows.Forms.Label();
            this.lblFileName = new System.Windows.Forms.Label();
            this.lblFileHash = new System.Windows.Forms.Label();
            this.lblSessionID = new System.Windows.Forms.Label();
            this.lblUser = new System.Windows.Forms.Label();
            this.txtDocumentID = new System.Windows.Forms.TextBox();
            this.txtFileName = new System.Windows.Forms.TextBox();
            this.txtHashID = new System.Windows.Forms.TextBox();
            this.txtSessionID = new System.Windows.Forms.TextBox();
            this.txtUploader = new System.Windows.Forms.TextBox();
            this.btnShowFile = new System.Windows.Forms.Button();
            this.btnSignFile = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // lblDocumentID
            // 
            this.lblDocumentID.AutoSize = true;
            this.lblDocumentID.Location = new System.Drawing.Point(27, 20);
            this.lblDocumentID.Name = "lblDocumentID";
            this.lblDocumentID.Size = new System.Drawing.Size(73, 13);
            this.lblDocumentID.TabIndex = 0;
            this.lblDocumentID.Text = "Doküman ID :";
            // 
            // lblFileName
            // 
            this.lblFileName.AutoSize = true;
            this.lblFileName.Location = new System.Drawing.Point(27, 46);
            this.lblFileName.Name = "lblFileName";
            this.lblFileName.Size = new System.Drawing.Size(61, 13);
            this.lblFileName.TabIndex = 1;
            this.lblFileName.Text = "Dosya Adı :";
            // 
            // lblFileHash
            // 
            this.lblFileHash.AutoSize = true;
            this.lblFileHash.Location = new System.Drawing.Point(27, 72);
            this.lblFileHash.Name = "lblFileHash";
            this.lblFileHash.Size = new System.Drawing.Size(85, 13);
            this.lblFileHash.TabIndex = 2;
            this.lblFileHash.Text = "Dosya Hash ID :";
            // 
            // lblSessionID
            // 
            this.lblSessionID.AutoSize = true;
            this.lblSessionID.Location = new System.Drawing.Point(27, 98);
            this.lblSessionID.Name = "lblSessionID";
            this.lblSessionID.Size = new System.Drawing.Size(64, 13);
            this.lblSessionID.TabIndex = 3;
            this.lblSessionID.Text = "Session ID :";
            // 
            // lblUser
            // 
            this.lblUser.AutoSize = true;
            this.lblUser.Location = new System.Drawing.Point(27, 124);
            this.lblUser.Name = "lblUser";
            this.lblUser.Size = new System.Drawing.Size(52, 13);
            this.lblUser.TabIndex = 4;
            this.lblUser.Text = "Kullanıcı :";
            // 
            // txtDocumentID
            // 
            this.txtDocumentID.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(224)))), ((int)(((byte)(224)))), ((int)(((byte)(224)))));
            this.txtDocumentID.Location = new System.Drawing.Point(133, 17);
            this.txtDocumentID.Name = "txtDocumentID";
            this.txtDocumentID.ReadOnly = true;
            this.txtDocumentID.Size = new System.Drawing.Size(289, 20);
            this.txtDocumentID.TabIndex = 5;
            // 
            // txtFileName
            // 
            this.txtFileName.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(224)))), ((int)(((byte)(224)))), ((int)(((byte)(224)))));
            this.txtFileName.Location = new System.Drawing.Point(133, 43);
            this.txtFileName.Name = "txtFileName";
            this.txtFileName.ReadOnly = true;
            this.txtFileName.Size = new System.Drawing.Size(289, 20);
            this.txtFileName.TabIndex = 6;
            // 
            // txtHashID
            // 
            this.txtHashID.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(224)))), ((int)(((byte)(224)))), ((int)(((byte)(224)))));
            this.txtHashID.Location = new System.Drawing.Point(133, 69);
            this.txtHashID.Name = "txtHashID";
            this.txtHashID.ReadOnly = true;
            this.txtHashID.Size = new System.Drawing.Size(289, 20);
            this.txtHashID.TabIndex = 7;
            // 
            // txtSessionID
            // 
            this.txtSessionID.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(224)))), ((int)(((byte)(224)))), ((int)(((byte)(224)))));
            this.txtSessionID.Location = new System.Drawing.Point(133, 95);
            this.txtSessionID.Name = "txtSessionID";
            this.txtSessionID.ReadOnly = true;
            this.txtSessionID.Size = new System.Drawing.Size(289, 20);
            this.txtSessionID.TabIndex = 8;
            // 
            // txtUploader
            // 
            this.txtUploader.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(224)))), ((int)(((byte)(224)))), ((int)(((byte)(224)))));
            this.txtUploader.Location = new System.Drawing.Point(133, 121);
            this.txtUploader.Name = "txtUploader";
            this.txtUploader.ReadOnly = true;
            this.txtUploader.Size = new System.Drawing.Size(289, 20);
            this.txtUploader.TabIndex = 9;
            // 
            // btnShowFile
            // 
            this.btnShowFile.Location = new System.Drawing.Point(133, 147);
            this.btnShowFile.Name = "btnShowFile";
            this.btnShowFile.Size = new System.Drawing.Size(136, 23);
            this.btnShowFile.TabIndex = 10;
            this.btnShowFile.Text = "Dosyayı Görüntüle";
            this.btnShowFile.UseVisualStyleBackColor = true;
            this.btnShowFile.Click += new System.EventHandler(this.btnShowFile_Click);
            // 
            // btnSignFile
            // 
            this.btnSignFile.Location = new System.Drawing.Point(286, 147);
            this.btnSignFile.Name = "btnSignFile";
            this.btnSignFile.Size = new System.Drawing.Size(136, 23);
            this.btnSignFile.TabIndex = 11;
            this.btnSignFile.Text = "İmzala";
            this.btnSignFile.UseVisualStyleBackColor = true;
            this.btnSignFile.Click += new System.EventHandler(this.btnSignFile_Click);
            // 
            // frmMain
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(455, 186);
            this.Controls.Add(this.btnSignFile);
            this.Controls.Add(this.btnShowFile);
            this.Controls.Add(this.txtUploader);
            this.Controls.Add(this.txtSessionID);
            this.Controls.Add(this.txtHashID);
            this.Controls.Add(this.txtFileName);
            this.Controls.Add(this.txtDocumentID);
            this.Controls.Add(this.lblUser);
            this.Controls.Add(this.lblSessionID);
            this.Controls.Add(this.lblFileHash);
            this.Controls.Add(this.lblFileName);
            this.Controls.Add(this.lblDocumentID);
            this.Name = "frmMain";
            this.Text = "ESigner Client";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label lblDocumentID;
        private System.Windows.Forms.Label lblFileName;
        private System.Windows.Forms.Label lblFileHash;
        private System.Windows.Forms.Label lblSessionID;
        private System.Windows.Forms.Label lblUser;
        private System.Windows.Forms.TextBox txtDocumentID;
        private System.Windows.Forms.TextBox txtFileName;
        private System.Windows.Forms.TextBox txtHashID;
        private System.Windows.Forms.TextBox txtSessionID;
        private System.Windows.Forms.TextBox txtUploader;
        private System.Windows.Forms.Button btnShowFile;
        private System.Windows.Forms.Button btnSignFile;
    }
}