function getParameter(param) {
    var query = window.location.search;
    var iLen = param.length;
    var iStart = query.indexOf(param);
    if (iStart == -1)
        return "";
    iStart += iLen + 1;
    var iEnd = query.indexOf("&", iStart);
    if (iEnd == -1)
        return query.substring(iStart);
    return query.substring(iStart, iEnd);
}

function filterCodeState(search) {
    var arr = search.slice(1).split('&');
    var result = '?';
    for (var i in arr) {
        var param_arr = arr[i].split('=');
        var key = param_arr[0];
        if (key=='code'||key=='state') {
            continue;
        }
        result = result + (key+'='+param_arr[1]) + '&';
    }
    return result.slice(0,-1);
}

function getEntryptPwd(pwd){
    var pubKey = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCy7kkPxA6rFFJD9oUQIr+unqPSW5zAnCrAVsrIz7iUOR/v7fVpxY+7hRGarmWW+Ipj+EfDdPJEvVab8KbCpNn4QR54IuXxkKhoAoaBCzpk4ml3VX7K62v7PwvyhpNk3oZRfnHDaVU4vpYkBnQt59ZCc/PqgU833/ZJRXuUxlaE2QIDAQAB';
    var encrypt = new JSEncrypt();
    encrypt.setPublicKey(pubKey);
    return encrypt.encrypt(pwd);
}