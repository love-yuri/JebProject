<!--
 * @Author: love-yuri yuri2078170658@gmail.com
 * @Date: 2023-11-07 16:15:20
 * @LastEditTime: 2023-11-30 20:45:14
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
    <div @click="$emit('login', user)" class="p-1 text-center text-[30px] rounded-[12px] cursor-pointer text-white bg-[rgba(23,23,23,0.2)]">
      登陆
    </div>
  </div>
</template>

<script lang="ts" setup>
import { reactive, ref, watch } from 'vue';
import type { FormProps } from 'element-plus';
import { ElMessage } from 'element-plus';
import axios from 'axios';
defineEmits(['login']);
const labelPosition = ref<FormProps['labelPosition']>('left');
const user = reactive({
  username: '',
  password: ''
});
watch(
  () => user.username,
  async () => {
    const { data } = await axios.post('http://localhost:8080/exp_9_war_exploded/user?action=check', {
      username: user.username,
      password: user.password
    });
    if (data.includes('ok')) {
      ElMessage.success(data);
    } else {
      ElMessage.error(data);
    }
  }
);
</script>
