<!--
 * @Author: love-yuri yuri2078170658@gmail.com
 * @Date: 2023-11-07 16:15:20
 * @LastEditTime: 2023-11-08 10:57:06
 * @Description: 登陆界面
-->
<template>
  <div class="bg-gray-200 p-12 rounded-md">
    <div class="text-gray-500 text-[20px] font-medium">欢迎登陆</div>
    <el-form :label-position="labelPosition" label-width="60px" class="mt-4">
      <el-form-item label="用户名">
        <el-input v-model="user.username" />
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="user.password" />
      </el-form-item>
    </el-form>
    <div class="flex items-center h-14">
      <span class="mr-4 text-gray-600">验证码</span>
      <el-input v-model="user.code" class="flex-1 h-10 mr-1 rounded-none" resize="both" />
      <img class="w-[100px] bg-gray-400 h-[40px] mr-1 rounded-md" :src="url" alt="Image" />
      <span class="text-blue-600 cursor-pointer" @click="verificationCode">看不清?</span>
    </div>
    <div @click="$emit('login', user)" class="p-1 text-center text-[30px] rounded-[12px] cursor-pointer text-white bg-[rgba(23,23,23,0.2)]">
      登陆
    </div>
  </div>
</template>

<script lang="ts" setup>
import { reactive, ref, onMounted } from 'vue';
import type { FormProps } from 'element-plus';
import axios from 'axios';
defineEmits(['login']);
const labelPosition = ref<FormProps['labelPosition']>('left');

const url = ref('');
const user = reactive({
  username: '',
  password: '',
  code: ''
});

const ImgGet = axios.create({
  responseType: 'blob' // 设置 responseType 为 'blob'
});

const verificationCode = async () => {
  const { data } = await ImgGet.get('http://localhost:8080/demo_war_exploded/hello-servlet');
  const blob = new Blob([data], { type: 'image/png' });
  url.value = URL.createObjectURL(blob);
};

onMounted(async () => {
  let resp = await axios.get('http://localhost:8080/demo_war_exploded/autoLogin');
  console.log(resp);
  verificationCode();
});
</script>
