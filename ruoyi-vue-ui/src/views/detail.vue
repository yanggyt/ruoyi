<template>
  <div class="shop-detail">
    <!-- header -->
    <shop-header>商城</shop-header>
    <!-- banner -->
    <div class="banner-box">
      <mt-swipe :auto="4000">
        <mt-swipe-item>
            <img src="../assets/1.jpeg" />
        </mt-swipe-item>
        <mt-swipe-item>
            <img src="../assets/2.jpeg" />
        </mt-swipe-item>
      </mt-swipe>
    </div>
    <!-- 详情 -->
    <div class="detail-des">
      <div class="detail-des-amout">{{ detailInfo.amount }}福豆</div>
      <h3>{{ detailInfo.title }}</h3>
      <p>{{ detailInfo.desc }}</p>
      <p class="shipping">运费: {{ detailInfo.shipping }}</p>
    </div>
    <!-- 兑换按钮 -->
    <div class="detail-button" @click="exchange">立即兑换</div>
  </div>
</template>

<script>
import Header from '../components/header';
export default {
  components: {
    shopHeader: Header,
  },
  data () {
    return {
      detailInfo: {},
    }
  },
  async created() {
    // const url = 'https://api.apiopen.top/getJoke?page=1&count=2&type=video';
    // const res = await request({}, url);
    // console.log('res >>> ', res);
    this.detailInfo = {
      amount: '336.00',
      title: '正宗潜江油焖大虾',
      desc: '正宗潜江油焖大虾正宗潜江油焖大虾正宗潜江油焖大虾正宗潜江油焖大虾正宗潜江油焖大虾正宗潜江油焖大虾正宗潜江油焖大虾',
      shipping: 0,
    };
  },
  methods: {
    exchange() {
      confirm('您确定要兑换此商品吗?');
    },
  },
}
</script>

<style scoped>
h3,p {
  margin: 0;
  padding: 0;
}
.shop-detail {

}
/* banner */
.banner-box {
  width: 100%;
  height: 200px;
}
.mint-swipe-item > img {
  width: 100%;
  height: 100%;
}
/*详情*/
.detail-des {
  padding: 0 15px 10px 15px;
  text-align: left;
  border-top: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
}
.detail-des-amout {
  padding: 10px 0;
  color: #E2778A;
  border-bottom: 1px solid #ccc;
  font-size: 20px;
}
.detail-des > h3 {
  padding: 10px 0;
  margin: 0;
}
.shipping {
  margin-top: 5px;
  color: #E2778A;
}
/*兑换按钮*/
.detail-button {
  width: 100%;
  height: 50px;
  line-height: 50px;
  color: #fff;
  background-color: #EB544D;
  position: fixed;
  left: 0;
  bottom: 0;
}
</style>
