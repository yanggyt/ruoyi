<template>
  <div class="components-order-body">
    <div class="components-order-title">
      <span>订单: {{ orderInfo.num }}</span>
      <span class="components-order-status">{{ orderInfo.status }}</span>
    </div>
    <div class="components-order-content">
      <img :src="orderInfo.logo" />
      <div class="components-orde-desc">
        <div class="components-orde-desc-cont">
          <p class="components-orde-desc-title">
            {{ orderInfo.name }}
          </p>
          <p class="components-orde-desc-quantity">
            ￥{{ orderInfo.amount }}
            <br>
            x{{ orderInfo.quantity }}
          </p>
        </div>

        <p>{{ orderInfo.amount }}福豆</p>
      </div>
    </div>
    <div class="components-order-foot">
      <p>共计{{orderInfo.quantity}}件商品 合计:￥{{ orderInfo.total }}</p>
      <div class="components-order-button-box" v-if="orderInfo.status === '待收货'">
        <div class="components-order-button">确认收货</div>
      </div>
    </div>
  </div>
</template>

<script>
import { Button } from 'mint-ui';

export default {
  components: {
    Button,
  },
  props: {
    orderInfo: {
      type: Object,
      required: true,
      default: () => {},
    },
  },
}
</script>

<style scoped>
p {
  margin: 0;
  padding: 0;
}
.components-order-body {
  width: 100%;
  margin: 0 auto;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 0 0.1rem #00000029;
  margin-top: 10px;
}
.components-order-title {
  padding: 10px 10px;
  display: flex;
  justify-content: space-between;
}
.components-order-title > .components-order-status {
  color: #F49D4D;
}

.components-order-content {
  display: flex;
  padding: 0 10px;
}
.components-order-content > img {
  width: 30%;
  border-radius: 3px;
}
.components-order-content > .components-orde-desc {
  flex: 1;
  margin-left: 10px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  text-align: left;
}
.components-orde-desc-cont {
  display: flex;
}
.components-orde-desc-title {
  flex: 1;
  display: -webkit-box;
  overflow: hidden;
  -webkit-line-clamp: 2;
  line-clamp: 2;
  -webkit-box-orient: vertical;
  box-orient: vertical;
  margin-bottom: 20px;
}
.components-orde-desc-quantity {
  padding-left: 10px;
  text-align: right;
}

.components-order-foot {
  padding: 10px 10px;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}
.components-order-foot > div > p{
  margin-bottom: 5px;
}
.components-order-button-box {
  width: 100%;
  display: flex;
  justify-content: flex-end;
  margin-top: 10px;
}
.components-order-button {
  width: auto;
  padding: 5px 10px;
  border: 1px solid #F49D4D;
  border-radius: 50px;
  font-size: 14px;
  text-align: center;
  min-width: 60px;
  color: #F49D4D;
}
</style>
