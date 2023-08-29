//src="https://code.jquery.com/jquery-3.6.0.min.js"
//src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"

//HTML Header&Footer연결       
        fetch("/header/header")
            .then(response => response.text())
            .then(data => {
                document.getElementById("header").innerHTML = data;
            });

        fetch("/header/footer")
            .then(response => response.text())
            .then(data => {
                document.getElementById("footer").innerHTML = data;
            });
            
// 버튼 상태 업데이트 함수
    function updateButtonVisibility(loggedInUserId) {
        var btnLoginCol = document.getElementById('btn-login-col');
        var btnJoinCol = document.getElementById('btn-join-col');
        var btnLogoutCol = document.getElementById('btn-logout-col');

        if (loggedInUserId) {
            btnLoginCol.style.display = 'none';   // 로그인 버튼 숨김
            btnJoinCol.style.display = 'none';    // 회원가입 버튼 숨김
            btnLogoutCol.style.display = 'block'; // 로그아웃 버튼 표시
        } else {
            btnLoginCol.style.display = 'block';  // 로그인 버튼 표시
            btnJoinCol.style.display = 'block';   // 회원가입 버튼 표시
            btnLogoutCol.style.display = 'none';  // 로그아웃 버튼 숨김
        }
    }
    
// 로그인 및 로그아웃 함수
    function handleLoginLogout(loggedInUserId) {
        updateButtonVisibility(loggedInUserId !== null && loggedInUserId !== "");
    }

    // 로그아웃 버튼 클릭 시 호출되는 함수
    function logout() {
        // 로그아웃 처리
        // 예: sessionStorage를 초기화하거나 로그아웃 API 호출 등
        sessionStorage.removeItem("loggedInUserId");
        
        //세션을 만료시키는 URL로 리다이렉
        window.location.href="/logout";
        
        handleLoginLogout(null); // 로그아웃 상태로 버튼 변경
        window.location.href = "/header/original_login"; 
    }

    // 페이지 로드 시 로그인 여부 확인 후 버튼 상태 업데이트
    window.addEventListener('load', function () {
        var loggedInUserId = sessionStorage.getItem("loggedInUserId");
        handleLoginLogout(loggedInUserId); // 로그인 및 로그아웃 처리
    });