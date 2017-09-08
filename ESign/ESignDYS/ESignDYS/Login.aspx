<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="ESignDYS.Login" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500" />
    <link rel="stylesheet" href="loginassets/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="loginassets/font-awesome/css/font-awesome.min.css" />
    <link rel="stylesheet" href="loginassets/css/form-elements.css" />
    <link rel="stylesheet" href="loginassets/css/style.css" />
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="loginassets/ico/favicon.png" />
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="loginassets/ico/apple-touch-icon-144-precomposed.png" />
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="loginassets/ico/apple-touch-icon-114-precomposed.png" />
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="loginassets/ico/apple-touch-icon-72-precomposed.png" />
    <link rel="apple-touch-icon-precomposed" href="loginassets/ico/apple-touch-icon-57-precomposed.png" />
    <title>Login - ESIGN - DYS</title>
</head>
<body>
    <form id="form1" runat="server">
    <!-- Top content -->
    <div class="top-content">
        <div class="inner-bg">
            <div class="container">
                <div class="row">
                    <div class="col-sm-8 col-sm-offset-2 text">
                        <h1>
                            <strong>ESIGN - DYS</strong> Login</h1>
                        <div class="description">
                            <p>
                                Örnek ESIGN DYS Uygulaması
                            </p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6 col-sm-offset-3 form-box">
                        <div class="form-top">
                            <div class="form-top-left">
                                <h3>
                                    Giriş yap.</h3>
                                <p>
                                    Kullanıcı adı ve parolanızı giriniz :</p>
                            </div>
                            <div class="form-top-right">
                                <i class="fa fa-lock"></i>
                            </div>
                        </div>
                        <div class="form-bottom">
                            <div class="login-form">
                                <div class="form-group">
                                    <label class="sr-only" for="form-username">
                                        Kullanıcı Adı</label>
                                    <asp:TextBox runat="server" placeholder="Kullanıcı..." class="form-username form-control"
                                        ID="txtUserName" />
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="form-password">
                                        Parola</label>
                                    <asp:TextBox runat="server" TextMode="Password" placeholder="Parola..." class="form-password form-control"
                                        ID="txtPassword" />
                                </div>
                                <asp:Button Text="Giriş" runat="server" CssClass="btn"  ID="btnLogin" 
                                    onclick="btnLogin_Click" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6 col-sm-offset-3 social-login">
                        <h3>
                            <%Response.Write(Session["loginerrors"]); %></h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Javascript -->
    <script src="loginassets/js/jquery-1.11.1.min.js"></script>
    <script src="loginassets/bootstrap/js/bootstrap.min.js"></script>
    <script src="loginassets/js/jquery.backstretch.min.js"></script>
    <script src="loginassets/js/scripts.js"></script>
    <!--[if lt IE 10]>
            <script src="loginassets/js/placeholder.js"></script>
        <![endif]-->
    </form>
</body>
</html>
