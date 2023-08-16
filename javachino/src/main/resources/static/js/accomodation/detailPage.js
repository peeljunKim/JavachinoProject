
var mapContainer = document.getElementById('map'),
	mapOption = {
		center: new kakao.maps.LatLng(37.556576, 126.919541),
		level: 3
	};

var placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 }),
	contentNode = document.createElement('div'),
	markers = [],
	currCategory = ''

var map = new kakao.maps.Map(mapContainer, mapOption);
var ps = new kakao.maps.services.Places(map);

kakao.maps.event.addListener(map, 'idle', searchPlaces);
contentNode.className = 'placeinfo_wrap';
addEventHandle(contentNode, 'mousedown', kakao.maps.event.preventMap);
addEventHandle(contentNode, 'touchstart', kakao.maps.event.preventMap);
placeOverlay.setContent(contentNode);
addCategoryClickEvent();
function addEventHandle(target, type, callback) {
	if (target.addEventListener) {
		target.addEventListener(type, callback);
	} else {
		target.attachEvent('on' + type, callback);
	}
}

function searchPlaces() {
	if (!currCategory) {
		return;
	}
	placeOverlay.setMap(null);
	removeMarker();
	ps.categorySearch(currCategory, placesSearchCB, { useMapBounds: true });
}
function placesSearchCB(data, status, pagination) {
	if (status === kakao.maps.services.Status.OK) {
		displayPlaces(data);
	} else if (status === kakao.maps.services.Status.ZERO_RESULT) { }
	else if (status === kakao.maps.services.Status.ERROR) { }
}

function displayPlaces(places) {

	var order = document.getElementById(currCategory).getAttribute('data-order');

	for (var i = 0; i < places.length; i++) {
		var marker = addMarker(new kakao.maps.LatLng(places[i].y, places[i].x), order);

		(function(marker, place) {
			kakao.maps.event.addListener(marker, 'click', function() {
				displayPlaceInfo(place);
			});
		})(marker, places[i]);
	}
}
function addMarker(position, order) {
	var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
		imageSize = new kakao.maps.Size(27, 28),  // 마커 이미지의 크기
		imgOptions = {
			spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
			spriteOrigin: new kakao.maps.Point(46, (order * 36)), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
			offset: new kakao.maps.Point(11, 28) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
		},
		markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
		marker = new kakao.maps.Marker({
			position: position,
			image: markerImage
		});

	marker.setMap(map);
	markers.push(marker);

	return marker;
}

function removeMarker() {
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(null);
	}
	markers = [];
}

function displayPlaceInfo(place) {
	var content = '<div class="placeinfo">' +
		'   <a class="title" href="' + place.place_url + '" target="_blank" title="' + place.place_name + '">' + place.place_name + '</a>';

	if (place.road_address_name) {
		content += '    <span title="' + place.road_address_name + '">' + place.road_address_name + '</span>' +
			'  <span class="jibun" title="' + place.address_name + '">(지번 : ' + place.address_name + ')</span>';
	} else {
		content += '    <span title="' + place.address_name + '">' + place.address_name + '</span>';
	}

	content += '    <span class="tel">' + place.phone + '</span>' +
		'</div>' +
		'<div class="after"></div>';

	contentNode.innerHTML = content;
	placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
	placeOverlay.setMap(map);
}


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

// DB에 정확한 주소가 안들어가서 아직 지도가 이상하게 나옴
// 주소 이상하면 학원 나오게 해놓음
var geocoder = new kakao.maps.services.Geocoder();
geocoder.addressSearch($('#accomodation_addr').html(), function(result, status) {
	if (status === kakao.maps.services.Status.OK) {
		var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		var marker = new kakao.maps.Marker({
			map: map,
			position: coords
		});

		map.setCenter(coords);
	}
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


$(function() {
	var selectedDropdownValue = null;

	$("#confirmPaymentLink").click(function(e) {
		e.preventDefault();
		var dateRangeValue = $("#confirm_date").html();
		selectedDropdownValue;
		var accomodationNo = $("#confirmPaymentBtn").val();
		console.log(dateRangeValue);
		console.log(selectedDropdownValue);
		var url = "/accomodation/" + accomodationNo +
			"/priceCheck?dateRange=" + dateRangeValue +
			"&people=" + selectedDropdownValue;
		location.href = url;
	});



	let liked = true;

	$('#likeButton').click(function() {
		$('.heart-icon, .heart-fill-icon').toggle();

		liked = !liked;

		$.ajax({
			type: 'POST',
			url: '/api/accomodationLike/like',
			data: {
				usersNo: 1,
				accomodationNo: 1
			},
			success: function() {
				if (liked) {
					console.log("Activity liked.");
				} else {
					console.log("Activity unliked.");
				}
			}
		});
	});


	$('#shareButton').on('click', function() {
		var currentUrl = window.location.href;
		alert(`주소: ${currentUrl}`);
	});

	var today = moment().format('YYYY/MM/DD');
	var picker = $("#date_range_picker").daterangepicker({
		autoUpdateInput: false,
		showDropdowns: false, // 월 및 연도 드롭다운 설정
		startDate: today,
		endDate: today,
		autoclose: true,
		minDate: moment(),
		opens: 'bottom',

		isInvalidDate: function(date) {
			var currentUrl = window.location.href;
			var accomodationNo = currentUrl.substring(currentUrl.lastIndexOf('/') + 1);
			var isDateInvalid = false; // 변수 초기화

			$.ajax({
				url: '/getDisabledDates',
				data: {
					accomodationNo: accomodationNo,
				},
				async: false,
				success: function(disabledDates) {
					isDateInvalid = disabledDates.some(disabledDate =>
						date.isSame(moment(disabledDate), 'day')
					);
				}
			});

			return isDateInvalid; // 결과 반환
		},

		locale: {
			format: 'YYYY/MM/DD',
			separator: ' ~ ', // 선택한 날짜 사이의 구분자 설정
			applyLabel: '적용', // 적용 버튼 텍스트 설정
			cancelLabel: '취소', // 취소 버튼 텍스트 설정
			daysOfWeek: ['일', '월', '화', '수', '목', '금', '토'], // 요일 이름 설정
			monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], // 월 이름 설정
			firstDay: 0, // 첫 번째 요일을 일요일로 설정
			language: "ko" // 달력 언어 설정
		}
	});

	picker.on("apply.daterangepicker", function(ev, picker) {
		var startDate = picker.startDate;
		var endDate = picker.endDate;
		var disabledDates = [];
		var currentUrl = window.location.href;
		var accomodationNo = currentUrl.substring(currentUrl.lastIndexOf('/') + 1);
		$.ajax({
			url: '/getDisabledDates',
			data: {
				accomodationNo: accomodationNo, // Make sure to define 'accomodationNo' or replace it with the correct variable
			},
			async: false,
			success: function(data) {
				disabledDates = data;
			}
		});

		var isInvalidRange = false;

		for (var d = startDate.clone(); d.isSameOrBefore(endDate); d.add(1, 'day')) {
			if (disabledDates.some(disabledDate => d.isSame(moment(disabledDate), 'day'))) {
				isInvalidRange = true;
				break;
			}
		}

		if (isInvalidRange) {
			alert("입력하신 날짜를 다시 한 번 확인해주세요.");
			$("#confirm_date").html("날짜 입력"); // 선택한 날짜 범위에 비활성화된 날짜가 포함되면 "날짜 입력"으로 변경
			picker.setStartDate(moment()); // 시작 날짜를 오늘로 재설정
			picker.setEndDate(moment()); // 종료 날짜를 오늘로 재설정
			picker.updateInputText(); // 입력 필드를 재설정된 날짜로 업데이트
		} else {
			$("#confirm_date").html(
				startDate.format("MM/DD") + ' ~ ' + endDate.format("MM/DD"));
		}
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