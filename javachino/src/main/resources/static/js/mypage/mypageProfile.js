
        // 이미지 선택 버튼 동작
        document.getElementById('selectBtn').addEventListener('click', function() {
            document.getElementById('fileInput').click();
        });

        // 파일 선택 시 이미지 업데이트
        document.getElementById('fileInput').addEventListener('change', function() {
            var selectedFile = this.files[0];
            if (selectedFile) {
                var profileImage = document.getElementById('profileImage');
                profileImage.src = URL.createObjectURL(selectedFile);
            }
        });


//document.getElementById('saveBtn').addEventListener('click', function() {
//    var newNickname = document.getElementById('nicknameInput').value;
//    var newPhone = document.getElementById('phoneInput').value;
//
//    var updatedData = {
//        newNickname: newNickname,
//        newPhone: newPhone
//    };
//
//    // 서버로 수정된 데이터 전송
//    fetch('/mypage/mypageProfile/update', {
//        method: 'POST',
//        headers: {
//            'Content-Type': 'application/json'
//        },
//        body: JSON.stringify(updatedData)
//    })
//    .then(response => response.json())
//    .then(data => {
//        console.log(data); // 서버 응답 처리
//        // 화면에 변경 사항 반영 등의 작업 수행
//    })
//    .catch(error => {
//        console.error(error); // 에러 처리
//    });
//});
//
//
//        // 폼 제출 시 로컬 스토리지에 사용자 데이터 저장
//        document.getElementById('updateUser').addEventListener('submit', function(event) {
//            event.preventDefault(); // 폼 기본 동작 중단
//
//            var userData = {
//                nickname: document.getElementById('nicknameInput').value,
//                phone: document.getElementById('phoneInput').value
//                // 필요한 데이터 추가
//            };
//
//            localStorage.setItem('userData', JSON.stringify(userData));
//
//            this.submit(); // 폼 서브밋
//        });
//
//        // 페이지 로드 시 저장된 사용자 데이터 표시
//        window.addEventListener('load', function() {
//            var storedUserData = localStorage.getItem('userData');
//            if (storedUserData) {
//                var userData = JSON.parse(storedUserData);
//                document.getElementsByName('newNickname')[0].value = userData.nickname;
//                document.getElementsByName('newPhone')[0].value = userData.phone;
//                // 필요한 데이터 표시
//            }
//        });

       