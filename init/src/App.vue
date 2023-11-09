<!--
 * @Author: love-yuri yuri2078170658@gmail.com
 * @Date: 2023-11-05 20:14:49
 * @LastEditTime: 2023-11-05 21:24:56
 * @Description: 初始化
-->
<template>
  <el-row>
    <el-button type="primary" :icon="Edit" circle @click="onClick" />
  </el-row>
</template>

<script lang="ts" setup>
import { Edit } from '@element-plus/icons-vue';
import axios from 'axios';
const onClick = async () => {
  console.log((await axios.get('http://localhost:8080/demo_war_exploded/hello-servlet')).data);
};
</script>
