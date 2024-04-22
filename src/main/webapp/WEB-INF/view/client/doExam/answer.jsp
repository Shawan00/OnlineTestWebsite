<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Kết quả thi</title>
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

    <style>
        .correct {
            color: green;
        }

        .incorrect {
            color: red;
        }
    </style>

</head>

<body>

<div class="container" style="margin-top: 20px">
    <div class="card">
        <div class="card-header">
            <h3 >Kết quả bài thi</h3>
        </div>

            <div class="card-body">
                <c:forEach items="${questionListWrapper.questions}" var="question" varStatus="status">
                    <div style="display: none">
                        <input path="questions[${status.index}].exam" value="${examId}"/>
                    </div>

                    <p>
                        <strong>Câu ${status.index +1}:</strong> ${question.questionContent}
                    <div style="display: none">
                        <input value="questions[${status.index}].questionContent" value="${question.questionContent}"/>
                    </div>
                    </p>

                    <div class="form-check">
                        <input
                                class="form-check-input"
                                type="radio"
                                name="questions[${status.index}].selectedOptionIndex"
                                id="Cau${status.index + 1}1"
                                value="1"
                            ${question.selectedOptionIndex == 1 || question.correctOptionIndex == 1 ? 'checked' : ''}
                                disabled
                        />
                        <label class="form-check-label ${question.correctOptionIndex == 1 ? 'correct' : (question.selectedOptionIndex == 1 && question.correctOptionIndex != 1 ? 'incorrect' : '')}"> A. ${question.optionA}</label>
                    </div>

                    <div class="form-check">
                        <input
                                class="form-check-input"
                                type="radio"
                                name="questions[${status.index}].selectedOptionIndex"
                                id="Cau${status.index + 1}1"
                                value="1"
                            ${question.selectedOptionIndex == 2 || question.correctOptionIndex == 2 ? 'checked' : ''}
                                disabled
                        />
                        <label class="form-check-label ${question.correctOptionIndex == 2 ? 'correct' : (question.selectedOptionIndex == 2 && question.correctOptionIndex != 2 ? 'incorrect' : '')}"> B. ${question.optionB}</label>
                    </div>

                    <div class="form-check">
                        <input
                                class="form-check-input"
                                type="radio"
                                name="questions[${status.index}].selectedOptionIndex"
                                id="Cau${status.index + 1}1"
                                value="1"
                            ${question.selectedOptionIndex == 3 || question.correctOptionIndex == 3 ? 'checked' : ''}
                                disabled
                        />
                        <label class="form-check-label ${question.correctOptionIndex == 3 ? 'correct' : (question.selectedOptionIndex == 3 && question.correctOptionIndex != 3 ? 'incorrect' : '')}"> C. ${question.optionC}</label>
                    </div>

                    <div class="form-check">
                        <input
                                class="form-check-input"
                                type="radio"
                                name="questions[${status.index}].selectedOptionIndex"
                                id="Cau${status.index + 1}1"
                                value="1"
                            ${question.selectedOptionIndex == 4 || question.correctOptionIndex == 4 ? 'checked' : ''}
                                disabled
                        />
                        <label class="form-check-label ${question.correctOptionIndex == 4 ? 'correct' : (question.selectedOptionIndex == 4 && question.correctOptionIndex != 4 ? 'incorrect' : '')}"> D. ${question.optionD}</label>
                    </div>

                    <div class="answer">
                        <input type="hidden" name="Cau${status.index + 1}" id="answer${status.index + 1}" value="${question.correctOptionIndex}">
                        <div style="display: none">
                            <input path="questions[${status.index}].correctOptionIndex" value="${question.correctOptionIndex}"/>
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
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="resultModalLabel">
                                    Xác nhận nộp bài?
                                </h1>
                                <button
                                        type="button"
                                        class="btn-close"
                                        data-bs-dismiss="modal"
                                        aria-label="Close"
                                ></button>
                            </div>
                            <div class="modal-footer">

                                <button type="button" class="btn btn-primary" id="submitExam">
                                    Nộp bài
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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
</script>

</body>