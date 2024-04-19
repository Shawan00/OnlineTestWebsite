let data = [];
var numberOfExamResultDTO = parseInt(document.getElementById("numberOfexamResultDTO").value);
for (var i = 1; i <= numberOfExamResultDTO; i++) {
  var scoreInput = document.getElementById(`diem${i}`).value;
  var scores = scoreInput.trim().split(",");
  scores[0] = scores[0].substring(1);
  var item = {
    userId: document.getElementById(`userId${i}`).value,
    name: document.getElementById(`ten${i}`).value,
    soLanThamGia: document.getElementById(`soLanThamGia${i}`).value,
    tiLeHoanThanh: document.getElementById(`tiLeHoanThanh${i}`).value,
    diem: scores.map(str => parseFloat(str))
  };
  console.log(item);
  data.push(item);
  console.log(data);
}
  
  
  let innerHTMLtext = '';
  
  
  const tinhDiemTrungBinh = (diem) => {
    let sum = 0;
    for (let i = 0; i < diem.length; i++) {
      sum += diem[i];
    }
    return (sum / diem.length).toFixed(2);
  };
  
  const renderData = (data) => {
    data.forEach((item, index) => {
      innerHTMLtext += `
      <div class="accordion-item">
      <h2 class="accordion-header">
        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${index}" aria-expanded="false" aria-controls="collapse${index}">
          ${item.name}
        </button>
      </h2>
      <div id="collapse${index}" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
        <div class="accordion-body">
          <div class="row">
            <div class="col-4">
              <strong>Số lần tham gia: ${item.soLanThamGia}</strong>
              <p>Tỉ lệ hoàn thành: ${item.tiLeHoanThanh}%</p>
              <p>Điểm trung bình: ${tinhDiemTrungBinh(item.diem)}</p>
              <a href="/admin/thongke/student/${item.userId}" class="btn btn-danger mb-2">Xem chi tiết</a>
            </div>
            <div class="col-8" style="height: 300px;">
              <canvas id="myChart${index}"></canvas>
            </div>
          </div>
        </div>
      </div>
    </div>
      `;
    });
    document.getElementById('danh-sach-sinh-vien').innerHTML = innerHTMLtext;
  
    // Tạo biểu đồ cho mỗi item
    data.forEach((item, index) => {
      let ctx = document.getElementById(`myChart${index}`).getContext('2d');
      let myChart = new Chart(ctx, {
        type: 'line',
        data: {
          labels: item.diem.map((_, index) => `Lần ${index + 1}`),
          datasets: [{
            label: 'Điểm của học sinh',
            data: item.diem,
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 1
          }]
        },
        options: {
          scales: {
            y: {
              beginAtZero: true,
              min: 0,
              max: 10
            }
          }
        }
      });
    });
  };
  
  document.addEventListener('DOMContentLoaded', (event) => {
    renderData(data);
  });
  
  let ExportButton = document.getElementById('export-data');
  ExportButton.addEventListener('click', () => {
    exportToCsv();
  });
  
  exportToCsv = function() {
    var CsvString = "";
  
    // Thêm tên cột vào hàng đầu tiên
    if (data.length > 0) {
      for (var i in data[0]) {
        CsvString += i + ",";
      }
      CsvString = CsvString.substring(0, CsvString.length - 1);
      CsvString += "\r\n";
    }
  
    data.forEach(function(RowItem, RowIndex) {
      Object.values(RowItem).forEach(function(ColItem, ColIndex) {
        CsvString += ColItem + ",";
      });
      CsvString += "\r\n";
    });
    CsvString = "data:application/csv," + encodeURIComponent(CsvString);
    var x = document.createElement("A");
    x.setAttribute("href", CsvString);
    x.setAttribute("download", "somedata.csv");
    document.body.appendChild(x);
    x.click();
  };
  
  let SearchButton = document.getElementById('search-button');
  let SearchInput = document.getElementById('search-input');
  SearchButton.addEventListener('click', () => {
    let searchValue = SearchInput.value;
    let result = data.filter((item) => {
      return item.name.toLowerCase().includes(searchValue.toLowerCase());
    });
    innerHTMLtext = '';
    renderData(result);
  });