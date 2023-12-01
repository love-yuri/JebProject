<!--
 * @Author: love-yuri yuri2078170658@gmail.com
 * @Date: 2023-11-05 20:14:49
 * @LastEditTime: 2023-11-30 20:39:31
 * @Description: 初始化
-->
<template>
  <div class="w-full h-full flex justify-center items-center">
    <el-tabs v-model="activeName" class="w-[700px]">
      <el-tab-pane label="登陆" name="login">
        <Login @login="login" />
      </el-tab-pane>
      <el-tab-pane label="注册" name="register">
        <Register @register="register" @login="activeName = 'login'" />
      </el-tab-pane>
      <el-tab-pane label="图书列表" name="booklist" ref="bookListRef">
        <BookList :flush="flush" :username="currentUser" />
      </el-tab-pane>
      <el-tab-pane label="我的订阅" name="myBook" ref="bookListRef" v-if="logingSuccess">
        <MyBook :flush="flush" :username="currentUser" />
      </el-tab-pane>
      <el-tab-pane label="添加图书" name="addbool">
        <AddBook />
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script lang="ts" setup>
import Register from './components/register.vue';
import Login from './components/login.vue';
import BookList from './components/book_list.vue';
import MyBook from './components/my_book.vue';
import AddBook from './components/add_book.vue';
import { ElMessage } from 'element-plus';
import { ref, watch } from 'vue';
import axios from 'axios';
const activeName = ref('login');
const logingSuccess = ref(false);
const currentUser = ref('');

const bookListRef = ref();
const flush = ref(false);
watch(activeName, () => {
  if (activeName.value === 'booklist') {
    flush.value = !flush.value;
  }
});

const register = async (user: any) => {
  const { username, password } = user;
  if (username == '') {
    ElMessage.warning('用户名为空!');
    return;
  }
  if (password == '') {
    ElMessage.warning('密码为空!');
    return;
  }
  const { data } = await axios.post('http://localhost:8080/exp_9_war_exploded/user?action=register', {
    username: username,
    password: password
  });
  if (data.includes('成功')) {
    ElMessage.success(data);
  } else {
    ElMessage.error(data);
  }
};

const login = async (user: any) => {
  const { username, password } = user;
  if (username == '') {
    ElMessage.warning('用户名为空!');
    return;
  }
  if (password == '') {
    ElMessage.warning('密码为空!');
    return;
  }
  const { data } = await axios.post('http://localhost:8080/exp_9_war_exploded/user?action=login', {
    username: username,
    password: password
  });
  if (data.includes('成功')) {
    logingSuccess.value = true;
    currentUser.value = username;
    ElMessage.success(data);
  } else {
    ElMessage.error(data);
  }
};
</script>
<style></style>
