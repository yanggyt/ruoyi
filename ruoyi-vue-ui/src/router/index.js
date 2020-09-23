import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/home'
import User from '../views/user'
import MyOrder from '../views/my-order'
import Login from '../views/login'
import TypeList from '../views/type-list'
import Detail from '../views/detail'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/home',
      name: 'Home',
      component: Home
    },
    {
      path: '/user',
      name: 'User',
      component: User
    },
    {
      path: '/my-order',
      name: 'MyOrder',
      component: MyOrder
    },
    {
      path: '/type-list',
      name: 'TypeList',
      component: TypeList
    },
    {
      path: '/detail',
      name: 'Detail',
      component: Detail
    },
    {
      path: '/',
      name: 'Login',
      component: Login
    }
  ]
})
