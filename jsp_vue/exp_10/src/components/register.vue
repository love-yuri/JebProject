<!--
 * @Author: love-yuri yuri2078170658@gmail.com
 * @Date: 2023-11-05 21:29:38
 * @LastEditTime: 2023-11-07 08:27:28
 * @Description:  登陆
-->
<template>
  <div class="bg-gray-200 p-12 rounded-md">
    <div class="text-gray-500 text-[20px] font-medium">欢迎注册</div>
    <div class="text-gray-500">
      <span>已有账号</span>
      <span class="text-blue-700 cursor-pointer" @click="$emit('login')">登陆?</span>
    </div>
    <el-form :label-position="labelPosition" label-width="60px" class="mt-4">
      <el-form-item label="用户名">
        <el-input v-model="user.username" />
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="user.password" />
      </el-form-item>
    </el-form>
    <div
      @click="$emit('register', user)"
      class="p-1 text-center text-[30px] rounded-[12px] cursor-pointer text-white bg-[rgba(23,23,23,0.2)]"
    >
      注册
    </div>
  </div>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue';
import type { FormProps } from 'element-plus';
defineEmits(['register', 'login']);
const labelPosition = ref<FormProps['labelPosition']>('left');
const user = reactive({
  username: '',
  password: ''
});
</script>
