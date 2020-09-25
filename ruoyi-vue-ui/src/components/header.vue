<template>
  <div>
    <div class="shop-header-normal"></div>
    <div class="shop-header-fixed">
      <div v-if="type === 'close'" class="shop-header-left" @click="back">
        <img src="../assets/close.png" />
      </div>
      <div v-else class="shop-header-left" @click="back">
        <i class="header-allow-left"></i>
        <p v-if="backName" class="back-name">{{ backName }}</p>
      </div>
      <div class="shop-header-mid">
        <slot />
      </div>
      <div class="shop-header-right">
        <span>{{ more }}</span>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'Header',
  props: {
    more: {
      type: String,
      default: ''
    },
    backName: {
      type: String,
      default: ''
    },
    type: {
      type: String,
      default: ''
    },
  },
  data () {
    return {
    }
  },
  created() {
    //未登录检测
    if(this.$route.path == "/") return;
    let token = localStorage.getItem("loginToken");
    if(!token || token.length == 0){
      this.$router.push({
        path: '/',
      });
    }
  },
  methods: {
    back() {
      if(this.type === 'close') {
        this.$emit('close');
      } else {
        this.$router.back();
      }
    }
  }
}
</script>

<style scoped>
p {
  margin: 0;
  padding: 0;
}
.shop-header-normal {
  width: 100%;
  height: 50px;
}
.shop-header-fixed {
  width: 100%;
  height: 50px;
  position: fixed;
  z-index: 99;
  top: 0;
  left: 0;
  display: flex;
  background-color: #fff;
  color: #000;
  /* box-shadow: 0 0 0.1rem #00000029; */
}
.shop-header-left, .shop-header-right {
  width: 20%;
  display: flex;
  align-items: center;
}
.shop-header-left > img {
  width: 18px;
  height: 18px;
  margin-left: 15px;
}
.shop-header-right {
  justify-content: flex-end;
  color: #666;
}
.shop-header-right > span {
  margin-right: 15px;
}
.shop-header-mid {
  flex: 1;
  text-align: center;
  line-height: 50px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-weight: 500;
  font-size: 18px;
}
.header-allow-left {
  border: solid 2px #666;
  border-bottom-width: 0;
  border-left-width: 0;
  display: block;
  width: 10px;
  height: 10px;
  margin-top: 10px;
  margin-left: 15px;
  margin-right: 3px;
  -webkit-transform: translateY(-50%) rotate(225deg);
  transform: translateY(-50%) rotate(225deg);
}
.back-name {
  font-size: 14px;
  line-height: 14px;
}
</style>
