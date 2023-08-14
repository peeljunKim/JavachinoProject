$(function() {

   var today = moment().format('YYYY/MM/DD');
   $("#date_range_picker").daterangepicker(
      {
         autoUpdateInput: false,
         showDropdowns: false, // 월과 연도 드롭다운 설정
         startDate: today,
         endDate: today,
         autoclose: true,
         minDate: moment(),
         opens: 'bottom',
         locale: {
            format: 'YYYY/MM/DD',
            separator: ' ~ ', // 선택한 날짜 사이 구분자 설정
            applyLabel: '적용', // 적용 버튼 텍스트 설정
            cancelLabel: '취소', // 취소 버튼 텍스트 설정
            daysOfWeek: ['일', '월', '화', '수', '목', '금', '토'], // 요일 이름 설정
            monthNames: ['1월', '2월', '3월', '4월', '5월', '6월',
               '7월', '8월', '9월', '10월', '11월', '12월'], // 월 이름 설정
            firstDay: 0, // 첫 번째 날을 일요일로 설정
            language: "ko" // 달력의 언어 선택
         },
   
      });
   $("#date_range_picker").on("apply.daterangepicker",
      function(ev, picker) {
         $(this).val(
            picker.startDate.format("YYYY/MM/DD") + ' ~ '
            + picker.endDate.format("YYYY/MM/DD"));
      });


   $("#date_range_picker").on("cancel.daterangepicker",
      function(ev, picker) {
         $(this).val("");
      });

   $("#date_range_picker").val(today + ' ~ ' + today); // 초기 값을 오늘로 설정
   // 달력 자바 스크립트

   $("#filter-reset").click(function() {
      $("input[name='filter']").prop("checked", false);
      $("input[name='sortPrice']").prop("checked", false);
   });
   // 필터 초기화 이벤트


});

function showFilterModal() {
    var modal = document.getElementById('filterModal');
    modal.style.display = 'block';
}