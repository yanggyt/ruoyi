<template>
  <div class="shop-user-body">
    <!-- header -->
    <shop-header>我的</shop-header>

    <div class="user-body-lower">
      <div class="user-body-lower-header"></div>
    </div>

    <div class="user-body-upper">
      <!-- 头像 -->
      <div class="user-info">
        <img src="../assets/touxiang.jpeg" />
        <div>
          {{ user.name }}
          <p>绑定手机号</p>
        </div>
      </div>

      <!-- 余额 -->
      <div class="user-balance">
        <ul>
          <li>
            福豆余额<br>(充值)
            <p>{{ user.info.balance }}</p>
          </li>
          <li>
            可用福豆
            <p>{{ user.info.available }}</p>
          </li>
          <li>
            团队福豆
            <p>{{ user.info.team }}</p>
          </li>
          <li>
            专项福豆
            <p>{{ user.info.item }}</p>
          </li>
          <li>
            福豆田
            <p>{{ user.info.field }}</p>
          </li>
        </ul>
      </div>

      <!-- 我的订单 -->
      <div class="user-order">
        <div class="user-order-title">
          <p class="my-order">我的订单</p>
          <div class="all-order" @click="gotoAllOrder">
            <p>全部订单</p>
            <i class="all-order-allow-right"></i>
          </div>
        </div>
        <div class="user-order-content">
          <ul>
            <li>
              <img src="../assets/home.png" />
              <p>待付款</p>
            </li>
            <li>
              <img src="../assets/home.png" />
              <p>待发货</p>
            </li>
            <li>
              <img src="../assets/home.png" />
              <p>待收货</p>
            </li>
            <li>
              <img src="../assets/home.png" />
              <p>已完成</p>
            </li>
          </ul>
        </div>
      </div>
      <!-- 列表 -->
      <div class="user-list">
        <ul>
          <li>
            <div class="user-list-cell">
              <img src="../assets/my.png" />
              我的团队
            </div>
          </li>
          <li>
            <div class="user-list-cell">
              <img src="../assets/my.png" />
              福豆充值
            </div>
          </li>
          <li>
            <div class="user-list-cell">
              <img src="../assets/my.png" />
              兑换记录
            </div>
          </li>
          <li>
            <div class="user-list-cell">
              <img src="../assets/my.png" />
              福豆兑现
            </div>
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
import { Cell } from 'mint-ui';
import { Tabbar, TabItem } from 'mint-ui';
import Header from '../components/header';

export default {
  components: {
    Swipe,
    SwipeItem,
    Cell,
    Tabbar,
    TabItem,
    shopHeader: Header,
  },
  data() {
    return {
      selected: '我的',
      user: {
        name: '隐形少女',
        info: {
          balance: 1000,
          available: 0,
          team: 0,
          item: 0,
          field: 0,
        }
      },
    }
  },
  created() {

  },
  methods: {
    gotoAllOrder() {
      this.$router.push({
        path: '/my-order',
      });
    },
  },
  watch: {
    selected() {
      if (this.selected === '商城') {
        this.$router.push({
          path: '/home',
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
  .shop-user-body {

  }
  .user-body-lower {
    width: 100%;
    min-height: 100vh;
    background-color: #F3F3F3;
    position: absolute;

  }
  .user-body-lower-header {
    width: 100%;
    height: 160px;
    background-color: #1AB773;
    /* border-radius: 0 0 50% 50%; */
    border-radius: 0 0 90% 90%/30%;
  }

  .user-body-upper {
    width: 94%;
    min-height: 100vh;
    left: 3%;
    position: absolute;
    z-index: 1;
    padding-bottom: 50px;
  }
  /*头像*/
  .user-info {
    display: flex;
    align-items: center;
    margin-top: 15px;
  }
  .user-info > div {
    margin-left: 15px;
    font-size: 22px;
    color: #fff;
  }
  .user-info > div p {
    margin-top: 5px;
    padding: 2px 10px;
    font-size: 10px;
    color: #fff;
    background-color: #CC1817;
    border-radius: 20px;
  }
  .user-info > img {
    display: block;
    width: 80px;
    height: 80px;
    border-radius: 50%;
  }
  /*余额*/
  .user-balance {
    background-color: #fff;
    padding: 15px 0;
    border-radius: 3px;
    margin-top: 15px;
  }
  .user-balance > ul {
    display: flex;
    margin: 0;
    padding: 0;
  }
  .user-balance > ul > li {
    list-style: none;
    flex: 1;
    font-size: 12px;
    border-right: 1px solid #ccc;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
  }
  .user-balance > ul > li:nth-of-type(5n + 0) {
    border-right: 0px;
  }
  .user-balance > ul > li > p {
    margin-top: 5px;
    margin-bottom: 0;
  }

  /*我的订单*/
  .user-order {
    background-color: #fff;
    border-radius: 3px;
    margin-top: 10px;
  }
  .user-order-title {
    padding: 15px 10px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 1px solid #CCCCCC;
  }
  .my-order {
    font-weight: bold;
  }
  .all-order {
    font-size: 14px;
    color: #333;
    display: flex;
    align-items: center;
  }
  .all-order-allow-right {
    border: solid 2px #c8c8cd;
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
  .user-order-content {
    padding: 15px 30px;
  }
  .user-order-content > ul {
    display: flex;
    margin: 0;
    padding: 0;
    justify-content: space-between;
  }
  .user-order-content > ul > li {
    list-style: none;
    font-size: 12px;
  }
  .user-order-content > ul > li > img {
    width: 30px;

  }
  /*列表*/
  .user-list {
    background-color: #fff;
    border-radius: 3px;
    margin-top: 10px;
  }
  .user-list > ul {
    margin: 0;
    padding: 0;
  }
  .user-list > ul > li {
    list-style: none;
    text-align: left;
    display: flex;
    justify-content: flex-end;
    padding-left: 10px;
  }
  .user-list-cell {
    width: 100%;
    padding: 15px 0;
    border-bottom: 1px solid #ccc;
    position: relative;
    display: flex;
    align-items: center;
  }
  .user-list > ul > li:last-child > .user-list-cell {
    border: 0;
  }
  .user-list-cell > img {
    width: 20px;
    height: 20px;
    margin-right: 5px;
  }
  .user-list-cell:after {
    content: '';
    border: solid 2px #c8c8cd;
    border-bottom-width: 0;
    border-left-width: 0;
    width: 5px;
    height: 5px;
    position: absolute;
    right: 10px;
    top: 50%;
    -webkit-transform: translateY(-50%) rotate(45deg);
    transform: translateY(-50%) rotate(45deg);
  }
  /*底部导航*/
  .mint-tabbar {
    z-index: 99;
  }
</style>
