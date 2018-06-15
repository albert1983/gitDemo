//新通知部分初始化
var mqttdomain;

/*
 * curuserid：手机号
 * messageArrivedCallback 消息发送接口
 * */
function connection_message(curuserid, messageArrivedCallback) {
    mqttdomain = "192.168.30.70";
    var tzclient;
    var username = 'admin';
    var password = 'password';
    var user_id = curuserid; //订阅用user_id
    var client_id = parseInt(Math.random() * 10000, 10) + 1;
    var subscibe = 'app1_user_' + user_id.toString();

    tzclient = new Paho.MQTT.Client(mqttdomain, 61623, "/", client_id.toString()); //创建连接对象
    tzclient.onConnectionLost = onConnectionLost;
    tzclient.onMessageArrived = onMessageArrived; //接受消息
    tzclient.connect({userName: username, password: password, onSuccess: onConnect});//进行连接


    //接收之前未读通知结束

    function onConnect() {
        tzclient.subscribe(subscibe); //进行订阅
    }

    function onConnectionLost(responseObject) { // 连接丢失后的处理
        tzclient = new Paho.MQTT.Client(mqttdomain, 61623, "/", client_id.toString());
        tzclient.connect({userName: username, password: password, onSuccess: onConnect});
    }

    //新通知接收部分

    //var notification_list = []; //全局的通知收集列表

    function onMessageArrived(message) {
        messageArrivedCallback(message.payloadString)
    }


    //新通知接收部分结束
}
    