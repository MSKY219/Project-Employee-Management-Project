$(document).ready(function () {

    // 상태 변경
    const projectStatus = $('.projectStatus');

    projectStatus.on('change', (e) => {
        let clickTarget = $(e.currentTarget).val();
        let pId = $(e.currentTarget).data('id');

        console.log("projectStatus : " + clickTarget);
        console.log("pId : " + pId);

        $.ajax({
            url: "/project/update/st",
            data: {
                "status": clickTarget,
                "id": pId
            },
            type: "post",

            success: function () {
                alert("상태 변경 완료");
                window.location.reload();
            },
            error: function () {
                console.log("실패");
            }
        });

    });

    // 체크 박스 선택
    const selectAll = $('#selectAll');
    const boxes = $('.boxes');
    const idArr = [];

    selectAll.on('change', (e) => {
        let checked = selectAll.prop('checked');
        console.log(checked);

        boxes.prop('checked', checked); // 모든 체크박스에 대해 상태 변경

        if (checked) {
            // 전체 선택 시 모든 pId를 배열에 추가
            idArr.length = 0; // 배열 비우기
            boxes.each(function () {
                let pId = $(this).val();
                idArr.push(pId);
            });
        } else {
            // 전체 해제 시 배열 비우기
            idArr.length = 0;
        }

        console.log("idArr : " + idArr);
    });

    boxes.on('change', (e) => {
        let clickTarget = $(e.currentTarget);
        let pId = clickTarget.val();

        if (clickTarget.prop('checked')) {
            // 체크된 경우 배열에 추가
            idArr.push(pId);
        } else {
            // 체크 해제된 경우 배열에서 제거
            let index = idArr.indexOf(pId);
            if (index !== -1) {
                idArr.splice(index, 1);
            }
        }

        console.log("idArr : " + idArr);
    });


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

    const searchCheck = $('.primary');
    const searchStatus = $('#status');
    const searchTech = $('#tech');
    const searchGrade = $('#grade');
    const searchDetail = $('#location-detail');

    searchCheck.on('click', (e) => {
        if (searchCheck.val() == "" && searchStatus.val() == "" && searchTech.val() == "" && searchGrade.val() == "" && dateAt1.val() == "" && dateAt2.val() == "" && searchDetail.val() == "") {
            alert("하나 이상의 검색 조건을 설정해 주세요.");
            e.preventDefault();
        }
    });


});