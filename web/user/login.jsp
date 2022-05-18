<!DOCTYPE html>
<html>
<head>
    <title>Bootstrap Example</title>
    <style>
        body {font-family: Arial, Helvetica, sans-serif;}
        * {box-sizing: border-box}

        input[type=text], input[type=password] {
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            display: inline-block;
            border: none;
            background: #f1f1f1;
        }

        input[type=text]:focus, input[type=password]:focus {
            background-color: #ddd;
            outline: none;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer; /* 손가락 커서 모양 */
            width: 100%;
            opacity: 0.9;
        }

        button:hover {
            opacity:1;

        }
        button:focus{
            outline:none;
        }

        /* 취소 버튼*/
        .cancelbtn, .join {
            padding: 14px 20px;
            background-color: #f44336;
        }

        .submitbtn, .join {
            float: left;
            width: 50%;
        }

        form {
            background-color: #fefefe;
            margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
            border: 1px solid #888;
            width: 80%; /* Could be more or less, depending on screen size */
            padding: 16px;
        }

        hr {
            border: 1px solid #f1f1f1;
            margin-bottom: 25px;
        }



        /* Clear floats */
        .clearfix::after {
            content: "";
            clear: both;
            display: table;
        }

        /* Change styles for cancel button and signup button on extra small screens */
        @media screen and (max-width: 300px) {
            .signupbtn, .join {
                width: 100%;
            }
        }
    </style>
    <script src = "http://code.jquery.com/jquery-Latest.js"></script>
    <script>
        $(function(){	//회원가입 버튼 클릭시 join.net 주소로 이동하도록 설정
            $(".join").click(function(){
                location.href = "joinForm.user";
            });
        })
    </script>
</head>
<body>
<%--<form action = "login.user" method = "post">
    <h1>로그인</h1>
    <hr>
    <b>아이디</b>
    <input type = "text" name = "id" placeholder = "Enter id" required>
    <b>Password</b>
    <input type = "password" name = "password" placeholder = "Enter password" required>
    <div class = "clearfix">
        <button type = "submit" class= "submitbtn">Submit</button>
        <button type = "button" class= "join">회원가입</button>
    </div>
</form>--%>
<button></button>
</body>
</html>