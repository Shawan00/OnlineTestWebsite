<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="/css/manageExam.css">


</head>
<body>
    <script src="/js/Question.js"></script>

<%--    <div class="container">--%>
        <aside>
            <div class="top">
                <div class="logo">
                    <img src="/client/img/logo-2.png" alt="">
                    <h2 > Hệ thống trắc nghiệm</h2>
                </div>
            </div>
            <div class="sidebar">
                <a href="/admin/exam" class="active">
                    <span class="fa-solid fa-laptop-code"></span>
                    <h3>Quản lý kì thi</h3>
                </a>

                <a href="/admin/user">
                    <span class="fa-regular fa-user"></span>
                    <h3>Quản lý người dùng</h3>
                </a>

                <a href="/admin/thongke/alluser/examresult">
                    <span class="fa-solid fa-chart-line"></span>
                    <h3>Thống kê</h3>
                </a>
            </div>
        </aside>


        <main>
            <h1>Quản lý câu hỏi</h1>

            <div class="content" >
                <div class="content-header">
                    <div class="box-header">
                        <p><b>Tạo câu hỏi cho bài thi</b></p>
                    </div>
                    <div class="box-add">
                        <button id="addButton">Thêm câu hỏi</button>
                    </div>
                </div>
                <div class="box-body">
                    <div class="modal-main">
                        <form:form id="addExamForm"  modelAttribute="newQuestion" method="post" action="/admin/exam/question/create_question">

                            <div id="inputsContainer"></div> <br>

                        </form:form>


                    </div>

                </div>

                <button id="submitButton" onclick="saveQuestions()" type="submit" data-exam-id="${examId}">Hoàn thành</button>

            </div>




        </main>





</body>
</html>

