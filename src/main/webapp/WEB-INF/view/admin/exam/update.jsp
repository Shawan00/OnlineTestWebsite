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
                <a href="/logout">
                    <span class="fa-solid fa-right-from-bracket"></span>
                    <h3>Đăng xuất</h3>
                </a>
            </div>
        </aside>

        <main>
            <h1>Quản lý kì thi</h1>

            <div class="content">
                <div class="content-header">
                    <div class="box-header">
                        <p><b>Cập nhật thông tin kì thi</b></p>
                    </div>
                    <div class="box-add">
                        <a href="/admin/exam/update/question/${newExam.id}"
                        class="viewquestion">
                        <button>
                            <span style="margin-right: 5px;" class="fa-solid fa-plus"></span>
                            Xem câu hỏi
                        </button>
                        </a>

                    </div>
                </div>

                <div class="box-body" >
                    <div class="modal-main">
                        <form id="updateExamForm" method="post" action="/admin/exam/update" modelAttribute="newExam">
                            <div>
                                <label>Mã kì thi:</label>
                                <form:input type="text" path="newExam.id" id="id11"/>
                            </div>
                            
                            <div>
                                <label>Tên kì thi:</label>
                                <form:input type="text" id="name11" path="newExam.name" />
                            </div>
                              <div>
                                <label>Loại kì thi:</label>
                                <form:select path="newExam.type">
                                  <form:option value="Luyện tập">Luyện tập</form:option>
                                  <form:option value="Cuối kỳ">Cuối kỳ</form:option>
                                  <form:option value="Giữa kỳ">Giữa kỳ</form:option>
                                </form:select>
                              </div>
                              <div>
                                <label >Mô tả:</label>
                                <form:input type="text" path="newExam.description" id="description11"/>
                              </div>
                              <div>
                                <label>Trạng thái:</label>
                                <form:select path="newExam.status" id="status11">
                                  <form:option value="Tự do">Tự do</form:option>
                                  <form:option value="Thời gian">Thời gian</form:option>
                                </form:select>
                              </div>
                            <div id="thoiGianBox">
                                <label>Thời gian làm bài: <form:input type="number" path="newExam.numberOfMinutes"/> phút</label>
                                <label>Thời gian bắt đầu: <form:input type="datetime-local" path="newExam.startTime"/></label>
                                <label>Thời gian kết thúc: <form:input type="datetime-local" path="newExam.endTime"/></label>
                            </div>
                            <div>
                                <label>Thời gian làm bài: <form:input type="number" path="newExam.numberOfMinutes"/> phút</label>
                            </div>
                              <div class="form-bot">
                                  <button style="width: 100%;" type="submit">Cập nhật</button>
                              </div>
                        
                        </form>
                    </div>
                </div>
            </div>

            
        </main>
   
    
    

    
<%--    </main>--%>
         
    <script>
        var currentStatus = document.getElementById('status11').value;
        console.log(currentStatus);
        function hienThiThoiGian() {
            var thoiGianBox = document.getElementById('thoiGianBox');
            if (currentStatus == 'Thời gian') {
                thoiGianBox.style.display = 'block';
                console.log('thoigian');
            }
            else {
                thoiGianBox.style.display = 'none';
                console.log('tudo');
            }
        }
        hienThiThoiGian();
        document.getElementById('status11').addEventListener('change', function() {
            var trangThai = this.value;
            var thoiGianBox = document.getElementById('thoiGianBox');
    
            if (trangThai === 'Thời gian') {
                thoiGianBox.style.display = 'block';
            } else {
                thoiGianBox.style.display = 'none';
            }
        });
    </script>
            

      
</body>
</html>

