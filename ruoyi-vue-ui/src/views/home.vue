<template>
  <div class="shop-home-body">
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
        <mt-swipe-item>
            <img src="../assets/3.jpeg" />
        </mt-swipe-item>
      </mt-swipe>
    </div>
    <!-- 中间专区按钮 -->
    <div class="home-special">
      <ul>
        <li v-for="(item, key) in specialData" :key="key" @click="gotoTypeList">
          <div class="img-box">
            <img :src="item.logo" />
          </div>
          <span>{{ item.desc }}</span>
        </li>
      </ul>
    </div>
    <!-- 热门 -->
    <div class="home-hot">
      <div class="home-hot-title">
        <p class="hot-list-title">热门榜单</p>
        <div class="more-list">
          <p>更多</p>
          <i class="more-list-allow-right"></i>
        </div>
      </div>
      <div class="home-hot-list">
        <ul>
          <li v-for="(item, key) in commodityData" :key="key">
            <img :src="item.logo" />
            <p>{{ item.description }}</p>
            <span>￥{{ item.price }}</span>
          </li>
        </ul>
      </div>
    </div>
    <!-- 底部导航 -->
    <mt-tabbar v-model="selected" :fixed="true">
      <mt-tab-item id="商城">
        <img slot="icon" src="../assets/home.png">
        福豆商城
      </mt-tab-item>
      <mt-tab-item id="我的">
        <img slot="icon" src="../assets/my.png">
        我的
      </mt-tab-item>
    </mt-tabbar>
  </div>
</template>

<script>
import { Toast } from 'mint-ui';
import { Swipe, SwipeItem } from 'mint-ui';
import { Tabbar, TabItem } from 'mint-ui';
import Header from '../components/header';

export default {
  components: {
    Swipe,
    SwipeItem,
    Tabbar,
    TabItem,
    shopHeader: Header,
  },
  data() {
    return {
      selected: '商城',
      specialData: [
        { desc: '全部商品', logo: require('../assets/home.png') },
        { desc: '图文资讯', logo: require('../assets/home.png') },
        { desc: '我的收藏', logo: require('../assets/home.png') },
        { desc: '优惠券', logo: require('../assets/home.png') },
        { desc: '拼团专区', logo: require('../assets/home.png') },
        { desc: '积分签到', logo: require('../assets/home.png') },
        { desc: '秒杀专区', logo: require('../assets/home.png') },
        { desc: '砍价签到', logo: require('../assets/home.png') },
      ],
      commodityData: [
        {
          description: '超级好吃的蛋糕',
          price: '1.99',
          logo: require('../assets/cake.jpg'),
        },
        {
          description: '超级便宜的蛋糕',
          price: '0.09',
          logo: require('../assets/cake.jpg'),
        },
        {
          description: '好吃又便宜的蛋糕',
          price: '0.01',
          logo: require('../assets/cake.jpg'),
        }
      ],
    }
  },
  created() {

  },
  methods: {
    gotoTypeList() {
      this.$router.push({
        path: '/type-list',
      });
    },
  },
  watch: {
    selected() {
      if (this.selected === '我的') {
        this.$router.push({
          path: '/user',
        });
      }
    }
  },
}

</script>

<style scoped>
  p {
    margin: 0;
    padding: 0;
  }
  .shop-home-body {
    padding: 0 10px;
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

  /* 中间专区按钮 */
  .home-special {
    padding: 20px 0;
  }
  .home-special > ul {
    display: flex;
    margin: 0;
    padding: 0;
    flex-wrap: wrap;
  }
  .home-special > ul >li {
    width: 25%;
    list-style: none;
    display: flex;
    flex-direction: column;
    align-items: center;
    font-size: 12px;
    margin-bottom: 15px;
  }
  .home-special > ul >li >span {
    margin-top: 5px;
  }
  .img-box {
    width: 50px;
    height: 50px;
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #F3F5F4;
  }
  .img-box > img {
    width: 50%;
  }

  /*热门*/
  .home-hot {
    height: 100px;
    background-color: darkcyan;
    position: relative;
    padding: 0 10px;
  }
  .home-hot::after {
    content: '';
    width: 100%;
    height: 20px;
    background: #fff;
    position: absolute;
    bottom: -5px;
    left: 0;
    border-top-left-radius: 200px 20px;
    border-top-right-radius: 200px 20px;
  }
  .home-hot-title {
    padding: 15px 0;
    display: flex;
    justify-content: space-between;
    align-items: center;
    color: #fff;
  }
  .hot-list-title {
    font-weight: bold;
  }
  .more-list {
    font-size: 14px;
    display: flex;
    align-items: center;
  }
  .more-list-allow-right {
    border: solid 2px #fff;
    border-bottom-width: 0;
    border-left-width: 0;
    display: block;
    width: 5px;
    height: 5px;
    margin-top: 5px;
    margin-left: 5px;
    -webkit-transform: translateY(-50%) rotate(45deg);
    transform: translateY(-50%) rotate(45deg);
  }
  .home-hot-list {
    /* height: 100px; */
    margin: 0 auto;
    background-color: #fff;
    border-radius: 5px 5px 0 0;
    position: relative;
    z-index: 9;
    padding: 10px;
    padding-bottom: 60px;
  }
  .home-hot-list > ul {
    display: flex;
    margin: 0;
    padding: 0;
    flex-wrap: wrap;
    justify-content: space-between;
  }
  .home-hot-list > ul >li {
    width: 45%;
    list-style: none;
    display: flex;
    flex-direction: column;
    align-items: center;
    font-size: 12px;
    margin-bottom: 15px;
    text-align: left;
  }
  .home-hot-list > ul >li > img {
    display: block;
    width: 100%;
    border-radius: 5px;
    box-shadow: 0 0 0.1rem #00000029;
  }
  .home-hot-list > ul >li > p {
    width: 100%;
    margin-top: 5px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  .home-hot-list > ul >li > span {
    display: block;
    width: 100%;
    color: darkcyan;
  }
  /*底部导航*/
  .mint-tabbar {
    z-index: 99;
  }
</style>
