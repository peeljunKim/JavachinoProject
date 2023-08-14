
    let container = document.getElementById('container');
    let signInBtn = document.getElementById('signInBtn'); // Changed button ID to signInBtn

    toggle = () => {
        container.classList.toggle('sign-in');
        container.classList.toggle('sign-up');
    };

    setTimeout(() => {
        container.classList.add('sign-in');
    }, 200);
