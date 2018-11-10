<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>login</title>
    <style>
        form{
            width: 300px;
            height: 300px;
            border: 1px black solid;
            margin: 200px auto;
        }
        input{
            width: 240px;
            display: block;
            position: relative;
            margin: 20px auto;
            height: 20px;
            padding: 10px 8px;
            border: 1px solid #ccc;
            transition: .3s;
            font-size: 14px;
            width: 280px;
            border-radius: 10px;
            background-color: transparent;
        }
        .submit {
            height: 40px;
            width: 298px;
        }
    </style>
</head>
<body>
    <form action="/MutualAid/DoMain?log=login" method="post">
    	<input type="text" placeholder="账号" name="account">
    	<input type="password" placeholder="密码" name="password">
        <input type="submit" class="submit">
    </form>
</body>
</html>