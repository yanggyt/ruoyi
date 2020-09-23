<template>
  <div class="shop-field">
    <div class="shop-field-left" v-if="show">
      <p>+86</p>
      <i class="field-allow-right"></i>
    </div>
    <div class="shop-field-mid">
      <input :type="thisType" :placeholder="placeholder" v-model="val" />
    </div>
    <div class="shop-field-right" v-if="type === 'password'" @click="showPassword">
      <div class="icon-box" :class="[showPwd ? 'eye-open' : 'eye-close']"></div>
    </div>
  </div>
</template>

<script>
import { Field } from 'mint-ui';

export default {
  name: 'field',
  props: {
    show: {
      type: Boolean,
      default: false,
    },
    placeholder: {
      type: String,
      default: '请输入手机号'
    },
    type: {
      type: String,
      default: 'text'
    },
  },
  data () {
    return {
      val: '',
      showPwd: false,
      thisType: 'text',
    }
  },
  created() {
    this.thisType = this.type;
  },
  methods: {
    back() {
      this.$router.back();
    },
    showPassword() {
      this.showPwd = !this.showPwd;
      if(this.thisType === 'password') {
        this.thisType = 'text';
      } else {
        this.thisType = 'password';
      }
    },
  }
}
</script>

<style scoped>
p {
  margin: 0;
  padding: 0;
}
.shop-field {
  display: flex;
  padding: 10px 1px;
  border-bottom: 1px solid #ccc;
  margin-top: 10px;
}
.shop-field-left, .shop-field-right {
  padding-right: 10px;
  display: flex;
  align-items: center;
}
.field-allow-right {
  border: solid 2px #999;
  border-bottom-width: 0;
  border-left-width: 0;
  display: block;
  width: 5px;
  height: 5px;
  margin-top: 5px;
  margin-left: 5px;
  margin-right: 3px;
  -webkit-transform: translateY(-50%) rotate(45deg);
  transform: translateY(-50%) rotate(45deg);
}
.shop-field-mid {
  flex: 1;
  display: flex;
  justify-content: center;
}
.shop-field-mid > input {
  width: 100%;
  border: 0;
  background-color: transparent;
  outline: none;
  font-size: 20px;
}
.icon-box {
  width: 20px;
  height: 20px;
  background-color: cadetblue;
}
.eye-close {
  background: url(../assets/eye-close.png) center center no-repeat;
  background-size: 100% 100%;
}
.eye-open {
  background: url(../assets/eye-open.png) center center no-repeat;
  background-size: 100% 100%;
}
</style>
