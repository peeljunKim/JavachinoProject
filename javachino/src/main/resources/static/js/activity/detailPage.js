function addCategoryClickEvent() {
	var category = document.getElementById('category'),
		children = category.children;

	for (var i = 0; i < children.length; i++) {
		children[i].onclick = onClickCategory;
	}
}

function onClickCategory() {
	var id = this.id,
		className = this.className;

	placeOverlay.setMap(null);

	if (className === 'on') {
		currCategory = '';
		changeCategoryClass();
		removeMarker();
	} else {
		currCategory = id;
		changeCategoryClass(this);
		searchPlaces();
	}
}

function changeCategoryClass(el) {
	var category = document.getElementById('category'),
		children = category.children,
		i;

	for (i = 0; i < children.length; i++) {
		children[i].className = '';
	}

	if (el) {
		el.className = 'on';
	}
}


//share button
$(function() {
	var selectedDropdownValue = null;
	$("#confirmPaymentLink").click(function(e) {
		e.preventDefault();
		var dateRangeValue = $("#confirm_date").html();
		selectedDropdownValue;
		var activityNo = $("#confirmPaymentBtn").val();
		console.log("ac")
		console.log(dateRangeValue);
		console.log(selectedDropdownValue);
		var url = "/activity/" + activityNo +
			"/priceCheck?dateRange=" + dateRangeValue +
			"&people=" + selectedDropdownValue;
		location.href = url;
	});

	$('#shareButton').on('click', function() {
		var currentUrl = window.location.href;
		alert(`Current page address: ${currentUrl}`);
	});
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
			$("#confirm_date").html(
				picker.startDate.format("MM/DD") + ' ~ '
				+ picker.endDate.format("MM/DD"));
		});


	$("#date_range_picker").on("cancel.daterangepicker",
		function(ev, picker) {
			$("#confirm_date").html("날짜입력");
		});

	$('.dropdown-item').click(function() {
		$("#confirm_people").html($(this).text() + "명");
		selectedDropdownValue = $(this).text();
	});

});

function showReviewGuidelines() {
	$('#reviewGuidelinesModal').modal('show');
}

function showTabContent(tabName) {
	const tabs = document.getElementsByClassName('tab-content');
	for (let i = 0; i < tabs.length; i++) {
		tabs[i].style.display = 'none';
	}

	const buttons = document.getElementsByClassName('tab-button');
	for (let i = 0; i < buttons.length; i++) {
		buttons[i].classList.remove('active');
	}

	document.getElementById(tabName + 'Content').style.display = 'block';
	document.querySelector(`[onclick="showTabContent('${tabName}')"]`).classList.add('active');
}

