// Thời gian kết thúc của bộ đếm ngược (5 phút từ thời điểm hiện tại)
const endTime = new Date(Date.now() + 5 * 60 * 1000);

function addLeadingZero(number) {
    return number < 10 ? '0' + number : number;
}


function updateCountdown() {
    const currentTime = new Date();

    const timeDifference = endTime - currentTime;

    if (timeDifference <= 0) {
        document.getElementById('countdown').innerHTML = 'Hết giờ';
        // Chuyển hướng đến trang doexam.com
        setTimeout(function() {
            window.location.href = '/';
        }, 2000); // Chuyển hướng sau 2 giây
    } else {
        const minutes = Math.floor(timeDifference / (1000 * 60));
        const seconds = Math.floor((timeDifference % (1000 * 60)) / 1000);

        const formattedMinutes = addLeadingZero(minutes);
        const formattedSeconds = addLeadingZero(seconds);

        document.getElementById('countdown').innerHTML = `${formattedMinutes} : ${formattedSeconds}`;
    }
}

// Cập nhật bộ đếm ngược mỗi giây
setInterval(updateCountdown, 1000);

// Cập nhật bộ đếm ngược lần đầu tiên khi trang được tải
updateCountdown();
