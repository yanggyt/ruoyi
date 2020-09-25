<template>
  <div>
    <!-- header -->
    <shop-header>商品列表</shop-header>

    <div class="shop-type-list">
      <!-- 左边导航 -->
      <div class="left-nav">
        <ul>
          <li v-for="(item, key) in dataList" :key="key" :class="{'is-active': item.type === actionType}" @click="clickAction(item.type)">{{ item.type }}</li>
        </ul>
      </div>
      <!-- 右边列表 -->
      <div class="right-list-box">
        <ul v-for="(item, key) in dataList" :key="key" v-if="item.type === actionType">
          <li v-for="(infos, key) in item.data" @click="gotoDetail">
            <img :src="infos.logo" />
            <span class="info-name">{{ infos.name }}</span>
            <span class="info-amount">{{ infos.amount }}福豆</span>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import Header from '../components/header';
import request from '../utils/request.js';

export default {
  components: {
    shopHeader: Header,
  },
  data () {
    return {
      actionType: '',
      dataList: [],
    }
  },
  async created() {
    // const url = 'https://api.apiopen.top/getJoke?page=1&count=2&type=video';
    // const res = await request({}, url);
    // console.log('res >>> ', res);
    this.dataList = [
      {
        type: '个人清洁',
        data: [
          {
            logo: require('../assets/xiazi.jpg'),
            amount: 1498,
            name: '个人清洁',
          },
          {
            logo: require('../assets/hundun.jpg'),
            amount: 6666,
            name: '个人清洁',
          },
          {
            logo: require('../assets/hundun.jpg'),
            amount: 6666,
            name: '个人清洁',
          },
        ],
      },
      {
        type: '美妆护肤',
        data: [
          {
            logo: require('../assets/xiazi.jpg'),
            amount: 1498,
            name: '美妆护肤',
          },
          {
            logo: require('../assets/hundun.jpg'),
            amount: 6666,
            name: '美妆护肤',
          },
        ],
      },
      {
        type: '厨房用品',
        data: [
          {
            logo: require('../assets/xiazi.jpg'),
            amount: 1498,
            name: '厨房用品',
          },
          {
            logo: require('../assets/hundun.jpg'),
            amount: 6666,
            name: '厨房用品',
          },
        ],
      },
      {
        type: '家用电器',
        data: [
          {
            logo: require('../assets/xiazi.jpg'),
            amount: 1498,
            name: '家用电器',
          },
          {
            logo: require('../assets/hundun.jpg'),
            amount: 6666,
            name: '家用电器',
          },
        ],
      },
      {
        type: '手机数码',
        data: [
          {
            logo: require('../assets/xiazi.jpg'),
            amount: 1498,
            name: '手机数码',
          },
          {
            logo: require('../assets/hundun.jpg'),
            amount: 6666,
            name: '手机数码',
          },
        ],
      },
    ];

    this.actionType = this.dataList[0].type;
  },
  methods: {
    clickAction(actionType) {
      this.actionType = actionType;
    },
    gotoDetail() {
      this.$router.push({
        path: '/detail',
      });
    },
  }
}
</script>

<style scoped>
ul,li,p {
  margin: 0;
  padding: 0;
}

.shop-type-list {
  width: 100%;
  height: calc(100vh - 51px);
  display: flex;
  border-top: 1px solid #ccc;
  box-sizing: border-box;
}
/*左边导航*/
.left-nav {
  width: 23%;
  height: calc(100vh - 51px);
  overflow-y: auto;
  background-color: #555C66;
  border-right: 1px solid #ccc;
}
.left-nav ul li {
  list-style: none;
  width: 100%;
  padding: 10px 0;
  color: #fff;
  border-bottom: 1px solid #7F848A;
  text-align: center;
  font-size: 14px;
}
.left-nav ul li:last-child {
  border-bottom: 0;
}
.left-nav ul li.is-active {
  background-color: #fff;
  color: #0000FF;
}
/*右边列表*/
.right-list-box {
  flex: 1;
  height: calc(100vh - 51px);
  padding: 10px 5px;
  overflow-y: auto;
  box-sizing: border-box;
}
.right-list-box ul {
  display: flex;
  flex-wrap: wrap;
}
.right-list-box ul li {
  list-style: none;
  width: 50%;
  display: flex;
  flex-direction: column;
  padding: 0 5px;
  margin-bottom: 10px;
  box-sizing: border-box;
}
.right-list-box ul li > img {
  width: 100%;
}
.info-name {
  font-size: 14px;
  padding: 5px 0;
}
.info-amount {
  color: #E2778A;
}
</style>
