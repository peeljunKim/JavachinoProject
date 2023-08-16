$(function() {
	var IMP = window.IMP;
	IMP.init("imp80816338");

	$("#payment-button").click(function() {
		var now = new Date()
		var year = now.getFullYear();
		var name = $("#nameInput").val();
		var phone = $("#phoneInput").val();
		var rangeRvDate = $("#rangeRvDate").html();
		var people = $("#peopleCountSelect").val();
		console.log(name);
		console.log(phone);
		console.log(rangeRvDate);
		console.log(people);
		var arr = rangeRvDate.split("~");
		var checkin = arr[0];
		var checkout = arr[1];
		IMP.request_pay({
			pg: "kcp",
			pay_method: "card",
			merchant_uid: "ORD" + new Date().getTime(),
			name: "",
			amount: 100,
			buyer_email: "",
			buyer_name: "",
			buyer_tel: "010-1111-1111",
			buyer_addr: "서울특별시 강남구 신사동",
			buyer_postcode: "01181"
		}, function(res) {
			if (res.success) {
				$.ajax({
					type: 'POST',
					url: '/api/accomodationRv/cardConfirm',
					data: {
						usersNo: 1,
						accomodationNo: 1,
						checkin: year+'/' + checkin,
						checkout: year+'/' + checkout,
						name: name,
						phone: phone,
						people: people,
						imp_uid: res.imp_uid,
						merchant_uid: res.merchant_uid,
						paid_amount: res.paid_amount,
						apply_num: res.apply_num
					},
					success: function(response) {
						console.log(response);
						url = "/accomodation/confirm?imp_uid=" + res.imp_uid + "&merchant_uid=" + res.merchant_uid + "&paid_amount=" + res.paid_amount + "&apply_num=" + res.apply_num;
						console.log("url:" + url)
						location.href = response;
					},
					error: function(error) {
						console.error(error);
					}
				});

			} else {
				console.log('에러내용' + res.error_msg);
			}
		});
	});

	const checkbox = document.getElementById('agreeCheckbox');
	var paymentButton = document.getElementById('payment-button');
	const nameInput = document.getElementById('nameInput');
	const phoneInput = document.getElementById('phoneInput');
	const peopleCountSelect = document.getElementById('peopleCountSelect');

	// 초기 상태에서 버튼 비활성화
	paymentButton.disabled = 'true';

	// 함수를 만들어 필수 입력 항목을 체크하고 버튼 활성화 여부를 결정
	function checkRequiredFields() {
		if (
			nameInput.value.trim() !== '' &&
			phoneInput.value.trim() !== '' &&
			peopleCountSelect.value.trim() !== ''
		) {
			// 필수 입력 항목이 모두 채워졌을 때 버튼 활성화
			paymentButton.disabled = false;
		} else {
			// 필수 입력 항목 중 하나라도 비어있으면 버튼 비활성화
			paymentButton.disabled = true;
		}
	}

	// 초기 상태에서 버튼 비활성화
	checkRequiredFields();

	// 각 필드에 change 이벤트 리스너 추가
	nameInput.addEventListener('change', checkRequiredFields);
	phoneInput.addEventListener('change', checkRequiredFields);
	peopleCountSelect.addEventListener('change', checkRequiredFields);

	// 체크박스에 change 이벤트 리스너 추가
	checkbox.addEventListener('change', function() {
		// 체크박스가 선택되었을 때 실행될 동작
		checkRequiredFields();
	});

});
//총 결제 금액 곱해주기 
document.addEventListener("DOMContentLoaded", function() {
	const peopleCountSelect = document.getElementById('peopleCountSelect');
	const activityPriceElement = document.querySelector('.activity-time2');
	const paymentAmountElement = document.querySelector('.payment-amount');

	peopleCountSelect.addEventListener('change', function() {
		const selectedPeopleCount = parseInt(peopleCountSelect.value);
		console.log(selectedPeopleCount);
		const activityPrice = parseInt(activityPriceElement.textContent.replace(/[^\d.-]/g, ''));
		const totalPrice = selectedPeopleCount * activityPrice;

		paymentAmountElement.textContent = formatCurrency(totalPrice);
	});

	function formatCurrency(value) {
		return new Intl.NumberFormat('ko-KR', {
			style: 'currency',
			currency: 'KRW'
		}).format(value);
	}
});