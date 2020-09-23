<template>
  <div class="login-body">
    <!-- header -->
    <shop-header type="close" more="帮助" @close="close"></shop-header>
    <div class="login-box">
      <div class="login-title">欢迎登录</div>
      <text-field ref="shopPhone" show type="number" placeholder="请输入手机号" />
      <text-field ref="shopPwd" type="password" placeholder="请输入密码" />
      <mt-button type="primary" size="large" @click="login">登录</mt-button>
    </div>
  </div>
</template>

<script>
import { Field } from 'mint-ui';
import { Button } from 'mint-ui';
import request from '../utils/request.js';
import { Toast } from 'mint-ui';
import { MessageBox } from 'mint-ui';
import field from '../components/field';
import Header from '../components/header';

export default {
  components: {
    Field,
    Button,
    Toast,
    MessageBox,
    TextField: field,
    shopHeader: Header,
  },
  data () {
    return {
      username: '',
      password: '',
    }
  },
  async created() {
    const url = 'https://api.apiopen.top/getJoke?page=1&count=2&type=video';
    const res = await request({}, url);
    console.log('res >>> ', res);
  },
  methods: {
    login() {
      const username = this.$refs.shopPhone.val;
      const password = this.$refs.shopPwd.val;
      if(!username && !password) {
        MessageBox({
          title: '提示',
          message: '账号或密码不能为空!',
          showCancelButton: false
        });
      } else {
        this.$router.push({
          path: '/home',
        });
      }
    },
    close() {
      alert('close');
    }
  },
}

</script>

<style scoped>
.login-body {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}
.login-box {
  width: 80%;
  border-radius: 5px;
}
.login-title {
  text-align: left;
  padding: 10px 0;
  font-size: 28px;
  margin-bottom: 40px;
}
.mint-button {
  margin-top: 40px;
}
</style>
