<%@ Page Title="" Language="C#" MasterPageFile="~/Main.Master" AutoEventWireup="true"
    CodeBehind="UploadDocument.aspx.cs" Inherits="ESignDYS.UploadDocument" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="row">
        <div class="col-md-12">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">
                        Doküman Yükle</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <% if (Session["saveerrors"] != null)
                   { %>
                <div class="alert alert-danger alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
                        &times;</button>
                    <h4>
                        <i class="icon fa fa-ban"></i>Hata!</h4>
                    <% Response.Write(Session["saveerrors"].ToString()); %>
                </div>
                <%} %>
                <% if (Session["savemessages"] != null)
                   { %>
                <div class="alert alert-success  alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
                        &times;</button>
                    <h4>
                        <i class="icon fa fa-ban"></i>Bilgi!</h4>
                    <% Response.Write(Session["savemessages"].ToString()); %>
                </div>
                <%} %>
            <div class="box-body">
                <div class="form-group">
                    <label for="txtName">
                        Dokuman Adı
                    </label>
                    <asp:TextBox runat="server" ID="txtName" class="form-control" />
                </div>
                <div class="form-group">
                    <label for="fileName">
                        Dokuman
                    </label>
                    <asp:FileUpload runat="server" ID="fileName" />
                </div>
                <div class="form-group">
                    <label for="txtComment">
                        Açıklama
                    </label>
                    <asp:TextBox CssClass="form-control" runat="server" ID="txtComment" TextMode="MultiLine" />
                </div>
            </div>
            <!-- /.box-body -->
            <div class="box-footer">
                <asp:Button Text="Kaydet" runat="server" ID="btnSave" 
                    CssClass="btn btn-primary" onclick="btnSave_Click" />
            </div>
        </div>
    </div>
    </div>
</asp:Content>
