document.querySelector("form").addEventListener("submit", function(event) {
    var memberId = document.getElementById("memberId").value;
    var isDuplicate = document.getElementById("isDuplicate").value;

    // 아이디가 공백인지 확인
    if (memberId.trim() === "") {
        alert('아이디를 입력해주세요.');
        event.preventDefault(); // 폼 제출을 중단
        return;
    }

    // 중복 체크를 하지 않은 경우
    if (isDuplicate === "") {
        alert('아이디 중복 체크를 해주세요.');
        event.preventDefault(); // 폼 제출을 중단
        return;
    }

    // 아이디가 중복인 경우
    if (isDuplicate === "true") {
        alert('아이디가 중복됩니다.');
        event.preventDefault(); // 폼 제출을 중단
        return;
    }
});

document.getElementById("checkBtn").addEventListener('click', function() {
    var memberId = document.getElementById("memberId").value;

    if (memberId.trim() === "") {
        alert('아이디를 입력해주세요.');
        return;
    }

    fetch(`/api/${memberId}/duplicate`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (response.status === 409) {
                alert('아이디가 중복됩니다.');
                document.getElementById("isDuplicate").value = 'true';
            } else if (response.status === 200) {
                alert('사용 가능한 아이디입니다.');
                document.getElementById("isDuplicate").value = 'false';
            } else {
                throw new Error('Unexpected response status: ' + response.status);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});