
var stompClient;
function connect() {
    var socket= new SockJS('/ws');
    stompClient = Stomp.over(socket);
    var error_callback = function (error) {
        alert("连接关闭，刷新页面重新建立连接");
        // display the error's message header:
    };
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        var dest = "/ws/web/chat/" + userId;
        stompClient.subscribe(dest, function (data) {
            showMessage(JSON.parse(data.body));
        });
        //TODO 断线重连
    }, error_callback);
}

function showMessage(data) {

    var html = '<li>'+data.fromUserId+":"+data.message+'</li>';
    $("#chat-list").append(html);

}


var userId;
$(document).ready(function () {
    $("#submit")
        .click(function () {
            sendMessage();
        });

    $("body").keydown(function () {
        if (event.keyCode == "13") {//keyCode=13是回车键
            sendMessage();
        }
    });
    //自身的userId
    userId = $("#userId").val();

    connect();
});

function sendMessage() {
    var toUserId = $("#to-user-id").val();
    if (toUserId == "") {
        alert("请输入目标用户的id");
        return;
    }
    var message = $("#message").val();

    //通过websocket发送消息
    var quote = {fromUserId: userId, message: message, toUserId: toUserId};
    stompClient.send("/ws/web/chat", {}, JSON.stringify(quote));
    //自己发的消息也上屏
    var html = '<li>'+userId+":"+message+'</li>';
    $("#chat-list").append(html);

    //清空消息输入框
    $("#message").val("");

}