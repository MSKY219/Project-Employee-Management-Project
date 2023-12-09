$(document).ready(function () {
    const addEmp = $('.addEmp');
    const pId = $('#pId');
    const pIdPop = $('#pId-pop');
    const closeBtn = $('.closeBtn');

    addEmp.on('click', () => {
        console.log('추가 버튼 누름');
        console.log("pId : " + pId.val());
        let openedWindow = window.open('/project/getList/' + pId.val(), 'test', 'width=1400px,height=700px,scrollbars=yes');
    });

    closeBtn.on('click', () => {
        refreshing();
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
            idArr.push(pId);
        } else {
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

    // 투입일
    const pstartAt = $('.pstartAt');
    pstartAt.on('change', (e) => {
        let clickTarget = $(e.currentTarget);
        let checking = clickTarget.val();
        console.log("clickTarget" + checking);

        if (!checking.trim()) { // trim()을 사용하여 값의 앞뒤 공백을 제거하고 빈 문자열 여부를 확인
            alert('날짜를 확인해 주세요.');
            pstartAt.val("");
            return false;
        }

        console.log(checking);
    });

    // 철수일
    const pendAt = $('.pendAt');
    pendAt.on('change', function (e) {
        let clickTarget = $(e.currentTarget);
        let startValue = $('.pstartAt', $(this).closest('tr')).val(); // 시작일의 값
        let endValue = clickTarget.val(); // 종료일의 값

        let startArray = startValue.split('-');
        let endArray = endValue.split('-');

        // 월은 01월, 02월 같이 0부터 시작하므로 1을 뺀다.
        let checkStartDate = new Date(startArray[0], startArray[1] - 1, startArray[2]);
        let checkEndDate = new Date(endArray[0], endArray[1] - 1, endArray[2]);

        if (checkStartDate.getTime() > checkEndDate.getTime()) {
            alert('종료일은 시작일보다 빠를 수 없습니다.');
            clickTarget.val("");
            return false;
        }

        if (!startValue) {
            alert('시작일을 먼저 선택해 주세요.');
            clickTarget.val("");
            return false;
        }

        let checking = endValue;

        if (!checking.trim()) {
            alert('날짜를 확인해 주세요.');
            clickTarget.val("");
            return false;
        }
    });

    // 투입일 선택 시, 철수일 초기화
    pstartAt.on('click', function () {
        let row = $(this).closest('tr');
        let pendAtInRow = $('.pendAt', row);

        if (pendAtInRow.val() === "") {
            pendAtInRow.val(""); // pendAt의 값을 초기화
        }
    });

    const searchCheck = $('.primary');
    const searchStatus = $('#status');
    const searchTech = $('#tech');
    const searchGrade = $('#grade');
    const searchDetail = $('#location-detail');
    const regEx = /^.{2,}$/;

    searchCheck.on('click', (e) => {
        if (searchCheck.val() == "" && searchStatus.val() == "" && searchTech.val() == "" && searchGrade.val() == "" && dateAt1.val() == "" && dateAt2.val() == "" && searchDetail.val() == "") {
            alert("하나 이상의 검색 조건을 설정해 주세요.");
            e.preventDefault();
        }

        if (searchDetail.val() !== "" && !regEx.test(searchDetail.val())) {
            alert("최소 두 글자 이상 입력해주세요.");
            e.preventDefault();
        }
    });

    const addAll = $('.addAll');
    addAll.on('click', function () {

        console.log("pIdPop : " + pIdPop.val());
        let dataArr = [];

        // Iterate over selected checkboxes
        $('.boxes:checked').each(function () {
            let row = $(this).closest('tr');
            let role = $('.roles', row).val();
            let startAt = $('.pstartAt', row).val();
            let endAt = $('.pendAt', row).val();

            // Validate and push data to array
            if (!role || !startAt || !endAt || !role.trim() || !startAt.trim() || !endAt.trim()) {
                alert('선택한 행의 역할, 투입일, 철수일을 입력해 주세요.');
                return false;
            }

            dataArr.push({
                id: $(this).val(),
                role: role,
                startAt: startAt,
                endAt: endAt,
                projectId: pIdPop.val()
            });
        });

        // Check if any data is selected
        if (dataArr.length === 0) {
            alert('최소 한 줄 이상 선택해 주세요.');
            return false;
        }

        console.log("전달될 데이터는 : ", dataArr);

        $.ajax({
            url: "/project/edit/emp",
            data: JSON.stringify({
                empDataArr: dataArr
            }),
            type: "post",
            contentType: 'application/json',
            success: function (res) {
                console.log("Success");
                refreshing();
            },
            error: function () {
                console.log("Error");
            }
        });

    });
});

function refreshing() {
    window.opener.location.reload();
    window.close();
}