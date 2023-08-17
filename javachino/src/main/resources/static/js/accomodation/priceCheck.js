$(function() {
	var IMP = window.IMP;
	IMP.init("imp80816338");
	const checkbox = document.getElementById('agreeCheckbox');
	var paymentButton = document.getElementById('payment-button');
	const nameInput = document.getElementById('nameInput');
	const phoneInput = document.getElementById('phoneInput');
	const peopleCountSelect = document.getElementById('peopleCountSelect');


	$("#payment-button").click(function() {
		IMP.request_pay({
			pg: "kcp",
			pay_method: "card",
			merchant_uid: "ORD" + new Date().getTime(),
			name: "",
			amount: 100,
			buyer_name: "김필준",
			buyer_tel: "010-1111-1111",
			buyer_addr: "서울특별시 강남구 신사동",
			buyer_postcode: "01181",
		}, function(res) {
			if (res.success) {
				var data = {
						usersNo: 1,
						accomodationNo: 1,
						accomodationRvCheckin: '2023-08-27',
						accomodationRvCheckout: '2023-08-28',
						accomodationRvName: '1',
						accomodationRvPhone: '2',
						accomodationRvPeople: '3'
						/*imp_uid: res.imp_uid,
						paid_amount: res.paid_amount,*/
				};
				$.ajax({
					url: '/accomodation/confirm',
					data: data,
					success: function(response) {
						console.log(response);
						/*var url = "/accomodation/confirm";
						console.log("url:" + url)
						location.href = url;*/
					},
					error: function(error) {
						console.error(error);

					}
				});

			} else {
				console.log('에러내용' + rsp.error_msg);
			}
		});
	});



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