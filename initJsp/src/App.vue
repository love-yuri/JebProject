<!--
 * @Author: love-yuri yuri2078170658@gmail.com
 * @Date: 2023-11-05 20:14:49
 * @LastEditTime: 2023-11-10 13:59:57
 * @Description: 初始化
-->
<template>
  <div class="h-screen w-screen flex justify-center items-center bg-gray-400">
    <Login v-if="isLoginning" @login="login" />
    <Register @register="register" @login="isLoginning = true" v-else />
  </div>
</template>

<script lang="ts" setup>
import axios from 'axios';
import { ref } from 'vue';
import Register from './components/register.vue';
import Login from './components/login.vue';
import { ElMessage } from 'element-plus';

const isLoginning = ref(false);

const register = async (user: any) => {
  const { username, password, phone, email, code } = user;
  if (username == '') {
    ElMessage.warning('用户名为空!');
    return;
  }
  if (password == '') {
    ElMessage.warning('密码为空!');
    return;
  }
  const { data } = await axios.post('http://localhost:8080/demo_war_exploded/register', {
    username: username,
    password: password,
    phone: phone,
    email: email,
    code: code
  });
  if (data.isRegister) {
    ElMessage.success(data.msg);
  } else {
    ElMessage.error(data.msg);
  }
};

const login = async (user: any) => {
  const { username, password, code } = user;
  if (username == '') {
    ElMessage.warning('用户名为空!');
    return;
  }
  if (password == '') {
    ElMessage.warning('密码为空!');
    return;
  }
  const { data } = await axios.post('http://localhost:8080/demo_war_exploded/login', {
    username: username,
    password: password,
    code: code
  });
  if (data.isLogin) {
    ElMessage.success(data.msg);
  } else {
    ElMessage.error(data.msg);
  }
};
</script>
