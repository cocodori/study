const main = {
    init : function () {
        const _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    }, //init
    save : function () {
        const data = {
            title   : $('#title').val(),
            author  : $('#author').val(),
            content : $('#content').val()
        };

        $.ajax({
            type        : 'POST',
            url         : '/api/v1/posts',
            dataType    : 'json',
            contentType : 'application/json; charset=UTF-8',
            data        : JSON.stringify(data)
        }).done(function () {
            alert('등록되었습니다.');
            //글이 성공적으로 등록되었다면 index페이지로 이동한다.
            window.location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        }); //ajax
    } //save
} //main

main.init();