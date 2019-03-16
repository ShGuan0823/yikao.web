function reloadCaptcha() {
    var captchaImg = document.getElementById("captchaImg");
    captchaImg.src = "/yikao/captcha?t=" + (new Date()).getTime();

}