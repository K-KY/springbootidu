//여기는 처음부터 다시한번 읽어보고 싶어
var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    save : function () {
//        사용자가 작성한 게시글 정보를 변수 data에 저장합니다.
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };
        //여기서 PostsApiController 에서 만든 메서드 url 로 요청을 보냄
        //리턴값을 받고 주소를 '/' 로 설정
        // jQuery 의 ajax 함수를 사용하여 HTTP POST 요청을 보냅니다.
        $.ajax({
            type: 'POST',
            //요청 방식을 POST로 설정합니다.
            url: '/api/v1/posts',
            //요청을 보낼 URL을 설정합니다.
            dataType: 'json',
            //응답 데이터 타입을 JSON으로 설정합니다.
            contentType:'application/json; charset=utf-8',
            //요청 데이터의 타입을 JSON으로 설정합니다.
            data: JSON.stringify(data)
            //전송할 데이터를 JSON 형식으로 문자열로 변환하여 설정합니다.
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
            // 글이 등록된 후, 메인 페이지로 이동합니다.
            alert(JSON.stringify(data))
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();