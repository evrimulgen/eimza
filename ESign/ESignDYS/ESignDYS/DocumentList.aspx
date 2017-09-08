<%@ Page Title="" Language="C#" MasterPageFile="~/Main.Master" AutoEventWireup="true" CodeBehind="DocumentList.aspx.cs" Inherits="ESignDYS.DocumentList" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
                 <% if (Session["signerrors"] != null)
                   { %>
                <div class="alert alert-danger alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
                        &times;</button>
                    <h4>
                        <i class="icon fa fa-ban"></i>Hata!</h4>
                    <% Response.Write(Session["signerrors"].ToString()); %>
                </div>
                <%} %>
                 
    <asp:GridView ID="GridView1" runat="server" AllowPaging="True" 
        AllowSorting="True" AutoGenerateColumns="False" DataKeyNames="Id" 
        DataSourceID="SqlDataSource1" Width="100%">
        <Columns>
            <asp:BoundField DataField="Id" HeaderText="Id" InsertVisible="False" 
                ReadOnly="True" SortExpression="Id" Visible="False" />
            <asp:BoundField DataField="Name" HeaderText="Name" SortExpression="Name" />
            <asp:BoundField DataField="FileName" HeaderText="FileName" 
                SortExpression="FileName" />
            <asp:BoundField DataField="FileHash" HeaderText="Hash" />
            <asp:CheckBoxField DataField="Signed" HeaderText="Signed" 
                SortExpression="Signed" />
            <asp:BoundField DataField="UploadDate" HeaderText="UploadDate" 
                SortExpression="UploadDate" />
            <asp:CheckBoxField DataField="TimeStamped" HeaderText="TimeStamped" 
                SortExpression="TimeStamped" />
            <asp:BoundField DataField="TimeStampingDate" HeaderText="TimeStampingDate" 
                SortExpression="TimeStampingDate" />
            <asp:BoundField DataField="Comment" HeaderText="Comment" 
                SortExpression="Comment" />
            <asp:BoundField DataField="Uploader" HeaderText="Uploader" 
                SortExpression="Uploader" />
            <asp:TemplateField HeaderText="İmzala">
                <ItemTemplate>
                    <asp:Button ID="btnImzala" runat="server" Text="İmzala" 
                        CommandArgument='<%# Eval("Id") %>' onclick="btnImzala_Click"  />
                </ItemTemplate>
            </asp:TemplateField>
        </Columns>
    </asp:GridView>
    <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
        ConnectionString="<%$ ConnectionStrings:ESignDYS.Properties.Settings.DB_CONNECTION_STRING %>" 
        SelectCommand="SELECT * FROM [Documents] ORDER BY [UploadDate] DESC">
    </asp:SqlDataSource>

</asp:Content>
