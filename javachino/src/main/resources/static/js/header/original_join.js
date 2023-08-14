
	  let container = document.getElementById('container')
	
	  toggle = () => {
	    container.classList.toggle('sign-in')
	    container.classList.toggle('sign-up')
	  }
	
	  setTimeout(() => {
	    container.classList.add('sign-in')
	  }, 200)

	    // form 제출 시 호출되는 함수
	    function submitForm() {
	        var form = document.getElementById('signupForm');
	        form.submit();
	    }
