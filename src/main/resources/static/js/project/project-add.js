$(document).ready(function () {
    const name = $('#name');
    const client = $('#client');
    const tech = $('#tech');
    const place = $('#place');



    const checkObj = {
        "name": false,
        "client": false,
        "tech": false,
        "place": false,
    };

    // 날짜 시작일
    const dateAt1 = $('#dateAt1');
    dateAt1.on('change', () => {
        let checking = dateAt1.val();

        if (!checking.trim()) { // trim()을 사용하여 값의 앞뒤 공백을 제거하고 빈 문자열 여부를 확인
            alert('날짜를 확인해 주세요.');
            dateAt1.val("");
            return false;
        }

        console.log(checking);
    });

    // 날짜 종료일
    const dateAt2 = $('#dateAt2');
    dateAt2.on('change', () => {

        let startValue = dateAt1.val(); // 시작일의 값
        let endValue = dateAt2.val(); // 종료일의 값

        let startArray = startValue.split('-');
        let endArray = endValue.split('-');

        // 월은 01월, 02월 같이 0부터 시작하므로 1을 뺀다.
        let checkStartDate = new Date(startArray[0], startArray[1] - 1, startArray[2]);
        let checkEndDate = new Date(endArray[0], endArray[1] - 1, endArray[2]);


        if (checkStartDate.getTime() > checkEndDate.getTime()) {
            alert('종료일은 시작일보다 빠를 수 없습니다.');
            $('#dateAt2').val("");
            return false;
        }

        if (!dateAt1.val()) {
            alert('시작일을 먼저 선택해 주세요.');
            $('#dateAt2').val("");
            return false;
        }

        let checking = dateAt2.val();

        if (!checking.trim()) { // trim()을 사용하여 값의 앞뒤 공백을 제거하고 빈 문자열 여부를 확인
            alert('날짜를 확인해 주세요.');
            dateAt2.val("");
            return false;
        }
    });

    // 시작일 선택 시, 종료일 초기화
    dateAt1.on('click', () => {
        if ($('#dateAt2').val("")) {
            $('#dateAt2').val("");
        }
    });

    // 취소 버튼 눌렀을 시, 뒤로가기.
    const goBackBtn = $('.secondary');
    goBackBtn.on('click', () => {
        window.history.back();
    });


    const savekBtn = $('.addProject');
    savekBtn.on('click', (e) => {

        console.log("name : " + name.val());
        console.log("client : " + client.val());
        console.log("dateAt1 : " + dateAt1.val());
        console.log("dateAt2 : " + dateAt2.val());
        console.log("tech : " + tech.val());
        console.log("place : " + place.val());

        // 프로젝트 등록 ajax
        $.ajax({
            url: "/project/save",
            data: {
                "pTitle": name.val(),
                "client": client.val(),
                "startAt": dateAt1.val(),
                "endAt": dateAt2.val(),
                "skill": tech.val(),
                "place": place.val()
            },
            type: "post",

            success: function (res) {
                console.log("성공");
                alert("프로젝트 등록 성공");
                location.replace("/project");
            },
            error: function () {
                console.log("실패");
            }
        });
    });
});