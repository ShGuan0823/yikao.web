function reloadCaptcha() {
    var captchaImg = document.getElementById("captchaImg");
    captchaImg.src = "/yikao/captcha?t=" + (new Date()).getTime();
}

// 获取验证码倒计时
function getEmailCaptcha() {

    // 绑定按钮
    var btn = document.getElementById("send_email_catptcha");
    // 计时60s
    var time = 60;
    // 计时器
    var timer = setInterval(function () {

        btn.disabled = true;
        btn.innerText = time + "s重新发送";
        time--;
        if (time == 0){
            btn.innerText = "重新发送";
            btn.disabled = false;
            // 清除定时器
            clearInterval(timer);
        }
    }, 1000);
    // return false;
}

function isInputEmpty(elementId) {
    var element = document.getElementById(elementId);
    if (element.value == ""){
        switch (elementId){
            case "email": element.css
        }
    }
}