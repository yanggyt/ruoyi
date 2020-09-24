import {MessageBox} from "mint-ui";

export default {
    warnFunc: (res) => {
        if (res.code == 301) {
            MessageBox({
                title: '提示',
                message: res.msg,
                showCancelButton: false
            });
            return true;
        }
        return false;
    }
}
