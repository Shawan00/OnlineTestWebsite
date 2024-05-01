<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Làm bài kiểm tra</title>
    <!--Bootstrap-->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
      crossorigin="anonymous"
    />

    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
    />
    <link rel="stylesheet" href="/css/clock.css">


</head>

<body>
  <input type="hidden" value="${exam.status}" id="examStatus">
  <input type="hidden" value="${exam.numberOfMinutes}" id="numberOfMinutes">
  <script src="/js/clock.js"></script>
  <div class="clock"  id="clock1">
    <div id="countdown"></div>
  </div>

  <div class="container" style="margin-top: 20px">
    <div class="card">
      <div class="card-header">
        <h3 >Chọn đáp án đúng nhất cho mỗi câu hỏi dưới đây</h3>
      </div>
      
      <form:form method="post" action="/doExam/examResult" modelAttribute="questionListWrapper">

        <div class="card-body">
          <c:forEach items="${questionListWrapper.questions}" var="question" varStatus="status">
            <div style="display: none">
              <form:input path="questions[${status.index}].exam" value="${examId}"/>
            </div>

            <div style="display: none">
              <form:input path="questions[${status.index}].id" />
            </div>

            <p>
              <strong>Câu ${status.index +1}:</strong> ${question.questionContent}
              <div style="display: none">
                <form:input path="questions[${status.index}].questionContent" value="${question.questionContent}"/>
              </div>
            </p>

            <div class="form-check">
              <input
                      class="form-check-input"
                      type="radio"
                      name="questions[${status.index}].selectedOptionIndex"
                      id="Cau${status.index + 1}1"
                      value="1"
              />
              <label class="form-check-label" for="Cau${status.index + 1}"> A. </label> ${question.optionA}
              <div style="display: none">
                <form:input path="questions[${status.index}].optionA" value="${question.optionA}"/>
              </div>
            </div>

            <div class="form-check">
              <input
                      class="form-check-input"
                      type="radio"
                      name="questions[${status.index}].selectedOptionIndex"
                      id="Cau${status.index + 1}.2"
                      value="2"
              />
              <label class="form-check-label" for="Cau${status.index + 1}"> B. </label> ${question.optionB}
              <div style="display: none">
                <form:input path="questions[${status.index}].optionB" value="${question.optionB}"/>
              </div>
            </div>

            <div class="form-check">
              <input
                      class="form-check-input"
                      type="radio"
                      name="questions[${status.index}].selectedOptionIndex"
                      id="Cau${status.index + 1}.3"
                      value="3"
              />
              <label class="form-check-label" for="Cau${status.index + 1}"> C. </label> ${question.optionC}
              <div style="display: none">
                <form:input path="questions[${status.index}].optionC" value="${question.optionC}"/>
              </div>
            </div>

            <div class="form-check">
              <input
                      class="form-check-input"
                      type="radio"
                      name="questions[${status.index}].selectedOptionIndex"
                      id="Cau${status.index + 1}.4"
                      value="4"
              />
              <label class="form-check-label" for="Cau${status.index + 1}"> D. </label> ${question.optionD}
              <div style="display: none">
                <form:input path="questions[${status.index}].optionD" value="${question.optionD}"/>
              </div>
            </div>

            <div class="answer">
              <input type="hidden" name="Cau${status.index + 1}" id="answer${status.index + 1}" value="${question.correctOptionIndex}">
              <div style="display: none">
                <form:input path="questions[${status.index}].correctOptionIndex" value="${question.correctOptionIndex}"/>
              </div>
            </div>


          </c:forEach>

        </div>

        <div class="card-footer">
          <div class="d-flex justify-content-center my-2">
            <button
                    type="submit"
                    class="btn btn-primary"
                    data-bs-toggle="modal"
                    data-bs-target="#resultModal"
            >
              Nộp bài
            </button>
          </div>

          <div
                  class="modal fade"
                  id="resultModal"
                  tabindex="-1"
                  aria-labelledby="resultModalLabel"
                  aria-hidden="true"
          >
            
          </div>
        </div>
      </form:form>
    </div>

  </div>

  <input type="hidden" id="totalQuestions" value="${questions.size()}" />
  <input type="hidden" id="examId" value="${examId}" />
  <input type="hidden" id="userId" value="${sessionScope.id}" />



  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
      crossorigin="anonymous"
    ></script>
  

  <script>
    $(document).ready(function() {
      function calculateScore() {
        var totalQuestion = parseInt(document.getElementById('totalQuestions').value);
        var correctAnswer = 0;
        
        for (var i = 1; i <= totalQuestion; i++) {
          var selectedOption = $("input[name='Cau" + i + "']:checked").val();
          var correctOptionIndex = document.getElementById("answer" + i).value;

          if (selectedOption == correctOptionIndex) {
            correctAnswer ++;
          }
        }
        console.log("Số câu đúng: " + correctAnswer);
        var totalScore = correctAnswer / totalQuestion * 10;
        totalScore = totalScore.toFixed(2);
        console.log("Điểm: " + totalScore);

        var userId = document.getElementById("userId").value;
        var examId = document.getElementById("examId").value;
        var examResult = {
          numberOfCorrectQuestion: correctAnswer,
          score: totalScore
        }

        console.log("userId: " + userId + "; examId: " + examId);
        console.log(examResult);

        // Gửi yêu cầu POST tới backend
        fetch(`/doexam/examresult/${userId}/${examId}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(examResult)
        })
        .then(response => {
            if (response.ok) {
                // Nếu yêu cầu thành công, chuyển hướng trang web
                window.location.href = `/examresult/${userId}/${examId}`;
            }
        })
        .catch(error => {
            // Xử lý lỗi nếu có
            console.error('Error:', error);
        });

      }

      $("#submitExam").click(function () {
        calculateScore();
      });
    });
    var examStatus = document.getElementById("examStatus").value;
    console.log(examStatus);
    function hienThiThoiGian() {
      var clock = document.getElementById("clock1");
      if (examStatus == "Tự do") {
          clock.style.display = 'none';
          console.log('tudo');
      }
    }
    hienThiThoiGian();
  </script>

</body>