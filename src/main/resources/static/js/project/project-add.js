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

    // 프로젝트명 유효성 검사
    const nameMessage = $('#nameMessage');
    name.on('input', $.debounce(1000, () => {

        if (name.val().length == 0) {
            nameMessage.text("프로젝트 명을 입력해 주세요.");
            nameMessage.removeClass("confirm").addClass("error");
            checkObj.name = false;
            return;
        };

        // 프로젝트명 확인
        if ($('#name').val()) {
            nameMessage.text("프로젝트 명 입력됨.");
            nameMessage.removeClass("error").addClass("confirm");
            checkObj.name = true;
        } else {
            checkObj.name = false;
        }
    }));

    // 장소 유효성 검사
    const placeMessage = $('#placeMessage');
    place.on('input', $.debounce(1000, () => {

        if (place.val().length == 0) {
            placeMessage.text("장소를 입력해 주세요.");
            placeMessage.removeClass("confirm").addClass("error");
            checkObj.place = false;
            return;
        };

        // 프로젝트명 확인
        if ($('#place').val()) {
            placeMessage.text("장소가 입력됨.");
            placeMessage.removeClass("error").addClass("confirm");
            checkObj.place = true;
        } else {
            checkObj.place = false;
        }
    }));

    // 날짜 시작일
    const dateAt1 = $('#dateAt1');
    dateAt1.on('change', () => {
        let checking = dateAt1.val();

        if (!checking.trim()) { // trim()을 사용하여 값의 앞뒤 공백을 제거하고 빈 문자열 여부를 확인
            alert('날짜를 확인해 주세요.');
            dateAt1.val("");
            return false;
        }
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

        if (checkObj.name == false) {
            alert('프로젝트명을 입력 하지 않았습니다.');
            name.focus();
            e.preventDefault();
            return false;
        };

        if (checkObj.place == false) {
            alert('장소를 입력 하지 않았습니다.');
            place.focus();
            e.preventDefault();
            return false;
        };

        if (checkObj.startAt == false) {
            alert('시작일을 선택 하지 않았습니다.');
            startAt.focus();
            e.preventDefault();
            return false;
        };

        if (checkObj.endAt == false) {
            alert('종료일을 선택 하지 않았습니다.');
            endAt.focus();
            e.preventDefault();
            return false;
        };

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
                alert("프로젝트 등록 성공");
                location.replace("/project");
            },
            error: function () {
            }
        });
    });
});