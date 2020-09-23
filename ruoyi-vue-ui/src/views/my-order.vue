<template>
  <div class="order-body">
    <!-- header -->
    <shop-header>订单列表</shop-header>

    <div class="order-tabs">
      <ul>
        <li v-for="(tab, key) in tabs" :key="key" :class="{'is-tabs-active': (key === index)}" @click="changeTab(key)">
          <p>{{ tab }}</p>
          <p class="order-tabs-line"></p>
        </li>
      </ul>
    </div>
    <div style="height: 40px;"></div>
    <div class="order-list" v-if="index === 0">
      <order v-for="(item, key) in orderInfo" :orderInfo="item" :key="key"></order>
    </div>
    <div class="order-list" v-else-if="index === 1">
      <order v-for="(item, key) in orderInfo" :orderInfo="item" :key="key" v-if="item.status === '待付款'"></order>
    </div>
    <div class="order-list" v-else-if="index === 2">
      <order v-for="(item, key) in orderInfo" :orderInfo="item" :key="key" v-if="item.status === '待发货'"></order>
    </div>
    <div class="order-list" v-else-if="index === 3">
      <order v-for="(item, key) in orderInfo" :orderInfo="item" :key="key" v-if="item.status === '待收货'"></order>
    </div>
    <div class="order-list" v-else-if="index === 4">
      <order v-for="(item, key) in orderInfo" :orderInfo="item" :key="key" v-if="item.status === '已完成'"></order>
    </div>

  </div>
</template>

<script>
import order from '../components/order';
import Header from '../components/header';

export default {
  components: {
    order,
    shopHeader: Header,
  },
  data() {
    return {
      tabs: ['待付款', '待发货', '待收货', '已完成'],
      index: 0,
      orderInfo: [
        {
          num: 'SP12345678901',
          status: '待付款',
          logo: require('../assets/xiazi.jpg'),
          amount: 1498,
          name: '正宗潜江油焖大虾正宗潜江油焖大虾正宗潜江油焖大虾正宗潜江油焖大虾正宗潜江油焖大虾',
          quantity: 1,
          total: 1498,
        },
        {
          num: 'SP12345678902',
          status: '待发货',
          logo: require('../assets/danta.jpg'),
          amount: 1498,
          name: '正宗皇冠蛋挞',
          quantity: 1,
          total: 1498,
        },
        {
          num: 'SP12345678903',
          status: '待发货',
          logo: require('../assets/hundun.jpg'),
          amount: 1498,
          name: '超级好吃的混沌',
          quantity: 1,
          total: 1498,
        },
        {
          num: 'SP12345678903',
          status: '待收货',
          logo: require('../assets/hundun.jpg'),
          amount: 1498,
          name: '超级好吃的混沌',
          quantity: 1,
          total: 1498,
        },
        {
          num: 'SP12345678903',
          status: '待收货',
          logo: require('../assets/hundun.jpg'),
          amount: 1498,
          name: '超级好吃的混沌',
          quantity: 1,
          total: 1498,
        },
        {
          num: 'SP12345678903',
          status: '已完成',
          logo: require('../assets/hundun.jpg'),
          amount: 1498,
          name: '超级好吃的混沌',
          quantity: 1,
          total: 1498,
        }
      ],

    }
  },
  created() {

  },
  methods: {
    buildImg() {
      return require('../assets/1.jpeg');
    },
    changeTab(key) {
      this.index = key;
    }
  },
}
</script>

<style scoped>
p {
  margin: 0;
  padding: 0;
}
.order-body {
  min-height: 100vh;
  background-color: #F3F3F3;
  padding-bottom: 50px;
}
.order-tabs {
  overflow: hidden;
  position: fixed;
  left: 0;
  top: 48px;
  background-color: #fff;
  width: 100%;
}
.order-tabs > ul {
  width: 100%;
  display: flex;
  margin: 0;
  padding: 0;
  flex-wrap: nowrap;
  overflow: auto;
}
.order-tabs > ul > li {
  list-style: none;
  flex: 1;
  padding: 10px 0;
  flex-shrink: 0;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.order-tabs > ul > li.is-tabs-active {
  color: #F49D4D;
}
.order-tabs > ul > li.is-tabs-active > .order-tabs-line{
  width: 20%;
  height: 2px;
  background-color: #F49D4D;
  margin-top: 5px;
}
.order-list {
  padding: 10px 10px;
}
</style>
