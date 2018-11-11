<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>报修信息</title>
    <style>
        .wrap {
            height: 500px;
            width: 300px;
            border: 1px black solid;
            margin: 200px auto;
        }

        input {
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
    <div class="wrap">
        <form action="/MutualAid/DoMain?log=repair" method="post">
            <input type="text" name="dormitory" placeholder="宿舍楼号">
            <input type="text" name="doorNum" placeholder="宿舍门牌">
            <input type="text" name="content" placeholder="报修内容">
            <input type="text" name="phone" placeholder="手机号码">
            <input type="text" name="time" placeholder="上门时间">
            <input type="submit" class="submit">
        </form>
    </div>
</body>

</html>